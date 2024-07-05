package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Server.GameServer;

public class ScoreBoard extends PlayMenu{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {

        GameServer.stage = stage;
        Pane pane = FXMLLoader.load(MainMenu.class.getResource("/FXML/ScoreBoardFXML.fxml"));

        stage.setScene(new Scene(pane));
        stage.show();
    }
}
