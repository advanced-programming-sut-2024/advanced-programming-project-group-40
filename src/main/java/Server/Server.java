package Server;

import java.net.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

import com.google.gson.*;

public class Server extends Thread {
    private static ServerSocket serverSocket;
    public Socket socket;
    private static Gson gsonAgent;

    private static final String INTERNAL_ERROR = "internal server error";
    private static final String INVALID_USERNAME = "no user exists with such username";
    private static final String USERNAME_TAKEN = "this username is taken";
    private static final String INVALID_TOKEN = "this token belongs to no user";
    private static final String WRONG_PASSWORD = "wrong password";
    private static final String BUSY_USER = "user is already logged in";

    private static int WORKERS;

    private static final ArrayList<Socket> connections = new ArrayList<>();

    private static void setupServer() {
        try {
            serverSocket = new ServerSocket(8000);
            WORKERS = 10;
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

//    private ClientMessage extractClientMessage(String clientStr) {
//        try {
//            ClientMessage clientMessage = gsonAgent.fromJson(clientStr, ClientMessage.class);
//            switch (clientMessage.getType()) {
//                case signupLogin:
//                    return gsonAgent.fromJson(clientStr, SignupLoginMessage.class);
//                case setbio:
//                    return gsonAgent.fromJson(clientStr, SetBioMessage.class);
//                case getbio:
//                    return gsonAgent.fromJson(clientStr, GetBioMessage.class);
//                case logout:
//                    return gsonAgent.fromJson(clientStr, LogoutMessage.class);
//                default:
//                    return null;
//            }
//        }
//        catch (Exception e) {
//            return null;
//        }
//    }


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
            System.out.println(STR."Client request: \{clientRequest}");
            System.out.println(socket.toString());
            System.out.println(connections.size());
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
}