package models.actions;

import enums.Origin;
import enums.cards.SpecialCardInfo;
import models.MatchTable;
import models.cards.Card;
import models.cards.CardWrapper;
import models.cards.SpecialCard;

public abstract class LeaderActions {
    private static MatchTable matchTable;
    private static void theSiegeMaster() {
        if (matchTable.isFirstPlayerTurn()){
            if (matchTable.getFirstPlayerInPlayCards().contains(SpecialCardInfo.getSpecialCardByName("impenetrable fog"))){
                matchTable.addToSpellCards(new CardWrapper(SpecialCardInfo.getSpecialCardByName("impenetrable fog"),
                        Origin.FIRSTPLAYER_INPLAY));
            }
        }else {
            if (matchTable.getSecondPlayerInPlayCards().contains(SpecialCardInfo.getSpecialCardByName("impenetrable fog"))){
                matchTable.addToSpellCards(new CardWrapper(SpecialCardInfo.getSpecialCardByName("impenetrable fog"),
                        Origin.SECONDPLAYER_INPLAY));
            }
        }

    }

    private static void theSteelForged() {

    }

    private static void kingOfTemeria() {

    }

    private static void lordCommanderOfTheNorth() {

    }

    private static void sonOfMedell() {

    }

    private static void theWhiteFlame() {

    }

    private static void hisImperialMajesty() {

    }

    private static void emperorOfNilfgaard() {

    }

    private static void theRelentless() {

    }

    private static void invaderOfTheNorth() {

    }

    private static void bringerOfDeath() {

    }

    private static void kingOfTheWildHunt() {

    }

    private static void destroyerOfWorlds() {

    }

    private static void commanderOfTheRedRiders() {

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
    public static void doActionByName(String name, MatchTable currentMatchTable){
        matchTable = currentMatchTable;
    }

}
