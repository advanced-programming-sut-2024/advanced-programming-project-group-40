package models.actions;

import enums.Origin;
import Server.GameServer;
import models.MatchTable;
import models.cards.Card;
import models.cards.CardWrapper;
import models.cards.UnitCard;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class UnitCardActions {
    private static void commandersHorn() {
        //filled
    }

    private static void decoy() {
        //todo
    }

    private static void medic() {
        //todo
    }

    private static void moralBoost() {
        //filled
    }

    private static void muster(Card card, int userID, int rowNumber) {
        Pattern pattern = Pattern.compile("(?<name>.+):");
        String musterName="";
        if (!card.getName().contains(":")) {
            musterName = card.getName();
        } else {
            Matcher matcher = pattern.matcher(card.getName());
            if (matcher.find()) musterName = matcher.group("name");
        }
        String tempMusterName="";
        ArrayList<CardWrapper> cardsToPlay = new ArrayList<>();
        if (userID == 0) {
            for (Card card1 : matchTable.getFirstPlayerDeckCards()) {
                if (!card1.getName().contains(":")) {
                    tempMusterName = card1.getName();
                } else {
                    Matcher matcher = pattern.matcher(card1.getName());
                    if (matcher.find()) tempMusterName = matcher.group("name");
                }
                if (Objects.equals(tempMusterName, musterName)) {
                    cardsToPlay.add(new CardWrapper(card1, Origin.FIRSTPLAYER_DECK));
                }
            }
            for (CardWrapper cardWrapper : cardsToPlay){
                System.out.println(cardWrapper.getCard().getName());
            }
            for (Card card1 : matchTable.getFirstPlayerInPlayCards()) {
                if (!card1.getName().contains(":")) {
                    tempMusterName = card1.getName();
                } else {
                    Matcher matcher = pattern.matcher(card1.getName());
                    if (matcher.find()) tempMusterName = matcher.group("name");
                }
                if (Objects.equals(tempMusterName, musterName)) {
                    cardsToPlay.add(new CardWrapper(card1, Origin.FIRSTPLAYER_INPLAY));
                }
            }
            System.out.println();
            for (CardWrapper cardWrapper : cardsToPlay){
                System.out.println(cardWrapper.getCard().getName());
            }
            for (CardWrapper cardWrapper : cardsToPlay) {
                matchTable.placeCardNoAbility(cardWrapper, userID, rowNumber);
            }
        }
        else {
            for (Card card1 : matchTable.getSecondPlayerDeckCards()) {
                if (!card1.getName().contains(":")) {
                    tempMusterName = card1.getName();
                } else {
                    Matcher matcher = pattern.matcher(card1.getName());
                    if (matcher.find()) tempMusterName = matcher.group("name");
                }
                if (Objects.equals(tempMusterName, musterName)) {
                    cardsToPlay.add(new CardWrapper(card1, Origin.SECONDPLAYER_DECK));
                }
            }
            for (CardWrapper cardWrapper : cardsToPlay){
                System.out.println(cardWrapper.getCard().getName());
            }
            for (Card card1 : matchTable.getSecondPlayerInPlayCards()) {
                if (!card1.getName().contains(":")) {
                    tempMusterName = card1.getName();
                } else {
                    Matcher matcher = pattern.matcher(card1.getName());
                    if (matcher.find()) tempMusterName = matcher.group("name");
                }
                if (Objects.equals(tempMusterName, musterName)) {
                    cardsToPlay.add(new CardWrapper(card1, Origin.SECONDPLAYER_INPLAY));
                }
            }
            System.out.println();
            for (CardWrapper cardWrapper : cardsToPlay){
                System.out.println(cardWrapper.getCard().getName());
            }
            for (CardWrapper cardWrapper : cardsToPlay) {
                System.out.println(cardWrapper.getCard().getName());
                matchTable.placeCardNoAbility(cardWrapper, userID, rowNumber);
            }

        }

    }
    private static void spy() {
        int i = 0;
        Card randomUnitcard;
        if (matchTable.isFirstPlayerTurn()) {
            while (i < 2 && !matchTable.getFirstPlayerDeckCards().isEmpty()) {
                i++;
                randomUnitcard = matchTable.getFirstPlayerDeckCards().get(
                        GameServer.random.nextInt(0, matchTable.getFirstPlayerDeckCards().size())
                );
                matchTable.addToInPlayCards(0, new CardWrapper(randomUnitcard, Origin.FIRSTPLAYER_DECK));

            }
        } else {
            while (i < 2 && !matchTable.getSecondPlayerDeckCards().isEmpty()) {
                i++;
                randomUnitcard = matchTable.getSecondPlayerDeckCards().get(
                        GameServer.random.nextInt(0, matchTable.getSecondPlayerDeckCards().size())
                );
                matchTable.addToInPlayCards(1, new CardWrapper(randomUnitcard, Origin.SECONDPLAYER_DECK));

            }
        }
    }

    private static void tightBond() {
        //filled
    }

    private static void scorch(int rowNumber) {
        UnitCard highestDamageCard = null;
        if (matchTable.isFirstPlayerTurn()) {
            int damage = MatchTable.getRowPowerNoHero(matchTable.getLeaderEffects(),
                    matchTable.isRowUnderWeather(rowNumber),
                    matchTable.isRowUnderBoost(1, rowNumber),
                    matchTable.getRowByID(1, rowNumber));
            System.out.println(damage);
            if (damage > 10) {

                ArrayList<Card> row = matchTable.getRowByID(1, rowNumber);
                for (Card card : row) {
                    if (card instanceof UnitCard unitCard) {
                        if (highestDamageCard == null) highestDamageCard = unitCard;
                        else {
                            if (unitCard.getShowingPower() > highestDamageCard.getShowingPower()) {
                                highestDamageCard = unitCard;
                            }
                        }
                    }
                }
                //todo: make these CardWrapper
                for (Card card : row) {
                    if (card instanceof UnitCard unitCard) {
                        if (unitCard.getShowingPower() == highestDamageCard.getShowingPower()) {
                            matchTable.addToDeadCards(1, unitCard);
                        }
                    }
                }
            }
        } else {
            int damage = MatchTable.getRowPowerNoHero(matchTable.getLeaderEffects(),
                    matchTable.isRowUnderWeather(rowNumber),
                    matchTable.isRowUnderBoost(0, rowNumber),
                    matchTable.getRowByID(0, rowNumber));
            System.out.println(damage);
            if (damage > 10) {
                ArrayList<Card> row = matchTable.getRowByID(0, rowNumber);
                for (Card card : row) {
                    if (card instanceof UnitCard unitCard) {
                        if (highestDamageCard == null) highestDamageCard = unitCard;
                        else {
                            if (unitCard.getShowingPower() > highestDamageCard.getShowingPower()) {
                                highestDamageCard = unitCard;
                            }
                        }
                    }
                }
                for (Card card : row) {
                    if (card instanceof UnitCard unitCard) {
                        if (unitCard.getShowingPower() == highestDamageCard.getShowingPower()) {
                            matchTable.addToDeadCards(0, unitCard);
                        }
                    }
                }
            }
        }
    }

    private static void scorchAll() {
        UnitCard highestDamageCard = null;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                ArrayList<Card> row = matchTable.getRowByID(i, j);
                for (Card card : row) {
                    if (card instanceof UnitCard unitCard) {
                        if (highestDamageCard == null) highestDamageCard = unitCard;
                        else {
                            if (unitCard.getShowingPower() > highestDamageCard.getShowingPower()) {
                                highestDamageCard = unitCard;
                            }
                        }
                    }
                }
            }
        }
        Origin origin = null;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 0) {
                    origin = switch (j) {
                        case 0 -> Origin.FIRSTPLAYER_CLOSECOMBAT;
                        case 1 -> Origin.FIRSTPLAYER_RANGED;
                        case 2 -> Origin.FIRSTPLAYER_SIEGE;
                        default -> origin;
                    };
                } else {
                    origin = switch (j) {
                        case 0 -> Origin.SECONDPLAYER_CLOSECOMBAT;
                        case 1 -> Origin.SECONDPLAYER_RANGED;
                        case 2 -> Origin.SECONDPLAYER_SIEGE;
                        default -> origin;
                    };
                }
                ArrayList<Card> row = matchTable.getRowByID(i, j);
                ArrayList<CardWrapper> toRemoveCards = new ArrayList<>();
                for (Card card : row) {
                    if (card instanceof UnitCard unitCard) {
                        if (unitCard.getShowingPower() == highestDamageCard.getShowingPower()) {
                            toRemoveCards.add(new CardWrapper(card, origin));

                        }
                    }
                }
                for (CardWrapper cardWrapper : toRemoveCards) {
                    matchTable.addToDeadCards(i,cardWrapper);
                }
            }
        }


    }

    private static void scorchAllEnemy() {
        UnitCard highestDamageCard = null;
        if (matchTable.isFirstPlayerTurn()) {
            for (int j = 0; j < 3; j++) {
                ArrayList<Card> row = matchTable.getRowByID(1, j);
                for (Card card : row) {
                    if (card instanceof UnitCard unitCard) {
                        if (highestDamageCard == null) highestDamageCard = unitCard;
                        else {
                            if (unitCard.getShowingPower() > highestDamageCard.getShowingPower()) {
                                highestDamageCard = unitCard;
                            }
                        }
                    }
                }
            }
            Origin origin = null;
            for (int j = 0; j < 3; j++) {
                origin = switch (j) {
                    case 0 -> Origin.SECONDPLAYER_CLOSECOMBAT;
                    case 1 -> Origin.SECONDPLAYER_RANGED;
                    case 2 -> Origin.SECONDPLAYER_SIEGE;
                    default -> origin;
                };
                ArrayList<Card> row = matchTable.getRowByID(1, j);
                for (Card card : row) {
                    if (card instanceof UnitCard unitCard) {
                        if (unitCard.getShowingPower() == highestDamageCard.getShowingPower()) {
                            matchTable.addToDeadCards(1, unitCard);
                        }
                    }
                }

            }
        } else {

            for (int j = 0; j < 3; j++) {
                ArrayList<Card> row = matchTable.getRowByID(0, j);
                for (Card card : row) {
                    if (card instanceof UnitCard unitCard) {
                        if (highestDamageCard == null) highestDamageCard = unitCard;
                        else {
                            if (unitCard.getShowingPower() > highestDamageCard.getShowingPower()) {
                                highestDamageCard = unitCard;
                            }
                        }
                    }
                }
            }
            Origin origin = null;
            for (int j = 0; j < 3; j++) {
                origin = switch (j) {
                    case 0 -> Origin.FIRSTPLAYER_CLOSECOMBAT;
                    case 1 -> Origin.FIRSTPLAYER_RANGED;
                    case 2 -> Origin.FIRSTPLAYER_SIEGE;
                    default -> origin;
                };
                ArrayList<Card> row = matchTable.getRowByID(0, j);
                for (Card card : row) {
                    if (card instanceof UnitCard unitCard) {
                        if (unitCard.getShowingPower() == highestDamageCard.getShowingPower()) {
                            matchTable.addToDeadCards(0, unitCard);
                        }
                    }
                }

            }
        }


    }

    private static void berserker() {
        //filled
    }

    private static void mardroeme() {
        //filled
    }

    private static void transformers() {
        //filled
    }

    private static MatchTable matchTable;

    public static void doActionByName(String name, MatchTable currentMatchTable) {
        matchTable = currentMatchTable;
        switch (name) {
            case "spy":
                spy();
                break;
        }
    }

    public static void doActionWhenPlaced(Card placedCard, int userID, int rowNumber, String name, MatchTable currentMatchTable) {
        matchTable = currentMatchTable;
        switch (name) {
            case "muster":
                muster(placedCard, userID, rowNumber);
                break;
            case "scorch":
                doScorch(placedCard);
                break;
        }

    }

    private static void doScorch(Card placedCard) {
        switch (placedCard.getName()) {
            case "Clan Dimun Pirate":
                scorchAllEnemy();
                break;
            case "Schirru":
                scorch(2);
                break;
            case "Toad":
                scorch(1);
                break;
            case "Villentretenmerth":
                scorch(0);
                break;
            case "Scorch":
                scorchAll();
                break;
        }

    }
}