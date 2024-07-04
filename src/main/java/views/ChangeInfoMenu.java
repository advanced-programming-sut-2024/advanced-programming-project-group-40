package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChangeInfoMenu extends PlayMenu{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        GameView.stage = stage;
        Pane pane = FXMLLoader.load(MainMenu.class.getResource("/FXML/ChangeInfoFXML.fxml"));
        stage.setScene(new Scene(pane));
        stage.show();
    }
}

