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
//        Client client = Client.getClient();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
      //TODO
//        Game.setAllUsers(Objects.requireNonNull(DataSaver.loadUsers()));
        LoginMenu.stage = stage;
        Pane pane = FXMLLoader.load(Objects.requireNonNull(LoginMenu.class.getResource("/FXML/LoginMenuFXML.fxml")));
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
