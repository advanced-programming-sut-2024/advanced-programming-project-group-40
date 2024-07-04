package views;

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
        SocketChannel clientChannel = SocketChannel.open();
        clientChannel.connect(new InetSocketAddress("localhost", 42069));
        ByteBuffer buffer = ByteBuffer.allocate(256);
        String message = new Scanner(System.in).nextLine();

        // Message to send to the server
        buffer.clear();
        buffer.put(message.getBytes());
        buffer.flip();

        // Send the message to the server
        while (buffer.hasRemaining()) {
            clientChannel.write(buffer);
        }
        buffer.clear();

        // Read the server's response
        clientChannel.read(buffer);
        buffer.flip();

        // Convert the response to a String and print it
        String response = new String(buffer.array(), 0, buffer.limit());
        System.out.println("Server Response: " + response);
        clientChannel.close();
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
