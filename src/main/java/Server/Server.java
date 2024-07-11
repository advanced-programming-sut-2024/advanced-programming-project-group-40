package Server;

import Server.DataBase.SQLDataBase;
import Server.Messages.Client.*;
import Server.Messages.MessageSubType;
import Server.Messages.ServerMessages;
import Server.Models.GameBoardVisualData;
import Server.Services.EliminationCup.EliminationCup;
import Server.Services.EliminationCup.Match;
import Server.Services.RequestService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controllers.GameMenuController;
import enums.AlertInfo.messages.LoginMenuMessages;
import enums.AlertInfo.messages.ProfileMenuMessages;
import enums.cards.CardInfo;
import models.MatchTable;
import models.User;

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
    private static final EliminationCup eliminationService = EliminationCup.getInstance();
    private static final HashMap<String, String> requestedGames = new HashMap<>();
    private static final HashMap<String, Boolean> onlineStatus = new HashMap<>();
    private static final ArrayList<MatchTable> matchTables = new ArrayList<>();
    private static SQLDataBase sqlDataBase;
    private static boolean requestSent = false;
    private static final ArrayList<String> usersInGame = new ArrayList<>();

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
                case ACCEPT_REJECT_REQUEST:
                    return gsonAgent.fromJson(clientStr, AcceptRejectRequest.class);
                case CHANGE_MATCH_TABLE_DATA:
                    return gsonAgent.fromJson(clientStr, ChangeMatchTableDataMessages.class);
                case CLICKED_ON_CARD:
                    return gsonAgent.fromJson(clientStr, ClickedOnCardMessages.class);
                case ELIMINATION:
                    return gsonAgent.fromJson(clientStr, EliminationMessage.class);
                case PREGAME:
                    return gsonAgent.fromJson(clientStr, PreGameMessages.class);
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }


    private void handleConnection(Socket socket) {
        String clientRequest = "";
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
            try {


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
                        requestedGames.put(user.getUsername(), "");
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
                            case GAME_REQUEST:
                                requestedGames.put(requestMessage.getDestinationUsername(), requestMessage.getOriginUsername());
                                break;
                            case CHECK_ONLINE:
                                serverMessage = new ServerMessages(onlineStatus.get(requestMessage.getDestinationUsername()), "");
                                sendBuffer.writeUTF(gsonAgent.toJson(serverMessage));
                                break;
                            case ADD_TO_USERS_IN_GAME:
                                usersInGame.add(requestMessage.getOriginUsername());
                                break;
                            case REMOVE_FROM_USERS_IN_GAME:
                                usersInGame.remove(requestMessage.getOriginUsername());
                                break;
                            case CHECK_IN_GAME:
                                serverMessage = new ServerMessages(usersInGame.contains(requestMessage.getOriginUsername()), "");
                                sendBuffer.writeUTF(gsonAgent.toJson(serverMessage));
                                break;
                            case GET_ALL_GAMES_IN_PLAY:
                                HashMap<String ,String> list=new HashMap<String,String>();
                                for (MatchTable match : matchTables){
                                    list.put(match.getFirstPlayer().getUsername(),match.getSecondPlayer().getUsername());
                                }
                                if (list == null) {
                                    serverMessage = new ServerMessages(false, ProfileMenuMessages.USER_NOT_FOUND.toString());
                                } else {
                                    String namesToJson = gson.toJson(list);
                                    serverMessage = new ServerMessages(true, namesToJson);
                                }
                                sendBuffer.writeUTF(gsonAgent.toJson(serverMessage));
                                break;

                        }
                        break;
                    case GET_LIST_OF_NAMES:
                        GetListOfNamesMessage getListOfNamesMessage = (GetListOfNamesMessage) clientMessage;
                        ArrayList<String> names = new ArrayList<>();
                        switch (getListOfNamesMessage.getSubType()) {
                            case GET_FRIENDS:
                                names = requestService.getFriends(getListOfNamesMessage.getKeyName());
                                break;
                            case GET_ALL_USERNAMES:
                                names = getAllUsersName();
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
                    case ELIMINATION:
                        EliminationMessage eliminationMessage = (EliminationMessage) clientMessage;
                        switch (eliminationMessage.getSubType()) {
                            case JOIN_ELIMINATION:
                                eliminationService.addPlayer(eliminationMessage.getUsername());
                                break;
                            case GET_MATCH_ELIMINATION:
                                Match match = eliminationService.getMatchByGroupNumber(eliminationMessage.getNumber());
                                if (match == null) {
                                    serverMessage = new ServerMessages(false, "match not found");
                                } else {
                                    String namesToJson = gson.toJson(match);
                                    serverMessage = new ServerMessages(true, namesToJson);
                                }
                                sendBuffer.writeUTF(gsonAgent.toJson(serverMessage));
                                break;
                            case IS_STARTED_ELIMINATION:
                                if (eliminationService.isEliminationStarted())
                                    serverMessage = new ServerMessages(true, "started");
                                else
                                    serverMessage = new ServerMessages(false, "not yet");
                                sendBuffer.writeUTF(gsonAgent.toJson(serverMessage));
                                break;
                            case GET_COMPETITOR_IN_ELIMINATION:
                                String name = eliminationService.getCompetitor(eliminationMessage.getUsername());
                                if (name.isEmpty())
                                    serverMessage = new ServerMessages(false, "user not found");
                                else
                                    serverMessage = new ServerMessages(true, name);
                                sendBuffer.writeUTF(gsonAgent.toJson(serverMessage));
                                break;
                        }
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
                        user = getUserByUsername(updateMessage.getOriginUsername());
                        assert user != null;
                        onlineStatus.put(user.getUsername(), true);
                        MessageSubType subType = updateMessage.getSubType();
                        switch (subType) {
                            case PREGAME_UPDATE:
                                assert user != null;
                                String requestedUser = requestedGames.get(user.getUsername());
                                if (!requestedUser.isEmpty()) {
                                    if (requestedGames.get(requestedUser).equals(user.getUsername())) {
                                        requestedGames.put(user.getUsername(), "");
                                        requestedGames.put(requestedUser, "");
                                        serverMessage = new ServerMessages(true, "accept");
                                        sendBuffer.writeUTF(gsonAgent.toJson(serverMessage));
                                    } else if (requestedUser.equals("decline")) {
                                        requestedGames.put(user.getUsername(), "");
                                        requestedGames.put(requestedUser, "");
                                        serverMessage = new ServerMessages(true, "decline");
                                        sendBuffer.writeUTF(gsonAgent.toJson(serverMessage));
                                    } else {
                                        if (!requestSent) {
                                            serverMessage = new ServerMessages(true, requestedUser);
                                            sendBuffer.writeUTF(gsonAgent.toJson(serverMessage));
                                            requestSent = true;
                                        } else {
                                            serverMessage = new ServerMessages(false, "no request");
                                            sendBuffer.writeUTF(gsonAgent.toJson(serverMessage));
                                        }
                                    }
                                } else {
                                    serverMessage = new ServerMessages(false, "no request");
                                    sendBuffer.writeUTF(gsonAgent.toJson(serverMessage));
                                }
                                break;
                            case MAIN_MENU_UPDATE:
                                ServerMessages serverMessages = new ServerMessages(true, "Main Menu Updated");
                                sendBuffer.writeUTF(gsonAgent.toJson(serverMessages));
                                break;
                            case RESET_GAME_REQUEST:
                                requestSent = false;
                                break;
                            case GAME_UPDATE:
                                User user1 = getUserByUsername(updateMessage.getToken());
                                MatchTable matchTable = null;
                                for (MatchTable matchTable1 : matchTables) {
                                    if (Objects.equals(matchTable1.getFirstPlayer().getUsername(), user1.getUsername())) {
                                        matchTable = matchTable1;
                                        break;
                                    } else if (Objects.equals(matchTable1.getSecondPlayer().getUsername(), user1.getUsername())) {
                                        matchTable = matchTable1;
                                        break;
                                    }
                                }
                                boolean online1 = onlineStatus.get(matchTable.getFirstPlayer().getUsername());
                                boolean online2 = onlineStatus.get(matchTable.getSecondPlayer().getUsername());
                                GameBoardVisualData visualData = new GameBoardVisualData(matchTable
                                        , false, false, false, false, false,online1,online2);
                                ServerMessages serverMessages3 = new ServerMessages(true, visualData.toJSON());
                                sendBuffer.writeUTF(gsonAgent.toJson(serverMessages3));
                                break;
                        }
                        break;
                    case ACCEPT_REJECT_REQUEST:
                        AcceptRejectRequest acceptRejectRequest = (AcceptRejectRequest) clientMessage;
                        if (acceptRejectRequest.isAccept()) {
                            requestedGames.put(acceptRejectRequest.getUsername(), acceptRejectRequest.getToken());
                            User user1 = getUserByUsername(acceptRejectRequest.getUsername());
                            User user2 = getUserByUsername(acceptRejectRequest.getToken());
                            user1.createDeckCards();
                            user2.createDeckCards();
                            MatchTable matchTable = new MatchTable(user1, user2, new GameMenuController(), acceptRejectRequest.isAccept());
                            matchTable.getGameMenuController().setMatchTable(matchTable);
                            matchTables.add(matchTable);
                            GameBoardVisualData a = new GameBoardVisualData(matchTable
                                    , false, false, false, false, false,true,true);
                            ServerMessages messages = new ServerMessages(true, a.toJSON());
                            sendBuffer.writeUTF(gsonAgent.toJson(messages));
                        } else {
                            requestedGames.put(acceptRejectRequest.getUsername(), "decline");
                        }

                        break;
                    case CHANGE_MATCH_TABLE_DATA:
                        ChangeMatchTableDataMessages changeMessage = (ChangeMatchTableDataMessages) clientMessage;
                        User user1 = getUserByUsername(changeMessage.getToken());
                        System.out.println(user1.getUsername());
                        MatchTable matchTable = null;
                        for (MatchTable matchTable1 : matchTables) {
                            if (Objects.equals(matchTable1.getFirstPlayer().getUsername(), user1.getUsername())) {
                                matchTable = matchTable1;
                                break;
                            } else if (Objects.equals(matchTable1.getSecondPlayer().getUsername(), user1.getUsername())) {
                                matchTable = matchTable1;
                                break;
                            }
                        }
                        assert matchTable != null;
                        matchTable.getGameMenuController().sendCommand(changeMessage.getMessage());
                        GameBoardVisualData visualData = new GameBoardVisualData(matchTable
                                , false, false, false, false, false,true,true);
                        ServerMessages serverMessages = new ServerMessages(true, gsonAgent.toJson(visualData));
                        sendBuffer.writeUTF(gsonAgent.toJson(serverMessages));

                        break;
                    case CLICKED_ON_CARD:
                        ClickedOnCardMessages clickMessage = (ClickedOnCardMessages) clientMessage;
                        User user2 = getUserByUsername(clickMessage.getToken());
                        MatchTable matchTable2 = null;
                        for (MatchTable matchTable1 : matchTables) {
                            if (Objects.equals(matchTable1.getFirstPlayer().getUsername(), user2.getUsername())) {
                                matchTable2 = matchTable1;
                                break;
                            } else if (Objects.equals(matchTable1.getSecondPlayer().getUsername(), user2.getUsername())) {
                                matchTable2 = matchTable1;
                                break;
                            }
                        }
                        CardInfo cardInfo = CardInfo.returnByName(clickMessage.getCardInfo());

                        matchTable2.getGameMenuController().ClickedOnCard(cardInfo, clickMessage.isSelectable(), clickMessage.getParentID());
                        System.out.println(matchTable2.getGameMenuController().selectedCard.getName());
                        GameBoardVisualData s = new GameBoardVisualData(matchTable2
                                , false, false, false, false, false,true,true);
                        ServerMessages serverMessages2 = new ServerMessages(true, s.toJSON());
                        sendBuffer.writeUTF(gsonAgent.toJson(serverMessages2));
                        break;
                }
            } catch (Exception ss) {
                System.out.println(clientRequest);

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
        for (User user : allUsers) {
            requestedGames.put(user.getUsername(), "");
            onlineStatus.put(user.getUsername(), false);
        }
        Thread makeAllUsersOffline = new Thread(() -> {
            while (true) {
                for (String username : onlineStatus.keySet()) {
                    onlineStatus.put(username, false);
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        makeAllUsersOffline.start();
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
    public ArrayList<String> getAllUsersName(){
        ArrayList<String> names=new ArrayList<>();
        for (User user : allUsers) {
            names.add(user.getUsername());
        }
        return names;
    }
}



