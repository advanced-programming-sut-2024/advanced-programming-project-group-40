package controllers.MenuController;

import Server.Models.GameBoardVisualData;
import enums.Ability;
import enums.Origin;
import enums.cards.CardInfo;
import models.Chat.Message;
import models.Chat.ReplyData;
import models.MatchTable;
import models.UserInputHandler.CardClickCommand;
import models.User;
import models.cards.*;
import views.ViewController.GameViewController;

import java.util.Date;
import java.util.Objects;


public class GameMenuController {
    private  MatchTable matchTable;
    private  boolean isNewWindowOpen = false;
    private  boolean isMedic = false;
    private  boolean isKingOfWildHunt = false;
    private  boolean isRedRider = false;
    private  boolean isDestroyer = false;

    public  void setMatchTable(MatchTable matchTable) {
        this.matchTable = matchTable;
    }

    private  Card selectedCard;

    public  void ClickedOnCard(CardInfo cardInfo, boolean isSelectable, String parentID) {
        Card selectedCard1 = GameBoardVisualData.getCardsFromEnum(cardInfo);
        if (isNewWindowOpen) {
            if (isMedic) {
                if (matchTable.isFirstPlayerTurn()) {
                    matchTable.doMedic(new CardWrapper(selectedCard1, Origin.FIRSTPLATER_DEAD));
                } else {
                    matchTable.doMedic(new CardWrapper(selectedCard1, Origin.SECONDPLAYER_DEAD));
                }
                isMedic = false;
                matchTable.endTurn();
            }
            if (isRedRider) {
                if (matchTable.isFirstPlayerTurn()) {
                    matchTable.addToSpellCards(new CardWrapper(selectedCard1, Origin.FIRSTPLAYER_DECK));
                } else {
                    matchTable.addToSpellCards(new CardWrapper(selectedCard1, Origin.SECONDPLAYER_DECK));
                }
                isRedRider = false;
            }
            if (isDestroyer) {
                if (matchTable.isFirstPlayerTurn()) {

                    matchTable.addToInPlayCards(0, new CardWrapper(selectedCard1, Origin.FIRSTPLAYER_DECK));
                } else {
                    matchTable.addToInPlayCards(1, new CardWrapper(selectedCard1, Origin.SECONDPLAYER_DECK));
                }
                isDestroyer = false;
            }
            if (isKingOfWildHunt) {
                if (matchTable.isFirstPlayerTurn()) {

                    matchTable.addToInPlayCards(0, new CardWrapper(selectedCard1, Origin.FIRSTPLAYER_DECK));
                } else {
                    matchTable.addToInPlayCards(1, new CardWrapper(selectedCard1, Origin.SECONDPLAYER_DECK));
                }
                isKingOfWildHunt = false;
            }
            isNewWindowOpen = false;
        } else {
            if (isSelectable) selectedCard = selectedCard1;
            else {
                if (selectedCard != null) {
                    if (Objects.equals(selectedCard.getName(), "Decoy")) {
                        matchTable.doDecoy(new CardWrapper(selectedCard, getCardOrigin("Hand", matchTable.isFirstPlayerTurn())),
                                new CardWrapper(selectedCard1, getCardOrigin(parentID, matchTable.isFirstPlayerTurn())), matchTable.isFirstPlayerTurn());
                        selectedCard = null;
                    }
                }
                matchTable.endTurn();
            }
        }
        sendData(false, false, false, false, false);
    }

    private  Origin GetDestination() {
        if (selectedCard instanceof UnitCard unitCard) {
            if (unitCard.getAbility() == Ability.SPY) {
                switch (unitCard.getUnit()) {
                    case AGILE -> {
                        return Origin.SECONDPLAYER_AGILE;
                    }
                    case CLOSE_COMBAT -> {
                        return Origin.SECONDPLAYER_CLOSECOMBAT;
                    }
                    case SIEGE -> {
                        return Origin.SECONDPLAYER_SIEGE;

                    }
                    case RANGED -> {
                        return Origin.SECONDPLAYER_RANGED;
                    }
                }
            } else {
                switch (unitCard.getUnit()) {
                    case AGILE -> {
                        return Origin.FIRSTPLAYER_AGILE;
                    }
                    case CLOSE_COMBAT -> {
                        return Origin.FIRSTPLAYER_CLOSECOMBAT;
                    }
                    case SIEGE -> {
                        return Origin.FIRSTPLAYER_SIEGE;

                    }
                    case RANGED -> {
                        return Origin.FIRSTPLAYER_RANGED;

                    }
                    case All -> {
                        return Origin.FIRSTPLAYER_ALL;
                    }
                }
            }

        }
        if (selectedCard instanceof Hero hero) {
            if (hero.getAbility() == Ability.SPY) {
                switch (hero.getUnit()) {
                    case AGILE -> {
                        return Origin.SECONDPLAYER_AGILE;
                    }
                    case CLOSE_COMBAT -> {
                        return Origin.SECONDPLAYER_CLOSECOMBAT;
                    }
                    case SIEGE -> {
                        return Origin.SECONDPLAYER_SIEGE;

                    }
                    case RANGED -> {
                        return Origin.SECONDPLAYER_RANGED;
                    }
                }
            } else {
                switch (hero.getUnit()) {
                    case AGILE -> {
                        return Origin.SECONDPLAYER_AGILE;

                    }
                    case CLOSE_COMBAT -> {
                        return Origin.FIRSTPLAYER_CLOSECOMBAT;
                    }
                    case SIEGE -> {
                        return Origin.FIRSTPLAYER_SIEGE;

                    }
                    case RANGED -> {
                        return Origin.FIRSTPLAYER_RANGED;

                    }
                }
            }
        }
        if (selectedCard instanceof SpecialCard specialCard) {
            if (Objects.equals(specialCard.getName(), "Commander's horn")) {
                return Origin.FIRSTPLAYER_ALL;
            } else {
                return Origin.WEATHER;

            }
        } else return null;
    }


    public  GameBoardVisualData initiateDeck() {
        //matchTable.getFirstPlayer().getMatchesPlayed().add(matchTable);
        //matchTable.getSecondPlayer().getMatchesPlayed().add(matchTable);
        matchTable.initilizeTable();
        return sendData(false, false, false, false, false);
    }


    private  int getRowID(Origin origin) {
        switch (origin) {
            case FIRSTPLAYER_CLOSECOMBAT, SECONDPLAYER_CLOSECOMBAT -> {
                return 0;
            }
            case FIRSTPLAYER_RANGED, SECONDPLAYER_RANGED -> {
                return 1;
            }
            case FIRSTPLAYER_SIEGE, SECONDPLAYER_SIEGE -> {
                return 2;
            }
        }
        return -1;
    }

    private  Origin getCardOrigin(String parentID, boolean isFirstPlayerTurn) {
        Origin origin;
        if (isFirstPlayerTurn) {
            switch (parentID) {
                case "firstPlayerCloseCombat" -> origin = Origin.FIRSTPLAYER_CLOSECOMBAT;
                case "firstPlayerRanged" -> origin = Origin.FIRSTPLAYER_RANGED;
                case "firstPlayerSiege" -> origin = Origin.FIRSTPLAYER_SIEGE;
                case "secondPlayerCloseCombat" -> origin = Origin.SECONDPLAYER_CLOSECOMBAT;
                case "secondPlayerRanged" -> origin = Origin.SECONDPLAYER_RANGED;
                case "secondPlayerSiege" -> origin = Origin.SECONDPLAYER_SIEGE;
                case "spellCards" -> origin = Origin.WEATHER;
                case "firstPlayerDiscard" -> origin = Origin.FIRSTPLATER_DEAD;
                case "secondPlayerDiscard" -> origin = Origin.SECONDPLAYER_DEAD;
                case "Hand" -> origin = Origin.FIRSTPLAYER_INPLAY;
                default -> origin = Origin.NULL;
            }

        } else {
            switch (parentID) {
                case "firstPlayerCloseCombat" -> origin = Origin.SECONDPLAYER_CLOSECOMBAT;
                case "firstPlayerRanged" -> origin = Origin.SECONDPLAYER_RANGED;
                case "firstPlayerSiege" -> origin = Origin.SECONDPLAYER_SIEGE;
                case "secondPlayerCloseCombat" -> origin = Origin.FIRSTPLAYER_CLOSECOMBAT;
                case "secondPlayerRanged" -> origin = Origin.FIRSTPLAYER_RANGED;
                case "secondPlayerSiege" -> origin = Origin.FIRSTPLAYER_SIEGE;
                case "spellCards" -> origin = Origin.WEATHER;
                case "firstPlayerDiscard" -> origin = Origin.SECONDPLAYER_DEAD;
                case "secondPlayerDiscard" -> origin = Origin.FIRSTPLATER_DEAD;
                case "Hand" -> origin = Origin.SECONDPLAYER_INPLAY;
                default -> origin = Origin.NULL;
            }
        }
        return origin;
    }

    public  GameBoardVisualData ClickedOnRow(Origin origin) {
        Origin destination = GetDestination();
        if (selectedCard != null) {
            if (origin.isSubOrigin(destination)) {
                boolean isMedic = false;
                if (selectedCard instanceof UnitCard unitCard) {
                    if (unitCard.getAbility() == Ability.MEDIC) {
                        if (matchTable.isFirstPlayerTurn()) {
                            if (!matchTable.getFirstPlayerDeadCards().isEmpty()) isMedic = true;

                        } else {
                            if (!matchTable.getSecondPlayerDeadCards().isEmpty()) isMedic = true;
                        }

                    }
                } else if (selectedCard instanceof Hero hero) {
                    if (hero.getAbility() == Ability.MEDIC) {
                        if (matchTable.isFirstPlayerTurn()) {
                            if (!matchTable.getFirstPlayerDeadCards().isEmpty()) isMedic = true;

                        } else {
                            if (!matchTable.getSecondPlayerDeadCards().isEmpty()) isMedic = true;
                        }
                    }
                }
                if (matchTable.isFirstPlayerTurn()) {
                    matchTable.placeCard(new CardWrapper(selectedCard, Origin.FIRSTPLAYER_INPLAY), 0, getRowID(origin));
                } else {

                    matchTable.placeCard(new CardWrapper(selectedCard, Origin.SECONDPLAYER_INPLAY), 1, getRowID(origin));
                }
                if (!isMedic) matchTable.endTurn();
                if (matchTable.isFirstPlayerTurn()) {
                    if (isMedic && !matchTable.getFirstPlayerDeadCards().isEmpty()) {
                        MakeMedicWindow();
                    }
                } else {
                    if (isMedic && !matchTable.getSecondPlayerDeadCards().isEmpty()) {
                        MakeMedicWindow();
                    }
                }
                selectedCard = null;

            }
        }
        return sendData(false, false, false, false, false);
    }

    private  void MakeMedicWindow() {
        isMedic = true;
        isNewWindowOpen = true;
        sendData(false, true, false, false, false);
    }

    public  void MakeHisImperialMajestyWindow() {
        sendData(false, false, false, false, true);
    }

    public  void MakeCommanderOfRedRidersWindow() {
        isRedRider = true;
        isNewWindowOpen = true;
        sendData(false, false, true, false, false);
    }

    public  void MakeKingOfWildHuntWindow() {
        isKingOfWildHunt = true;
        isNewWindowOpen = true;
        sendData(false, false, false, true, false);
    }

    public  void MakeDestroyerOfWorldsWindow() {
        isDestroyer = true;
        isNewWindowOpen = true;
        sendData(true, false, false, false, false);
    }

    public  GameBoardVisualData ClickedOnBoost(int rowID) {
        if (selectedCard instanceof SpecialCard) {
            if (matchTable.isFirstPlayerTurn()) {
                matchTable.placeBoostCard(new CardWrapper(selectedCard, Origin.FIRSTPLAYER_INPLAY), 0, rowID);
            } else {
                matchTable.placeBoostCard(new CardWrapper(selectedCard, Origin.SECONDPLAYER_INPLAY), 1, rowID);
            }
            selectedCard = null;
            matchTable.endTurn();
        }
        return sendData(false, false, false, false, false);
    }

    public  GameBoardVisualData clickedOnWeather() {
        if (selectedCard instanceof SpecialCard && !(
                Objects.equals(selectedCard.getName(), "Commander’s horn") ||
                        Objects.equals(selectedCard.getName(), "Mardroeme"))) {
            if (matchTable.isFirstPlayerTurn()) {
                matchTable.addToSpellCards(new CardWrapper(selectedCard, Origin.FIRSTPLAYER_INPLAY));
            } else {
                matchTable.addToSpellCards(new CardWrapper(selectedCard, Origin.SECONDPLAYER_INPLAY));
            }
            selectedCard = null;
            matchTable.endTurn();
        }
        return sendData(false, false, false, false, false);
    }

    public  GameBoardVisualData LeaderAction() {
        if (matchTable.isFirstPlayerTurn() && matchTable.getFirstPlayerLeader() != null) {
            matchTable.leaderAction();
            matchTable.setFirstPlayerLeaderUsed(true);
        } else if (!matchTable.isFirstPlayerTurn() && matchTable.getSecondPlayerLeader() != null) {
            matchTable.leaderAction();
            matchTable.setSecondPlayerLeaderUsed(true);
        }
        return sendData(false, false, false, false, false);
    }

    public  GameBoardVisualData passRound() {
        if (matchTable.isFirstPlayerTurn()) {
            matchTable.pass(0);
        } else {
            matchTable.pass(1);
        }
        matchTable.endTurn();
        return sendData(false, false, false, false, false);
    }

    public  GameBoardVisualData sendData(boolean isDestroyer, boolean isMedic,
                                boolean isRedRider, boolean isKingOfWildHunt, boolean isImperialMajesty) {
        matchTable.updatePoints();
        GameBoardVisualData gameBoardVisualData = new GameBoardVisualData(matchTable
                , isDestroyer, isMedic, isRedRider, isKingOfWildHunt, isImperialMajesty);
        return gameBoardVisualData;
    }

    public  GameBoardVisualData sendDataWithReaction(String Recation) {
        matchTable.updatePoints();
        GameBoardVisualData gameBoardVisualData = new GameBoardVisualData(matchTable
                , false, false, false, false, false);
        gameBoardVisualData.setRecation(Recation);
        return gameBoardVisualData;
    }

    public  GameBoardVisualData sendDataWithMessage(Message message) {
        matchTable.updatePoints();
        GameBoardVisualData gameBoardVisualData = new GameBoardVisualData(matchTable
                , false, false, false, false, false);
        gameBoardVisualData.setTime(message.getTime());
        gameBoardVisualData.setMessage(message.getMessage());
        gameBoardVisualData.setUsername(message.getUsername());
        gameBoardVisualData.setUserName(message.getReplyData().getUserName());
        gameBoardVisualData.setReply(message.replyData.isReply());
        return gameBoardVisualData;
    }

    public  GameBoardVisualData sendReaction(String value) {
        return sendDataWithReaction(value);
    }

    private  GameBoardVisualData sendMessage(String substring, boolean isReply) {
        User user1 = matchTable.getFirstPlayer();
        User user2 = matchTable.getSecondPlayer();
        if (!matchTable.isFirstPlayerTurn()) {
            user1 = matchTable.getSecondPlayer();
            user2 = matchTable.getFirstPlayer();
        }
        Date date = new Date();
        String time=date.getHours()+":"+date.getMinutes()+"\n";
        Message message = new Message(user1.getNickname(), substring, new ReplyData(isReply, user2.getNickname()), time);
        return sendDataWithMessage(message);
    }

    public  GameBoardVisualData sendCommand(String s) {
        if (s.startsWith("message")) {
            return sendReaction(s.substring(7));
        } else if (s.startsWith("chat")) {
            if (s.substring(5, 9).equals("true")) {
                return  sendMessage(s.substring(9), true);
            } else {
                return  sendMessage(s.substring(10), false);
            }

        } else {
            return switch (s) {
                case "secondPlayerSiegeClicked" -> ClickedOnRow(Origin.SECONDPLAYER_SIEGE);
                case "secondPlayerRangedClicked" -> ClickedOnRow(Origin.SECONDPLAYER_RANGED);
                case "secondPlayerCloseCombatClicked" -> ClickedOnRow(Origin.SECONDPLAYER_CLOSECOMBAT);
                case "firstPlayerCloseCombatClicked" -> ClickedOnRow(Origin.FIRSTPLAYER_CLOSECOMBAT);
                case "firstPlayerRangedClicked" -> ClickedOnRow(Origin.FIRSTPLAYER_RANGED);
                case "firstPlayerSiegeClicked" -> ClickedOnRow(Origin.FIRSTPLAYER_SIEGE);
                case "weatherClicked" -> clickedOnWeather();
                case "0" -> ClickedOnBoost(0);
                case "1" -> ClickedOnBoost(1);
                case "2" -> ClickedOnBoost(2);
                case "LeaderAction" -> LeaderAction();
                case "PassRound" -> passRound();
                case "initiateDeck" -> initiateDeck();
                default -> throw new RuntimeException("message is not registered");
            };
        }
    }
    public  User changeTurn(User user1, User user2){
        User temp = user1;
        user1 = user2;
        user2 = temp;
        return user1;
    }
}