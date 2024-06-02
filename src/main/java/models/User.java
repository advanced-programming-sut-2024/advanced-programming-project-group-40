package models;

import models.cards.Card;
import models.cards.Leader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

public class User {
    private String username;
    private String password;
    private String email;
    private String nickName;
    private String faction = "Nilfgaardian Empire";
    private int point;
    private int gamesPlayed;
    private int won;
    private int draw;
    private int lost;
    //    private final ArrayList<Card> allCards = new ArrayList<>();
    private final HashMap<Integer, String> securityQuestion = new HashMap<>();
    private final ArrayList<Card> deckCards = new ArrayList<>();
    private Leader leader;
    private final ArrayList<MatchTable> matchesPlayed = new ArrayList<>();

    public User(String username, String password, String email, String nickName, int questionNumber, String answer) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
        this.securityQuestion.put(questionNumber, answer);
    }

    public int getRank() {
        return 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }

    public HashMap<Integer, String> getSecurityQuestion() {
        return securityQuestion;
    }

    public ArrayList<Card> getDeckCards() {
        return deckCards;
    }
    public int getPoint() {
        return point;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getWon() {
        return won;
    }

    public int getDraw() {
        return draw;
    }

    public int getLost() {
        return lost;
    }

    public ArrayList<MatchTable> getMatchesPlayed() {
        return matchesPlayed;
    }

    public void addToGamesPlayed(){
        gamesPlayed++;
    }
    public void addToWin(){
        won++;
    }
    public void addToDraw(){
        draw++;
    }
    public void addToLost(){
        lost++;
    }
    public void addToMatchesPlayed(MatchTable matchTable){
        matchesPlayed.add(matchTable);
    }
    public void addPoint(int addingPoint){
        point += addingPoint;
    }
}
