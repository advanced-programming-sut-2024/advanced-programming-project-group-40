package models;

import enums.Factions;
import enums.cards.LeaderInfo;
import models.cards.Card;
import models.cards.Leader;

import java.net.InetAddress;
import java.util.*;

public class User {
    private String username;
    private String password;
    private String email;
    private String nickname;
    private Factions faction;
    private int point;
    private int gamesPlayed;
    private int won;
    private int draw;
    private int lost;
    private String SecurityAnswer;
    private int SecurityQuestionNumber;
    private transient final ArrayList<Card> deckCards = new ArrayList<>();
    private transient Leader leader;
    private transient final ArrayList<MatchTable> matchesPlayed = new ArrayList<>();
    private boolean stayLoggedIn;
    private int numberOfUnitCards;
    private int numberOfSpecialCards;
    private int numberOfHeroCards;
    private int totalUnitCardsStrength;
    public InetAddress ip;
    public int port;

    public User(String username, String password, String email, String nickName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickName;
        this.stayLoggedIn = false;
        faction = Factions.MONSTERS;
        leader = new Leader(LeaderInfo.BRINGER_OF_DEATH);
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

    public int getNumberOfMatches() {
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

    public Factions getFaction() {
        return faction;
    }

    public void setFaction(Factions faction) {
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

    public int getNumberOfUnitCards() {
        return numberOfUnitCards;
    }

    public void setNumberOfUnitCards(int numberOfUnitCards) {
        this.numberOfUnitCards = numberOfUnitCards;
    }

    public int getNumberOfSpecialCards() {
        return numberOfSpecialCards;
    }

    public void setNumberOfSpecialCards(int numberOfSpecialCards) {
        this.numberOfSpecialCards = numberOfSpecialCards;
    }

    public int getNumberOfHeroCards() {
        return numberOfHeroCards;
    }

    public void setNumberOfHeroCards(int numberOfHeroCards) {
        this.numberOfHeroCards = numberOfHeroCards;
    }

    public int getTotalUnitCardsStrength() {
        return totalUnitCardsStrength;
    }

    public void setTotalUnitCardsStrength(int totalUnitCardsStrength) {
        this.totalUnitCardsStrength = totalUnitCardsStrength;
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

    public void removeCardFromDeck(Card card) {
        if (!deckCards.isEmpty()) {
            for (Card card1 : deckCards) {
                if (card1.getName().equals(card.getName())) {
                    deckCards.remove(card1);
                    break;
                }
            }
        }
    }
    public int cardsInDeckFromCardName(String cardName) {
        int count = 0;
        for (Card card : deckCards) {
            if (card.getName().equals(cardName)) {
                count++;
            }
        }
        return count;
    }
    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, nickname, faction);
    }


}
