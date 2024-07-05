package controllers.MenuController;

import Server.Models.GameBoardVisualData;
import enums.Ability;
import enums.Origin;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.MatchTable;
import models.Result;
import models.UserInputHandler.CardClickCommand;
import models.cards.*;
import views.ViewController.GameViewController;

import java.util.ArrayList;
import java.util.Objects;

public class GameMenuController {
    private static MatchTable matchTable;
    private static boolean isNewWindowOpen = false;
    private static boolean isMedic = false;
    private static boolean isKingOfWildHunt = false;
    private static boolean isRedRider = false;
    private static boolean isDestroyer = false;
    private static GameViewController gameViewController2;

    public static void setGameViewController2(GameViewController gameViewController2) {
        GameMenuController.gameViewController2 = gameViewController2;
    }

    public static void setMatchTable(MatchTable matchTable) {
        GameMenuController.matchTable = matchTable;
    }

    private static Card selectedCard;

    public static void ClickedOnCard(Card selectedCard1) {
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
            if (isSelectable(selectedCard1)) selectedCard = selectedCard1;
            else {
                if (selectedCard != null) {
                    if (Objects.equals(selectedCard.getName(), "Decoy")) {
                        matchTable.doDecoy(new CardWrapper(selectedCard, getCardOrigin(selectedCard, matchTable.isFirstPlayerTurn())),
                                new CardWrapper(selectedCard1, getCardOrigin(selectedCard1, matchTable.isFirstPlayerTurn())), matchTable.isFirstPlayerTurn());
                        selectedCard = null;
                    }
                }
                matchTable.endTurn();
            }
        }
        sendData(false, false, false, false, false);
    }

    private static Origin GetDestination() {
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


    private static boolean isSelectable(Card selectedCard) {
        return Objects.equals(selectedCard.getParent().getId(), "Hand");
    }


    public static void initiateDeck() {
        matchTable.getFirstPlayer().getMatchesPlayed().add(matchTable);
        matchTable.getSecondPlayer().getMatchesPlayed().add(matchTable);
        matchTable.initilizeTable();
        sendData(false, false, false, false, false);
    }


    private static int getRowID(Origin origin) {
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

    private static Origin getCardOrigin(Card card, boolean isFirstPlayerTurn) {
        Origin origin;
        if (isFirstPlayerTurn) {
            switch (card.getParent().getId()) {
                case "firstPlayerCloseCombat" -> {
                    origin = Origin.FIRSTPLAYER_CLOSECOMBAT;
                }
                case "firstPlayerRanged" -> {
                    origin = Origin.FIRSTPLAYER_RANGED;
                }
                case "firstPlayerSiege" -> {
                    origin = Origin.FIRSTPLAYER_SIEGE;
                }
                case "secondPlayerCloseCombat" -> {
                    origin = Origin.SECONDPLAYER_CLOSECOMBAT;
                }
                case "secondPlayerRanged" -> {
                    origin = Origin.SECONDPLAYER_RANGED;
                }
                case "secondPlayerSiege" -> {
                    origin = Origin.SECONDPLAYER_SIEGE;
                }
                case "spellCards" -> {
                    origin = Origin.WEATHER;
                }
                case "firstPlayerDiscard" -> {
                    origin = Origin.FIRSTPLATER_DEAD;
                }
                case "secondPlayerDiscard" -> {
                    origin = Origin.SECONDPLAYER_DEAD;
                }
                case "Hand" -> {
                    origin = Origin.FIRSTPLAYER_INPLAY;
                }
                default -> {
                    origin = Origin.NULL;
                }
            }

        } else {
            switch (card.getParent().getId()) {
                case "firstPlayerCloseCombat" -> {
                    origin = Origin.SECONDPLAYER_CLOSECOMBAT;
                }
                case "firstPlayerRanged" -> {
                    origin = Origin.SECONDPLAYER_RANGED;
                }
                case "firstPlayerSiege" -> {
                    origin = Origin.SECONDPLAYER_SIEGE;
                }
                case "secondPlayerCloseCombat" -> {
                    origin = Origin.FIRSTPLAYER_CLOSECOMBAT;
                }
                case "secondPlayerRanged" -> {
                    origin = Origin.FIRSTPLAYER_RANGED;
                }
                case "secondPlayerSiege" -> {
                    origin = Origin.FIRSTPLAYER_SIEGE;
                }
                case "spellCards" -> {
                    origin = Origin.WEATHER;
                }
                case "firstPlayerDiscard" -> {
                    origin = Origin.SECONDPLAYER_DEAD;
                }
                case "secondPlayerDiscard" -> {
                    origin = Origin.FIRSTPLATER_DEAD;
                }
                case "Hand" -> {
                    origin = Origin.SECONDPLAYER_INPLAY;
                }
                default -> {
                    origin = Origin.NULL;
                }
            }
        }
        return origin;
    }

    public static void ClickedOnRow(Origin origin) {
        Origin destination = GetDestination();
        if (selectedCard != null) {
            if (origin.isSubOrigin(destination)) {
                boolean isMedic = false;
                if (selectedCard instanceof UnitCard unitCard) {
                    if (unitCard.getAbility() == Ability.MEDIC) {
                        isMedic = true;
                    }
                } else if (selectedCard instanceof Hero hero) {
                    if (hero.getAbility() == Ability.MEDIC) {
                        isMedic = true;
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
        sendData(false, false, false, false, false);
    }

    private static void MakeMedicWindow() {
        isMedic = true;
        isNewWindowOpen = true;
        sendData(false, true, false, false, false);
    }

    public static void MakeHisImperialMajestyWindow() {
        sendData(false, false, false, false, true);
    }

    public static void MakeCommanderOfRedRidersWindow() {
        isRedRider = true;
        isNewWindowOpen = true;
        sendData(false, false, true, false, false);
    }

    public static void MakeKingOfWildHuntWindow() {
        isKingOfWildHunt = true;
        isNewWindowOpen = true;
        sendData(false, false, false, true, false);
    }

    public static void MakeDestroyerOfWorldsWindow() {
        isDestroyer = true;
        isNewWindowOpen = true;
        sendData(true, false, false, false, false);
    }

    public static void ClickedOnBoost(int rowID) {
        if (selectedCard instanceof SpecialCard) {
            if (matchTable.isFirstPlayerTurn()) {
                matchTable.placeBoostCard(new CardWrapper(selectedCard, Origin.FIRSTPLAYER_INPLAY), 0, rowID);
            } else {
                matchTable.placeBoostCard(new CardWrapper(selectedCard, Origin.SECONDPLAYER_INPLAY), 1, rowID);
            }
            selectedCard = null;
            matchTable.endTurn();
        }
        sendData(false, false, false, false, false);
    }

    public static void clickedOnWeather() {
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
        sendData(false, false, false, false, false);
    }

    public static void LeaderAction() {
        if (matchTable.isFirstPlayerTurn() && matchTable.getFirstPlayerLeader() != null) {
            matchTable.leaderAction();
            matchTable.setFirstPlayerLeaderUsed(true);
        } else if (!matchTable.isFirstPlayerTurn() && matchTable.getSecondPlayerLeader() != null) {
            matchTable.leaderAction();
            matchTable.setSecondPlayerLeaderUsed(true);
        }
    }

    public static void passRound() {
        if (matchTable.isFirstPlayerTurn()) {
            matchTable.pass(0);
        } else {
            matchTable.pass(1);
        }
        matchTable.endTurn();
        sendData(false, false, false, false, false);
    }

    public static void sendData(boolean isDestroyer, boolean isMedic,
                                boolean isRedRider, boolean isKingOfWildHunt, boolean isImperialMajesty) {
        matchTable.updatePoints();
        GameBoardVisualData gameBoardVisualData = new GameBoardVisualData(matchTable
                , isDestroyer, isMedic, isRedRider, isKingOfWildHunt, isImperialMajesty);
        gameViewController2.setVisualData(gameBoardVisualData.toJSON());
    }

}