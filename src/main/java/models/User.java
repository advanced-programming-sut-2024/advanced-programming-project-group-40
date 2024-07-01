package models;

import models.cards.Card;
import models.cards.Leader;

import java.util.*;

public class User {
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String faction = "Nilfgaardian Empire";
    private int point;
    private int gamesPlayed;
    private int won;
    private int draw;
    private int lost;
    //    private final ArrayList<Card> allCards = new ArrayList<>();
    private String SecurityAnswer;
    private int SecurityQuestionNumber;
    private final ArrayList<Card> deckCards = new ArrayList<>();
    private Leader leader;
    private final ArrayList<MatchTable> matchesPlayed = new ArrayList<>();
    private boolean stayLoggedIn;


    public User(String username, String password, String email, String nickName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickName;
        this.stayLoggedIn = false;
    }

    public int getRank() {
        return 0;
    }

    public int getHighestScore() {
        // todo check if player one is the desired user
        int highestScore = 0;
        for (MatchTable match : matchesPlayed) {
            if (match.getPlayerTotalScore(0) > highestScore) {
                highestScore = match.getPlayerTotalScore(0);
            }
        }
        return highestScore;
    }

    public int getNumberOfMatches(){
        return matchesPlayed.size();
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSecurityAnswer() {
        return SecurityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        SecurityAnswer = securityAnswer;
    }

    public void setSecurityQuestionNumber(int securityQuestionNumber) {
        SecurityQuestionNumber = securityQuestionNumber;
    }

    public int getSecurityQuestionNumber() {
        return SecurityQuestionNumber;
    }

    public boolean isStayLoggedIn() {
        return stayLoggedIn;
    }

    public void setStayLoggedIn(boolean stayLoggedIn) {
        this.stayLoggedIn = stayLoggedIn;
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

    public void addToGamesPlayed() {
        gamesPlayed++;
    }

    public void addToWin() {
        won++;
    }

    public void addToDraw() {
        draw++;
    }

    public void addToLost() {
        lost++;
    }

    public void addToMatchesPlayed(MatchTable matchTable) {
        matchesPlayed.add(matchTable);
    }

    public void addPoint(int addingPoint) {
        point += addingPoint;
    }


    //this function checks if two users are the same by checking the USERNAME & PASSWORD & EMAIL & NICKNAME & FACTION
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(nickname, user.nickname) &&
                Objects.equals(faction, user.faction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, nickname, faction);
    }


}
