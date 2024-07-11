package views;

import Server.ClientHandler;
import Server.Messages.Client.UpdateMessage;
import Server.Messages.MessageSubType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Game;

import java.util.Objects;

public class ForgetPasswordMenu extends PlayMenu {
    @Override
    public void start(Stage stage) throws Exception {
        ClientHandler.client.update(new UpdateMessage(Game.getLoggedInUser().getUsername(), MessageSubType.MAIN_MENU_UPDATE));
        GameView.stage = stage;
        Pane pane = FXMLLoader.load(Objects.requireNonNull(LoginMenu.class.getResource("/FXML/ForgetPasswordMenuFXML.fxml")));
        stage.setScene(new Scene(pane));
        stage.show();
    }

}
