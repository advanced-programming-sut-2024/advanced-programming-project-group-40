package views.ViewController;


import Server.Models.GameBoardVisualData;
import controllers.MenuController.GameMenuController;
import enums.Factions;
import enums.Origin;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import views.Main;
import views.PlayMenu;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameViewController extends PlayMenu implements Initializable {
    private static GameBoardVisualData visualData;

    public static void setVisualData(String Json) {
        visualData = null;
        GameBoardVisualData temp;
        temp = GameBoardVisualData.deSerialize(Json);
        GameViewController.visualData = temp;
    }

    @FXML
    private HBox secondPlayerLeaderImage;
    @FXML
    private HBox firstPlayerLeaderImage;
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
    private Label secondPlayerRemainingCards;
    @FXML
    private Label firstPlayerName;
    @FXML
    private Label firstPlayerFaction;
    @FXML
    private Label firstPlayerRemainingCards;
    @FXML
    private HBox spellCards;
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
        Game.getLoggedInUser().getMatchesPlayed().add(GameMenuController.getMatchTable());
        GameMenuController.initiateDeck(GameMenuController.getMatchTable());
        InitiateCardEvents();
        GameMenuController.sendData();
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
        GameMenuController.updatePoints();
        GameMenuController.sendData();
        if (visualData.isFirstPlayerTurn()) {
            if (visualData.getLeader(0) != null) {
                if (firstPlayerLeaderImage != null) {
                    if (firstPlayerLeaderImage.getChildren().isEmpty()) {
                        firstPlayerLeaderImage.getChildren().add(visualData.getLeader(0));
                    }
                }
            }
            if (visualData.getLeader(1) != null) {
                if (secondPlayerLeaderImage != null) {
                    if (secondPlayerLeaderImage.getChildren().isEmpty()) {
                        secondPlayerLeaderImage.getChildren().add(visualData.getLeader(1));
                    }
                }
            }


            if (visualData.getCrystals(0) == 2) {
                firstPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                firstPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            } else if (visualData.getCrystals(0) == 1) {
                firstPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                firstPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

            } else {
                firstPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                firstPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

            }


            if (visualData.getCrystals(1) == 2) {
                secondPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                secondPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            } else if (visualData.getCrystals(1) == 1) {
                secondPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                secondPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

            } else {
                secondPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                secondPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

            }


            firstplayerdeckamount.setText(STR."\{visualData.getCardArrayByArrayName("firstPlayerDeck").size()}");
            secondplayerdeckamount.setText(STR."\{visualData.getCardArrayByArrayName("secondPlayerDeck").size()}");
            firstPlayerRemainingCards.setText(STR."\{visualData.getCardArrayByArrayName("firstPlayerInPlay").size()}");
            secondPlayerRemainingCards.setText(STR."\{visualData.getCardArrayByArrayName("secondPlayerInPlay").size()}");
            FirstPlayerCloseCombatNum.setText(STR."\{visualData.getRowPoints(0, 0)}");
            FirstPlayerRangedNum.setText(STR."\{visualData.getRowPoints(0, 1)}");
            FirstPlayerSiegeNum.setText(STR."\{visualData.getRowPoints(0, 2)}");
            SecondPlayerCloseCombatNum.setText(STR."\{visualData.getRowPoints(1, 0)}");
            SecondPlayerRangedNum.setText(STR."\{visualData.getRowPoints(1, 1)}");
            SecondPlayerSiegeNum.setText(STR."\{visualData.getRowPoints(1, 2)}");
            firstPlayerTotalScore.setText(STR."\{visualData.getTotalPoints(0)}");
            secondPlayerTotalScore.setText(STR."\{visualData.getTotalPoints(1)}");

            if (visualData.getBoost(0, 0) != null) {
                firstplayerclosecombatspecial.getChildren().clear();
                firstplayerclosecombatspecial.getChildren().add(visualData.getBoost(0, 0));
            }
            if (visualData.getBoost(0, 1) != null) {
                firstplayerrangedspecial.getChildren().clear();
                firstplayerrangedspecial.getChildren().add(visualData.getBoost(0, 1));
            }
            if (visualData.getBoost(0, 2) != null) {
                firstplayersiegespecial.getChildren().clear();
                firstplayersiegespecial.getChildren().add(visualData.getBoost(0, 2));
            }

            if (visualData.getBoost(1, 0) != null) {
                secondplayerclosecombatspecial.getChildren().clear();
                secondplayerclosecombatspecial.getChildren().add(visualData.getBoost(1, 0));
            }
            if (visualData.getBoost(1, 1) != null) {
                secondplayerrangedspecial.getChildren().clear();
                secondplayerrangedspecial.getChildren().add(visualData.getBoost(1, 1));
            }
            if (visualData.getBoost(1, 2) != null) {
                secondplayersiegespecial.getChildren().clear();
                secondplayersiegespecial.getChildren().add(visualData.getBoost(1, 2));
            }
            //f inplay

            if (!(visualData.getCardArrayByArrayName("firstPlayerInPlay").isEmpty() &&
                    Hand.getChildren().isEmpty())) {
                Hand.getChildren().clear();
                Hand.getChildren().addAll(visualData.getCardArrayByArrayName("firstPlayerInPlay"));
            }
            //fs
            if (!(visualData.getCardArrayByArrayName("firstPlayerCC").isEmpty() &&
                    firstPlayerCloseCombat.getChildren().isEmpty())) {
                firstPlayerCloseCombat.getChildren().clear();
                firstPlayerCloseCombat.getChildren().addAll(visualData.getCardArrayByArrayName("firstPlayerCC"));
            }
            //fr
            if (!(visualData.getCardArrayByArrayName("firstPlayerRanged").isEmpty() &&
                    firstPlayerRanged.getChildren().isEmpty())) {
                firstPlayerRanged.getChildren().clear();
                firstPlayerRanged.getChildren().addAll(visualData.getCardArrayByArrayName("firstPlayerRanged"));
            }
            //fs
            if (!(visualData.getCardArrayByArrayName("firstPlayerSiege").isEmpty() &&
                    firstPlayerSiege.getChildren().isEmpty())) {
                firstPlayerSiege.getChildren().clear();
                firstPlayerSiege.getChildren().addAll(visualData.getCardArrayByArrayName("firstPlayerSiege"));
            }
            //sc
            if (!(visualData.getCardArrayByArrayName("secondPlayerCC").isEmpty() &&
                    secondPlayerCloseCombat.getChildren().isEmpty())) {
                secondPlayerCloseCombat.getChildren().clear();
                secondPlayerCloseCombat.getChildren().addAll(visualData.getCardArrayByArrayName("secondPlayerCC"));
            }
            //sr
            if (!(visualData.getCardArrayByArrayName("secondPlayerRanged").isEmpty() &&
                    secondPlayerRanged.getChildren().isEmpty())) {
                secondPlayerRanged.getChildren().clear();
                secondPlayerRanged.getChildren().addAll(visualData.getCardArrayByArrayName("secondPlayerRanged"));
            }
            //ss
            if (!(visualData.getCardArrayByArrayName("secondPlayerSiege").isEmpty() &&
                    secondPlayerSiege.getChildren().isEmpty())) {
                secondPlayerSiege.getChildren().clear();
                secondPlayerSiege.getChildren().addAll(visualData.getCardArrayByArrayName("secondPlayerSiege"));
            }
            if (!(visualData.getCardArrayByArrayName("firstPlayerDiscard").isEmpty() &&
                    firstPlayerDiscard.getChildren().isEmpty())) {
                firstPlayerDiscard.getChildren().clear();
                if (!visualData.getCardArrayByArrayName("firstPlayerDiscard").isEmpty()) {
                    firstPlayerDiscard.getChildren().add(visualData.getCardArrayByArrayName("firstPlayerDiscard").getLast());
                }
            }
            if (!(visualData.getCardArrayByArrayName("secondPlayerDiscard").isEmpty() &&
                    secondPlayerDiscard.getChildren().isEmpty())) {
                secondPlayerDiscard.getChildren().clear();
                if (!visualData.getCardArrayByArrayName("secondPlayerDiscard").isEmpty()) {
                    secondPlayerDiscard.getChildren().add(visualData.getCardArrayByArrayName("secondPlayerDiscard").getLast());
                }
            }

            secondPlayerName.setText(STR."\{visualData.getNickName(0)}");
            firstPlayerName.setText(STR."\{visualData.getNickName(1)}");
            firstPlayerFaction.setText(STR."\{visualData.getFaction(0)}");
            secondPlayerFaction.setText(STR."\{visualData.getFaction(1)}");
        }
        else {
            if (visualData.getLeader(1) != null) {
                if (firstPlayerLeaderImage != null) {
                    if (firstPlayerLeaderImage.getChildren().isEmpty()) {
                        firstPlayerLeaderImage.getChildren().add(visualData.getLeader(1));
                    }
                }
            }
            if (visualData.getLeader(0) != null) {
                if (secondPlayerLeaderImage != null) {
                    if (secondPlayerLeaderImage.getChildren().isEmpty()) {
                        secondPlayerLeaderImage.getChildren().add(visualData.getLeader(0));
                    }
                }
            }


            if (visualData.getCrystals(1) == 2) {
                firstPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                firstPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            } else if (visualData.getCrystals(1) == 1) {
                firstPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                firstPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

            } else {
                firstPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                firstPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

            }


            if (visualData.getCrystals(0) == 2) {
                secondPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                secondPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            } else if (visualData.getCrystals(0) == 1) {
                secondPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                secondPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_red.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

            } else {
                secondPLayerCrystal1.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                secondPLayerCrystal2.setBackground(new Background(new BackgroundImage(new Image(Card.class.getResource("/Assets/InfoHolder/ruby_grey.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

            }


            firstplayerdeckamount.setText(STR."\{visualData.getCardArrayByArrayName("firstPlayerDeck").size()}");
            secondplayerdeckamount.setText(STR."\{visualData.getCardArrayByArrayName("secondPlayerDeck").size()}");
            firstPlayerRemainingCards.setText(STR."\{visualData.getCardArrayByArrayName("firstPlayerInPlay").size()}");
            secondPlayerRemainingCards.setText(STR."\{visualData.getCardArrayByArrayName("secondPlayerInPlay").size()}");
            FirstPlayerCloseCombatNum.setText(STR."\{visualData.getRowPoints(1, 0)}");
            FirstPlayerRangedNum.setText(STR."\{visualData.getRowPoints(1, 1)}");
            FirstPlayerSiegeNum.setText(STR."\{visualData.getRowPoints(1, 2)}");
            SecondPlayerCloseCombatNum.setText(STR."\{visualData.getRowPoints(0, 0)}");
            SecondPlayerRangedNum.setText(STR."\{visualData.getRowPoints(0, 1)}");
            SecondPlayerSiegeNum.setText(STR."\{visualData.getRowPoints(0, 2)}");
            firstPlayerTotalScore.setText(STR."\{visualData.getTotalPoints(1)}");
            secondPlayerTotalScore.setText(STR."\{visualData.getTotalPoints(0)}");

            if (visualData.getBoost(1, 0) != null) {
                firstplayerclosecombatspecial.getChildren().clear();
                firstplayerclosecombatspecial.getChildren().add(visualData.getBoost(1, 0));
            }
            if (visualData.getBoost(1, 1) != null) {
                firstplayerrangedspecial.getChildren().clear();
                firstplayerrangedspecial.getChildren().add(visualData.getBoost(1, 1));
            }
            if (visualData.getBoost(1, 2) != null) {
                firstplayersiegespecial.getChildren().clear();
                firstplayersiegespecial.getChildren().add(visualData.getBoost(1, 2));
            }

            if (visualData.getBoost(0, 0) != null) {
                secondplayerclosecombatspecial.getChildren().clear();
                secondplayerclosecombatspecial.getChildren().add(visualData.getBoost(0, 0));
            }
            if (visualData.getBoost(0, 1) != null) {
                secondplayerrangedspecial.getChildren().clear();
                secondplayerrangedspecial.getChildren().add(visualData.getBoost(0, 1));
            }
            if (visualData.getBoost(0, 2) != null) {
                secondplayersiegespecial.getChildren().clear();
                secondplayersiegespecial.getChildren().add(visualData.getBoost(0, 2));
            }
            //f inplay

            if (!(visualData.getCardArrayByArrayName("secondPlayerInPlay").isEmpty() &&
                    Hand.getChildren().isEmpty())) {
                Hand.getChildren().clear();
                Hand.getChildren().addAll(visualData.getCardArrayByArrayName("secondPlayerInPlay"));
            }
            //fs
            if (!(visualData.getCardArrayByArrayName("secondPlayerCC").isEmpty() &&
                    firstPlayerCloseCombat.getChildren().isEmpty())) {
                firstPlayerCloseCombat.getChildren().clear();
                firstPlayerCloseCombat.getChildren().addAll(visualData.getCardArrayByArrayName("secondPlayerCC"));
            }
            //fr
            if (!(visualData.getCardArrayByArrayName("secondPlayerRanged").isEmpty() &&
                    firstPlayerRanged.getChildren().isEmpty())) {
                firstPlayerRanged.getChildren().clear();
                firstPlayerRanged.getChildren().addAll(visualData.getCardArrayByArrayName("secondPlayerRanged"));
            }
            //fs
            if (!(visualData.getCardArrayByArrayName("secondPlayerSiege").isEmpty() &&
                    firstPlayerSiege.getChildren().isEmpty())) {
                firstPlayerSiege.getChildren().clear();
                firstPlayerSiege.getChildren().addAll(visualData.getCardArrayByArrayName("secondPlayerSiege"));
            }
            //sc
            if (!(visualData.getCardArrayByArrayName("firstPlayerCC").isEmpty() &&
                    secondPlayerCloseCombat.getChildren().isEmpty())) {
                secondPlayerCloseCombat.getChildren().clear();
                secondPlayerCloseCombat.getChildren().addAll(visualData.getCardArrayByArrayName("firstPlayerCC"));
            }
            //sr
            if (!(visualData.getCardArrayByArrayName("firstPlayerRanged").isEmpty() &&
                    secondPlayerRanged.getChildren().isEmpty())) {
                secondPlayerRanged.getChildren().clear();
                secondPlayerRanged.getChildren().addAll(visualData.getCardArrayByArrayName("firstPlayerRanged"));
            }
            //ss
            if (!(visualData.getCardArrayByArrayName("firstPlayerSiege").isEmpty() &&
                    secondPlayerSiege.getChildren().isEmpty())) {
                secondPlayerSiege.getChildren().clear();
                secondPlayerSiege.getChildren().addAll(visualData.getCardArrayByArrayName("firstPlayerSiege"));
            }
            if (!(visualData.getCardArrayByArrayName("secondPlayerDiscard").isEmpty() &&
                    firstPlayerDiscard.getChildren().isEmpty())) {
                firstPlayerDiscard.getChildren().clear();
                if (!visualData.getCardArrayByArrayName("secondPlayerDiscard").isEmpty()) {
                    firstPlayerDiscard.getChildren().add(visualData.getCardArrayByArrayName("secondPlayerDiscard").getLast());
                }
            }
            if (!(visualData.getCardArrayByArrayName("firstPlayerDiscard").isEmpty() &&
                    secondPlayerDiscard.getChildren().isEmpty())) {
                secondPlayerDiscard.getChildren().clear();
                if (!visualData.getCardArrayByArrayName("firstPlayerDiscard").isEmpty()) {
                    secondPlayerDiscard.getChildren().add(visualData.getCardArrayByArrayName("firstPlayerDiscard").getLast());
                }
            }

            secondPlayerName.setText(STR."\{visualData.getNickName(1)}");
            firstPlayerName.setText(STR."\{visualData.getNickName(0)}");
            firstPlayerFaction.setText(STR."\{visualData.getFaction(1)}");
            secondPlayerFaction.setText(STR."\{visualData.getFaction(0)}");

        }

        //spell
        if (!(visualData.getCardArrayByArrayName("weather").isEmpty() &&
                spellCards.getChildren().isEmpty())) {
            spellCards.getChildren().clear();
            spellCards.getChildren().addAll(visualData.getCardArrayByArrayName("weather"));
        }
        InitiateCardEvents();
        if (visualData.isMatchFinished()) {
            System.out.println("sock these nuts");
        }
    }

    public void secondPlayerSiegeClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnRow(Origin.SECONDPLAYER_SIEGE);
    }

    public void secondPlayerRangedClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnRow(Origin.SECONDPLAYER_RANGED);
    }


    public void secondPlayerCloseCombatClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnRow(Origin.SECONDPLAYER_CLOSECOMBAT);
    }

    public void firstPlayerCloseCombatClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnRow(Origin.FIRSTPLAYER_CLOSECOMBAT);
    }

    public void firstPlayerRangedClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnRow(Origin.FIRSTPLAYER_RANGED);
    }

    public void firstPlayerSiegeClicked(MouseEvent mouseEvent) {
        GameMenuController.ClickedOnRow(Origin.FIRSTPLAYER_SIEGE);
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