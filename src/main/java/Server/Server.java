package Server;

import java.net.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.io.*;

import Server.Messages.Client.ClientMessages;
import Server.Messages.Client.LoginMessages;
import Server.Messages.Client.SignUpMessages;
import Server.Messages.ServerMessages;
import com.google.gson.*;
import enums.AlertInfo.messages.LoginMenuMessages;
import models.User;

public class Server extends Thread {
    private static ServerSocket serverSocket;
    public Socket socket;
    private static Gson gsonAgent;
    private static final ArrayList<User> allUsers = new ArrayList<>();
    private static final ArrayList<Socket> connections = new ArrayList<>();

    private static void setupServer() {
        try {
            serverSocket = new ServerSocket(8000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Server() {
        GsonBuilder builder = new GsonBuilder();
        gsonAgent = builder.create();
    }

    public void listen() throws IOException {
        Socket socket;
        while (true) {
            socket = serverSocket.accept();
            connections.add(socket);
            synchronized (connections) {
                connections.notify();
            }
        }
    }

    @Override
    public void run() {
        Socket socket;
        while (true) {
            socket = null;
            synchronized (connections) {
                while (connections.isEmpty()) {
                    try {
                        connections.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                socket = connections.getFirst();
                connections.removeFirst();
            }
            if (socket != null) {
                handleConnection(socket);
            }
        }
    }

    private String generateNewToken() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 256; i++)
            sb.append((char) (random.nextInt(128)));
        return sb.toString();
    }

    private ClientMessages extractClientMessage(String clientStr) {
        try {
            ClientMessages clientMessage = gsonAgent.fromJson(clientStr, ClientMessages.class);
            switch (clientMessage.getType()) {
                case LOGIN:
                    return gsonAgent.fromJson(clientStr, LoginMessages.class);
                case SIGNUP:
                    return gsonAgent.fromJson(clientStr, SignUpMessages.class);
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }


    private void handleConnection(Socket socket) {
        String clientRequest;
        try {
            DataInputStream receiveBuffer = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream())
            );
            DataOutputStream sendBuffer = new DataOutputStream(
                    new BufferedOutputStream(socket.getOutputStream())
            );
            clientRequest = receiveBuffer.readUTF();
            ClientMessages clientMessage = extractClientMessage(clientRequest);
            switch (Objects.requireNonNull(clientMessage).getType()) {
                case LOGIN:
                    LoginMessages loginMessage = (LoginMessages) clientMessage;
                    System.out.println(loginMessage.getUsername() + " " + loginMessage.getPassword());
                    User user = getUserByUsername(loginMessage.getUsername().trim());
                    System.out.println(allUsers);
                    ServerMessages serverMessage;
                    if (user == null) {
                        serverMessage = new ServerMessages(false, LoginMenuMessages.INCORRECT_USERNAME.toString());
                    } else if (!user.getPassword().equals(loginMessage.getPassword())) {
                        serverMessage = new ServerMessages(false, LoginMenuMessages.INCORRECT_PASSWORD.toString());
                    } else {
                        serverMessage = new ServerMessages(true, LoginMenuMessages.LOGGED_IN_SUCCESSFULLY.toString());
                    }
                    sendBuffer.writeUTF(gsonAgent.toJson(serverMessage));
                    break;
                case SIGNUP:
                    SignUpMessages signUpMessage = (SignUpMessages) clientMessage;
                    User newUser = signUpMessage.getUser();
                    allUsers.add(newUser);
                    break;
            }
            sendBuffer.close();
            receiveBuffer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Server.setupServer();
            Server server1 = new Server();
            Server server2 = new Server();
            server1.start();
            server2.listen();
        } catch (Exception e) {
            System.out.println("Server encountered a problem!");
            e.printStackTrace();
        }
    }

    public static User getUserByUsername(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void addUser(User user) {
        System.out.println(allUsers);
        allUsers.add(user);
        System.out.println(allUsers);
    }
}