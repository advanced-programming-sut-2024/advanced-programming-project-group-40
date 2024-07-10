package models.actions;

import controllers.MenuController.GameMenuController;
import enums.Origin;
import enums.Unit;
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
                matchTable.addToSpellCards(new CardWrapper(SpecialCardInfo.getSpecialCardByName("impenetrable fog"),Origin.NULL));
            }
        } else {
            if (matchTable.getSecondPlayerDeckCards().contains(SpecialCardInfo.getSpecialCardByName("impenetrable fog"))) {
                matchTable.addToSpellCards(new CardWrapper(SpecialCardInfo.getSpecialCardByName("impenetrable fog"),Origin.NULL));
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
            if (matchTable.getSecondPlayerRowPoints().get(2) >= 10) {
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
            if (matchTable.getFirstPlayerRowPoints().get(2) >= 10) {
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
            if (matchTable.getSecondPlayerRowPoints().get(1) >= 10) {
                removeStrongestRangedPlayer1();
            }
        } else {
            if (matchTable.getFirstPlayerRowPoints().get(1) >= 10) {
                removeStrongestRangedPlayer2();
            }
        }
    }

    private static void theWhiteFlame() {
        if (matchTable.isFirstPlayerTurn()) {
            if (matchTable.getFirstPlayerDeckCards().contains(SpecialCardInfo.getSpecialCardByName("Torrential Rain"))) {
                matchTable.addToSpellCards(new CardWrapper(SpecialCardInfo.getSpecialCardByName("Torrential Rain"),Origin.NULL));
            }
        } else {
            if (matchTable.getSecondPlayerDeckCards().contains(SpecialCardInfo.getSpecialCardByName("Torrential Rain"))) {
                matchTable.addToSpellCards(new CardWrapper(SpecialCardInfo.getSpecialCardByName("Torrential Rain"),Origin.NULL));
            }
        }
    }

    private static void hisImperialMajesty() {
        matchTable.getGameMenuController().MakeHisImperialMajestyWindow();
    }

    private static void emperorOfNilfgaard() {
        matchTable.setSecondPlayerLeaderUsed(true);
        matchTable.getLeaderEffects().setKingBran(false);
        matchTable.getLeaderEffects().setSpyDoublePower(false);
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
        matchTable.getGameMenuController().MakeKingOfWildHuntWindow();
    }

    private static void destroyerOfWorlds() {
        if (matchTable.isFirstPlayerTurn()) {
            ArrayList<Card> cardsToKill = new ArrayList<>(
                    MatchTable.randomSelectedCards(matchTable.getFirstPlayerInPlayCards(), 2)
            );
            for (Card card : cardsToKill) {
                matchTable.addToDeadCards(0, new CardWrapper(card, Origin.FIRSTPLAYER_INPLAY));
            }
        } else {
            ArrayList<Card> cardsToKill = new ArrayList<>(
                    MatchTable.randomSelectedCards(matchTable.getSecondPlayerInPlayCards(), 2)
            );
            for (Card card : cardsToKill) {
                matchTable.addToDeadCards(1, new CardWrapper(card, Origin.SECONDPLAYER_INPLAY));
            }
        }
        matchTable.getGameMenuController().MakeDestroyerOfWorldsWindow();

    }

    private static void commanderOfTheRedRiders() {
        matchTable.getGameMenuController().MakeCommanderOfRedRidersWindow();
    }

    private static void theTreacherous() {
        matchTable.getLeaderEffects().setSpyDoublePower(true);
    }

    private static void queenOfDolBlathanna() {
        if (matchTable.isFirstPlayerTurn()) {
            if (matchTable.getSecondPlayerRowPoints().getFirst() >= 10) {
                removeStrongestRangedPlayer1();
            }
        } else {
            if (matchTable.getFirstPlayerRowPoints().getFirst() >= 10) {
                removeStrongestRangedPlayer2();
            }
        }
    }


    private static void theBeautiful() {
        if (matchTable.isFirstPlayerTurn()) {
            if (matchTable.getFirstPlayerRangedBoostCard() == null) {
                matchTable.placeBoostCard(SpecialCardInfo.getSpecialCardByName("Commander's Horn"), 0, 1);
            }
        } else {
            if (matchTable.getSecondPlayerRangedBoostCard() == null) {
                matchTable.placeBoostCard(SpecialCardInfo.getSpecialCardByName("Commander's Horn"), 1, 1);
            }
        }
    }

    private static void daisyOfTheValley() {
        //done
    }

    private static void purebloodElf() {
        if (matchTable.isFirstPlayerTurn()) {
            if (matchTable.getFirstPlayerDeckCards().contains(SpecialCardInfo.getSpecialCardByName("Biting Frost"))) {
                matchTable.addToSpellCards(new CardWrapper(SpecialCardInfo.getSpecialCardByName("Biting Frost"),Origin.NULL));

            }
        } else {
            if (matchTable.getSecondPlayerDeckCards().contains(SpecialCardInfo.getSpecialCardByName("Biting Frost"))) {
                matchTable.addToSpellCards(new CardWrapper(SpecialCardInfo.getSpecialCardByName("Biting Frost"),Origin.NULL));
            }
        }
    }

    private static void hopeOfTheAenSeidhe() {
        ArrayList<Card> agileCardsInCC = new ArrayList<>();
        boolean isCCUnderWeather = false;
        boolean isRUnderWeather = false;
        boolean isCCUnderBoost = false;
        boolean isRUnderBoost = false;
        if (matchTable.getSpellCards().contains(SpecialCardInfo.getSpecialCardByName("Biting Frost"))) {
            isCCUnderWeather = true;
        }
        if (matchTable.getSpellCards().contains(SpecialCardInfo.getSpecialCardByName("Impenetrable fog"))) {
            isRUnderWeather = true;
        }
        if (matchTable.isFirstPlayerTurn()) {

            if (matchTable.getFirstPlayerCloseCombatBoostCard() != null) {
                isCCUnderBoost = true;
            }
            if (matchTable.getFirstPlayerRangedBoostCard() != null) {
                isRUnderBoost = true;
            }


            ArrayList<Card> agileCardsInR = new ArrayList<>();
            for (Card card : matchTable.getFirstPlayerCloseCombatRow()) {
                UnitCard unitCard = (UnitCard) card;
                if (unitCard.getUnit() == Unit.AGILE) agileCardsInCC.add(card);
            }
            for (Card card : matchTable.getFirstPlayerRangedRow()) {
                UnitCard unitCard = (UnitCard) card;
                if (unitCard.getUnit() == Unit.AGILE) agileCardsInR.add(card);
            }
            ArrayList<Card> tempCC = new ArrayList<>(matchTable.getFirstPlayerCloseCombatRow());
            tempCC.addAll(agileCardsInR);
            ArrayList<Card> tempR = new ArrayList<>(matchTable.getFirstPlayerRangedRow());
            tempR.addAll(agileCardsInCC);
            if (MatchTable.getRowPower(matchTable.getLeaderEffects()
                    , isCCUnderWeather
                    , isCCUnderBoost,
                    tempCC
            ) >
                    MatchTable.getRowPower(matchTable.getLeaderEffects()
                            , isRUnderWeather
                            , isRUnderBoost,
                            tempR
                    )) {
                for (Card card : agileCardsInR) {
                    matchTable.placeCard(new CardWrapper(card, Origin.FIRSTPLAYER_RANGED), 0, 0);
                }
            } else {
                for (Card card : agileCardsInCC) {
                    matchTable.placeCard(new CardWrapper(card, Origin.FIRSTPLAYER_CLOSECOMBAT), 0, 1);
                }
            }

        } else {
            if (matchTable.getSecondPlayerCloseCombatBoostCard() != null) {
                isCCUnderBoost = true;
            }
            if (matchTable.getSecondPlayerRangedBoostCard() != null) {
                isRUnderBoost = true;
            }


            ArrayList<Card> agileCardsInR = new ArrayList<>();
            for (Card card : matchTable.getSecondPlayerCloseCombatRow()) {
                UnitCard unitCard = (UnitCard) card;
                if (unitCard.getUnit() == Unit.AGILE) agileCardsInCC.add(card);
            }
            for (Card card : matchTable.getSecondPlayerRangedRow()) {
                UnitCard unitCard = (UnitCard) card;
                if (unitCard.getUnit() == Unit.AGILE) agileCardsInR.add(card);
            }
            ArrayList<Card> tempCC = new ArrayList<>(matchTable.getSecondPlayerCloseCombatRow());
            tempCC.addAll(agileCardsInR);
            ArrayList<Card> tempR = new ArrayList<>(matchTable.getSecondPlayerRangedRow());
            tempR.addAll(agileCardsInCC);
            if (MatchTable.getRowPower(matchTable.getLeaderEffects()
                    , isCCUnderWeather
                    , isCCUnderBoost,
                    tempCC
            ) >
                    MatchTable.getRowPower(matchTable.getLeaderEffects()
                            , isRUnderWeather
                            , isRUnderBoost,
                            tempR
                    )) {
                for (Card card : agileCardsInR) {
                    matchTable.placeCard(new CardWrapper(card, Origin.SECONDPLAYER_RANGED), 1, 0);
                }
            } else {
                for (Card card : agileCardsInCC) {
                    matchTable.placeCard(new CardWrapper(card, Origin.SECONDPLAYER_CLOSECOMBAT), 1, 1);
                }
            }
        }
    }

    private static void crachAnCraite() {
        ArrayList<CardWrapper> tempList = new ArrayList<>();
        for (Card card : matchTable.getFirstPlayerDeadCards()) {
            tempList.add(new CardWrapper(card, Origin.FIRSTPLATER_DEAD));

        }
        for (CardWrapper cardWrapper : tempList){
            matchTable.addToDeckCards(0, cardWrapper);
        }
        tempList.clear();
        for (Card card : matchTable.getSecondPlayerDeadCards()) {
            tempList.add(new CardWrapper(card, Origin.SECONDPLAYER_DEAD));
        }
        for (CardWrapper cardWrapper : tempList){
            matchTable.addToDeckCards(1, cardWrapper);
        }
        matchTable.shufleDeck(0);
        matchTable.shufleDeck(1);
    }

    private static void kingBran() {
        matchTable.getLeaderEffects().setKingBran(true);
    }

    private static void removeStrongestRangedPlayer1() {
        UnitCard cardToDelete = null;
        for (Card card : matchTable.getSecondPlayerRangedRow()) {
            if (cardToDelete == null) cardToDelete = (UnitCard) card;
            if (((UnitCard) card).getShowingPower() > cardToDelete.getShowingPower()) {
                cardToDelete = (UnitCard) card;
            }
        }
        matchTable.getSecondPlayerRangedRow().remove(cardToDelete);
    }

    private static void removeStrongestRangedPlayer2() {
        UnitCard cardToDelete = null;
        for (Card card : matchTable.getFirstPlayerRangedRow()) {
            if (cardToDelete == null) cardToDelete = (UnitCard) card;
            if (((UnitCard) card).getShowingPower() > cardToDelete.getShowingPower()) {
                cardToDelete = (UnitCard) card;
            }
        }
        matchTable.getFirstPlayerRangedRow().remove(cardToDelete);
    }

    private static boolean isArrayOnlyHero(ArrayList<Card> cards) {
        for (Card card : cards) {
            if (!(card instanceof Hero)) return false;
        }
        return true;
    }

    private static ArrayList<Card> getHerolessArray(ArrayList<Card> cards) {
        ArrayList<Card> returnArray = new ArrayList<>();
        for (Card card : cards) {
            if (!(card instanceof Hero)) returnArray.add(card);
        }
        return returnArray;
    }

    public static void doActionByName(String name, MatchTable currentMatchTable) {
        matchTable = currentMatchTable;
        switch (name) {
            case "The Siegemaster":
                theSiegeMaster();
                break;
            case "The Steel-Forged":
                theSteelForged();
                break;
            case "King of Temeria":
                kingOfTemeria();
                break;
            case "Lord Commander of the North":
                lordCommanderOfTheNorth();
                break;
            case "Son of Medell":
                sonOfMedell();
                break;
            case "The White Flame":
                theWhiteFlame();
                break;
            case "His Imperial Majesty":
                hisImperialMajesty();
                break;
            case "Emperor of NILFGAARD":
                emperorOfNilfgaard();
                break;
            case "The Relentless":
                theRelentless();
                break;
            case "Invader of the North":
                invaderOfTheNorth();
                break;
            case "Bringer of Death":
                bringerOfDeath();
                break;
            case "King of the wild Hunt":
                kingOfTheWildHunt();
                break;
            case "Destroyer of Worlds":
                destroyerOfWorlds();
                break;
            case "Commander of the Red Riders":
                commanderOfTheRedRiders();
                break;
            case "The Treacherous":
                theTreacherous();
                break;
            case "Queen of Dol Blathanna":
                queenOfDolBlathanna();
                break;
            case "The Beautiful":
                theBeautiful();
                break;
            case "Daisy of the Valley":
                daisyOfTheValley();
                break;
            case "Pureblood Elf":
                purebloodElf();
                break;
            case "Hope of the Aen Seidhe":
                hopeOfTheAenSeidhe();
                break;
            case "Crach an Craite":
                crachAnCraite();
                break;
            case "King Bran":
                kingBran();
                break;


        }
    }

}