package Server;

import models.User;

import java.io.*;
import java.net.Socket;

public class ClientHandler {
    private final User user;
    private final Server server;
    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public ClientHandler(User user, Server server, Socket socket) {
        this.user = user;
        this.server = server;
        this.socket = socket;
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
