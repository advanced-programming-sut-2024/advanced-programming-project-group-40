package Server;

import Server.Messages.Client.*;
import Server.Messages.MessageSubType;
import Server.Messages.ServerMessages;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import models.Game;
import views.ViewController.PreGameViewController;

import java.io.*;
import java.net.Socket;
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
        if (updateThread != null)
            stopUpdateThread();
        startUpdateThread(updateMessage.getSubType());
    }

    private void startUpdateThread(MessageSubType messageSubType) {
        updateThread = new Thread(() -> {
            while (true) {
                UpdateMessage updateMessage = new UpdateMessage(Game.getLoggedInUser().getUsername(), messageSubType);
                ServerMessages serverMessages = getServerMessage(updateMessage);
                if (serverMessages == null) {
                    try {
                        System.out.println("Server is not responding");
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
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
                                if (alert.getResult().getText().equals("OK")) {
                                    PreGameViewController.startGameStatus = "Game Started";
                                    AcceptRejectRequest requestMessage = new AcceptRejectRequest(serverMessages.getAdditionalInfo(), true);
                                    establishConnection();
                                    sendMessage(gsonAgent.toJson(requestMessage));
                                    endConnection();

                                    //TODO: Start the game
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
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        updateThread.start();
    }

    private void stopUpdateThread() {
        updateThread.interrupt();
    }


}