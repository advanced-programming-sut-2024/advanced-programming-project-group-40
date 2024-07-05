package views.ViewController;

import Server.Models.GameBoardVisualData;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import models.cards.Card;

import views.Main;
import views.PlayMenu;

import java.net.URL;

import java.util.ResourceBundle;

public class SpectatorViewController extends PlayMenu implements Initializable {
    private static final int SPAM_FILTER_TIME = 5000;

    Thread spamThread = new Thread(() -> {
        try {

            Thread.sleep(SPAM_FILTER_TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    });
    public StackPane firstPlayerFactionImage;
    public HBox firstplayerLeaderImage;
    public HBox SecondPlayerHand;
    public ScrollPane chat;
    public TextField textInput;
    public VBox vboxMessages;
    private GameBoardVisualData visualData;
    private final Stage tempStage = new Stage();

    public void setVisualData(String Json) {
        GameBoardVisualData temp;
        temp = GameBoardVisualData.deSerialize(Json);
        this.visualData = temp;
        update();
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
        URL url = Main.class.getResource("/FXML/SpectatorBoard.fxml");
        assert url != null;
        stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(url));
        stage.setScene(scene);
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //update();
    }

    public void update() {
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
        if (!(visualData.getCardArrayByArrayName("secondPlayerInPlay").isEmpty() &&
                SecondPlayerHand.getChildren().isEmpty())) {
            SecondPlayerHand.getChildren().clear();
            SecondPlayerHand.getChildren().addAll(visualData.getCardArrayByArrayName("secondPlayerInPlay"));
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


        //spell
        if (!(visualData.getCardArrayByArrayName("weather").isEmpty() &&
                spellCards.getChildren().isEmpty())) {
            spellCards.getChildren().clear();
            spellCards.getChildren().addAll(visualData.getCardArrayByArrayName("weather"));
        }
        if (visualData.isMatchFinished()) {
            System.out.println("sock these nuts");
        }
    }


    public void sendMassege(MouseEvent keyEvent) {
        if (!spamThread.isAlive()) {
            if (!textInput.getText().isEmpty()) {
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
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_RIGHT);
                hBox.setPadding(new Insets(5, 5, 5, 10));
                Text text = new Text(textInput.getText());
                TextFlow textFlow = new TextFlow(text);
                textFlow.setStyle("-fx-background-color: rgb(15,125,242); " +
                        "-fx-background-radius: 20px;"
                        + "-fx-color-label-visible: rgb(239,242,255);");
                textFlow.setPadding(new Insets(5, 10, 5, 10));
                hBox.getChildren().add(textFlow);
                vboxMessages.getChildren().add(hBox);
                textInput.setText("");
            }
        }
    }
}
