package views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Game;

import java.util.Objects;

public class ChangeInfoMenu extends PlayMenu{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Game.stage = stage;
        Pane pane = FXMLLoader.load(Objects.requireNonNull(MainMenu.class.getResource("/FXML/ChangeInfoFXML.fxml")));
        stage.setScene(new Scene(pane));
        stage.show();
    }
}

