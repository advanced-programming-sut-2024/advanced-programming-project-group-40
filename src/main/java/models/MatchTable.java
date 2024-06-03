package models;

import enums.Ability;
import enums.Factions;
import enums.cards.UnitCardInfo;
import models.actions.FactionActions;
import models.actions.LeaderActions;
import models.cards.Card;
import models.cards.Leader;
import models.cards.SpecialCard;
import models.cards.UnitCard;

import java.util.*;

public class MatchTable {
    private User firstPlayer;
    private User secondPlayer;
    private boolean isFirstPlayerTurn;
    private boolean secondPlayerPassed = false;
    private boolean firstPlayerPassed = false;
    private int round = 1;
    private Date date;
    private final ArrayList<Integer> firstPlayerPoints = new ArrayList<>();
    private final ArrayList<Integer> secondPlayerPoints = new ArrayList<>();
    private int firstPlayerCurrentPoint;
    private int secondPlayerCurrentPoint;
    private final ArrayList<Integer> firstPlayerRowPoints = new ArrayList<>(Arrays.asList(0, 0, 0));
    private final ArrayList<Integer> secondPlayerRowPoints = new ArrayList<>(Arrays.asList(0, 0, 0));
    private final ArrayList<Card> firstPlayerDeckCards = new ArrayList<>();
    private final ArrayList<Card> secondPlayerDeckCards = new ArrayList<>();
    private final ArrayList<Card> firstPlayerCloseCombatRow = new ArrayList<>(Collections.singletonList(null));
    private Card firstPlayerCloseCombatBoostCard;
    private final ArrayList<Card> secondPlayerCloseCombatRow = new ArrayList<>(Collections.singletonList(null));
    private Card secondPlayerCloseCombatBoostCard;

    private final ArrayList<Card> firstPlayerRangedRow = new ArrayList<>(Collections.singletonList(null));
    private Card firstPlayerRangedBoostCard;
    private final ArrayList<Card> secondPlayerRangedRow = new ArrayList<>(Collections.singletonList(null));
    private Card secondPlayerRangedBoostCard;
    private final ArrayList<Card> firstPlayerSiegeRow = new ArrayList<>(Collections.singletonList(null));
    private Card firstPlayerSiegeBoostCard;
    private final ArrayList<Card> secondPlayerSiegeRow = new ArrayList<>(Collections.singletonList(null));
    private Card secondPlayerSiegeBoostCard;


    private final ArrayList<Card> firstPlayerDeadCards = new ArrayList<>();
    private final ArrayList<Card> secondPlayerDeadCards = new ArrayList<>();
    private final ArrayList<Card> firstPlayerInPlayCards = new ArrayList<>();
    private final ArrayList<Card> secondPlayerInPlayCards = new ArrayList<>();
    private int firstPlayerCrystals = 2;
    private int secondPlayerCrystals = 2;
    private final ArrayList<SpecialCard> spellCards = new ArrayList<>();
    private Leader firstPlayerLeader;
    private Leader secondPlayerLeader;
    private boolean isFirstPlayerLeaderUsed = false;
    private boolean isSecondPlayerLeaderUsed = false;


    public MatchTable(User firstPlayer, User secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        firstPlayerDeckCards.addAll(firstPlayer.getDeckCards());
        secondPlayerDeckCards.addAll(secondPlayer.getDeckCards());
    }

    //firstPlayer -> id=0
    //secondPlayer -> id=1
    public int getPlayerTotalScore(int user_id) {
        int retVal = 0;
        for (int i = 0; i < 3; i++) {
            retVal += getPlayerRowScore(user_id, i);
        }

        return retVal;
    }

    //firstPlayer -> id=0
    //secondPlayer -> id=1
    // closeCombat:0 Range:1 Siege:2
    public int getPlayerRowScore(int user_id, int rowNumber) {
        int retVal = 0;
        ArrayList<Card> row = getRowByID(user_id, rowNumber);
        for (Card card : row) {
            retVal += ((UnitCard) card).getShowingPower();
        }
        return retVal;
    }

    public void setPlayerRowScore(int user_id, int rowNumber) {
        boolean areCardsUnderWeather = isRowUnderWeather(rowNumber);
        boolean areCardsBoosted = isRowUnderBoost(user_id, rowNumber);
        ArrayList<Card> row = getRowByID(user_id, rowNumber);
        ArrayList<Card> tightBondCards = getCardsWithAbility(Ability.TIGHT_BOND, row);
        ArrayList<Card> moralBoostCards = getCardsWithAbility(Ability.MORAL_BOOST, row);
        if (areCardsUnderWeather) {
            for (Card card : row) {
                UnitCard unitCard = (UnitCard) card;
                unitCard.setShowingPower(1);
            }
        }
        for (Card card : row) {
            UnitCard unitCard = (UnitCard) card;
            if (tightBondCards.contains(card)) {
                unitCard.setShowingPower(unitCard.getShowingPower() * getNumberOfCards(card, tightBondCards));
            }
        }
        for (Card card : row) {
            UnitCard unitCard = (UnitCard) card;
            if (moralBoostCards.contains(card)) {
                unitCard.setShowingPower(unitCard.getShowingPower() + moralBoostCards.size() - 1);
            } else {
                unitCard.setShowingPower(unitCard.getShowingPower() + moralBoostCards.size());
            }
        }
        if (areCardsBoosted) {
            for (Card card : row) {
                UnitCard unitCard = (UnitCard) card;
                unitCard.setShowingPower(unitCard.getShowingPower() * 2);
            }
        }
    }


    public int getRound() {
        return round;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<Integer> getFirstPlayerPoints() {
        return firstPlayerPoints;
    }

    public ArrayList<Integer> getSecondPlayerPoints() {
        return secondPlayerPoints;
    }

    public int getFirstPlayerCurrentPoint() {
        return firstPlayerCurrentPoint;
    }

    public int getSecondPlayerCurrentPoint() {
        return secondPlayerCurrentPoint;
    }

    public ArrayList<Card> getFirstPlayerCloseCombatRow() {
        return firstPlayerCloseCombatRow;
    }

    public ArrayList<Card> getSecondPlayerCloseCombatRow() {
        return secondPlayerCloseCombatRow;
    }

    public ArrayList<Card> getFirstPlayerRangedRow() {
        return firstPlayerRangedRow;
    }

    public ArrayList<Card> getSecondPlayerRangedRow() {
        return secondPlayerRangedRow;
    }

    public ArrayList<Card> getFirstPlayerSiegeRow() {
        return firstPlayerSiegeRow;
    }

    public ArrayList<Card> getSecondPlayerSiegeRow() {
        return secondPlayerSiegeRow;
    }

    public ArrayList<Card> getFirstPlayerDeadCards() {
        return firstPlayerDeadCards;
    }

    public ArrayList<Card> getSecondPlayerDeadCards() {
        return secondPlayerDeadCards;
    }

    public ArrayList<Card> getFirstPlayerInPlayCards() {
        return firstPlayerInPlayCards;
    }

    public ArrayList<Card> getFirstPlayerDeckCards() {
        return firstPlayerDeckCards;
    }

    public ArrayList<Card> getSecondPlayerDeckCards() {
        return secondPlayerDeckCards;
    }

    public User getFirstPlayer() {
        return firstPlayer;
    }

    public User getSecondPlayer() {
        return secondPlayer;
    }

    public ArrayList<Card> getSecondPlayerInPlayCards() {
        return secondPlayerInPlayCards;
    }

    public int getFirstPlayerCrystals() {
        return firstPlayerCrystals;
    }

    public int getSecondPlayerCrystals() {
        return secondPlayerCrystals;
    }

    public ArrayList<SpecialCard> getSpellCards() {
        return spellCards;
    }

    public Leader getFirstPlayerLeader() {
        return firstPlayerLeader;
    }

    public Leader getSecondPlayerLeader() {
        return secondPlayerLeader;
    }

    public ArrayList<Integer> getFirstPlayerRowPoints() {
        return firstPlayerRowPoints;
    }

    public ArrayList<Integer> getSecondPlayerRowPoints() {
        return secondPlayerRowPoints;
    }

    //gives some random card without removing them from the deck
    public ArrayList<Card> randomSelectedCards(ArrayList<Card> deck) {
        ArrayList<Card> randomCards = new ArrayList<>();
        ArrayList<Card> copiedCards = new ArrayList<>(deck);
        for (int i = 0; i < 10; i++) {
            Card tempCard = copiedCards.get(Game.random.nextInt(0, copiedCards.size()));
            randomCards.add(tempCard);
            copiedCards.remove(tempCard);
        }
        return randomCards;
    }

    //places card without removing it from its origin(we don't know where the origin is)
    public void placeCard(Card card, int userID, int rowNumber) {
        ArrayList<Card> row = getRowByID(userID, rowNumber);
        row.add(card);
    }

    //places card without removing it from its origin(we don't know where the origin is)
    public void placeBoostCard(Card card, int userID, int rowNumber) {
        switch (card.getName()) {
            case "Commander's Horn":
                switch (userID) {
                    case 0:
                        switch (rowNumber) {
                            case 0:
                                firstPlayerCloseCombatBoostCard = card;
                                break;
                            case 1:
                                firstPlayerRangedBoostCard = card;
                                break;
                            case 2:
                                firstPlayerSiegeBoostCard = card;
                                break;
                        }
                        break;
                    case 1:
                        switch (rowNumber) {
                            case 0:
                                secondPlayerCloseCombatBoostCard = card;
                                break;
                            case 1:
                                secondPlayerRangedBoostCard = card;
                                break;
                            case 2:
                                secondPlayerSiegeBoostCard = card;
                                break;
                        }
                        break;
                }
                break;
            case "Mardroeme":
                applyMardroeme(userID, rowNumber);
                break;
            default:

        }
    }

    //places card without removing it from its origin(we don't know where the origin is)
    public void addToSpellCards(SpecialCard specialCard) {
        spellCards.add(specialCard);
    }

    //places card without removing it from its origin(we don't know where the origin is)
    public void addToInPlayCards(int userID, Card card) {
        switch (userID) {
            case 0:
                firstPlayerInPlayCards.add(card);
                break;
            case 1:
                secondPlayerInPlayCards.add(card);
                break;
        }
    }

    //places card without removing it from its origin(we don't know where the origin is)
    public void addToDeadCards(int userID, Card card) {
        switch (userID) {
            case 0:
                firstPlayerDeckCards.add(card);
                break;
            case 1:
                secondPlayerDeadCards.add(card);
                break;
        }
    }

    public void updatePoints() {
        //update everything
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                setPlayerRowScore(i, j);
            }
        }
        for (int i = 0; i < 3; i++) {
            firstPlayerRowPoints.set(i, getPlayerRowScore(0, i));
            secondPlayerRowPoints.set(i, getPlayerRowScore(1, i));
        }
        firstPlayerCurrentPoint = getPlayerTotalScore(0);
        secondPlayerCurrentPoint = getPlayerTotalScore(1);
    }


    public void leaderAction() {
        LeaderActions.doActionByName(firstPlayerLeader.getName(), this);
    }

    public void factionAction(int playerID, Factions faction) {
        FactionActions.doActionByName(playerID, faction.name(), this);
    }

    public void startTurn(int userID) {
        switch (userID) {
            case 0:
                isFirstPlayerTurn = true;
                break;
            case 1:
                isFirstPlayerTurn = false;
                break;
        }
    }

    public void endTurn(int userID) {
        switch (userID) {
            case 0:
                if (!secondPlayerPassed) isFirstPlayerTurn = false;
                break;
            case 1:
                if (!firstPlayerPassed) isFirstPlayerTurn = true;
                break;
        }

    }

    public void pass(int userID) {
        switch (userID) {
            case 0:
                firstPlayerPassed = true;
                break;
            case 1:
                secondPlayerPassed = true;
                break;
        }
        if (isRoundFinished()) finishRound();
    }

    private void finishRound() {
        round++;
        int firstPlayerScore = getPlayerTotalScore(0);
        int secondPlayerScore = getPlayerTotalScore(1);
        if (firstPlayerScore == secondPlayerScore) {
            //nillfgardian empire ability
            if (Objects.equals(firstPlayer.getFaction(), "Empire Nilfgaardian") &&
                    !Objects.equals(secondPlayer.getFaction(), "Empire Nilfgaardian")) {
                reduceCrystal(1);
            } else if (!Objects.equals(firstPlayer.getFaction(), "Empire Nilfgaardian") &&
                    Objects.equals(secondPlayer.getFaction(), "Empire Nilfgaardian")) {
                reduceCrystal(0);
            }

        } else if (firstPlayerScore > secondPlayerScore) {
            reduceCrystal(1);
            if (Objects.equals(firstPlayer.getFaction(), "Realms Northern")) {
                factionAction(0, Factions.NORTHERN_REALMS);
            }
            //monsters already handled in clear match table

        } else {
            reduceCrystal(0);
            if (Objects.equals(secondPlayer.getFaction(), "Realms Northern")) {
                factionAction(1, Factions.NORTHERN_REALMS);
            }
            //monsters already handled in clear match table

        }
        clearMatchTable();
        //Skellige ability
        if (round == 3) {
            if (Objects.equals(firstPlayer.getFaction(), "Skellige")) {
                factionAction(0, Factions.SKELLIGE);
            }
            if (Objects.equals(secondPlayer.getFaction(), "Skellige")) {
                factionAction(1, Factions.SKELLIGE);
            }
        }
    }

    public boolean isRoundFinished() {
        return secondPlayerPassed && firstPlayerPassed;
    }

    public boolean isMatchFinished() {
        return firstPlayerCrystals == 2 || secondPlayerCrystals == 2;
    }

    public void clearMatchTable() {
        int row = 0;
        //for monsterFaction;
        Card savedCard = null;
        boolean isMonster = false;
        if (Objects.equals(firstPlayer.getFaction(), "monsters")) {
            isMonster = true;
            row = Game.random.nextInt(0, 3);
            savedCard = getRowByID(0, row).get(Game.random.nextInt(0, getRowByID(0, row).size()));
        }
        for (int i = 0; i < 3; i++) {
            getRowByID(0, i).clear();
        }
        if (isMonster) getRowByID(0, row).add(savedCard);


        isMonster = false;
        if (Objects.equals(secondPlayer.getFaction(), "monsters")) {
            isMonster = true;
            row = Game.random.nextInt(0, 3);
            savedCard = getRowByID(1, row).get(Game.random.nextInt(0, getRowByID(1, row).size()));
        }
        for (int i = 0; i < 3; i++) {
            getRowByID(1, i).clear();
        }
        if (isMonster) getRowByID(1, row).add(savedCard);

        //boost cards:
        firstPlayerCloseCombatBoostCard = null;
        firstPlayerSiegeBoostCard = null;
        firstPlayerRangedBoostCard = null;
        secondPlayerCloseCombatBoostCard = null;
        secondPlayerSiegeBoostCard = null;
        secondPlayerRangedBoostCard = null;

    }

    public void clearSpellCards() {
        spellCards.clear();
    }

    public void reduceCrystal(int playerID) {
        switch (playerID) {
            case 0:
                firstPlayerCrystals--;
                break;
            case 1:
                secondPlayerCrystals--;
                break;

        }
    }

    public User winningUser() {
        if (firstPlayerCrystals == 2) return firstPlayer;
        if (secondPlayerCrystals == 2) return secondPlayer;
        return null;
    }

    //-----------------------------------------------------private Functions------------------------------------------//


    // closeCombat:0 Range:1 Siege:2
    private boolean isRowUnderWeather(int rowID) {
        for (Card card : spellCards) {
            switch (card.getName()) {
                case "Biting Frost":
                    if (rowID == 0) return true;
                    break;
                case "Impenetrable fog":
                    if (rowID == 1) return true;
                    break;
                case "Torrential Rain":
                    if (rowID == 2) return true;
                    break;
            }
        }
        return false;
    }

    //checks for commander horn in boost card place and
    // for specials cards in the given row that give the commanders horn effect
    private boolean isRowUnderBoost(int user_id, int rowID) {
        ArrayList<Card> row = getRowByID(user_id, rowID);
        boolean x;
        switch (user_id) {
            case 0:
                x = isThereCommanderHorn(rowID, firstPlayerCloseCombatBoostCard, firstPlayerRangedBoostCard, firstPlayerSiegeBoostCard);
                if (x) return true;
                break;
            case 1:
                x = isThereCommanderHorn(rowID, secondPlayerCloseCombatBoostCard, secondPlayerRangedBoostCard, secondPlayerSiegeBoostCard);
                if (x) return true;
                break;
        }
        for (Card card : row) {
            if (Objects.equals(card.getName(), "Draig Bon-Dhu") ||
                    Objects.equals(card.getName(), "Dandelion")) {
                return true;
            }
        }

        return false;
    }

    //checks for commander horn in the given boost card place
    private boolean isThereCommanderHorn(int rowID, Card secondPlayerCloseCombatBoostCard,
                                         Card secondPlayerRangedBoostCard,
                                         Card secondPlayerSiegeBoostCard) {
        switch (rowID) {
            case 0:
                if (secondPlayerCloseCombatBoostCard != null) {
                    if (Objects.equals(secondPlayerCloseCombatBoostCard.getName(), "Commander's Horn")) return true;
                }
                break;
            case 1:
                if (secondPlayerRangedBoostCard != null) {
                    if (Objects.equals(secondPlayerRangedBoostCard.getName(), "Commander's Horn")) return true;
                }
                break;
            case 2:
                if (secondPlayerSiegeBoostCard != null) {
                    if (Objects.equals(secondPlayerSiegeBoostCard.getName(), "Commander's Horn")) return true;
                }
                break;
        }
        return false;
    }

    //gives a row by player and row id
    private ArrayList<Card> getRowByID(int user_id, int rowID) {
        ArrayList<Card> row = null;
        row = switch (user_id) {
            case 0 -> switch (rowID) {
                case 0 -> firstPlayerCloseCombatRow;
                case 1 -> firstPlayerRangedRow;
                case 2 -> firstPlayerSiegeRow;
                default -> row;
            };
            case 1 -> switch (rowID) {
                case 0 -> secondPlayerCloseCombatRow;
                case 1 -> secondPlayerRangedRow;
                case 2 -> secondPlayerSiegeRow;
                default -> row;
            };
            default -> null;
        };
        return row;
    }

    private int getNumberOfCards(Card card, ArrayList<Card> cards) {
        int num = 0;
        for (Card card1 : cards) {
            if (card1.equals(card)) num++;
        }
        return num;
    }

    private int getNumberOfCardsWithName(String name, ArrayList<Card> cards) {
        int num = 0;
        for (Card card : cards) {
            if (Objects.equals(card.getName(), "name")) num++;
        }
        return num;
    }


    private ArrayList<Card> getCardsWithAbility(Ability ability, ArrayList<Card> cards) {
        ArrayList<Card> retVal = new ArrayList<>();
        for (Card card : cards) {
            UnitCard unitCard = (UnitCard) card;
            if (unitCard.getAbility() == ability) retVal.add(card);
        }
        return retVal;
    }

    private void applyMardroeme(int userID, int rowID) {
        ArrayList<Card> row = getRowByID(userID, rowID);
        int berserkerNum = 0;
        ArrayList<Card> toRemove = new ArrayList<>();
        for (Card card : row) {
            UnitCard unitCard = (UnitCard) card;
            if (unitCard.getAbility() == Ability.MARDROEME) {
                berserkerNum++;
                toRemove.add(unitCard);
            }
        }
        row.removeAll(toRemove);
        UnitCard unitCard = new UnitCard(UnitCardInfo.BEAR);
        for (int i = 0; i < berserkerNum; i++) {
            row.add(unitCard);

        }
    }

}
