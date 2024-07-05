package Server;

import models.User;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler {
    private static Scanner scanner = new Scanner(System.in);
    private static Client client;
    static void run () {
        System.out.println("Welcome!");
        while (true) {
            client.test();
        }
    }

    public static void main(String[] args) {
        client = new Client("localhost", 8000);
        run();
    }}
