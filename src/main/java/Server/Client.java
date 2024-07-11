package Server;

import Server.Messages.Client.*;
import Server.Messages.MessageSubType;
import Server.Messages.MessageType;
import Server.Messages.ServerMessages;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import models.Game;
import views.ViewController.GameViewController;
import views.ViewController.PreGameViewController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;


public class Client {
    private Socket socket;
    private DataInputStream receiveBuffer;
    private DataOutputStream sendBuffer;
    private String serverIP;
    private int serverPort;

    private Gson gsonAgent;
    private String token;
    private Thread updateThread = null;
    private GameViewController gameViewController;

    public GameViewController getGameViewController() {
        return gameViewController;
    }

    public void setGameViewController(GameViewController gameViewController) {
        this.gameViewController = gameViewController;
    }

    public Client(String serverIP, int serverPort) {
        GsonBuilder builder = new GsonBuilder();
        this.gsonAgent = builder.create();
        this.serverIP = serverIP;
        this.serverPort = serverPort;

    }

    private boolean establishConnection() {
        try {
            socket = new Socket(serverIP, serverPort);
            sendBuffer = new DataOutputStream(socket.getOutputStream());
            receiveBuffer = new DataInputStream(socket.getInputStream());
            return true;
        } catch (Exception e) {
            System.err.println("Unable to initialize socket!");
            e.printStackTrace();
            return false;
        }
    }

    private boolean endConnection() {
        if (socket == null) return true;
        try {
            socket.close();
            receiveBuffer.close();
            sendBuffer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private void sendMessage(String message) {
        try {
            sendBuffer.writeUTF(message);
        } catch (Exception e) {
        }
    }

    private String receiveResponse() {
        try {
            return receiveBuffer.readUTF();
        } catch (IOException e) {
            return null;
        }
    }

    public void test() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Client is running");
        establishConnection();
        String input = scanner.nextLine();
        sendMessage(input);
        endConnection();
    }

    public void addCard(AddRemoveCardMessage addRemoveCardMessage) {
        getServerMessage(addRemoveCardMessage);
    }

    public void removeCard(AddRemoveCardMessage addRemoveCardMessage) {
        getServerMessage(addRemoveCardMessage);
    }

    public ServerMessages login(LoginMessages loginMessages) {
        return getServerMessage(loginMessages);
    }

    public void signUp(SignUpMessages signUpMessages) {
        getServerMessage(signUpMessages);
    }

    public ServerMessages getUser(GetUserMessage getUserMessage) {
        return getServerMessage(getUserMessage);
    }
    public ServerMessages elimination(EliminationMessage eliminationMessage) {
        return getServerMessage(eliminationMessage);
    }

    public ServerMessages getListOfNames(GetListOfNamesMessage getListOfNamesMessage) {
        return getServerMessage(getListOfNamesMessage);
    }

    public ServerMessages request(RequestMessage requestMessage) {
        return getServerMessage(requestMessage);
    }

    private ServerMessages getServerMessage(ClientMessages clientMessages) {
        establishConnection();
        sendMessage(gsonAgent.toJson(clientMessages));
        String response = receiveResponse();
        ServerMessages serverMessages = gsonAgent.fromJson(response, ServerMessages.class);
        endConnection();
        return serverMessages;
    }

    public void update(UpdateMessage updateMessage) {
//        if (updateThread != null)
//            updateThread.;
        startUpdateThread(updateMessage);
    }

    private void startUpdateThread(UpdateMessage updateMessage) {
        updateThread = new Thread(() -> {
            while (true) {
                ServerMessages serverMessages = getServerMessage(updateMessage);
                MessageSubType messageSubType = updateMessage.getSubType();
                MessageType messageType = updateMessage.getType();
                if (serverMessages == null) {
                    try {
                        System.out.println("Server is not responding");
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                    continue;
                }
                if (serverMessages.wasSuccessfull()) {
                    if (messageSubType == MessageSubType.PREGAME_UPDATE) {
                        if (serverMessages.getAdditionalInfo().equals("accept")) {
                            PreGameViewController.startGameStatus = "Game Started";
                        } else if (serverMessages.getAdditionalInfo().equals("decline")) {
                            PreGameViewController.startGameStatus = "Game Request Declined";
                        } else {
                            Platform.runLater(() -> {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Game Request");
                                alert.setHeaderText("Game Request");
                                alert.setContentText("Game Request from " + serverMessages.getAdditionalInfo());
                                alert.showAndWait();
                                System.out.println(Game.getLoggedInUser().getUsername());
                                if (alert.getResult().getText().equals("OK")) {
                                    PreGameViewController.startGameStatus = "Game Started";
                                    AcceptRejectRequest requestMessage = new AcceptRejectRequest(serverMessages.getAdditionalInfo(), true);
                                    establishConnection();
                                    sendMessage(gsonAgent.toJson(requestMessage));
                                    endConnection();
                                    RequestMessage requestMessage1 = new RequestMessage(Game.getLoggedInUser().getUsername(), Game.getLoggedInUser().getUsername(), MessageSubType.ADD_TO_USERS_IN_GAME);
                                    establishConnection();
                                    sendMessage(gsonAgent.toJson(requestMessage1));
                                    endConnection();

                                    if (messageType==MessageType.ELIMINATION){
                                        // todo go to pre game
                                    }
                                    else {
                                        //TODO: Start the game
                                    }
                                } else {
                                    PreGameViewController.startGameStatus = "Game Request Declined";
                                    AcceptRejectRequest requestMessage = new AcceptRejectRequest(serverMessages.getAdditionalInfo(), false);
                                    establishConnection();
                                    sendMessage(gsonAgent.toJson(requestMessage));
                                    endConnection();
                                }
                                UpdateMessage updateMessage1 = new UpdateMessage(Game.getLoggedInUser().getUsername(), MessageSubType.RESET_GAME_REQUEST);
                                establishConnection();
                                sendMessage(gsonAgent.toJson(updateMessage1));
                                endConnection();
                            });
                        }

                    }
                    //TODO
                    switch (messageSubType) {
                        case GAME_UPDATE -> {
                            if (Objects.equals(serverMessages.getAdditionalInfo(), "finished")) {
                                finishGame();
                            } else {
                                gameViewController.setVisualData(serverMessages.getAdditionalInfo());
                            }
                        }
                    }

                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        });
        updateThread.setDaemon(true);
        updateThread.start();
    }

    private void finishGame() {
        //todo
    }

    public void clickedOnCard(ClickedOnCardMessages messages) {
        getServerMessage(messages);

    }

    public void sendCommand(String command) {
        ChangeMatchTableDataMessages messages = new ChangeMatchTableDataMessages(command);
        getServerMessage(messages);


    }

    private void stopUpdateThread() {
        updateThread.interrupt();
    }


}