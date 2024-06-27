package controllers.MenuController;

import controllers.Controller;
import enums.Ability;
import enums.Origin;
import javafx.geometry.Orientation;
import models.MatchTable;
import models.Result;
import models.User;
import models.cards.Card;
import models.cards.Hero;
import models.cards.SpecialCard;
import models.cards.UnitCard;
import views.ViewController.GameViewController;

import java.util.Collections;
import java.util.Objects;

public class GameMenuController extends Controller {
    private static Card selectedCard;

    public static Card getSelectedCard() {
        return selectedCard;
    }

    public static void setSelectedCard(Card selectedCard1,GameViewController gameViewController) {
        if (isSelectable(selectedCard1)) {
            GameMenuController.selectedCard = selectedCard1;
            gameViewController.unHighlight();
            if (selectedCard instanceof UnitCard unitCard){
                if (unitCard.getAbility() == Ability.SPY){
                    switch (unitCard.getUnit()){
                        case AGILE -> {
                            gameViewController.highLightRow(Origin.SECONDPLAYER_RANGED);
                            gameViewController.highLightRow(Origin.SECONDPLAYER_CLOSECOMBAT);
                        }
                        case CLOSE_COMBAT -> {
                            gameViewController.highLightRow(Origin.SECONDPLAYER_CLOSECOMBAT);
                        }
                        case SIEGE -> {
                            gameViewController.highLightRow(Origin.SECONDPLAYER_SIEGE);

                        }
                        case RANGED -> {
                            gameViewController.highLightRow(Origin.SECONDPLAYER_RANGED);
                        }
                    }
                }else {
                    switch (unitCard.getUnit()){
                        case AGILE -> {
                            gameViewController.highLightRow(Origin.FIRSTPLAYER_CLOSECOMBAT);
                            gameViewController.highLightRow(Origin.FIRSTPLAYER_RANGED);

                        }
                        case CLOSE_COMBAT -> {
                            gameViewController.highLightRow(Origin.FIRSTPLAYER_CLOSECOMBAT);
                        }
                        case SIEGE -> {
                            gameViewController.highLightRow(Origin.FIRSTPLAYER_SIEGE);

                        }
                        case RANGED -> {
                            gameViewController.highLightRow(Origin.FIRSTPLAYER_RANGED);

                        }
                    }
                }

            }
            if (selectedCard instanceof Hero hero){
                if (hero.getAbility() == Ability.SPY){
                    switch (hero.getUnit()){
                        case AGILE -> {
                            gameViewController.highLightRow(Origin.SECONDPLAYER_RANGED);
                            gameViewController.highLightRow(Origin.SECONDPLAYER_CLOSECOMBAT);
                        }
                        case CLOSE_COMBAT -> {
                            gameViewController.highLightRow(Origin.SECONDPLAYER_CLOSECOMBAT);
                        }
                        case SIEGE -> {
                            gameViewController.highLightRow(Origin.SECONDPLAYER_SIEGE);

                        }
                        case RANGED -> {
                            gameViewController.highLightRow(Origin.SECONDPLAYER_RANGED);
                        }
                    }
                }else {
                    switch (hero.getUnit()){
                        case AGILE -> {
                            gameViewController.highLightRow(Origin.FIRSTPLAYER_CLOSECOMBAT);
                            gameViewController.highLightRow(Origin.FIRSTPLAYER_RANGED);

                        }
                        case CLOSE_COMBAT -> {
                            gameViewController.highLightRow(Origin.FIRSTPLAYER_CLOSECOMBAT);
                        }
                        case SIEGE -> {
                            gameViewController.highLightRow(Origin.FIRSTPLAYER_SIEGE);

                        }
                        case RANGED -> {
                            gameViewController.highLightRow(Origin.FIRSTPLAYER_RANGED);

                        }
                    }
                }
            }
            if (selectedCard instanceof SpecialCard specialCard){
                if (Objects.equals(specialCard.getName(), "Commander's horn")){
                    gameViewController.highLightRow(Origin.FIRSTPLAYER_CLOSECOMBAT);
                    gameViewController.highLightRow(Origin.FIRSTPLAYER_RANGED);
                    gameViewController.highLightRow(Origin.FIRSTPLAYER_SIEGE);
                }
                else {
                    gameViewController.highLightRow(Origin.WEATHER);

                }
            }
        }
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

}
