package views;

import enums.cards.UnitCardInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import models.cards.UnitCard;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GameMenu extends PlayMenu implements Initializable {
    @FXML
    private Pane secondplayersiegespecial;
    @FXML
    private Pane secondplayerrangedspecial;
    @FXML
    private Pane secondplayerclosecombatspecial;
    @FXML
    private Pane firstplayerrangedspecial;
    @FXML
    private Pane firstplayerclosecombatspecial;
    @FXML
    private Pane firstplayersiegespecial;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UnitCard unitCard = new UnitCard(UnitCardInfo.TEST);
        Hand.getChildren().add(unitCard);
        unitCard = new UnitCard(UnitCardInfo.TEST2);
        Hand.getChildren().add(unitCard);
        firstplayerclosecombatspecial.getChildren().add(unitCard);

    }
}
