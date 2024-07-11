package Server;

import Server.ClientHandler;
import Server.Messages.Client.*;
import Server.Messages.MessageSubType;
import Server.Messages.MessageType;
import Server.Messages.ServerMessages;
import Server.Models.InterfaceAdapter;
import Server.Models.MessageAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import models.Game;
import views.GameView;
import views.ViewController.PreGameViewController;
import views.ViewController.GameViewController;
import views.ViewController.PreGameViewController;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;


public class Client {
    private Socket socket;
    private BufferedInputStream bufferedReceive;
    private BufferedOutputStream bufferedSend;
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
            bufferedSend = new BufferedOutputStream(socket.getOutputStream());
            bufferedReceive = new BufferedInputStream(socket.getInputStream());
            sendBuffer = new DataOutputStream(bufferedSend);
            receiveBuffer = new DataInputStream(bufferedReceive);
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
            bufferedReceive.close();
            bufferedSend.close();
            receiveBuffer.close();
            sendBuffer.close();
            socket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private synchronized void sendMessage(String message) {
        try {
            sendBuffer.writeUTF(message);
            sendBuffer.flush(); // Ensure all data is sent
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized String receiveResponse() {
        try {
            return receiveBuffer.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void test() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Client is running");
        if (establishConnection()) {
            String input = scanner.nextLine();
            sendMessage(input);
            endConnection();
        }
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

    private synchronized ServerMessages getServerMessage(ClientMessages clientMessages) {
        if (establishConnection()) {
            sendMessage(gsonAgent.toJson(clientMessages));
            String response = receiveResponse();
            endConnection();
            return gsonAgent.fromJson(response, ServerMessages.class);
        }
        return null;
    }

    public void update(UpdateMessage updateMessage) {
//        if (updateThread != null)
//            updateThread.;
        startUpdateThread(updateMessage);
    }

    private void startUpdateThread(UpdateMessage updateMessage) {
        updateThread = new Thread(() -> {
            while (true) {
                updateMessage.setToken(Game.getLoggedInUser().getUsername());
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
                        handlePreGameUpdate(serverMessages, messageType);
                    }
                    switch (messageSubType) {
                        case GAME_UPDATE -> {
                            if (Objects.equals(serverMessages.getAdditionalInfo(), "finished")) {
                                finishGame();
                            } else {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
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

    private void handlePreGameUpdate(ServerMessages serverMessages, MessageType messageType) {
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

                    if (messageType == MessageType.ELIMINATION) {
                        // todo go to pre game
                    } else {
                        try {
                            new GameView().start(Game.stage);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        //TODO: Start the game
                        Platform.runLater(() -> {
                            try {
                                new GameView().start(Game.stage);
                                update(new UpdateMessage(Game.getLoggedInUser().getUsername(), MessageSubType.GAME_UPDATE));

                            } catch (Exception e) {
                                try {
                                    new GameView().start(Game.stage);
                                    update(new UpdateMessage(Game.getLoggedInUser().getUsername(), MessageSubType.GAME_UPDATE));

                                } catch (Exception w) {
                                    try {
                                        new GameView().start(Game.stage);
                                        update(new UpdateMessage(Game.getLoggedInUser().getUsername(), MessageSubType.GAME_UPDATE));

                                    } catch (Exception q) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                        });
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

    private void finishGame() {
        // TODO: Implement finishGame logic
    }

    public void clickedOnCard(ClickedOnCardMessages messages) {
        getServerMessage(messages);
    }

    public String sendCommand(String command) {
        ChangeMatchTableDataMessages messages = new ChangeMatchTableDataMessages(command);
        ServerMessages serverMessages = getServerMessage(messages);
        if (serverMessages == null) {
            System.out.println("Server is not responding");
            return null;
        }
        return serverMessages.getAdditionalInfo();
    }

//    public void stopUpdateThread() {
//        updateThread.;
//    }


}