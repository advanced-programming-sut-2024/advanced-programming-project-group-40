package Server;

import Server.Messages.Client.*;
import Server.Messages.MessageSubType;
import Server.Messages.MessageType;
import Server.Messages.ServerMessages;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    private boolean sendMessage(String message) {
        try {
            sendBuffer.writeUTF(message);
            return true;
        } catch (Exception e) {
            return false;
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
                establishConnection();
                UpdateMessage updateMessage = new UpdateMessage(token, messageSubType);
                sendMessage(gsonAgent.toJson(updateMessage));
                String response = receiveResponse();
                ServerMessages serverMessages = gsonAgent.fromJson(response, ServerMessages.class);
                endConnection();
                if (serverMessages.wasSuccessfull()) {
                    //TODO


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