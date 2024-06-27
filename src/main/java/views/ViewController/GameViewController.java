package views.ViewController;

import enums.cards.UnitCardInfo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import models.MatchTable;
import models.UserInputHandler.ClickCommand;
import models.cards.Card;
import models.cards.UnitCard;
import views.GameView;
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

    public static void setMatchTable(MatchTable matchTable) {
        GameViewController.matchTable = matchTable;
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
        Scene scene = new Scene(FXMLLoader.load(url));
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*secondPlayerName.setText(STR."\{matchTable.getSecondPlayer().getNickname()}");
        firstPlayerName.setText(STR."\{matchTable.getFirstPlayer().getNickname()}");
        firstPlayerFaction.setText(STR."\{matchTable.getFirstPlayer().getFaction()}");
        secondPlayerFaction.setText(STR."\{matchTable.getSecondPlayer().getFaction()}");*/
        //todo
        //initialize faction and leader images for each player
        UnitCard card1 = new UnitCard(UnitCardInfo.SPONGE_BOB);
        UnitCard card2 = new UnitCard(UnitCardInfo.TEST2);
        Hand.getChildren().add(card1);
        firstPlayerCloseCombat.getChildren().add(card2);
        InitiateCardEvents();

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
       /* firstplayerdeckamount.setText(STR."\{matchTable.getFirstPlayerDeckCards().size()}");
        secondplayerdeckamount.setText(STR."\{matchTable.getSecondPlayerDeckCards().size()}");
        firstPlayerRemainingCards.setText(STR."\{matchTable.getFirstPlayerInPlayCards().size()}");
        secondPlayerRemainingCards.setText(STR."\{matchTable.getSecondPlayerInPlayCards().size()}");*/
    }

}