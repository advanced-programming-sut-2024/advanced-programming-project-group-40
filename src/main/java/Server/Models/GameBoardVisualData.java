package Server.Models;

import enums.cards.CardInfo;
import enums.cards.LeaderInfo;
import models.MatchTable;
import models.cards.*;

import java.util.ArrayList;

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
    public GameBoardVisualData(MatchTable matchTable) {
        InitializeArrays(matchTable);
        InitializeVariables(matchTable);
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

        firstPlayerLeader = matchTable.getFirstPlayerLeader().getLeaderInfo();
        secondPlayerLeader = matchTable.getSecondPlayerLeader().getLeaderInfo();

        firstPlayerCrystals = matchTable.getFirstPlayerCrystals();
        secondPlayerCrystals = matchTable.getSecondPlayerCrystals();
    }
    private static void fillInfoArray(ArrayList<CardInfo> infoArray , ArrayList<Card> cardArray){
        for (Card card : cardArray){
            infoArray.set(cardArray.indexOf(card),getCardInfoFromCard(card));
        }
    }
    private static CardInfo getCardInfoFromCard(Card card) {
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
