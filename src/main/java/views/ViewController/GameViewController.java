package views.ViewController;


import controllers.MenuController.GameMenuController;
import enums.Origin;
import enums.cards.SpecialCardInfo;
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
import models.cards.SpecialCard;
import models.cards.UnitCard;
import views.Main;
import views.PlayMenu;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameViewController extends PlayMenu implements Initializable {
    @FXML
    private GridPane pane;
    @FXML
    private Label secondPlayerFaction;
    @FXML
    private HBox firstPlayerDiscard;
    @FXML
    private HBox secondPlayerDiscard;
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
        GameMenuController.setMatchTable(new MatchTable(Game.getLoggedInUser(), tempUser));
        UnitCard card1 = new UnitCard(UnitCardInfo.ALBRICH);
        UnitCard card2 = new UnitCard(UnitCardInfo.DANDELION);
        UnitCard card3 = new UnitCard(UnitCardInfo.ICE_GIANT);
        UnitCard card4 = new UnitCard(UnitCardInfo.BERSERKER);
        SpecialCard card5 = new SpecialCard(SpecialCardInfo.COMMANDERS_HORN);
        SpecialCard card6 = new SpecialCard(SpecialCardInfo.BITING_FROST);
        SpecialCard card7 = new SpecialCard(SpecialCardInfo.SKELLIGE_STORM);
        SpecialCard card8 = new SpecialCard(SpecialCardInfo.CLEAR_WEATHER);


        GameMenuController.getMatchTable().getFirstPlayerRangedRow().add(card4);
        Game.getLoggedInUser().getDeckCards().add(card1);
        Game.getLoggedInUser().getDeckCards().add(card2);
        Game.getLoggedInUser().getDeckCards().add(card3);
        Game.getLoggedInUser().getDeckCards().add(card5);
        Game.getLoggedInUser().getDeckCards().add(card6);
        Game.getLoggedInUser().getDeckCards().add(card7);
        Game.getLoggedInUser().getDeckCards().add(card8);


        InitiateCardEvents();
        GameMenuController.intiateDeck(GameMenuController.getMatchTable());
        secondPlayerName.setText(STR."\{GameMenuController.getMatchTable().getSecondPlayer().getNickName()}");
        firstPlayerName.setText(STR."\{GameMenuController.getMatchTable().getFirstPlayer().getNickName()}");
        firstPlayerFaction.setText(STR."\{GameMenuController.getMatchTable().getFirstPlayer().getFaction()}");
        secondPlayerFaction.setText(STR."\{GameMenuController.getMatchTable().getSecondPlayer().getFaction()}");
        update();
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
                System.out.println(STR."name:\{card.getName()}");
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
        firstplayerdeckamount.setText(STR."\{GameMenuController.getMatchTable().getFirstPlayerDeckCards().size()}");
        secondplayerdeckamount.setText(STR."\{GameMenuController.getMatchTable().getSecondPlayerDeckCards().size()}");
        firstPlayerRemainingCards.setText(STR."\{GameMenuController.getMatchTable().getFirstPlayerInPlayCards().size()}");
        secondPlayerRemainingCards.setText(STR."\{GameMenuController.getMatchTable().getSecondPlayerInPlayCards().size()}");

        if (GameMenuController.getMatchTable().getFirstPlayerCloseCombatBoostCard() != null) {
            firstplayerclosecombatspecial.getChildren().clear();
            firstplayerclosecombatspecial.getChildren().add(GameMenuController.getMatchTable().getFirstPlayerCloseCombatBoostCard());
        }
        if (GameMenuController.getMatchTable().getFirstPlayerRangedBoostCard() != null) {
            firstplayerrangedspecial.getChildren().clear();
            firstplayerrangedspecial.getChildren().add(GameMenuController.getMatchTable().getFirstPlayerRangedBoostCard());
        }
        if (GameMenuController.getMatchTable().getFirstPlayerSiegeBoostCard() != null) {
            firstplayersiegespecial.getChildren().clear();
            firstplayersiegespecial.getChildren().add(GameMenuController.getMatchTable().getFirstPlayerSiegeBoostCard());
        }

        if (GameMenuController.getMatchTable().getSecondPlayerCloseCombatBoostCard() != null) {
            secondplayerclosecombatspecial.getChildren().clear();
            secondplayerclosecombatspecial.getChildren().add(GameMenuController.getMatchTable().getSecondPlayerCloseCombatBoostCard());
        }
        if (GameMenuController.getMatchTable().getSecondPlayerRangedBoostCard() != null) {
            secondplayerrangedspecial.getChildren().clear();
            secondplayerrangedspecial.getChildren().add(GameMenuController.getMatchTable().getSecondPlayerRangedBoostCard());
        }
        if (GameMenuController.getMatchTable().getSecondPlayerSiegeBoostCard() != null) {
            secondplayersiegespecial.getChildren().clear();
            secondplayersiegespecial.getChildren().add(GameMenuController.getMatchTable().getSecondPlayerSiegeBoostCard());
        }

        if (!GameMenuController.getMatchTable().getFirstPlayerInPlayCards().isEmpty()) {
            Hand.getChildren().clear();
            System.out.println(Hand.getChildren());
            Hand.getChildren().addAll(GameMenuController.getMatchTable().getFirstPlayerInPlayCards());
        }

        if (!GameMenuController.getMatchTable().getFirstPlayerCloseCombatRow().isEmpty()) {
            firstPlayerCloseCombat.getChildren().clear();
            firstPlayerCloseCombat.getChildren().addAll(GameMenuController.getMatchTable().getFirstPlayerCloseCombatRow());
        }
        if (!GameMenuController.getMatchTable().getFirstPlayerRangedRow().isEmpty()) {
            firstPlayerRanged.getChildren().clear();
            firstPlayerRanged.getChildren().addAll(GameMenuController.getMatchTable().getFirstPlayerRangedRow());
        }
        if (!GameMenuController.getMatchTable().getFirstPlayerSiegeRow().isEmpty()) {
            firstPlayerSiege.getChildren().clear();
            firstPlayerSiege.getChildren().addAll(GameMenuController.getMatchTable().getFirstPlayerSiegeRow());
        }

        if (!GameMenuController.getMatchTable().getSecondPlayerCloseCombatRow().isEmpty()) {
            secondPlayerCloseCombat.getChildren().clear();
            secondPlayerCloseCombat.getChildren().addAll(GameMenuController.getMatchTable().getSecondPlayerCloseCombatRow());
        }
        if (!GameMenuController.getMatchTable().getSecondPlayerRangedRow().isEmpty()) {
            secondPlayerRanged.getChildren().clear();
            secondPlayerRanged.getChildren().addAll(GameMenuController.getMatchTable().getSecondPlayerRangedRow());
        }
        if (!GameMenuController.getMatchTable().getSecondPlayerSiegeRow().isEmpty()) {
            secondPlayerSiege.getChildren().clear();
            secondPlayerSiege.getChildren().addAll(GameMenuController.getMatchTable().getSecondPlayerSiegeRow());
        }
        if (!GameMenuController.getMatchTable().getSpellCards().isEmpty()) {
            spellCards.getChildren().clear();
            spellCards.getChildren().addAll(GameMenuController.getMatchTable().getSpellCards());
            System.out.println(firstPlayerDiscard);
        }
        InitiateCardEvents();

    }


    public void awoga(MouseEvent mouseEvent) {
        update();
    }

    public void secondPlayerSiegeClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnRow(Origin.SECONDPLAYER_SIEGE);
        update();
    }

    public void secondPlayerRangedClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnRow(Origin.SECONDPLAYER_RANGED);
        update();
    }


    public void secondPlayerCloseCombatClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnRow(Origin.SECONDPLAYER_CLOSECOMBAT);
        update();
    }

    public void firstPlayerCloseCombatClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnRow(Origin.FIRSTPLAYER_CLOSECOMBAT);
        update();
    }

    public void firstPlayerRangedClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnRow(Origin.FIRSTPLAYER_RANGED);
        update();
    }

    public void firstPlayerSiegeClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnRow(Origin.FIRSTPLAYER_SIEGE);
        update();
    }

    public void closeCombatBoostClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnBoost(0);
        update();
    }

    public void rangedBoostClicked(MouseEvent mouseEvent) {
        System.out.println("here");
        GameMenuController.ClickedOnBoost(1);
        System.out.println(GameMenuController.getMatchTable().getFirstPlayerRangedBoostCard());
        if (GameMenuController.getMatchTable().getFirstPlayerInPlayCards().contains(new SpecialCard(SpecialCardInfo.COMMANDERS_HORN))){
            System.out.println("kys");
        }
        System.out.println(firstplayerrangedspecial.getChildren());
        update();
    }

    public void siegeBoostClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnBoost(2);
        update();
    }

    public void weatherClicked(MouseEvent mouseEvent) {
        GameMenuController.clickedOnWeather();
        update();
    }
}
