package views;

import Server.ClientHandler;
import Server.Messages.Client.UpdateMessage;
import Server.Messages.MessageSubType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Game;

public class ScoreBoard extends PlayMenu{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        ClientHandler.client.update(new UpdateMessage(Game.getLoggedInUser().getUsername(), MessageSubType.MAIN_MENU_UPDATE));
        Game.stage = stage;
        Pane pane = FXMLLoader.load(MainMenu.class.getResource("/FXML/ScoreBoardFXML.fxml"));

        stage.setScene(new Scene(pane));
        stage.show();
    }
}
