package views.ViewController;


import controllers.MenuController.GameMenuController;
import enums.Origin;
import enums.cards.UnitCardInfo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Game;
import models.MatchTable;
import models.User;
import models.UserInputHandler.CardClickCommand;
import models.cards.Card;
import models.cards.UnitCard;
import views.Main;
import views.PlayMenu;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    private StackPane firstPlayerDiscard;
    @FXML
    private StackPane secondPlayerDiscard;
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
    private HBox spellCards;
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

        User tempUser = new User("a", "a", "a", "a");

        tempUser.setFaction("as");
        Game.getLoggedInUser().setFaction("we");
        matchTable = new MatchTable(Game.getLoggedInUser(), tempUser);
        UnitCard card1 = new UnitCard(UnitCardInfo.ALBRICH);
        UnitCard card2 = new UnitCard(UnitCardInfo.DANDELION);
        UnitCard card3 = new UnitCard(UnitCardInfo.ICE_GIANT);
        UnitCard card4 = new UnitCard(UnitCardInfo.BERSERKER);


        matchTable.getFirstPlayerRangedRow().add(card4);
        Game.getLoggedInUser().getDeckCards().add(card1);
        Game.getLoggedInUser().getDeckCards().add(card2);
        Game.getLoggedInUser().getDeckCards().add(card3);


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
                System.out.println(card.getName());
                CardClickCommand cardClickCommand = new CardClickCommand(card, this);
                cardClickCommand.excute();
            });
        }
    }

    public void highLightRow(Origin origin) {
        switch (origin) {
            case SECONDPLAYER_CLOSECOMBAT -> {
                secondPlayerCloseCombat.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(179, 217, 209, 0.75), 20, 0, 0, 1));
            }
            case SECONDPLAYER_RANGED -> {
                secondPlayerRanged.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(179, 217, 209, 0.75), 20, 0, 0, 1));
            }
            case SECONDPLAYER_SIEGE -> {
                secondPlayerSiege.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(179, 217, 209, 0.75), 20, 0, 0, 1));
            }
            case FIRSTPLAYER_CLOSECOMBAT -> {
                firstPlayerCloseCombat.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(179, 217, 209, 0.75), 20, 0, 0, 1));
            }
            case FIRSTPLAYER_RANGED -> {
                firstPlayerRanged.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(179, 217, 209, 0.75), 20, 0, 0, 1));
            }
            case FIRSTPLAYER_SIEGE -> {
                firstPlayerSiege.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(179, 217, 209, 0.75), 20, 0, 0, 1));

            }
            case WEATHER -> {
                spellCards.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(179, 217, 209, 0.75), 20, 0, 0, 1));
            }
            case FIRSTPLAYER_AGILE -> {
                firstPlayerRanged.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(179, 217, 209, 0.75), 20, 0, 0, 1));
                firstPlayerCloseCombat.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(179, 217, 209, 0.75), 20, 0, 0, 1));
            }
            case SECONDPLAYER_AGILE -> {
                secondPlayerCloseCombat.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(179, 217, 209, 0.75), 20, 0, 0, 1));
                secondPlayerRanged.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(179, 217, 209, 0.75), 20, 0, 0, 1));
            }
            case FIRSTPLAYER_ALL -> {
                firstPlayerRanged.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(179, 217, 209, 0.75), 20, 0, 0, 1));
                firstPlayerCloseCombat.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(179, 217, 209, 0.75), 20, 0, 0, 1));
                firstPlayerSiege.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(179, 217, 209, 0.75), 20, 0, 0, 1));
            }

        }
    }

    public void unHighlight() {
        spellCards.setEffect(null);
        firstPlayerSiege.setEffect(null);
        firstPlayerRanged.setEffect(null);
        firstPlayerCloseCombat.setEffect(null);
        secondPlayerSiege.setEffect(null);
        secondPlayerRanged.setEffect(null);
        secondPlayerCloseCombat.setEffect(null);
    }

    private void update() {
        firstplayerdeckamount.setText(STR."\{matchTable.getFirstPlayerDeckCards().size()}");
        secondplayerdeckamount.setText(STR."\{matchTable.getSecondPlayerDeckCards().size()}");
        firstPlayerRemainingCards.setText(STR."\{matchTable.getFirstPlayerInPlayCards().size()}");
        secondPlayerRemainingCards.setText(STR."\{matchTable.getSecondPlayerInPlayCards().size()}");

        if (matchTable.getFirstPlayerCloseCombatBoostCard() != null) {
            firstplayerclosecombatspecial.getChildren().clear();
            firstplayerclosecombatspecial.getChildren().add(matchTable.getFirstPlayerCloseCombatBoostCard());
        }
        if (matchTable.getFirstPlayerRangedBoostCard() != null) {
            firstplayerrangedspecial.getChildren().clear();
            firstplayerrangedspecial.getChildren().add(matchTable.getFirstPlayerRangedBoostCard());
        }
        if (matchTable.getFirstPlayerSiegeBoostCard() != null) {
            firstplayersiegespecial.getChildren().clear();
            firstplayersiegespecial.getChildren().add(matchTable.getFirstPlayerSiegeBoostCard());
        }

        if (matchTable.getSecondPlayerCloseCombatBoostCard() != null) {
            secondplayerclosecombatspecial.getChildren().clear();
            secondplayerclosecombatspecial.getChildren().add(matchTable.getSecondPlayerCloseCombatBoostCard());
        }
        if (matchTable.getSecondPlayerRangedBoostCard() != null) {
            secondplayerrangedspecial.getChildren().clear();
            secondplayerrangedspecial.getChildren().add(matchTable.getSecondPlayerRangedBoostCard());
        }
        if (matchTable.getSecondPlayerSiegeBoostCard() != null) {
            secondplayersiegespecial.getChildren().clear();
            secondplayersiegespecial.getChildren().add(matchTable.getSecondPlayerSiegeBoostCard());
        }

        if (!matchTable.getFirstPlayerInPlayCards().isEmpty()) {
            Hand.getChildren().clear();
            System.out.println(Hand.getChildren());
            Hand.getChildren().addAll(matchTable.getFirstPlayerInPlayCards());
        }

        if (!matchTable.getFirstPlayerCloseCombatRow().isEmpty()) {
            firstPlayerCloseCombat.getChildren().clear();
            firstPlayerCloseCombat.getChildren().addAll(matchTable.getFirstPlayerCloseCombatRow());
        }
        if (!matchTable.getFirstPlayerRangedRow().isEmpty()) {
            firstPlayerRanged.getChildren().clear();
            firstPlayerRanged.getChildren().addAll(matchTable.getFirstPlayerRangedRow());
        }
        if (!matchTable.getFirstPlayerSiegeRow().isEmpty()) {
            firstPlayerSiege.getChildren().clear();
            firstPlayerSiege.getChildren().addAll(matchTable.getFirstPlayerSiegeRow());
        }

        if (!matchTable.getSecondPlayerCloseCombatRow().isEmpty()) {
            secondPlayerCloseCombat.getChildren().clear();
            secondPlayerCloseCombat.getChildren().addAll(matchTable.getSecondPlayerCloseCombatRow());
        }
        if (!matchTable.getSecondPlayerRangedRow().isEmpty()) {
            secondPlayerRanged.getChildren().clear();
            secondPlayerRanged.getChildren().addAll(matchTable.getSecondPlayerRangedRow());
        }
        if (!matchTable.getSecondPlayerSiegeRow().isEmpty()) {
            secondPlayerSiege.getChildren().clear();
            secondPlayerSiege.getChildren().addAll(matchTable.getSecondPlayerSiegeRow());
        }
        if (!matchTable.getSpellCards().isEmpty()) {
            spellCards.getChildren().clear();
            spellCards.getChildren().addAll(matchTable.getSpellCards());
        }
        InitiateCardEvents();

    }


    public void awoga(MouseEvent mouseEvent) {
        InitiateCardEvents();
        update();
    }
}
