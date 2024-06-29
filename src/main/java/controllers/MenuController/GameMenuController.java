package controllers.MenuController;

import controllers.Controller;
import enums.Ability;
import enums.Origin;
import javafx.geometry.Orientation;
import models.MatchTable;
import models.Result;
import models.User;
import models.cards.*;
import views.ViewController.GameViewController;

import java.util.Collections;
import java.util.Objects;

public class GameMenuController extends Controller {
    private static MatchTable matchTable;

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

    public static void setSelectedCard(Card selectedCard1, GameViewController gameViewController) {
        if (isSelectable(selectedCard1)) {
            GameMenuController.selectedCard = selectedCard1;
            gameViewController.unHighlight();
            Origin origin = GetDestination();
            gameViewController.highLightRow(origin);
            System.out.println("selected");
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

    public static void ClickedOnRow(Origin origin) {
        Origin destination = GetDestination();
        if (selectedCard != null) {
            if (origin.isSubOrigin(destination)) {
                if (selectedCard.isSpy()) {
                    matchTable.placeCard(new CardWrapper(selectedCard, origin), 1, getRowID(origin));
                } else {
                    matchTable.placeCard(new CardWrapper(selectedCard, origin), 0, getRowID(origin));
                }
                selectedCard = null;
            }
        }
    }

    public static void ClickedOnBoost(int rowID) {
        if (selectedCard instanceof SpecialCard ){
                matchTable.placeBoostCard(new CardWrapper(selectedCard,Origin.FIRSTPLAYER_INPLAY),0,rowID);
                selectedCard = null;
        }
    }

    public static void clickedOnWeather() {
        if (selectedCard instanceof SpecialCard ){
            matchTable.addToSpellCards(selectedCard);
            selectedCard=null;
        }

    }
}