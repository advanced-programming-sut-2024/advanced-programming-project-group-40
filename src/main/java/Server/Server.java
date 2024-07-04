package Server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server extends Thread{
    public static final int PORT = 8080;
    private final ServerSocket serverSocket;
    private ArrayList<ClientHandler> clients = new ArrayList<>();

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static void main(String[] args) throws IOException {
        new Server(new ServerSocket(PORT));
    }

}
