package models.actions;

import enums.Origin;
import enums.cards.SpecialCardInfo;
import models.Game;
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
        Pattern pattern = Pattern.compile("(?<string>.+)(?=:)");
        String musterName;
        if (!card.getName().contains(":")) {
            musterName = card.getName();
        } else {
            Matcher matcher = pattern.matcher(card.getName());
            musterName = matcher.group("string");
        }
        String tempMusterName;
        ArrayList<CardWrapper> cardsToPlay = new ArrayList<>();
        if (userID == 0) {
            for (Card card1 : matchTable.getFirstPlayerDeckCards()) {
                if (!card1.getName().contains(":")) {
                    tempMusterName = card1.getName();
                } else {
                    Matcher matcher = pattern.matcher(card1.getName());
                    tempMusterName = matcher.group("string");
                }
                if (Objects.equals(tempMusterName, musterName)) {
                    cardsToPlay.add(new CardWrapper(card1, Origin.FIRSTPLAYER_DECK));
                }
            }
            for (Card card1 : matchTable.getFirstPlayerInPlayCards()) {
                if (!card1.getName().contains(":")) {
                    tempMusterName = card1.getName();
                } else {
                    Matcher matcher = pattern.matcher(card1.getName());
                    tempMusterName = matcher.group("string");
                }
                if (Objects.equals(tempMusterName, musterName)) {
                    cardsToPlay.add(new CardWrapper(card1, Origin.FIRSTPLAYER_INPLAY));
                }
            }
            for (CardWrapper cardWrapper : cardsToPlay) {
                matchTable.placeCardNoAbility(cardWrapper, userID, rowNumber);

            }
        } else {
            for (Card card1 : matchTable.getSecondPlayerDeckCards()) {
                if (!card1.getName().contains(":")) {
                    tempMusterName = card1.getName();
                } else {
                    Matcher matcher = pattern.matcher(card1.getName());
                    tempMusterName = matcher.group("string");
                }
                if (Objects.equals(tempMusterName, musterName)) {
                    cardsToPlay.add(new CardWrapper(card1, Origin.SECONDPLAYER_DECK));
                }
            }
            for (Card card1 : matchTable.getSecondPlayerInPlayCards()) {
                if (!card1.getName().contains(":")) {
                    tempMusterName = card1.getName();
                } else {
                    Matcher matcher = pattern.matcher(card1.getName());
                    tempMusterName = matcher.group("string");
                }
                if (Objects.equals(tempMusterName, musterName)) {
                    cardsToPlay.add(new CardWrapper(card1, Origin.SECONDPLAYER_INPLAY));
                }
            }
            for (CardWrapper cardWrapper : cardsToPlay) {
                matchTable.placeCardNoAbility(cardWrapper, userID, rowNumber);
            }

        }

    }

    private static void spy() {
        int i = 0;
        Card randomUnitcard;
        if (matchTable.isFirstPlayerTurn()) {
            while (i < 2 && !matchTable.getFirstPlayerDeadCards().isEmpty()) {
                i++;
                randomUnitcard = matchTable.getFirstPlayerDeadCards().get(
                        Game.random.nextInt(0, matchTable.getFirstPlayerDeadCards().size())
                );
                matchTable.addToInPlayCards(0, new CardWrapper(randomUnitcard, Origin.FIRSTPLATER_DEAD));

            }
        } else {
            while (i < 2 && !matchTable.getSecondPlayerDeadCards().isEmpty()) {
                i++;
                randomUnitcard = matchTable.getSecondPlayerDeadCards().get(
                        Game.random.nextInt(0, matchTable.getSecondPlayerDeadCards().size())
                );
                matchTable.addToInPlayCards(1, new CardWrapper(randomUnitcard, Origin.SECONDPLAYER_DEAD));

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
                Origin origin = switch (rowNumber) {
                    case 0 -> Origin.SECONDPLAYER_CLOSECOMBAT;
                    case 1 -> Origin.SECONDPLAYER_RANGED;
                    case 2 -> Origin.SECONDPLAYER_SIEGE;
                    default -> null;
                };

                for (Card card : row) {
                    if (card instanceof UnitCard unitCard) {
                        if (unitCard.getShowingPower() == highestDamageCard.getShowingPower()) {
                            matchTable.addToDeadCards(1,unitCard);
                        }
                    }
                }
            }
        } else {
            int damage = MatchTable.getRowPowerNoHero(matchTable.getLeaderEffects(),
                    matchTable.isRowUnderWeather(rowNumber),
                    matchTable.isRowUnderBoost(0, rowNumber),
                    matchTable.getRowByID(0, rowNumber));
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
                Origin origin = switch (rowNumber) {
                    case 0 -> Origin.FIRSTPLAYER_CLOSECOMBAT;
                    case 1 -> Origin.FIRSTPLAYER_RANGED;
                    case 2 -> Origin.FIRSTPLAYER_SIEGE;
                    default -> null;
                };

                for (Card card : row) {
                    if (card instanceof UnitCard unitCard) {
                        if (unitCard.getShowingPower() == highestDamageCard.getShowingPower()) {
                            matchTable.addToDeadCards(0,unitCard);
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
                for (Card card : row) {
                    if (card instanceof UnitCard unitCard) {
                        if (unitCard.getShowingPower() == highestDamageCard.getShowingPower()) {
                            matchTable.addToDeadCards(i,unitCard);
                        }
                    }
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
                            matchTable.addToDeadCards(0,unitCard);
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
                scorch(3);
                break;
            case "Toad":
                scorch(2);
                break;
            case "Scorch":
                scorchAll();
                break;
        }

    }
}
