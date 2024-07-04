package views;


import enums.cards.UnitCardInfo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.MatchTable;
import models.UserInputHandler.ClickCommand;
import models.cards.Card;
import models.cards.UnitCard;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class GameView extends PlayMenu{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        MainMenu.stage = stage;
        Pane pane = FXMLLoader.load(MainMenu.class.getResource("/FXML/GameBoard.fxml"));
        stage.setScene(new Scene(pane));
        stage.show();

    }
}
