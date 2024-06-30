package views;


import enums.cards.UnitCardInfo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;


public class GameView extends PlayMenu{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        GameView.stage = stage;
        Pane pane = FXMLLoader.load(MainMenu.class.getResource("/FXML/GameBoard.fxml"));
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
