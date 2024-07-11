package views;

import Server.ClientHandler;
import Server.Messages.Client.UpdateMessage;
import Server.Messages.MessageSubType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Game;

import java.util.Objects;
import java.util.Scanner;

public class ProfileMenu extends PlayMenu {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        ClientHandler.client.update(new UpdateMessage(Game.getLoggedInUser().getUsername(), MessageSubType.MAIN_MENU_UPDATE));
        Game.stage = stage;
        Pane pane = FXMLLoader.load(Objects.requireNonNull(MainMenu.class.getResource("/FXML/ProfileMenuFXML.fxml")));
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
