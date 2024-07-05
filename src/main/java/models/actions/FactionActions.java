package models.actions;

import enums.Origin;
import Server.GameServer;
import models.MatchTable;
import models.cards.Card;
import models.cards.CardWrapper;

public abstract class FactionActions {
    private static void monster() {
    }

    private static void nilfgaardianEmpire() {
    }

    private static void northernRealms(int userID) {
        Card randomCard;
        CardWrapper cardWrapper;
        switch (userID) {
            case 0:
                randomCard = matchTable.getFirstPlayerDeckCards().get(
                        GameServer.random.nextInt(0, matchTable.getFirstPlayerDeckCards().size())
                );
                cardWrapper = new CardWrapper(randomCard, Origin.FIRSTPLAYER_DECK);
                matchTable.addToInPlayCards(0, cardWrapper);
                break;
            case 1:
                randomCard = matchTable.getSecondPlayerDeckCards().get(
                        GameServer.random.nextInt(0, matchTable.getSecondPlayerDeckCards().size())
                );
                cardWrapper = new CardWrapper(randomCard, Origin.SECONDPLAYER_DECK);
                matchTable.addToInPlayCards(1, cardWrapper);

        }
    }

    private static void scoiatael() {
    }

    private static void skellige() {
    }

    private static MatchTable matchTable;

    public static void doActionByName(int userID, String name, MatchTable currentMatchTable) {
        matchTable = currentMatchTable;
        switch (name) {
            case "NORTHERN_REALMS Northern":
                northernRealms(userID);
                break;
        }
    }

}
