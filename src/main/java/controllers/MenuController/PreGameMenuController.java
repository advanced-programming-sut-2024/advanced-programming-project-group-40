package controllers.MenuController;

import controllers.Controller;
import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.ChangeInfoMenuMessages;
import enums.AlertInfo.messages.PreGameMenuMessages;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.AlertMaker;
import models.Game;
import models.Result;
import models.User;
import models.cards.Card;
import views.GameView;
import views.ViewController.PreGameViewController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class PreGameMenuController extends UserInfoController {
    public static Result createGame(String opponentUsername) {
        return null;
    }

    public static Result showFaction() {
        return null;
    }

    public static Result selectFaction(String faction) {
        return null;
    }

    public static Result showCards() {
        return null;
    }

    public static Result showDeck() {
        return null;
    }

    public static Result showCurrentUserInfo() {
        return null;
    }

    public static Result saveDeckByName(String name) {
        return null;
    }

    public static Result saveDeckByAddress(String address) {
        return null;
    }

    public static Result loadDeckByName(String name) {
        return null;
    }

    public static Result loadDeckByAddress(String address) {
        return null;
    }

    public static Result showLeaders() {
        return null;
    }

    public static Result selectLeader(String leaderName) {
        return null;
    }

    public static Result addToDeck(String name, int count) {
        return null;
    }

    public static Result deleteFromDeck(String name, int count) {
        return null;
    }

    public static Result changeTurn() {
        return null;
    }


    public static ArrayList<Card> getCardsByFaction() {
        return null;
    }

    public static int heroCounter(ArrayList<Card> Deck) {
        return 0;
    }

    public static int specialCardCounter(ArrayList<Card> Deck) {
        return 0;
    }

    public static int soldiersCounter(ArrayList<Card> Deck) {
        return 0;
    }

    public static int allCardsPower(ArrayList<Card> Deck) {
        return 0;
    }

    public static AlertMaker checkCompetitorData(String username) {
        if (isUsernameUnique(username))
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.PRE_GAME.toString(), PreGameMenuMessages.INVALID_COMPETITOR_USERNAME.toString());
        User user = Game.getUserByName(username);
        if (user.getNumberOfUnitCards() < 22)
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.PRE_GAME.toString(), PreGameMenuMessages.NOT_ENOUGH_CARDS.toString());
        if (user.getNumberOfSpecialCards() > 10)
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.PRE_GAME.toString(), PreGameMenuMessages.TOO_MUCH_SPECIAL_CARDS.toString());
        return new AlertMaker(Alert.AlertType.INFORMATION,AlertHeader.PRE_GAME.toString(),PreGameMenuMessages.GAME_STARTED.toString());
    }

}
