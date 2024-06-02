package models;

import enums.Factions;
import enums.Unit;
import models.actions.FactionActions;
import models.actions.LeaderActions;
import models.cards.Card;
import models.cards.Leader;
import models.cards.SpecialCard;

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
    private final ArrayList<Card> secondPlayerCloseCombatRow = new ArrayList<>(Collections.singletonList(null));
    private final ArrayList<Card> firstPlayerRangedRow = new ArrayList<>(Collections.singletonList(null));
    private final ArrayList<Card> secondPlayerRangedRow = new ArrayList<>(Collections.singletonList(null));
    private final ArrayList<Card> firstPlayerSiegeRow = new ArrayList<>(Collections.singletonList(null));
    private final ArrayList<Card> secondPlayerSiegeRow = new ArrayList<>(Collections.singletonList(null));
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

    public static int getPlayerTotalScore(User user) {
        return 0;
    }

    public static int getPlayerRowScore(User user, int rowNumber) {
        return 0;
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

    public ArrayList<Card> randomSelectedCards(ArrayList<Card> deck){
        return null;
    }
    public void placeCard(Card card, int rowNumber){

    }
    public void addToCloseCombatRow(Card card){

    }
    public void addToRangeRow(Card card){

    }
    public void addToSiegeRow(Card card){

    }
    public void addToSpellCards(SpecialCard specialCard){

    }
    public void addToInPlayCards(Card card){

    }
    public void addToDeadCards(Card card){

    }
    public void updatePoints(){

    }
    public void calculatePlayersPoints(){

    }
    public void leaderAction(){
        LeaderActions.doActionByName(firstPlayerLeader.getName(), this);
    }
    public void factionAction(Factions faction){
        FactionActions.doActionByName(faction.name(), this);
    }
    public void startTurn(){

    }
    public void endTurn(){

    }
    public boolean isRoundFinished(){
        return true;
    }
    public boolean isMatchFinished(){
        return true;
    }
    public void clearMatchTable(){

    }
    public void clearSpecialCards(){

    }
    public void reduceCrystal(){

    }
    public User winningUser(){
        return null;
    }
}
