package controllers.MenuController;

import controllers.Controller;
import enums.Ability;
import enums.Origin;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.MatchTable;
import models.Result;
import models.cards.*;
import views.ViewController.GameViewController;

import java.util.Objects;

public class GameMenuController extends Controller {
    private static MatchTable matchTable;
    private static Stage tempStage;

    public static MatchTable getMatchTable() {
        return matchTable;
    }

    public static void setMatchTable(MatchTable matchTable) {
        GameMenuController.matchTable = matchTable;
    }

    private static Card selectedCard;

    public static Card getSelectedCard() {
        return selectedCard;
    }

    public static void ClickedOnCard(Card selectedCard1, GameViewController gameViewController) {
        if (Objects.equals(selectedCard1.getParent().getId(), "tempRow")) {
            matchTable.doMedic(new CardWrapper(selectedCard1, Origin.FIRSTPLATER_DEAD));
            tempStage.close();
            gameViewController.update();
        } else {
            if (isSelectable(selectedCard1)) {
                selectedCard = selectedCard1;
                gameViewController.unHighlight();
                Origin origin = GetDestination();
                gameViewController.highLightRow(origin);
            } else {
                if (selectedCard != null) {
                    if (Objects.equals(selectedCard.getName(), "Decoy")) {
                        matchTable.doDecoy(new CardWrapper(selectedCard, getCardOrigin(selectedCard)),
                                new CardWrapper(selectedCard1, getCardOrigin(selectedCard1)));
                        selectedCard = null;
                    }
                }
            }
        }
    }

    private static Origin GetDestination() {
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


    private static boolean isSelectable(Card selectedCard) {
        return Objects.equals(selectedCard.getParent().getId(), "Hand");
    }


    public static Result intiateDeck(MatchTable matchTable) {
        matchTable.getFirstPlayerDeckCards().addAll(matchTable.getFirstPlayer().getDeckCards());
        matchTable.getSecondPlayerDeckCards().addAll(matchTable.getSecondPlayer().getDeckCards());
        matchTable.initilizeTable();

        return null;
    }

    public static Result vetoCards(String number) {
        return null;
    }

    public static Result inHandDeck(String option) {
        return null;
    }

    public static Result remainingCards() {
        return null;
    }

    public static Result outOfPlayCards() {
        return null;
    }

    public static Result cardsInRow(String rowNumber) {
        return null;
    }

    public static Result spellsInPlay() {
        return null;
    }

    public static Result placeCard(String cardNumber, String rowNumber) {
        return null;
    }

    public static Result takeBackDead(String cardNumber) {
        return null;
    }

    public static Result showCommander() {
        return null;
    }

    public static Result commanderPowerPlay() {
        return null;
    }

    public static Result showPlayersInfo() {
        return null;
    }

    public static Result showPlayerLives() {
        return null;
    }

    public static Result showNumberOfCardsInHand() {
        return null;
    }

    public static Result showTurnInfo() {
        return null;
    }

    public static Result showTotalScore() {
        return null;
    }

    public static Result showRowScore(String rowNumber) {
        return null;
    }

    public static Result passRound() {
        return null;
    }

    public static boolean isRowValidForCard(Card card, String rowNumber) {
        return true;
    }


    private static int getRowID(Origin origin) {
        switch (origin) {
            case FIRSTPLAYER_CLOSECOMBAT, SECONDPLAYER_CLOSECOMBAT -> {
                return 0;
            }
            case FIRSTPLAYER_RANGED, SECONDPLAYER_RANGED -> {
                return 1;
            }
            case FIRSTPLAYER_SIEGE, SECONDPLAYER_SIEGE -> {
                return 2;
            }
        }
        return -1;
    }

    private static Origin getCardOrigin(Card card) {
        Origin origin;
        switch (card.getParent().getId()) {
            case "firstPlayerCloseCombat" -> {
                origin = Origin.FIRSTPLAYER_CLOSECOMBAT;
            }
            case "firstPlayerRanged" -> {
                origin = Origin.FIRSTPLAYER_RANGED;
            }
            case "firstPlayerSiege" -> {
                origin = Origin.FIRSTPLAYER_SIEGE;
            }
            case "secondPlayerCloseCombat" -> {
                origin = Origin.SECONDPLAYER_CLOSECOMBAT;
            }
            case "secondPlayerRanged" -> {
                origin = Origin.SECONDPLAYER_RANGED;
            }
            case "secondPlayerSiege" -> {
                origin = Origin.SECONDPLAYER_SIEGE;
            }
            case "spellCards" -> {
                origin = Origin.WEATHER;
            }
            case "firstPlayerDiscard" -> {
                origin = Origin.FIRSTPLATER_DEAD;
            }
            case "secondPlayerDiscard" -> {
                origin = Origin.SECONDPLAYER_DEAD;
            }
            case "Hand" -> {
                origin = Origin.FIRSTPLAYER_INPLAY;
            }
            default -> {
                origin = Origin.NULL;
            }
        }
        return origin;
    }

    public static void ClickedOnRow(Origin origin, GameViewController gameViewController) {
        Origin destination = GetDestination();
        if (selectedCard != null) {
            if (origin.isSubOrigin(destination)) {
                boolean isMedic = false;
                if (selectedCard instanceof UnitCard unitCard) {
                    if (unitCard.getAbility() == Ability.MEDIC) {
                        isMedic = true;
                    }
                } else if (selectedCard instanceof Hero hero) {
                    if (hero.getAbility() == Ability.MEDIC) {
                        isMedic = true;
                    }
                }
                matchTable.placeCard(new CardWrapper(selectedCard, Origin.FIRSTPLAYER_INPLAY), 0, getRowID(origin));
                gameViewController.update();
                if (isMedic) {
                    gameViewController.getFirstPlayerDiscard().getChildren().clear();
                    tempStage = new Stage();
                    tempStage.setHeight(140);
                    tempStage.setWidth(800);
                    tempStage.setResizable(false);
                    HBox hBox = new HBox();
                    Scene scene = new Scene(hBox);
                    hBox.getChildren().addAll(matchTable.getFirstPlayerDeadCards());
                    hBox.setId("tempRow");
                    tempStage.setScene(scene);
                    tempStage.show();
                }
                selectedCard = null;
            }
        }

    }

    public static void ClickedOnBoost(int rowID) {
        if (selectedCard instanceof SpecialCard) {
            matchTable.placeBoostCard(new CardWrapper(selectedCard, Origin.FIRSTPLAYER_INPLAY), 0, rowID);
            selectedCard = null;
        }
    }

    public static void clickedOnWeather() {
        if (selectedCard instanceof SpecialCard &&
                !Objects.equals(selectedCard.getName(), "Commander’s horn")) {
            matchTable.addToSpellCards(new CardWrapper(selectedCard, Origin.FIRSTPLAYER_INPLAY));

            selectedCard = null;
        }
    }
}

