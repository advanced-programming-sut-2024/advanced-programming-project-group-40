package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Game;

import java.util.Objects;


public class ChoseFactionMenu extends PlayMenu {


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        Game.stage = stage;
        Pane pane = FXMLLoader.load(Objects.requireNonNull(ChoseFactionMenu.class.getResource("/FXML/ChoseFactionFXML.fxml")));
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
