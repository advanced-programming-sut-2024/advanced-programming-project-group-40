package controllers.MenuController;

import controllers.Controller;
import models.Result;
import models.User;
import models.cards.Card;

public class GameMenuController extends Controller {
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
    public static Result passRound(){
        return null;
    }
    public static boolean isRowValidForCard(Card card, String rowNumber) {
        return true;
    }

}
