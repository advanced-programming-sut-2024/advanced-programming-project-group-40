package Server;

import Server.Messages.Client.*;
import Server.Messages.ServerMessages;
import Server.Services.RequestService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import enums.AlertInfo.messages.LoginMenuMessages;
import enums.AlertInfo.messages.ProfileMenuMessages;
import enums.cards.HeroInfo;
import enums.cards.LeaderInfo;
import enums.cards.SpecialCardInfo;
import enums.cards.UnitCardInfo;
import models.Game;
import models.User;
import models.cards.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Server extends Thread {
    private static ServerSocket serverSocket;
    public Socket socket;
    private static Gson gsonAgent;
    private static final ArrayList<User> allUsers = new ArrayList<>();
    private static final ArrayList<Socket> connections = new ArrayList<>();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final RequestService requestService = RequestService.getInstance();

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
                case GET_USER:
                    return gsonAgent.fromJson(clientStr, GetUserMessage.class);
                case REQUEST:
                    return gsonAgent.fromJson(clientStr, RequestMessage.class);
                case GET_LIS_OF_NAMES:
                    return gsonAgent.fromJson(clientStr, GetListOfNamesMessage.class);
                    return gsonAgent.fromJson(clientStr,GetUserMessage.class);
                case SEND_FOLLOW_REQUEST:
                    return gsonAgent.fromJson(clientStr,RequestMessage.class);
                case ADD_CARD, REMOVE_CARD:
                    return gsonAgent.fromJson(clientStr,AddRemoveCardMessage.class);
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
            User user;
            ServerMessages serverMessage;
            switch (Objects.requireNonNull(clientMessage).getType()) {
                case LOGIN:
                    LoginMessages loginMessage = (LoginMessages) clientMessage;
//                    System.out.println(loginMessage.getKeyName() + " " + loginMessage.getPassword());
                    user = getUserByUsername(loginMessage.getUsername().trim());
//                    System.out.println(allUsers);
                    System.out.println(loginMessage.getUsername());
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
                    user = signUpMessage.getUser();
                    allUsers.add(user);
                    break;
                case GET_USER:
                    GetUserMessage getUserMessage = (GetUserMessage) clientMessage;
                    user = getUserByUsername(getUserMessage.getUsername());
                    if (user == null) {
                        serverMessage = new ServerMessages(false, ProfileMenuMessages.USER_NOT_FOUND.toString());
                    } else {
                        String userToJson = gson.toJson(Game.getAllUsers());
                        serverMessage = new ServerMessages(true, userToJson);
                    }
                    sendBuffer.writeUTF(gsonAgent.toJson(serverMessage));
                    break;
                case REQUEST:
                    RequestMessage requestMessage = (RequestMessage) clientMessage;
                    switch (requestMessage.getSubType()) {
                        case SEND_FOLLOW_REQUEST:
                            requestService.createFriendRequest(requestMessage.getOriginUsername(), requestMessage.getDestinationUsername());
                            break;
                        case ACCEPT_FOLLOW_REQUEST:
                            requestService.acceptFollowRequest(requestMessage.getOriginUsername(), requestMessage.getDestinationUsername());
                            break;
                        case REJECT_FOLLOW_REQUEST:
                            requestService.rejectFollowRequest(requestMessage.getOriginUsername(), requestMessage.getDestinationUsername());
                            break;
                    }
                case GET_LIS_OF_NAMES:
                    System.out.println(6);
                    GetListOfNamesMessage getListOfNamesMessage = (GetListOfNamesMessage) clientMessage;
                    ArrayList<String> names = new ArrayList<>();
                    switch (getListOfNamesMessage.getSubType()) {
                        case GET_FRIENDS:
                            System.out.println(7);
                            names = requestService.getFriends(getListOfNamesMessage.getKeyName());
                            break;
                        case GET_REJECTED_REQUESTS:
                            names = requestService.getRejectedFollowRequest(getListOfNamesMessage.getKeyName());
                            break;
                        case GET_PENDING_FOLLOW_REQUESTS:
                            names = requestService.getPendingFollowRequests(getListOfNamesMessage.getKeyName());
                            break;
                        case GET_FOLLOW_REQUESTS:
                            names = requestService.getFollowRequests(getListOfNamesMessage.getKeyName());
                            break;
                    }
                    if (names == null) {
                        serverMessage = new ServerMessages(false, ProfileMenuMessages.USER_NOT_FOUND.toString());
                    } else {
                        String namesToJson = gson.toJson(Game.getAllUsers());
                        serverMessage = new ServerMessages(true, namesToJson);
                    }
                    sendBuffer.writeUTF(gsonAgent.toJson(serverMessage));
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