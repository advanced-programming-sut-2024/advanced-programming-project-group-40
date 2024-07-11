package Server.Models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import enums.cards.*;
import models.Chat.Message;
import models.Chat.PublicChat;
import models.Chat.ReplyData;
import models.MatchTable;
import models.cards.*;

import java.util.ArrayList;
import java.util.Objects;

public class GameBoardVisualData {
    ArrayList<CardInfo> firstPlayerInPlay = new ArrayList<>();
    ArrayList<CardInfo> secondPlayerInPlay = new ArrayList<>();
    ArrayList<CardInfo> firstPlayerDiscard = new ArrayList<>();
    ArrayList<CardInfo> secondPlayerDiscard = new ArrayList<>();
    ArrayList<CardInfo> firstPlayerDeck = new ArrayList<>();
    ArrayList<CardInfo> secondPlayerDeck = new ArrayList<>();
    ArrayList<CardInfo> firstPlayerCC = new ArrayList<>();
    ArrayList<CardInfo> firstPlayerRanged = new ArrayList<>();
    ArrayList<CardInfo> firstPlayerSiege = new ArrayList<>();
    ArrayList<CardInfo> secondPlayerCC = new ArrayList<>();
    ArrayList<CardInfo> secondPlayerRanged = new ArrayList<>();
    ArrayList<CardInfo> secondPlayerSiege = new ArrayList<>();
    CardInfo firstPlayerCCSpecial;
    CardInfo firstPlayerRangedSpecial;
    CardInfo firstPlayerSiegeSpecial;
    CardInfo secondPlayerCCSpecial;
    CardInfo secondPlayerRangedSpecial;
    CardInfo secondPlayerSiegeSpecial;
    ArrayList<CardInfo> weather = new ArrayList<>();
    LeaderInfo firstPlayerLeader;
    LeaderInfo secondPlayerLeader;
    int firstPlayerCrystals;
    int secondPlayerCrystals;
    boolean isFirstPlayerTurn;
    boolean isMatchFinished;

    ArrayList<Integer> firstPlayerPoints = new ArrayList<>(3);
    ArrayList<Integer> secondPlayerPoints = new ArrayList<>(3);

    String firstPlayerNickName;
    String secondPlayerNickName;

    String firstPlayerFaction;
    String secondPlayerFaction;
    boolean isDestroyer;
    boolean isMedic;
    boolean isRedRider;
    boolean isKingOfWildHunt;
    boolean isImperialMajesty;
    String Recation;

    String username;
    String message;
    boolean isReply;
    String userName;
    String time;
    boolean isThereAMessage = false;

    public GameBoardVisualData(MatchTable matchTable
            , boolean isDestroyer, boolean isMedic, boolean isRedRider, boolean isKingOfWildHunt, boolean isImperialMajesty) {
            InitializeArrays(matchTable);
            InitializeVariables(matchTable);

        this.isDestroyer = isDestroyer;
        this.isMedic = isMedic;
        this.isRedRider = isRedRider;
        this.isKingOfWildHunt = isKingOfWildHunt;
        this.isImperialMajesty = isImperialMajesty;
    }

    private void InitializeArrays(MatchTable matchTable) {

            fillInfoArray(firstPlayerInPlay, matchTable.getFirstPlayerInPlayCards());
            fillInfoArray(secondPlayerInPlay, matchTable.getSecondPlayerInPlayCards());

            fillInfoArray(firstPlayerDiscard, matchTable.getFirstPlayerDeadCards());
            fillInfoArray(secondPlayerDiscard, matchTable.getSecondPlayerDeadCards());

            fillInfoArray(firstPlayerDeck, matchTable.getFirstPlayerDeckCards());
            fillInfoArray(secondPlayerDeck, matchTable.getSecondPlayerDeckCards());


            fillInfoArray(firstPlayerCC, matchTable.getFirstPlayerCloseCombatRow());
            fillInfoArray(firstPlayerRanged, matchTable.getFirstPlayerRangedRow());
            fillInfoArray(firstPlayerSiege, matchTable.getFirstPlayerSiegeRow());

            fillInfoArray(secondPlayerCC, matchTable.getSecondPlayerCloseCombatRow());
            fillInfoArray(secondPlayerRanged, matchTable.getSecondPlayerRangedRow());
            fillInfoArray(secondPlayerSiege, matchTable.getSecondPlayerSiegeRow());

            fillInfoArray(weather, matchTable.getSpellCards());

    }

    private void InitializeVariables(MatchTable matchTable) {
            firstPlayerCCSpecial = getCardInfoFromCard(matchTable.getFirstPlayerCloseCombatBoostCard());
            firstPlayerRangedSpecial = getCardInfoFromCard(matchTable.getFirstPlayerRangedBoostCard());
            firstPlayerSiegeSpecial = getCardInfoFromCard(matchTable.getFirstPlayerSiegeBoostCard());

            secondPlayerCCSpecial = getCardInfoFromCard(matchTable.getSecondPlayerCloseCombatBoostCard());
            secondPlayerRangedSpecial = getCardInfoFromCard(matchTable.getSecondPlayerRangedBoostCard());
            secondPlayerSiegeSpecial = getCardInfoFromCard(matchTable.getSecondPlayerSiegeBoostCard());
            if (matchTable.getFirstPlayerLeader() != null)
                firstPlayerLeader = matchTable.getFirstPlayerLeader().getLeaderInfo();
            if (matchTable.getSecondPlayerLeader() != null)
                secondPlayerLeader = matchTable.getSecondPlayerLeader().getLeaderInfo();

            firstPlayerCrystals = matchTable.getFirstPlayerCrystals();
            secondPlayerCrystals = matchTable.getSecondPlayerCrystals();
            firstPlayerPoints.add(matchTable.getPlayerRowScore(0, 0));
            firstPlayerPoints.add(matchTable.getPlayerRowScore(0, 1));
            firstPlayerPoints.add(matchTable.getPlayerRowScore(0, 2));
            secondPlayerPoints.add(matchTable.getPlayerRowScore(1, 0));
            secondPlayerPoints.add(matchTable.getPlayerRowScore(1, 1));
            secondPlayerPoints.add(matchTable.getPlayerRowScore(1, 2));
            firstPlayerNickName = matchTable.getFirstPlayer().getUsername();
            secondPlayerNickName = matchTable.getSecondPlayer().getUsername();
            firstPlayerFaction = matchTable.getFirstPlayer().getFaction().name;
            secondPlayerFaction = matchTable.getSecondPlayer().getFaction().name;
            isFirstPlayerTurn = matchTable.isFirstPlayerTurn();
            isMatchFinished = matchTable.isMatchFinished();

    }

    //////////////////////////Serializations
    public String toJSON() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(CardInfo.class, new InterfaceAdapter());
        Gson gson = gsonBuilder.create();
        return gson.toJson(this);
    }

    public static GameBoardVisualData deSerialize(String JSON) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(CardInfo.class, new InterfaceAdapter());
        Gson gson = gsonBuilder.create();
        return gson.fromJson(JSON, GameBoardVisualData.class);
    }
    /////////////////////////////////


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isReply() {
        return isReply;
    }

    public void setReply(boolean reply) {
        isReply = reply;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {

        return time;
    }

    public boolean isThereAMessage() {
        return isThereAMessage;
    }

    public void setTime(String time) {
        isThereAMessage = true;
        this.time = time;
    }

    public String getReaction() {
        return Recation;
    }

    public void setRecation(String Recation) {
        this.Recation = Recation;
    }


    public boolean isDestroyer() {
        return isDestroyer;
    }

    public boolean isImperialMajesty() {
        return isImperialMajesty;
    }

    public boolean isMedic() {
        return isMedic;
    }

    public boolean isRedRider() {
        return isRedRider;
    }

    public boolean isKingOfWildHunt() {
        return isKingOfWildHunt;
    }

    //getters
    public Card getBoost(int userID, int Row) {
        switch (userID) {
            case 0:
                switch (Row) {
                    case 0:
                        if (firstPlayerCCSpecial != null)
                            return new SpecialCard((SpecialCardInfo) firstPlayerCCSpecial);

                    case 1:
                        if (firstPlayerRangedSpecial != null)
                            return new SpecialCard((SpecialCardInfo) firstPlayerRangedSpecial);

                    case 2:
                        if (firstPlayerSiegeSpecial != null)
                            return new SpecialCard((SpecialCardInfo) firstPlayerSiegeSpecial);

                    default:
                        return null;
                }

            case 1:
                switch (Row) {
                    case 0:
                        if (secondPlayerCCSpecial != null)
                            return new SpecialCard((SpecialCardInfo) secondPlayerCCSpecial);

                    case 1:
                        if (secondPlayerRangedSpecial != null)
                            return new SpecialCard((SpecialCardInfo) secondPlayerRangedSpecial);

                    case 2:
                        if (secondPlayerSiegeSpecial != null)
                            return new SpecialCard((SpecialCardInfo) secondPlayerSiegeSpecial);

                    default:
                        return null;
                }
            default:
                return null;
        }
    }

    public int getRowPoints(int userID, int row) {
        if (userID == 0) {
            return firstPlayerPoints.get(row);
        } else {
            return secondPlayerPoints.get(row);
        }
    }

    public boolean isFirstPlayerTurn() {
        return isFirstPlayerTurn;
    }

    public int getTotalPoints(int userID) {
        if (userID == 0) {
            return firstPlayerPoints.get(0) + firstPlayerPoints.get(1) + firstPlayerPoints.get(2);
        } else {
            return secondPlayerPoints.get(0) + secondPlayerPoints.get(1) + secondPlayerPoints.get(2);
        }
    }

    public boolean isMatchFinished() {
        return isMatchFinished;
    }

    public String getFaction(int userID) {
        if (userID == 0) {
            return firstPlayerFaction;
        } else {
            return secondPlayerFaction;
        }
    }

    public String getNickName(int userID) {
        if (userID == 0) {
            return firstPlayerNickName;
        } else {
            return secondPlayerNickName;
        }
    }

    public ArrayList<Card> getCardArrayByArrayName(String name) {
        return switch (name) {
            case "firstPlayerInPlay" -> getCardsFromEnumArray(firstPlayerInPlay);
            case "secondPlayerInPlay" -> getCardsFromEnumArray(secondPlayerInPlay);
            case "firstPlayerDiscard" -> getCardsFromEnumArray(firstPlayerDiscard);
            case "secondPlayerDiscard" -> getCardsFromEnumArray(secondPlayerDiscard);
            case "firstPlayerDeck" -> getCardsFromEnumArray(firstPlayerDeck);
            case "secondPlayerDeck" -> getCardsFromEnumArray(secondPlayerDeck);
            case "firstPlayerCC" -> getCardsFromEnumArray(firstPlayerCC);
            case "firstPlayerRanged" -> getCardsFromEnumArray(firstPlayerRanged);
            case "firstPlayerSiege" -> getCardsFromEnumArray(firstPlayerSiege);
            case "secondPlayerCC" -> getCardsFromEnumArray(secondPlayerCC);
            case "secondPlayerRanged" -> getCardsFromEnumArray(secondPlayerRanged);
            case "secondPlayerSiege" -> getCardsFromEnumArray(secondPlayerSiege);
            case "weather" -> getCardsFromEnumArray(weather);
            default -> null;
        };
    }


    public int getCrystals(int userID) {
        if (userID == 0) return firstPlayerCrystals;
        else return secondPlayerCrystals;
    }

    public Card getLeader(int userID) {
        if (userID == 0) {
            if (firstPlayerLeader == null) return null;
            return new Leader(firstPlayerLeader);
        } else {
            if (secondPlayerLeader == null) return null;
            return new Leader(secondPlayerLeader);
        }
    }


    ////////////////////// private static fields
    private static ArrayList<Card> getCardsFromEnumArray(ArrayList<CardInfo> cardInfos) {
        ArrayList<Card> cards = new ArrayList<>();
        for (CardInfo cardInfo : cardInfos) {
            if (cardInfo instanceof UnitCardInfo unitCardInfo) {
                cards.add(new UnitCard(unitCardInfo));
            }
            if (cardInfo instanceof SpecialCardInfo specialCardInfo) {
                cards.add(new SpecialCard(specialCardInfo));
            }
            if (cardInfo instanceof HeroInfo heroInfo) {
                cards.add(new Hero(heroInfo));
            }
        }
        return cards;
    }

    public static Card getCardsFromEnum(CardInfo cardInfo) {
        Card card = null;
        if (cardInfo instanceof UnitCardInfo unitCardInfo) {
            card = new UnitCard(unitCardInfo);
        }
        if (cardInfo instanceof SpecialCardInfo specialCardInfo) {
            card = new SpecialCard(specialCardInfo);
        }
        if (cardInfo instanceof HeroInfo heroInfo) {
            card = new Hero(heroInfo);
        }

        return card;
    }

    private static void fillInfoArray(ArrayList<CardInfo> infoArray, ArrayList<Card> cardArray) {
        if (!cardArray.isEmpty()) {
            for (Card card : cardArray) {
                infoArray.add(getCardInfoFromCard(card));
            }
        }
    }

    public static CardInfo getCardInfoFromCard(Card card) {
        CardInfo cardInfo = null;
        if (card instanceof UnitCard unitCard) {
            cardInfo = unitCard.getUnitCardInfo();
        } else if (card instanceof Hero hero) {
            cardInfo = hero.getHeroInfo();
        } else if (card instanceof SpecialCard specialCard) {
            cardInfo = specialCard.getSpecialCardInfo();
        }
        return cardInfo;
    }

}





