package views;

import Server.Client;
import controllers.DataSaver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Game;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Objects;
import java.util.Scanner;


public class LoginMenu extends PlayMenu {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        String message = "Hello, NIO Server!";

        // Message to send to the server
        client.getBuffer().clear();
        client.getBuffer().put(message.getBytes());
        client.getBuffer().flip();

        // Send the message to the server
        while (client.getBuffer().hasRemaining()) {
            client.getClientChannel().write(client.getBuffer());
        }
        client.getBuffer().clear();

        // Read the server's response
        client.getClientChannel().read(client.getBuffer());
        client.getBuffer().flip();

        // Convert the response to a String and print it
        String response = new String(client.getBuffer().array(), 0, client.getBuffer().limit());
        System.out.println("Server Response: " + response);
        client.getClientChannel().close();

    launch(args);

    }
    @Override
    public void start(Stage stage) throws Exception {
        Game.setAllUsers(Objects.requireNonNull(DataSaver.loadUsers()));
        LoginMenu.stage = stage;
        Pane pane = FXMLLoader.load(Objects.requireNonNull(LoginMenu.class.getResource("/FXML/LoginMenuFXML.fxml")));
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
