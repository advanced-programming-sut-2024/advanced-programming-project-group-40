package views;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

import java.util.Scanner;

public class GameMenu extends PlayMenu{
    @FXML
    private HBox secondPlayerSiege;
    @FXML
    private HBox Hand;
    @FXML
    private HBox firstPlayerSiege;
    @FXML
    private HBox firstPlayerCloseCombat;
    @FXML
    private HBox firstPlayerRanged;
    @FXML
    private HBox secondPlayerCloseCombat;
    @FXML
    private HBox secondPlayerRanged;

    @Override
    public void check(Scanner scanner) {

    }
}
