package Server;

import models.Game;
import models.User;
import views.LoginMenu;
import views.Main;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler {
    public static Client client;

    static void run(String[] args) throws Exception {
        Main.main(args);
    }

    public static void main(String[] args) throws Exception {
        client = new Client("localhost", 8000);
        run(args);
    }
}
