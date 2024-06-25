package models.actions;

import enums.Origin;
import enums.cards.SpecialCardInfo;
import models.Game;
import models.MatchTable;
import models.cards.*;

import java.util.ArrayList;

public abstract class LeaderActions {
    private static MatchTable matchTable;

    private static void theSiegeMaster() {
        if (matchTable.isFirstPlayerTurn()) {
            if (matchTable.getFirstPlayerDeckCards().contains(SpecialCardInfo.getSpecialCardByName("impenetrable fog"))) {
                matchTable.addToSpellCards(new CardWrapper(SpecialCardInfo.getSpecialCardByName("impenetrable fog"),
                        Origin.FIRSTPLAYER_DECK));
            }
        } else {
            if (matchTable.getSecondPlayerDeckCards().contains(SpecialCardInfo.getSpecialCardByName("impenetrable fog"))) {
                matchTable.addToSpellCards(new CardWrapper(SpecialCardInfo.getSpecialCardByName("impenetrable fog"),
                        Origin.SECONDPLAYER_DECK));
            }
        }

    }

    private static void theSteelForged() {
        matchTable.getSpellCards().clear();
    }

    private static void kingOfTemeria() {
        if (matchTable.isFirstPlayerTurn()) {
            if (matchTable.getFirstPlayerSiegeBoostCard() == null) {
                matchTable.placeBoostCard(SpecialCardInfo.getSpecialCardByName("Commander's Horn"), 0, 2);
            }
        } else {
            if (matchTable.getSecondPlayerSiegeBoostCard() == null) {
                matchTable.placeBoostCard(SpecialCardInfo.getSpecialCardByName("Commander's Horn"), 1, 2);
            }
        }
    }

    private static void lordCommanderOfTheNorth() {
        if (matchTable.isFirstPlayerTurn()) {
            if (matchTable.getSecondPlayerPoints().get(2) >= 10) {
                UnitCard cardToDelete = null;
                for (Card card : matchTable.getSecondPlayerSiegeRow()) {
                    if (cardToDelete == null) cardToDelete = (UnitCard) card;
                    if (((UnitCard) card).getShowingPower() > cardToDelete.getShowingPower()) {
                        cardToDelete = (UnitCard) card;
                    }
                }
                matchTable.getSecondPlayerSiegeRow().remove(cardToDelete);
            }
        } else {
            if (matchTable.getFirstPlayerPoints().get(2) >= 10) {
                UnitCard cardToDelete = null;
                for (Card card : matchTable.getFirstPlayerSiegeRow()) {
                    if (cardToDelete == null) cardToDelete = (UnitCard) card;
                    if (((UnitCard) card).getShowingPower() > cardToDelete.getShowingPower()) {
                        cardToDelete = (UnitCard) card;
                    }
                }
                matchTable.getFirstPlayerSiegeRow().remove(cardToDelete);
            }
        }
    }

    private static void sonOfMedell() {
        if (matchTable.isFirstPlayerTurn()) {
            if (matchTable.getSecondPlayerPoints().get(1) >= 10) {
                UnitCard cardToDelete = null;
                for (Card card : matchTable.getSecondPlayerRangedRow()) {
                    if (cardToDelete == null) cardToDelete = (UnitCard) card;
                    if (((UnitCard) card).getShowingPower() > cardToDelete.getShowingPower()) {
                        cardToDelete = (UnitCard) card;
                    }
                }
                matchTable.getSecondPlayerRangedRow().remove(cardToDelete);
            }
        } else {
            if (matchTable.getFirstPlayerPoints().get(1) >= 10) {
                UnitCard cardToDelete = null;
                for (Card card : matchTable.getFirstPlayerRangedRow()) {
                    if (cardToDelete == null) cardToDelete = (UnitCard) card;
                    if (((UnitCard) card).getShowingPower() > cardToDelete.getShowingPower()) {
                        cardToDelete = (UnitCard) card;
                    }
                }
                matchTable.getFirstPlayerRangedRow().remove(cardToDelete);
            }
        }
    }

    private static void theWhiteFlame() {
        if (matchTable.isFirstPlayerTurn()) {
            if (matchTable.getFirstPlayerDeckCards().contains(SpecialCardInfo.getSpecialCardByName("Torrential Rain"))) {
                matchTable.addToSpellCards(new CardWrapper(SpecialCardInfo.getSpecialCardByName("Torrential Rain"),
                        Origin.FIRSTPLAYER_DECK));
            }
        } else {
            if (matchTable.getSecondPlayerDeckCards().contains(SpecialCardInfo.getSpecialCardByName("Torrential Rain"))) {
                matchTable.addToSpellCards(new CardWrapper(SpecialCardInfo.getSpecialCardByName("Torrential Rain"),
                        Origin.SECONDPLAYER_DECK));
            }
        }
    }

    private static void hisImperialMajesty() {
        //todo
        //what za hellllllllllllllllllllllllllllllllllllll
        // oh mah goh
    }

    private static void emperorOfNilfgaard() {
        //todo
        //kys
    }

    private static void theRelentless() {
        if (matchTable.isFirstPlayerTurn()) {
            if (!isArrayOnlyHero(matchTable.getSecondPlayerDeadCards())) {
                ArrayList<Card> herolessArray = getHerolessArray(matchTable.getSecondPlayerDeadCards());
                int randomNumber = Game.random.nextInt(0, herolessArray.size());
                matchTable.addToInPlayCards(0, new CardWrapper(herolessArray.get(randomNumber),
                        Origin.SECONDPLAYER_DEAD));
            }
        } else {
            if (!isArrayOnlyHero(matchTable.getFirstPlayerDeadCards())) {
                ArrayList<Card> herolessArray = getHerolessArray(matchTable.getFirstPlayerDeadCards());
                int randomNumber = Game.random.nextInt(0, herolessArray.size());
                matchTable.addToInPlayCards(1, new CardWrapper(herolessArray.get(randomNumber),
                        Origin.FIRSTPLATER_DEAD));
            }
        }
    }

    private static void invaderOfTheNorth() {
        int randomNumber = Game.random.nextInt(0, matchTable.getSecondPlayerDeadCards().size());
        matchTable.addToInPlayCards(1, new CardWrapper(matchTable.getSecondPlayerDeadCards().get(randomNumber),
                Origin.SECONDPLAYER_DEAD));
        randomNumber = Game.random.nextInt(0, matchTable.getFirstPlayerDeadCards().size());
        matchTable.addToInPlayCards(0, new CardWrapper(matchTable.getFirstPlayerDeadCards().get(randomNumber),
                Origin.FIRSTPLATER_DEAD));
    }

    private static void bringerOfDeath() {
        if (matchTable.isFirstPlayerTurn()) {
            if (matchTable.getFirstPlayerCloseCombatBoostCard() == null) {
                matchTable.placeBoostCard(SpecialCardInfo.getSpecialCardByName("Commander's Horn"), 0, 0);
            }
        } else {
            if (matchTable.getSecondPlayerCloseCombatBoostCard() == null) {
                matchTable.placeBoostCard(SpecialCardInfo.getSpecialCardByName("Commander's Horn"), 1, 0);
            }
        }
    }

    private static void kingOfTheWildHunt() {
        if (matchTable.isFirstPlayerTurn()) {
            if (!isArrayOnlyHero(matchTable.getFirstPlayerDeadCards())) {
                ArrayList<Card> herolessArray = getHerolessArray(matchTable.getFirstPlayerDeadCards());
                int randomNumber = Game.random.nextInt(0, herolessArray.size());
                matchTable.addToInPlayCards(0, new CardWrapper(herolessArray.get(randomNumber),
                        Origin.FIRSTPLATER_DEAD));
            }
        } else {
            if (!isArrayOnlyHero(matchTable.getSecondPlayerDeadCards())) {
                ArrayList<Card> herolessArray = getHerolessArray(matchTable.getSecondPlayerDeadCards());
                int randomNumber = Game.random.nextInt(0, herolessArray.size());
                matchTable.addToInPlayCards(1, new CardWrapper(herolessArray.get(randomNumber),
                        Origin.SECONDPLAYER_DEAD));
            }
        }
    }

    private static void destroyerOfWorlds() {
        //todo

    }

    private static void commanderOfTheRedRiders() {
        //todo
    }

    private static void theTreacherous() {

    }

    private static void queenOfDolBlathanna() {

    }

    private static void theBeautiful() {

    }

    private static void daisyOfTheValley() {

    }

    private static void purebloodElf() {

    }

    private static void hopeOfTheAenSeidhe() {

    }

    private static void crachAnCraite() {

    }

    private static void kingBran() {
    }

    private static boolean isArrayOnlyHero(ArrayList<Card> cards) {
        for (Card card : cards) {
            if (!(card instanceof Hero)) return false;
        }
        return true;
    }
    private static ArrayList<Card> getHerolessArray(ArrayList<Card> cards){
        ArrayList<Card> returnArray = new ArrayList<>();
        for (Card card : cards){
            if (!(card instanceof Hero)) returnArray.add(card);
        }
        return returnArray;
    }

    public static void doActionByName(String name, MatchTable currentMatchTable) {
        matchTable = currentMatchTable;
    }

}
