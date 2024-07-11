package models;

import enums.Factions;
import enums.cards.LeaderInfo;
import models.cards.Card;
import models.cards.Leader;


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
    private transient ArrayList<Card> deckCards = new ArrayList<>();
    private final ArrayList<String> deckCardsName = new ArrayList<>();
    private transient Leader leader;
    private String leaderName;

    public void setMatchesPlayed(ArrayList<MatchTable> matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    private transient ArrayList<MatchTable> matchesPlayed = new ArrayList<>();
    private boolean stayLoggedIn;
    private transient ArrayList<User> frineds = new ArrayList<>();
    private transient ArrayList<User> requests = new ArrayList<>();
    private transient ArrayList<User> requestsHasSent = new ArrayList<>();
    private transient ArrayList<User> rejectedRequests = new ArrayList<>();
    private transient ArrayList<User> gameRequests = new ArrayList<>();
    private transient ArrayList<User> gameRequestsHasSent = new ArrayList<>();
    private transient ArrayList<User> gameRejectedRequests = new ArrayList<>();
    private int numberOfUnitCards;
    private int numberOfSpecialCards;
    private int numberOfHeroCards;
    private int totalUnitCardsStrength;

    public User(String username, String password, String email, String nickName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickName;
        this.stayLoggedIn = false;
        faction = Factions.MONSTERS;
        leaderName = LeaderInfo.BRINGER_OF_DEATH.name;
        leader = new Leader(LeaderInfo.BRINGER_OF_DEATH);
    }

    public User(String username, String password, String email, String nickName, boolean test) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.setSecurityAnswer(nickName);
        this.stayLoggedIn = false;
    }


    public int getRank() {
        return 0;
    }

    public int getHighestScore() {
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

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
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

    public ArrayList<User> getGameRequests() {
        return gameRequests;
    }

    public ArrayList<User> getGameRequestsHasSent() {
        return gameRequestsHasSent;
    }

    public ArrayList<User> getGameRejectedRequests() {
        return gameRejectedRequests;
    }

    public ArrayList<User> getRejectedRequests() {
        return rejectedRequests;
    }


    public ArrayList<User> getFrineds() {
        return frineds;
    }

    public ArrayList<User> getRequestsHasSent() {
        return requestsHasSent;
    }

    public ArrayList<User> getRequests() {
        return requests;
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

    public ArrayList<String> getDeckCardsName() {
        return deckCardsName;
    }

    public void createDeckCards(){
        deckCards = new ArrayList<>();
        for (String name : deckCardsName){
            deckCards.add(Card.getCardByName(name));
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, nickname, faction);
    }


    public void setDeckCards(ArrayList<Card> cards) {
        deckCards.clear();
        deckCards.addAll(cards);
    }
}
