package controllers.MenuController;

import controllers.Controller;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Game;
import models.Result;
import models.cards.Card;
import views.GameView;

import java.util.ArrayList;
import java.util.Objects;

public class PreGameMenuController extends Controller {
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

    public static Result startGame() {
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


    public static ArrayList<ImageView> getOtherFactions(String userFactionName) {
        ArrayList<ImageView> cards = new ArrayList<ImageView>();
        for (String cardName : Game.getAllFactions()) {
            if (!cardName.equals(userFactionName)) {
                cards.add(new ImageView(new Image(Objects.requireNonNull(GameView.class.getResource("/Assets/Factions/faction_" + cardName + ".png")).toExternalForm())));
            }
        }
        return cards;
    }

}
