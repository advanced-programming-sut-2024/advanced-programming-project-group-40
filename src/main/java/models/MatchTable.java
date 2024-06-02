package models;

import enums.Ability;
import enums.Factions;
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
    private boolean opponentPassed;
    private int round;
    private Date date;
    private final ArrayList<Integer> firstPlayerPoints = new ArrayList<>();
    private final ArrayList<Integer> secondPlayerPoints = new ArrayList<>();
    private int firstPlayerCurrentPoint;
    private int secondPlayerCurrentPoint;
    private final ArrayList<Integer> firstPlayerRowPoints = new ArrayList<>(Arrays.asList(0, 0, 0));
    private final ArrayList<Integer> secondPlayerRowPoints = new ArrayList<>(Arrays.asList(0, 0, 0));

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
    private int firstPlayerCrystals;
    private int secondPlayerCrystals;
    private final ArrayList<SpecialCard> spellCards = new ArrayList<>();
    private Leader firstPlayerLeader;
    private Leader secondPlayerLeader;


    public MatchTable(User firstPlayer, User secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
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
        ArrayList<Card> tightBondCards = getCardsWithAbility(Ability.TIGHT_BOND,row);
        ArrayList<Card> moralBoostCards = getCardsWithAbility(Ability.MORAL_BOOST,row);
        if (areCardsUnderWeather){
            for (Card card : row){
                UnitCard unitCard = (UnitCard) card;
                unitCard.setShowingPower(1);
            }
        }
        for (Card card : row){
            UnitCard unitCard = (UnitCard) card;
            if (tightBondCards.contains(card)){
                unitCard.setShowingPower(unitCard.getShowingPower() * getNumberOfCards(card,tightBondCards));
            }
        }
        for (Card card : row){
            UnitCard unitCard = (UnitCard) card;
            if (moralBoostCards.contains(card)){
                unitCard.setShowingPower(unitCard.getShowingPower()+ moralBoostCards.size()-1);
            }else {
                unitCard.setShowingPower(unitCard.getShowingPower()+ moralBoostCards.size());
            }
        }
        if (areCardsBoosted){
            for (Card card : row){
                UnitCard unitCard = (UnitCard) card;
                unitCard.setShowingPower(unitCard.getShowingPower()*2);
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

    public ArrayList<Card> randomSelectedCards(ArrayList<Card> deck) {
        return null;
    }

    public void placeCard(Card card, int rowNumber) {

    }

    public void addToCloseCombatRow(Card card) {

    }

    public void addToRangeRow(Card card) {

    }

    public void addToSiegeRow(Card card) {

    }

    public void addToSpellCards(SpecialCard specialCard) {

    }

    public void addToInPlayCards(Card card) {

    }

    public void addToDeadCards(Card card) {

    }

    public void updatePoints() {

    }

    public void calculatePlayersPoints() {

    }

    public void leaderAction() {
        LeaderActions.doActionByName(firstPlayerLeader.getName(), this);
    }

    public void factionAction(Factions faction) {
        FactionActions.doActionByName(faction.name(), this);
    }

    public void startTurn() {

    }

    public void endTurn() {

    }

    public boolean isRoundFinished() {
        return true;
    }

    public boolean isMatchFinished() {
        return true;
    }

    public void clearMatchTable() {

    }

    public void clearSpecialCards() {

    }

    public void reduceCrystal() {

    }

    public User winningUser() {
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

    //checks for comander horn in boost card place and
    // for specials cards in the given row that give the commanders horn effect
    private boolean isRowUnderBoost(int user_id, int rowID) {
        ArrayList<Card> row = getRowByID(user_id, rowID);
        boolean x;
        switch (user_id) {
            case 0:
                x = getaBoolean(rowID, firstPlayerCloseCombatBoostCard, firstPlayerRangedBoostCard, firstPlayerSiegeBoostCard);
                if (x) return true;
                break;
            case 1:
                x = getaBoolean(rowID, secondPlayerCloseCombatBoostCard, secondPlayerRangedBoostCard, secondPlayerSiegeBoostCard);
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
    private boolean getaBoolean(int rowID, Card secondPlayerCloseCombatBoostCard,
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


    private ArrayList<Card> getCardsWithAbility(Ability ability,ArrayList<Card> cards){
        ArrayList<Card> retVal = new ArrayList<>();
        for (Card card: cards){
            UnitCard unitCard = (UnitCard) card;
            if (unitCard.getAbility() == ability) retVal.add(card);
        }
        return retVal;
    }

}
