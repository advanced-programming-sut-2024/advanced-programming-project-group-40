package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Server.GameServer;

public class SecurityQuestionMenu extends PlayMenu {
    @Override
    public void start(Stage stage) throws Exception {
        GameServer.stage = stage;
        Pane pane = FXMLLoader.load(LoginMenu.class.getResource("/FXML/SecurityQuestionFXML.fxml"));
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
