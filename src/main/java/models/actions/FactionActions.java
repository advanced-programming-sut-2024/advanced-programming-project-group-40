package models.actions;

import enums.Ability;
import enums.Origin;
import models.Game;
import models.MatchTable;
import models.cards.*;

import java.util.ArrayList;
import java.util.Objects;

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
                        Game.random.nextInt(0, matchTable.getFirstPlayerDeckCards().size())
                );
                cardWrapper = new CardWrapper(randomCard, Origin.FIRSTPLAYER_DECK);
                matchTable.addToInPlayCards(0, cardWrapper);
                break;
            case 1:
                randomCard = matchTable.getSecondPlayerDeckCards().get(
                        Game.random.nextInt(0, matchTable.getSecondPlayerDeckCards().size())
                );
                cardWrapper = new CardWrapper(randomCard, Origin.SECONDPLAYER_DECK);
                matchTable.addToInPlayCards(1, cardWrapper);

        }
    }

    private static void scoiatael() {
    }

    private static void skellige(int userID) {
        ArrayList<Card> randomCards = new ArrayList<>();
        switch (userID) {
            case 0:
                randomCards.addAll(MatchTable.randomSelectedCards(matchTable.getFirstPlayerDeadCards(),2));
                for (Card card : randomCards){
                    System.out.println(card.getName());
                    matchTable.placeCard(new CardWrapper(card,Origin.FIRSTPLATER_DEAD),0,MatchTable.getRowID(GetDestination(card)));
                }
                break;
            case 1:
                randomCards.addAll(MatchTable.randomSelectedCards(matchTable.getSecondPlayerDeadCards(),2));
                for (Card card : randomCards){
                    System.out.println(card.getName());
                    matchTable.placeCard(new CardWrapper(card,Origin.SECONDPLAYER_DEAD),1,MatchTable.getRowID(GetDestination(card)));
                }

        }
    }

    private static MatchTable matchTable;
    private static Origin GetDestination(Card selectedCard) {
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
    public static void doActionByName(int userID, String name, MatchTable currentMatchTable) {
        matchTable = currentMatchTable;
        switch (name) {
            case "realms":
                northernRealms(userID);
                break;
            case "skellige":
                skellige(userID);
                break;
        }
    }

}
