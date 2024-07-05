package Server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import enums.Menu;
import enums.cards.HeroInfo;
import enums.cards.SpecialCardInfo;
import enums.cards.UnitCardInfo;
import javafx.stage.Stage;
import models.User;
import models.cards.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class GameServer extends Thread {
    public static final Random random = new Random();
    private static ArrayList<User> allUsers = new ArrayList<>();
    private static final ArrayList<Card> allCards = GameServer.setAllCards();
    private static final ArrayList<Leader> allLeaders = new ArrayList<>();
    private static final ArrayList<Card> selectedCards = new ArrayList<>();
    private static User loggedInUser;
    private static Menu currentMenu = Menu.LoginMenu;

    public static Stage stage;

//    private static ServerSocket serverSocket;
    private static Gson gsonAgent;
    private static int WORKERS;

//    private static final ArrayList<Socket> connections = new ArrayList<>();

//    private static void setupServer() {
//        try {
//            serverSocket = new ServerSocket(1643);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public GameServer() {
        GsonBuilder builder = new GsonBuilder();
        gsonAgent = builder.create();
    }

//    public void listen() throws IOException {
//        while (true) {
//            try {
//                Socket socket = serverSocket.accept();
//                synchronized (connections) {
//                    connections.add(socket);
//                    connections.notify();
//                }
//            } catch (IOException e) {
//                System.err.println("Error accepting connection");
//                e.printStackTrace();
//            }
//        }
//    }

//    @Override
//    public void run() {
//        while (true) {
//            Socket socket = null;
//            synchronized (connections) {
//                while (connections.isEmpty()) {
//                    try {
//                        connections.wait();
//                    } catch (InterruptedException e) {
//                        System.err.println("Connection wait interrupted");
//                        e.printStackTrace();
//                    }
//                }
//                if (!connections.isEmpty()) {
//                    socket = connections.remove(0);
//                }
//            }
//            if (socket != null) {
//                handleConnection(socket);
//            }
//        }
//    }

//    private void handleConnection(Socket socket) {
//        String clientRequest;
//        try {
//            DataInputStream receiveBuffer = new DataInputStream(
//                    new BufferedInputStream(socket.getInputStream())
//            );
//            DataOutputStream sendBuffer = new DataOutputStream(
//                    new BufferedOutputStream(socket.getOutputStream())
//            );
//            clientRequest = receiveBuffer.readUTF();
//            System.out.println(STR."Client request: \{clientRequest}");
//            System.out.println(socket.toString());
//            System.out.println(connections.size());
//            sendBuffer.close();
//            receiveBuffer.close();
//            socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        System.out.println(1211);
//        try {
//            GameServer.setupServer();
//            GameServer server1 = new GameServer();
//            GameServer server2 = new GameServer();
//            server1.start();
//            server2.listen();
//        } catch (Exception e) {
//            System.err.println("Server encountered a problem!");
//            e.printStackTrace();
//            System.exit(1);
//        }
    }

    private String generateNewToken() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 256; i++)
            sb.append((char) (random.nextInt(128)));
        return sb.toString();
    }

    public static ArrayList<User> getUsersRanked() {
        return null;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        GameServer.loggedInUser = loggedInUser;
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static User getUserByName(String name) {
        for (User user : allUsers) {
            if (user.getUsername().equals(name))
                return user;
        }
        return null;
    }

    public static void addNewUser(User newUser) {
        allUsers.add(newUser);
    }

    public static void setCurrentMenu(Menu currentMenu) {
        GameServer.currentMenu = currentMenu;
    }

    public static void setAllUsers(ArrayList<User> allUsers) {
        if (!allUsers.isEmpty())
            GameServer.allUsers = allUsers;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static ArrayList<Card> setAllCards() {
        ArrayList<Card> allCards = new ArrayList<>();
        for (UnitCardInfo unitCardInfo : UnitCardInfo.values()) {
            UnitCard unitCard = new UnitCard(unitCardInfo);
            allCards.add(new UnitCard(unitCardInfo));
        }
        for (HeroInfo heroInfo : HeroInfo.values()) {
            allCards.add(new Hero(heroInfo));
        }
        for (SpecialCardInfo specialCardInfo : SpecialCardInfo.values()) {
            allCards.add(new SpecialCard(specialCardInfo));
        }
        return allCards;
    }

    public static ArrayList<Card> getAllCards() {
        return allCards;
    }

    public static void addToSelectedCards(Card card) {
        selectedCards.add(card);
    }

    public static ArrayList<Leader> getAllLeaders() {
        return allLeaders;
    }
}
