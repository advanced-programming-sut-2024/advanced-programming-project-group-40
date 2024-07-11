package views.ViewController;


import Server.ClientHandler;
import Server.Messages.Client.GetUserMessage;
import Server.Messages.Client.UpdateMessage;
import Server.Messages.MessageSubType;
import Server.Models.GameBoardVisualData;
import enums.Ability;
import enums.Origin;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;


import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import models.Game;
import models.UserInputHandler.CardClickCommand;
import models.cards.Card;
import models.cards.Hero;
import models.cards.SpecialCard;
import models.cards.UnitCard;
import views.Main;
import views.PlayMenu;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameViewController  implements Initializable {

    private static final int SPAM_FILTER_TIME = 2000;
    public VBox vboxMessages;
    private boolean isFirstPlayerMainUser;
    public ScrollPane chat;
    public CheckBox isReply;
    public Label ReactionInput;
    private GameBoardVisualData visualData;
    private Stage tempStage;
    Thread spamThread = new Thread(() -> {
        try {

            Thread.sleep(SPAM_FILTER_TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    });
    Thread spamThread2 = new Thread(() -> {
        try {

            Thread.sleep(SPAM_FILTER_TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    });

    public void setVisualData(String Json) {
        GameBoardVisualData temp;
        temp = GameBoardVisualData.deSerialize(Json);
        this.visualData = temp;
        update();
    }

    @FXML
    private TextField messageInput;
    @FXML
    private ChoiceBox<String> Messages;
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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ClientHandler.client.setGameViewController(this);
        String[] messages = {"kys", "ALI ABD'EL AZIZ", "YOU MAD TERRORIST"
                , "sure buddy",
                "nice argument senator why don't you back it up with a source?", "UwU"};
        Messages.getItems().addAll(messages);
        emptyMessage();
        chat.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        chat.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        String a = ClientHandler.client.sendCommand("initiateDeck");
        setVisualData(a);
        ClientHandler.client.update(new UpdateMessage(Game.getLoggedInUser().getUsername(), MessageSubType.GAME_UPDATE));
    }

    private static Origin GetDestination(Card selectedCard) {
        if (selectedCard instanceof UnitCard unitCard) {
            if (unitCard.getAbility() == Ability.SPY) {
                switch (unitCard.getUnit()) {
                    case AGILE -> {
                        return Origin.SECONDPLAYER_AGILE;
                    }
                    case CLOSE_COMBAT -> {
                        return Origin.SECONDPLAYER_CLOSECOMBAT;
                    }
                    case SIEGE -> {
                        return Origin.SECONDPLAYER_SIEGE;

                    }
                    case RANGED -> {
                        return Origin.SECONDPLAYER_RANGED;
                    }
                }
            } else {
                switch (unitCard.getUnit()) {
                    case AGILE -> {
                        return Origin.FIRSTPLAYER_AGILE;
                    }
                    case CLOSE_COMBAT -> {
                        return Origin.FIRSTPLAYER_CLOSECOMBAT;
                    }
                    case SIEGE -> {
                        return Origin.FIRSTPLAYER_SIEGE;

                    }
                    case RANGED -> {
                        return Origin.FIRSTPLAYER_RANGED;

                    }
                    case All -> {
                        return Origin.FIRSTPLAYER_ALL;
                    }
                }
            }

        }
        if (selectedCard instanceof Hero hero) {
            if (hero.getAbility() == Ability.SPY) {
                switch (hero.getUnit()) {
                    case AGILE -> {
                        return Origin.SECONDPLAYER_AGILE;
                    }
                    case CLOSE_COMBAT -> {
                        return Origin.SECONDPLAYER_CLOSECOMBAT;
                    }
                    case SIEGE -> {
                        return Origin.SECONDPLAYER_SIEGE;

                    }
                    case RANGED -> {
                        return Origin.SECONDPLAYER_RANGED;
                    }
                }
            } else {
                switch (hero.getUnit()) {
                    case AGILE -> {
                        return Origin.SECONDPLAYER_AGILE;

                    }
                    case CLOSE_COMBAT -> {
                        return Origin.FIRSTPLAYER_CLOSECOMBAT;
                    }
                    case SIEGE -> {
                        return Origin.FIRSTPLAYER_SIEGE;

                    }
                    case RANGED -> {
                        return Origin.FIRSTPLAYER_RANGED;

                    }
                }
            }
        }
        if (selectedCard instanceof SpecialCard specialCard) {
            if (Objects.equals(specialCard.getName(), "Commander's horn")) {
                return Origin.FIRSTPLAYER_ALL;
            } else {
                return Origin.WEATHER;

            }
        } else return null;


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
            card.initilizePics();
            card.setOnMouseClicked(_ -> {
                CardClickCommand cardClickCommand = new CardClickCommand(card, isSelectable(card), card.getParent().getId());
                cardClickCommand.excute();
                unHighlight();
                highLightRow(GetDestination(card));
            });
        }

    }

    private static boolean isSelectable(Card selectedCard) {
        return Objects.equals(selectedCard.getParent().getId(), "Hand");
    }

    private void InitiateCardEvents(Pane pane) {
        ArrayList<Card> cards = new ArrayList<>();
        getCards(pane, cards);
        for (Card card : cards) {
            card.setOnMouseClicked(_ -> {
                CardClickCommand cardClickCommand = new CardClickCommand(card, true, card.getParent().getId());
                cardClickCommand.excute();
                tempStage.close();
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
        isFirstPlayerMainUser = Objects.equals(Game.getLoggedInUser().getUsername(), visualData.getFirstPlayerUserName());
        Platform.runLater(() -> {
            if (visualData.isDestroyer())
                MakeDestroyerOfWorldsWindow(visualData.isFirstPlayerTurn() == isFirstPlayerMainUser);
            if (visualData.isRedRider())
                MakeCommanderOfRedRidersWindow(visualData.isFirstPlayerTurn() == isFirstPlayerMainUser);
            if (visualData.isMedic()) MakeMedicWindow(visualData.isFirstPlayerTurn() == isFirstPlayerMainUser);
            if (visualData.isImperialMajesty())
                MakeHisImperialMajestyWindow(visualData.isFirstPlayerTurn() == isFirstPlayerMainUser);
            if (visualData.isKingOfWildHunt())
                MakeKingOfWildHuntWindow(visualData.isFirstPlayerTurn() == isFirstPlayerMainUser);
            if (visualData.getReaction() != null) {
                ReactionInput.setText(visualData.getReaction());

                Thread removeMessageThread = new Thread(() -> {
                    try {

                        Thread.sleep(5000);
                        Platform.runLater(() -> messageInput.setText(""));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
                removeMessageThread.start();


            }
            if (visualData.isThereAMessage()) {
                HBox hBox = new HBox();
                if (visualData.isReply()) {
                    if (Objects.equals(visualData.getNickName(0), visualData.getUsername())) {
                        hBox.setAlignment(Pos.CENTER_RIGHT);
                        hBox.setPadding(new Insets(5, 5, 5, 10));
                        Text text = new Text(STR."replyingTo:\{visualData.getUserName()}\n\{visualData.getTime()} \{visualData.getUsername()}:\{visualData.getMessage()}");
                        TextFlow textFlow = new TextFlow(text);
                        textFlow.setStyle("-fx-background-color: rgb(212,232,242); " +
                                "-fx-background-radius: 20px;"
                                + "-fx-color-label-visible: rgb(239,242,255);");
                        textFlow.setPadding(new Insets(5, 10, 5, 10));
                        hBox.getChildren().add(textFlow);
                        vboxMessages.getChildren().add(hBox);
                    } else {
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.setPadding(new Insets(5, 5, 5, 10));
                        Text text = new Text(STR."replyingTo:\\{visualData.getMessage().replyData().userName()}\n\{visualData.getTime()} \{visualData.getUsername()}:\{visualData.getMessage()}");
                        TextFlow textFlow = new TextFlow(text);
                        textFlow.setStyle("-fx-background-color: rgb(15,125,242); " +
                                "-fx-background-radius: 20px;"
                                + "-fx-color-label-visible: rgb(239,242,255);");
                        textFlow.setPadding(new Insets(5, 10, 5, 10));
                        hBox.getChildren().add(textFlow);
                        vboxMessages.getChildren().add(hBox);
                    }

                } else {
                    if (Objects.equals(visualData.getNickName(0), visualData.getUsername())) {
                        hBox.setAlignment(Pos.CENTER_RIGHT);
                        hBox.setPadding(new Insets(5, 5, 5, 10));
                        Text text = new Text(STR."\{visualData.getTime()} \{visualData.getUsername()}\n:\{visualData.getMessage()}");
                        TextFlow textFlow = new TextFlow(text);
                        textFlow.setStyle("-fx-background-color: rgb(212,232,242); " +
                                "-fx-background-radius: 20px;"
                                + "-fx-color-label-visible: rgb(239,242,255);");
                        textFlow.setPadding(new Insets(5, 10, 5, 10));
                        hBox.getChildren().add(textFlow);
                        vboxMessages.getChildren().add(hBox);
                    } else {
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.setPadding(new Insets(5, 5, 5, 10));
                        Text text = new Text(STR."\{visualData.getTime()} \{visualData.getUsername()}\n:\{visualData.getMessage()}");
                        TextFlow textFlow = new TextFlow(text);
                        textFlow.setStyle("-fx-background-color: rgb(15,125,242); " +
                                "-fx-background-radius: 20px;"
                                + "-fx-color-label-visible: rgb(239,242,255);");
                        textFlow.setPadding(new Insets(5, 10, 5, 10));
                        hBox.getChildren().add(textFlow);
                        vboxMessages.getChildren().add(hBox);
                    }

                }
            }
            if (isFirstPlayerMainUser) {
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
            } else {
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
        });

    }

    public void secondPlayerSiegeClicked() {
        if (isFirstPlayerMainUser == visualData.isFirstPlayerTurn())
            ClientHandler.client.sendCommand("secondPlayerSiegeClicked");
    }

    public void secondPlayerRangedClicked() {
        if (isFirstPlayerMainUser == visualData.isFirstPlayerTurn())
            ClientHandler.client.sendCommand("secondPlayerRangedClicked");
    }


    public void secondPlayerCloseCombatClicked() {
        if (isFirstPlayerMainUser == visualData.isFirstPlayerTurn())
            ClientHandler.client.sendCommand("secondPlayerCloseCombatClicked");
    }

    public void firstPlayerCloseCombatClicked() {
        if (isFirstPlayerMainUser == visualData.isFirstPlayerTurn())
            ClientHandler.client.sendCommand("firstPlayerCloseCombatClicked");
    }

    public void firstPlayerRangedClicked() {
        if (isFirstPlayerMainUser == visualData.isFirstPlayerTurn())
            ClientHandler.client.sendCommand("firstPlayerRangedClicked");
    }

    public void firstPlayerSiegeClicked() {
        if (isFirstPlayerMainUser == visualData.isFirstPlayerTurn())
            ClientHandler.client.sendCommand("firstPlayerSiegeClicked");
    }

    public void closeCombatBoostClicked() {
        if (isFirstPlayerMainUser == visualData.isFirstPlayerTurn())
            ClientHandler.client.sendCommand("0");
    }

    public void rangedBoostClicked() {
        if (isFirstPlayerMainUser == visualData.isFirstPlayerTurn())
            ClientHandler.client.sendCommand("1");
    }

    public void siegeBoostClicked() {
        if (isFirstPlayerMainUser == visualData.isFirstPlayerTurn())
            ClientHandler.client.sendCommand("2");
    }

    public void weatherClicked() {
        if (isFirstPlayerMainUser == visualData.isFirstPlayerTurn())
            ClientHandler.client.sendCommand("weatherClicked");
    }

    public HBox getFirstPlayerDiscard() {
        return firstPlayerDiscard;
    }

    public void LeaderAction() {
        if (isFirstPlayerMainUser == visualData.isFirstPlayerTurn())
            ClientHandler.client.sendCommand("LeaderAction");
        update();
    }

    public void PassRound() {
        if (isFirstPlayerMainUser == visualData.isFirstPlayerTurn())
            ClientHandler.client.sendCommand("PassRound");
    }

    public void MakeDestroyerOfWorldsWindow(boolean isFirstPlayerTurn) {

        tempStage.setResizable(false);
        HBox hBox = new HBox();
        Scene scene = new Scene(hBox);
        ArrayList<Card> selectedCards;
        if (isFirstPlayerTurn) {
            selectedCards = new ArrayList<>(visualData.getCardArrayByArrayName("firstPlayerDeck"));
        } else {
            selectedCards = new ArrayList<>(visualData.getCardArrayByArrayName("secondPlayerDeck"));
        }
        hBox.getChildren().addAll(selectedCards);
        tempStage.setScene(scene);
        tempStage.show();
        InitiateCardEvents(hBox);
    }

    public void MakeCommanderOfRedRidersWindow(boolean isFirstPlayerTurn) {
        tempStage.setHeight(140);
        tempStage.setWidth(800);
        tempStage.setResizable(false);
        HBox hBox = new HBox();
        Scene scene = new Scene(hBox);
        ArrayList<Card> weatherCards = new ArrayList<>();

        if (isFirstPlayerTurn) {
            for (Card card : visualData.getCardArrayByArrayName("firstPlayerDeck")) {
                if (card instanceof SpecialCard) {
                    if (!(Objects.equals(card.getName(), "Commander’s horn") ||
                            Objects.equals(card.getName(), "Scorch") ||
                            Objects.equals(card.getName(), "Mardroeme"))
                    ) {
                        weatherCards.add(card);
                    }

                }
            }
        } else {
            for (Card card : visualData.getCardArrayByArrayName("secondPlayerDeck")) {
                if (card instanceof SpecialCard) {
                    if (!(Objects.equals(card.getName(), "Commander’s horn") ||
                            Objects.equals(card.getName(), "Scorch") ||
                            Objects.equals(card.getName(), "Mardroeme"))
                    ) {
                        weatherCards.add(card);
                    }

                }
            }
        }
        hBox.getChildren().addAll(weatherCards);
        tempStage.setScene(scene);
        tempStage.show();
        InitiateCardEvents(hBox);
    }

    public void MakeMedicWindow(boolean isFirstPlayerTurn) {
        this.getFirstPlayerDiscard().getChildren().clear();
        tempStage.setHeight(140);
        tempStage.setWidth(800);
        tempStage.setResizable(false);
        HBox hBox = new HBox();
        Scene scene = new Scene(hBox);
        if (isFirstPlayerTurn) {
            hBox.getChildren().addAll(visualData.getCardArrayByArrayName("firstPlayerDiscard"));
        } else {
            hBox.getChildren().addAll(visualData.getCardArrayByArrayName("secondPlayerDiscard"));
        }
        tempStage.setScene(scene);
        tempStage.show();
        InitiateCardEvents(hBox);
    }

    public static ArrayList<Card> randomSelectedCards(ArrayList<Card> deck, int numOfRandomCards) {
        ArrayList<Card> randomCards = new ArrayList<>();
        int i = 0;
        int size = deck.size();
        while (i < numOfRandomCards) {
            if (deck.isEmpty() || deck.size() < numOfRandomCards) {
                return randomCards;
            }
            Card tempCard = deck.get(Game.random.nextInt(size));
            if (!randomCards.contains(tempCard)) {
                randomCards.add(tempCard);
                i++;
            }
        }
        return randomCards;
    }

    public void MakeKingOfWildHuntWindow(boolean isFirstPlayerTurn) {
        this.getFirstPlayerDiscard().getChildren().clear();
        tempStage.setHeight(140);
        tempStage.setWidth(800);
        tempStage.setResizable(false);
        HBox hBox = new HBox();
        Scene scene = new Scene(hBox);
        if (isFirstPlayerTurn) {
            for (Card card : visualData.getCardArrayByArrayName("firstPlayerDiscard")) {
                if (!(card instanceof Hero)) {
                    hBox.getChildren().add(card);
                }
            }

        } else {
            for (Card card : visualData.getCardArrayByArrayName("secondPlayerDiscard")) {
                if (!(card instanceof Hero)) {
                    hBox.getChildren().add(card);
                }
            }
        }
        tempStage.setScene(scene);
        tempStage.show();
        InitiateCardEvents(hBox);
    }

    public void MakeHisImperialMajestyWindow(boolean isFirstPlayerTurn) {
        tempStage.setHeight(140);
        tempStage.setWidth(800);
        tempStage.setResizable(false);
        HBox hBox = new HBox();
        Scene scene = new Scene(hBox);
        if (isFirstPlayerTurn) {

            hBox.getChildren().addAll(randomSelectedCards(visualData.getCardArrayByArrayName("firstPlayerInPlay"), 3));
        } else {
            hBox.getChildren().addAll(randomSelectedCards(visualData.getCardArrayByArrayName("secondPlayerInPlay"), 3));
        }
        tempStage.setScene(scene);
        tempStage.show();
    }

    private void emptyMessage() {
        messageInput.setText("");
    }

    public void SendReaction() {
        if (!spamThread.isAlive()) {
            if (Messages.getValue() != null) {
                try {
                    spamThread.start();
                } catch (Exception q) {
                    spamThread = new Thread(() -> {
                        try {

                            Thread.sleep(SPAM_FILTER_TIME);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    spamThread.start();
                }

                ClientHandler.client.sendCommand(STR."message \{Messages.getValue()}");

            }
        }

    }

    public void SendMessage() {
        if (!spamThread2.isAlive()) {
            if (!messageInput.getText().isEmpty()) {
                try {
                    spamThread2.start();
                } catch (Exception q) {
                    spamThread2 = new Thread(() -> {
                        try {

                            Thread.sleep(SPAM_FILTER_TIME);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    spamThread2.start();

                }
                if (isReply.isSelected()) {
                    ClientHandler.client.sendCommand("chat true" + messageInput.getText());
                } else {
                    ClientHandler.client.sendCommand("chat false" + messageInput.getText());
                }
                messageInput.setText("");
            }
        }
        update();
    }
}