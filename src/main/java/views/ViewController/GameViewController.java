package views.ViewController;


import controllers.MenuController.GameMenuController;
import enums.cards.UnitCardInfo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;
import models.Game;
import models.MatchTable;
import models.User;
import models.UserInputHandler.ClickCommand;
import models.cards.Card;
import models.cards.UnitCard;
import views.PlayMenu;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GameViewController extends PlayMenu implements Initializable {
    private static MatchTable matchTable;


    public static MatchTable getMatchTable() {
        return matchTable;
    }

    public static void setMatchTable(MatchTable matchTable1) {
        matchTable = matchTable1;
    }

    @FXML
    private GridPane pane;
    @FXML
    private Label secondPlayerFaction;
    @FXML
    private Label secondPlayerName;
    @FXML
    private StackPane secondPlayerFactionImage;
    @FXML
    private Label secondPlayerRemainingCards;
    @FXML
    private StackPane firstPlayerFactionImage;
    @FXML
    private Label firstPlayerName;
    @FXML
    private Label firstPlayerFaction;
    @FXML
    private Label firstPlayerRemainingCards;
    @FXML
    private StackPane secondplayerdeckfaction;
    @FXML
    private StackPane firstplayerdeckfaction;
    @FXML
    private StackPane firstPLayerCrystal1;
    @FXML
    private StackPane firstPLayerCrystal2;
    @FXML
    private StackPane secondPLayerCrystal1;
    @FXML
    private StackPane secondPLayerCrystal2;
    @FXML
    private Label secondplayerdeckamount;
    @FXML
    private Label firstplayerdeckamount;
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
    public void start(Stage primaryStage) throws Exception {
        URL url = Main.class.getResource("/FXML/GameBoard.fxml");
        assert url != null;
        Scene scene = new Scene(FXMLLoader.load(url));
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //todo
        //initialize faction and leader images for each player

        User tempUser = new User("a","a","a","a");
        tempUser.setFaction("as");
        Game.getLoggedInUser().setFaction("we");
        matchTable = new MatchTable(Game.getLoggedInUser(),tempUser);
        UnitCard card1 = new UnitCard(UnitCardInfo.SPONGE_BOB);
        UnitCard card2 = new UnitCard(UnitCardInfo.TEST2);
        UnitCard card3 = new UnitCard(UnitCardInfo.TEST);
        UnitCard card4 = new UnitCard(UnitCardInfo.BEAR);
        UnitCard card5 = new UnitCard(UnitCardInfo.TEST3);
        UnitCard card6 = new UnitCard(UnitCardInfo.TEST4);
        UnitCard card7 = new UnitCard(UnitCardInfo.TEST5);
        UnitCard card8 = new UnitCard(UnitCardInfo.TEST6);
        UnitCard card9 = new UnitCard(UnitCardInfo.TEST7);
        UnitCard card10 = new UnitCard(UnitCardInfo.TEST8);
        UnitCard card11 = new UnitCard(UnitCardInfo.TEST9);
        UnitCard card12 = new UnitCard(UnitCardInfo.TEST10);
        UnitCard card13 = new UnitCard(UnitCardInfo.TEST11);
        UnitCard card14 = new UnitCard(UnitCardInfo.TEST12);


        Game.getLoggedInUser().getDeckCards().add(card1);
        Game.getLoggedInUser().getDeckCards().add(card2);
        Game.getLoggedInUser().getDeckCards().add(card3);
        Game.getLoggedInUser().getDeckCards().add(card4);
        Game.getLoggedInUser().getDeckCards().add(card5);
        Game.getLoggedInUser().getDeckCards().add(card6);
        Game.getLoggedInUser().getDeckCards().add(card7);
        Game.getLoggedInUser().getDeckCards().add(card8);
        Game.getLoggedInUser().getDeckCards().add(card9);
        Game.getLoggedInUser().getDeckCards().add(card10);
        Game.getLoggedInUser().getDeckCards().add(card11);
        Game.getLoggedInUser().getDeckCards().add(card12);
        Game.getLoggedInUser().getDeckCards().add(card13);
        Game.getLoggedInUser().getDeckCards().add(card14);

        InitiateCardEvents();
        GameMenuController.intiateDeck(matchTable);
        secondPlayerName.setText(STR."\{matchTable.getSecondPlayer().getNickName()}");
        firstPlayerName.setText(STR."\{matchTable.getFirstPlayer().getNickName()}");
        firstPlayerFaction.setText(STR."\{matchTable.getFirstPlayer().getFaction()}");
        secondPlayerFaction.setText(STR."\{matchTable.getSecondPlayer().getFaction()}");
    }

    private void getCards(Pane pane, ArrayList<Card> nodes) {
        ObservableList<Node> children = pane.getChildren();
        for (Node node : children) {
            if (node instanceof Pane pane1) {
                getCards(pane1, nodes);
            }
            if (node instanceof Card card) {
                nodes.add(card);
            }
        }
    }

    private void InitiateCardEvents() {
        ArrayList<Card> cards = new ArrayList<>();
        getCards(pane, cards);
        for (Card card : cards) {
            card.setOnMouseClicked(_ -> {
                ClickCommand clickCommand = new ClickCommand(card, card.getParent().getId());
                clickCommand.excute();
            });
        }
    }

    private void update() {
        firstplayerdeckamount.setText(STR."\{matchTable.getFirstPlayerDeckCards().size()}");
        secondplayerdeckamount.setText(STR."\{matchTable.getSecondPlayerDeckCards().size()}");
        firstPlayerRemainingCards.setText(STR."\{matchTable.getFirstPlayerInPlayCards().size()}");
        secondPlayerRemainingCards.setText(STR."\{matchTable.getSecondPlayerInPlayCards().size()}");

        if (matchTable.getFirstPlayerCloseCombatBoostCard() != null) firstplayerclosecombatspecial.getChildren().add(matchTable.getFirstPlayerCloseCombatBoostCard());
        if (matchTable.getFirstPlayerRangedBoostCard() != null) firstplayerrangedspecial.getChildren().add(matchTable.getFirstPlayerRangedBoostCard());
        if (matchTable.getFirstPlayerSiegeBoostCard() != null) firstplayersiegespecial.getChildren().add(matchTable.getFirstPlayerSiegeBoostCard());

        if (matchTable.getSecondPlayerCloseCombatBoostCard() != null) secondplayerclosecombatspecial.getChildren().add(matchTable.getSecondPlayerCloseCombatBoostCard());
        if (matchTable.getSecondPlayerRangedBoostCard() != null) secondplayerrangedspecial.getChildren().add(matchTable.getSecondPlayerRangedBoostCard());
        if (matchTable.getSecondPlayerSiegeBoostCard() != null) secondplayersiegespecial.getChildren().add(matchTable.getSecondPlayerSiegeBoostCard());

        if (!matchTable.getFirstPlayerInPlayCards().isEmpty()) Hand.getChildren().addAll(matchTable.getFirstPlayerInPlayCards());

        if (!matchTable.getFirstPlayerCloseCombatRow().isEmpty()) firstPlayerCloseCombat.getChildren().addAll(matchTable.getFirstPlayerCloseCombatRow());
        if (!matchTable.getFirstPlayerRangedRow().isEmpty()) firstPlayerRanged.getChildren().addAll(matchTable.getFirstPlayerRangedRow());
        if (!matchTable.getFirstPlayerSiegeRow().isEmpty()) firstPlayerSiege.getChildren().addAll(matchTable.getFirstPlayerSiegeRow());

        if (!matchTable.getSecondPlayerCloseCombatRow().isEmpty()) secondPlayerCloseCombat.getChildren().addAll(matchTable.getSecondPlayerCloseCombatRow());
        if (!matchTable.getSecondPlayerRangedRow().isEmpty()) secondPlayerRanged.getChildren().addAll(matchTable.getSecondPlayerRangedRow());
        if (!matchTable.getSecondPlayerSiegeRow().isEmpty()) secondPlayerSiege.getChildren().addAll(matchTable.getSecondPlayerSiegeRow());
        InitiateCardEvents();

    }



    public void awoga(MouseEvent mouseEvent) {
        update();
    }
}
