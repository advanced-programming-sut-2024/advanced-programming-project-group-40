package Server;

import Server.DataBase.SQLDataBase;
import Server.Messages.Client.*;
import Server.Messages.ServerMessages;
import Server.Services.RequestService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import enums.AlertInfo.messages.LoginMenuMessages;
import enums.AlertInfo.messages.PreGameMenuMessages;
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
import java.util.*;

public class Server extends Thread {
    private static ServerSocket serverSocket;
    public Socket socket;
    private static Gson gsonAgent;
    private static final ArrayList<User> allUsers = new ArrayList<>();
    private static final ArrayList<Socket> connections = new ArrayList<>();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final RequestService requestService = RequestService.getInstance();
    private static SQLDataBase sqlDataBase;
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
                case GET_LIST_OF_NAMES:
                    return gsonAgent.fromJson(clientStr, GetListOfNamesMessage.class);

                case ADD_CARD, REMOVE_CARD:
                    return gsonAgent.fromJson(clientStr, AddRemoveCardMessage.class);
                case UPDATE:
                    return gsonAgent.fromJson(clientStr, UpdateMessage.class);
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
                    user = getUserByUsername(loginMessage.getUsername().trim());
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
                    sqlDataBase.addUser(user);
                    break;
                case GET_USER:
                    GetUserMessage getUserMessage = (GetUserMessage) clientMessage;
                    user = sqlDataBase.getUserWithUsername(getUserMessage.getUsername());
                    if (user == null) {
                        serverMessage = new ServerMessages(false, ProfileMenuMessages.USER_NOT_FOUND.toString());
                    } else {
                        String userToJson = gson.toJson(user);
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
                        case SEND_GAME_REQUEST:
                            requestService.createGameRequest(requestMessage.getOriginUsername(), requestMessage.getDestinationUsername());
                            break;
                        case ACCEPT_GAME_REQUEST:
                            requestService.acceptGameRequest(requestMessage.getOriginUsername(), requestMessage.getDestinationUsername());
                            break;
                        case REJECT_GAME_REQUEST:
                            requestService.rejectGameRequest(requestMessage.getOriginUsername(), requestMessage.getOriginUsername());
                            break;
                        case MAKE_PERSON_GO_TO_PRE_GAME:

                            break;
                        case GAME_REQUEST:

                    }
                    break;
                case GET_LIST_OF_NAMES:
                    GetListOfNamesMessage getListOfNamesMessage = (GetListOfNamesMessage) clientMessage;
                    ArrayList<String> names = new ArrayList<>();
                    switch (getListOfNamesMessage.getSubType()) {
                        case GET_FRIENDS:
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
                        case GET_GAME_REQUEST:
                            names = requestService.getGameRequests(getListOfNamesMessage.getKeyName());
                            break;
                        case GET_ACCEPTED_GAME_REQUESTS:
                            names = requestService.getAcceptedGameRequest(getListOfNamesMessage.getKeyName());
                            break;
                        case GET_REJECTED_GAME_REQUEST:
                            names = requestService.getRejectedGameRequest(getListOfNamesMessage.getKeyName());
                            break;
                        case GET_PENDING_GAME_REQUEST:
                            names = requestService.getPendingGameRequests(getListOfNamesMessage.getKeyName());
                            break;
                    }
                    if (names == null) {
                        serverMessage = new ServerMessages(false, ProfileMenuMessages.USER_NOT_FOUND.toString());
                    } else {
                        String namesToJson = gson.toJson(names);
                        serverMessage = new ServerMessages(true, namesToJson);
                    }
                    sendBuffer.writeUTF(gsonAgent.toJson(serverMessage));
                    break;
                case ADD_CARD:
                    AddRemoveCardMessage addCardMessage = (AddRemoveCardMessage) clientMessage;
                    user = getUserByUsername(addCardMessage.getToken());
                    assert user != null;
                    user.getDeckCardsName().add(addCardMessage.getCardName());
                    if (addCardMessage.getCardType() == 1) {
                        user.setNumberOfUnitCards(user.getNumberOfUnitCards() + 1);
                        user.setTotalUnitCardsStrength(user.getTotalUnitCardsStrength() + addCardMessage.getPower());
                    } else if (addCardMessage.getCardType() == 2) {
                        user.setNumberOfSpecialCards(user.getNumberOfSpecialCards() + 1);
                    } else if (addCardMessage.getCardType() == 3) {
                        user.setNumberOfHeroCards(user.getNumberOfHeroCards() + 1);
                        user.setTotalUnitCardsStrength(user.getTotalUnitCardsStrength() + addCardMessage.getPower());
                    }
                    ServerMessages addCardServerMessage = new ServerMessages(true, "Card added successfully!");
                    sendBuffer.writeUTF(gsonAgent.toJson(addCardServerMessage));
                    sqlDataBase.updateUser(user, user.getUsername());
                    break;
                case REMOVE_CARD:
                    AddRemoveCardMessage removeCardMessage = (AddRemoveCardMessage) clientMessage;
                    user = getUserByUsername(removeCardMessage.getToken());
                    assert user != null;
                    user.getDeckCardsName().remove(removeCardMessage.getCardName());
                    if (removeCardMessage.getCardType() == 1) {
                        user.setNumberOfUnitCards(user.getNumberOfUnitCards() - 1);
                        user.setTotalUnitCardsStrength(user.getTotalUnitCardsStrength() - removeCardMessage.getPower());
                    } else if (removeCardMessage.getCardType() == 2) {
                        user.setNumberOfSpecialCards(user.getNumberOfSpecialCards() - 1);
                    } else if (removeCardMessage.getCardType() == 3) {
                        user.setNumberOfHeroCards(user.getNumberOfHeroCards() - 1);
                        user.setTotalUnitCardsStrength(user.getTotalUnitCardsStrength() - removeCardMessage.getPower());
                    }
                    ServerMessages removeCardServerMessage = new ServerMessages(true, "Card removed successfully!");
                    sendBuffer.writeUTF(gsonAgent.toJson(removeCardServerMessage));
                    sqlDataBase.updateUser(user, user.getUsername());
                    break;
                case UPDATE:
                    UpdateMessage updateMessage = (UpdateMessage) clientMessage;
            }
            sendBuffer.close();
            receiveBuffer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        sqlDataBase = SQLDataBase.getInstance();
        allUsers.addAll(sqlDataBase.getAllUsers());
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

}