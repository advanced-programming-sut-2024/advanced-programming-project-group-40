package views.ViewController;


import controllers.MenuController.GameMenuController;
import enums.Factions;
import enums.Origin;
import enums.cards.LeaderInfo;
import enums.cards.SpecialCardInfo;
import enums.cards.UnitCardInfo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;


import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Game;
import models.MatchTable;
import models.User;
import models.UserInputHandler.CardClickCommand;
import models.cards.Card;
import models.cards.Leader;
import models.cards.SpecialCard;
import models.cards.UnitCard;
import views.Main;
import views.PlayMenu;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameViewController extends PlayMenu implements Initializable {
    @FXML
    private Button firstPLayerLeaderButton;
    @FXML
    private HBox secondPlayerLeaderImage;
    @FXML
    private HBox firstplayerLeaderImage;
    @FXML
    private Label firstPlayerTotalScore;
    @FXML
    private Label secondPlayerTotalScore;
    @FXML
    private Label SecondPlayerSiegeNum;
    @FXML
    private Label SecondPlayerRangedNum;
    @FXML
    private Label FirstPlayerCloseCombatNum;
    @FXML
    private Label SecondPlayerCloseCombatNum;
    @FXML
    private Label FirstPlayerRangedNum;
    @FXML
    private Label FirstPlayerSiegeNum;
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
        GameMenuController.setGameViewController2(this);
        User tempUser = new User("voices in your head", "a", "a@schizo.com", "kiomars");
        tempUser.setFaction("monsters");

        GameMenuController.setMatchTable(new MatchTable(Game.getLoggedInUser(), tempUser));
        Game.getLoggedInUser().getMatchesPlayed().add(GameMenuController.getMatchTable());
        GameMenuController.initiateDeck(GameMenuController.getMatchTable());
        InitiateCardEvents();
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
                System.out.println(GameMenuController.getMatchTable().isFirstPlayerTurn());
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

    public void update() {
        if (GameMenuController.getMatchTable().isFirstPlayerTurn()) {
            if (GameMenuController.getMatchTable().getFirstPlayer().getLeader() != null &&
                    firstplayerLeaderImage.getChildren().isEmpty()) {
                firstplayerLeaderImage.getChildren().add(GameMenuController.getMatchTable().getFirstPlayerLeader());
            }
            if (GameMenuController.getMatchTable().getSecondPlayer().getLeader() != null &&
                    secondPlayerLeaderImage.getChildren().isEmpty()) {
                secondPlayerLeaderImage.getChildren().add(GameMenuController.getMatchTable().getSecondPlayerLeader());

            }


            if (GameMenuController.getMatchTable().getFirstPlayerCrystals() == 2) {
                firstPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                firstPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            } else if (GameMenuController.getMatchTable().getFirstPlayerCrystals() == 1) {
                firstPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                firstPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

            } else {
                firstPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                firstPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

            }


            if (GameMenuController.getMatchTable().getSecondPlayerCrystals() == 2) {
                secondPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                secondPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            } else if (GameMenuController.getMatchTable().getSecondPlayerCrystals() == 1) {
                secondPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                secondPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

            } else {
                secondPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                secondPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

            }
            GameMenuController.getMatchTable().updatePoints();
            firstplayerdeckamount.setText(STR."\{GameMenuController.getMatchTable().getFirstPlayerDeckCards().size()}");
            secondplayerdeckamount.setText(STR."\{GameMenuController.getMatchTable().getSecondPlayerDeckCards().size()}");
            firstPlayerRemainingCards.setText(STR."\{GameMenuController.getMatchTable().getFirstPlayerInPlayCards().size()}");
            secondPlayerRemainingCards.setText(STR."\{GameMenuController.getMatchTable().getSecondPlayerInPlayCards().size()}");
            FirstPlayerCloseCombatNum.setText(GameMenuController.getMatchTable().getFirstPlayerRowPoints().get(0).toString());
            FirstPlayerRangedNum.setText(GameMenuController.getMatchTable().getFirstPlayerRowPoints().get(1).toString());
            FirstPlayerSiegeNum.setText(GameMenuController.getMatchTable().getFirstPlayerRowPoints().get(2).toString());
            SecondPlayerCloseCombatNum.setText(GameMenuController.getMatchTable().getSecondPlayerRowPoints().get(0).toString());
            SecondPlayerRangedNum.setText(GameMenuController.getMatchTable().getSecondPlayerRowPoints().get(1).toString());
            SecondPlayerSiegeNum.setText(GameMenuController.getMatchTable().getSecondPlayerRowPoints().get(2).toString());
            firstPlayerTotalScore.setText(STR."\{GameMenuController.getMatchTable().getPlayerTotalScore(0)}");
            secondPlayerTotalScore.setText(STR."\{GameMenuController.getMatchTable().getPlayerTotalScore(1)}");
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
            //f inplay
            if (!(GameMenuController.getMatchTable().getFirstPlayerInPlayCards().isEmpty() &&
                    Hand.getChildren().isEmpty())) {
                Hand.getChildren().clear();
                Hand.getChildren().addAll(GameMenuController.getMatchTable().getFirstPlayerInPlayCards());
            }
            //fs
            if (!(GameMenuController.getMatchTable().getFirstPlayerCloseCombatRow().isEmpty() &&
                    firstPlayerCloseCombat.getChildren().isEmpty())) {
                firstPlayerCloseCombat.getChildren().clear();
                firstPlayerCloseCombat.getChildren().addAll(GameMenuController.getMatchTable().getFirstPlayerCloseCombatRow());
            }
            //fr
            if (!(GameMenuController.getMatchTable().getFirstPlayerRangedRow().isEmpty() &&
                    firstPlayerRanged.getChildren().isEmpty())) {
                firstPlayerRanged.getChildren().clear();
                firstPlayerRanged.getChildren().addAll(GameMenuController.getMatchTable().getFirstPlayerRangedRow());
            }
            //fs
            if (!(GameMenuController.getMatchTable().getFirstPlayerSiegeRow().isEmpty() &&
                    firstPlayerSiege.getChildren().isEmpty())) {
                firstPlayerSiege.getChildren().clear();
                firstPlayerSiege.getChildren().addAll(GameMenuController.getMatchTable().getFirstPlayerSiegeRow());
            }
            //sc
            if (!(GameMenuController.getMatchTable().getSecondPlayerCloseCombatRow().isEmpty() &&
                    secondPlayerCloseCombat.getChildren().isEmpty())) {
                secondPlayerCloseCombat.getChildren().clear();
                secondPlayerCloseCombat.getChildren().addAll(GameMenuController.getMatchTable().getSecondPlayerCloseCombatRow());
            }
            //sr
            if (!(GameMenuController.getMatchTable().getSecondPlayerRangedRow().isEmpty() &&
                    secondPlayerRanged.getChildren().isEmpty())) {
                secondPlayerRanged.getChildren().clear();
                secondPlayerRanged.getChildren().addAll(GameMenuController.getMatchTable().getSecondPlayerRangedRow());
            }
            //ss
            if (!(GameMenuController.getMatchTable().getSecondPlayerSiegeRow().isEmpty() &&
                    secondPlayerSiege.getChildren().isEmpty())) {
                secondPlayerSiege.getChildren().clear();
                secondPlayerSiege.getChildren().addAll(GameMenuController.getMatchTable().getSecondPlayerSiegeRow());
            }
            if (!(GameMenuController.getMatchTable().getFirstPlayerDeadCards().isEmpty() &&
                    firstPlayerDiscard.getChildren().isEmpty())) {
                firstPlayerDiscard.getChildren().clear();
                if (!GameMenuController.getMatchTable().getFirstPlayerDeadCards().isEmpty()) {
                    firstPlayerDiscard.getChildren().add(GameMenuController.getMatchTable().getFirstPlayerDeadCards().getLast());
                }
            }
            if (!(GameMenuController.getMatchTable().getSecondPlayerDeadCards().isEmpty() &&
                    secondPlayerDiscard.getChildren().isEmpty())) {
                secondPlayerDiscard.getChildren().clear();
                if (!GameMenuController.getMatchTable().getSecondPlayerDeadCards().isEmpty()) {
                    secondPlayerDiscard.getChildren().add(GameMenuController.getMatchTable().getSecondPlayerDeadCards().getLast());
                }
            }
            secondPlayerName.setText(STR."\{GameMenuController.getMatchTable().getSecondPlayer().getNickname()}");
            firstPlayerName.setText(STR."\{GameMenuController.getMatchTable().getFirstPlayer().getNickname()}");
            firstPlayerFaction.setText(STR."\{GameMenuController.getMatchTable().getFirstPlayer().getFaction()}");
            secondPlayerFaction.setText(STR."\{GameMenuController.getMatchTable().getSecondPlayer().getFaction()}");
        } else {
            if (GameMenuController.getMatchTable().getFirstPlayer().getLeader() != null &&
                    secondPlayerLeaderImage.getChildren().isEmpty()) {
                secondPlayerLeaderImage.getChildren().add(GameMenuController.getMatchTable().getFirstPlayerLeader());
            }
            if (GameMenuController.getMatchTable().getSecondPlayer().getLeader() != null &&
                    firstplayerLeaderImage.getChildren().isEmpty()) {
                firstplayerLeaderImage.getChildren().add(GameMenuController.getMatchTable().getSecondPlayerLeader());
            }

            if (GameMenuController.getMatchTable().getSecondPlayerCrystals() == 2) {
                firstPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                firstPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            } else if (GameMenuController.getMatchTable().getSecondPlayerCrystals() == 1) {
                firstPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                firstPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

            } else {
                firstPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                firstPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

            }


            if (GameMenuController.getMatchTable().getFirstPlayerCrystals() == 2) {
                secondPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                secondPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            } else if (GameMenuController.getMatchTable().getFirstPlayerCrystals() == 1) {
                secondPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                secondPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

            } else {
                secondPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                secondPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

            }


            GameMenuController.getMatchTable().updatePoints();
            firstplayerdeckamount.setText(STR."\{GameMenuController.getMatchTable().getSecondPlayerDeckCards().size()}");
            secondplayerdeckamount.setText(STR."\{GameMenuController.getMatchTable().getFirstPlayerDeckCards().size()}");
            firstPlayerRemainingCards.setText(STR."\{GameMenuController.getMatchTable().getSecondPlayerInPlayCards().size()}");
            secondPlayerRemainingCards.setText(STR."\{GameMenuController.getMatchTable().getFirstPlayerInPlayCards().size()}");
            FirstPlayerCloseCombatNum.setText(GameMenuController.getMatchTable().getSecondPlayerRowPoints().get(0).toString());
            FirstPlayerRangedNum.setText(GameMenuController.getMatchTable().getSecondPlayerRowPoints().get(1).toString());
            FirstPlayerSiegeNum.setText(GameMenuController.getMatchTable().getSecondPlayerRowPoints().get(2).toString());
            SecondPlayerCloseCombatNum.setText(GameMenuController.getMatchTable().getFirstPlayerRowPoints().get(0).toString());
            SecondPlayerRangedNum.setText(GameMenuController.getMatchTable().getFirstPlayerRowPoints().get(1).toString());
            SecondPlayerSiegeNum.setText(GameMenuController.getMatchTable().getFirstPlayerRowPoints().get(2).toString());
            firstPlayerTotalScore.setText(STR."\{GameMenuController.getMatchTable().getPlayerTotalScore(1)}");
            secondPlayerTotalScore.setText(STR."\{GameMenuController.getMatchTable().getPlayerTotalScore(0)}");
            if (GameMenuController.getMatchTable().getSecondPlayerCloseCombatBoostCard() != null) {
                firstplayerclosecombatspecial.getChildren().clear();
                firstplayerclosecombatspecial.getChildren().add(GameMenuController.getMatchTable().getSecondPlayerCloseCombatBoostCard());
            }
            if (GameMenuController.getMatchTable().getSecondPlayerRangedBoostCard() != null) {
                firstplayerrangedspecial.getChildren().clear();
                firstplayerrangedspecial.getChildren().add(GameMenuController.getMatchTable().getSecondPlayerRangedBoostCard());
            }
            if (GameMenuController.getMatchTable().getSecondPlayerSiegeBoostCard() != null) {
                firstplayersiegespecial.getChildren().clear();
                firstplayersiegespecial.getChildren().add(GameMenuController.getMatchTable().getSecondPlayerSiegeBoostCard());
            }

            if (GameMenuController.getMatchTable().getFirstPlayerCloseCombatBoostCard() != null) {
                secondplayerclosecombatspecial.getChildren().clear();
                secondplayerclosecombatspecial.getChildren().add(GameMenuController.getMatchTable().getFirstPlayerCloseCombatBoostCard());
            }
            if (GameMenuController.getMatchTable().getFirstPlayerRangedBoostCard() != null) {
                secondplayerrangedspecial.getChildren().clear();
                secondplayerrangedspecial.getChildren().add(GameMenuController.getMatchTable().getFirstPlayerRangedBoostCard());
            }
            if (GameMenuController.getMatchTable().getFirstPlayerSiegeBoostCard() != null) {
                secondplayersiegespecial.getChildren().clear();
                secondplayersiegespecial.getChildren().add(GameMenuController.getMatchTable().getFirstPlayerSiegeBoostCard());
            }
            //f inplay
            if (!(GameMenuController.getMatchTable().getSecondPlayerInPlayCards().isEmpty() &&
                    Hand.getChildren().isEmpty())) {
                Hand.getChildren().clear();
                Hand.getChildren().addAll(GameMenuController.getMatchTable().getSecondPlayerInPlayCards());
            }
            //fs
            if (!(GameMenuController.getMatchTable().getSecondPlayerCloseCombatRow().isEmpty() &&
                    firstPlayerCloseCombat.getChildren().isEmpty())) {
                firstPlayerCloseCombat.getChildren().clear();
                firstPlayerCloseCombat.getChildren().addAll(GameMenuController.getMatchTable().getSecondPlayerCloseCombatRow());
            }
            //fr
            if (!(GameMenuController.getMatchTable().getSecondPlayerRangedRow().isEmpty() &&
                    firstPlayerRanged.getChildren().isEmpty())) {
                firstPlayerRanged.getChildren().clear();
                firstPlayerRanged.getChildren().addAll(GameMenuController.getMatchTable().getSecondPlayerRangedRow());
            }
            //fs
            if (!(GameMenuController.getMatchTable().getSecondPlayerSiegeRow().isEmpty() &&
                    firstPlayerSiege.getChildren().isEmpty())) {
                firstPlayerSiege.getChildren().clear();
                firstPlayerSiege.getChildren().addAll(GameMenuController.getMatchTable().getSecondPlayerSiegeRow());
            }
            //sc
            if (!(GameMenuController.getMatchTable().getFirstPlayerCloseCombatRow().isEmpty() &&
                    secondPlayerCloseCombat.getChildren().isEmpty())) {
                secondPlayerCloseCombat.getChildren().clear();
                secondPlayerCloseCombat.getChildren().addAll(GameMenuController.getMatchTable().getFirstPlayerCloseCombatRow());
            }
            //sr
            if (!(GameMenuController.getMatchTable().getFirstPlayerRangedRow().isEmpty() &&
                    secondPlayerRanged.getChildren().isEmpty())) {
                secondPlayerRanged.getChildren().clear();
                secondPlayerRanged.getChildren().addAll(GameMenuController.getMatchTable().getFirstPlayerRangedRow());
            }
            //ss
            if (!(GameMenuController.getMatchTable().getFirstPlayerSiegeRow().isEmpty() &&
                    secondPlayerSiege.getChildren().isEmpty())) {
                secondPlayerSiege.getChildren().clear();
                secondPlayerSiege.getChildren().addAll(GameMenuController.getMatchTable().getFirstPlayerSiegeRow());
            }

            if (!(GameMenuController.getMatchTable().getSecondPlayerDeadCards().isEmpty() &&
                    firstPlayerDiscard.getChildren().isEmpty())) {
                firstPlayerDiscard.getChildren().clear();
                if (!GameMenuController.getMatchTable().getSecondPlayerDeadCards().isEmpty()) {
                    firstPlayerDiscard.getChildren().add(GameMenuController.getMatchTable().getSecondPlayerDeadCards().getLast());
                }
            }
            if (!(GameMenuController.getMatchTable().getFirstPlayerDeadCards().isEmpty() &&
                    secondPlayerDiscard.getChildren().isEmpty())) {
                secondPlayerDiscard.getChildren().clear();
                if (!GameMenuController.getMatchTable().getFirstPlayerDeadCards().isEmpty()) {
                    secondPlayerDiscard.getChildren().add(GameMenuController.getMatchTable().getFirstPlayerDeadCards().getLast());
                }
            }
            secondPlayerName.setText(STR."\{GameMenuController.getMatchTable().getFirstPlayer().getNickname()}");
            firstPlayerName.setText(STR."\{GameMenuController.getMatchTable().getSecondPlayer().getNickname()}");
            firstPlayerFaction.setText(STR."\{GameMenuController.getMatchTable().getSecondPlayer().getFaction()}");
            secondPlayerFaction.setText(STR."\{GameMenuController.getMatchTable().getFirstPlayer().getFaction()}");


        }
        //spell
        if (!(GameMenuController.getMatchTable().getSpellCards().isEmpty() &&
                spellCards.getChildren().isEmpty())) {
            spellCards.getChildren().clear();
            spellCards.getChildren().addAll(GameMenuController.getMatchTable().getSpellCards());
        }
        InitiateCardEvents();
        if (GameMenuController.getMatchTable().isMatchFinished()) {
            System.out.println("sock these nuts");
        }
    }


    public void awoga(MouseEvent mouseEvent) {
        update();
    }


    public void secondPlayerSiegeClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnRow(Origin.SECONDPLAYER_SIEGE, this);
        update();
    }

    public void secondPlayerRangedClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnRow(Origin.SECONDPLAYER_RANGED, this);
        update();
    }


    public void secondPlayerCloseCombatClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnRow(Origin.SECONDPLAYER_CLOSECOMBAT, this);
    }

    public void firstPlayerCloseCombatClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnRow(Origin.FIRSTPLAYER_CLOSECOMBAT, this);
    }

    public void firstPlayerRangedClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnRow(Origin.FIRSTPLAYER_RANGED, this);
    }

    public void firstPlayerSiegeClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnRow(Origin.FIRSTPLAYER_SIEGE, this);
    }

    public void closeCombatBoostClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnBoost(0);
        update();
    }

    public void rangedBoostClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnBoost(1);
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

    public HBox getFirstPlayerDiscard() {
        return firstPlayerDiscard;
    }

    public void LeaderAction(MouseEvent mouseEvent) {
        GameMenuController.LeaderAction();
        update();
    }

    public void PassRound(MouseEvent mouseEvent) {
        GameMenuController.passRound();
        update();
    }
}

