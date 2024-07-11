package controllers.MenuController;

import enums.Ability;
import enums.Origin;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Game;
import models.MatchTable;
import models.Result;
import models.UserInputHandler.CardClickCommand;
import models.cards.*;
import views.ViewController.GameViewController;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.StringTemplate.STR;

public class GameMenuController {
    private static MatchTable matchTable;
    private static Stage tempStage;
    private static boolean isNewWindowOpen = false;
    private static boolean isMedic = false;
    private static boolean isKingOfWildHunt = false;
    private static boolean isRedRider = false;
    private static boolean isDestroyer = false;
    private static boolean isVeto = true;
    private static GameViewController gameViewController2;

    public static void setGameViewController2(GameViewController gameViewController2) {
        GameMenuController.gameViewController2 = gameViewController2;
    }

    public static MatchTable getMatchTable() {
        return matchTable;
    }

    public static void setMatchTable(MatchTable matchTable) {
        GameMenuController.matchTable = matchTable;
    }

    private static Card selectedCard;

    public static void ClickedOnCard(Card selectedCard1, GameViewController gameViewController) {
        matchTable.updatePoints();
        if (isVeto) {
            Card randomCard = matchTable.getFirstPlayerDeckCards().get(Game.random.nextInt(matchTable.getFirstPlayerDeckCards().size()));
            matchTable.addToInPlayCards(0, new CardWrapper(randomCard, Origin.FIRSTPLAYER_DECK));
            matchTable.addToDeckCards(0, new CardWrapper(selectedCard1, Origin.FIRSTPLAYER_INPLAY));
            isVeto = false;
        } else {
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
                tempStage.close();
                gameViewController.update();
                isNewWindowOpen = false;

                matchTable.updatePoints();

            } else {
                if (isSelectable(selectedCard1)) {
                    selectedCard = selectedCard1;
                    gameViewController.unHighlight();
                    Origin origin = GetDestination(matchTable.isFirstPlayerTurn());
                    gameViewController.highLightRow(origin);
                } else {
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
        }
        matchTable.updatePoints();
        gameViewController.update();
    }

    private static Origin GetDestination(boolean isFirstPlayerTurn) {
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


    public static void initiateDeck(MatchTable matchTable) {
        matchTable.initilizeTable();
        matchTable.updatePoints();

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

    public static void ClickedOnRow(Origin origin, GameViewController gameViewController) {
        Origin destination = GetDestination(matchTable.isFirstPlayerTurn());
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
                gameViewController.update();
                if (matchTable.isFirstPlayerTurn()) {
                    if (isMedic && !matchTable.getFirstPlayerDeadCards().isEmpty()) {
                        MakeMedicWindow(matchTable.isFirstPlayerTurn());
                    }
                } else {
                    if (isMedic && !matchTable.getSecondPlayerDeadCards().isEmpty()) {
                        MakeMedicWindow(matchTable.isFirstPlayerTurn());
                    }
                }
                selectedCard = null;

            }
        }
        matchTable.updatePoints();


    }

    private static void MakeMedicWindow(boolean isFirstPlayerTurn) {
        gameViewController2.getFirstPlayerDiscard().getChildren().clear();
        tempStage = new Stage();
        tempStage.setHeight(140);
        tempStage.setWidth(800);
        tempStage.setResizable(false);
        isMedic = true;
        HBox hBox = new HBox();
        Scene scene = new Scene(hBox);
        if (isFirstPlayerTurn) {
            hBox.getChildren().addAll(matchTable.getFirstPlayerDeadCards());
        } else {
            hBox.getChildren().addAll(matchTable.getSecondPlayerDeadCards());
        }
        isNewWindowOpen = true;
        tempStage.setScene(scene);
        tempStage.show();
        matchTable.updatePoints();

    }

    public static void MakeHisImperialMajestyWindow(boolean isFirstPlayerTurn) {
        tempStage = new Stage();
        tempStage.setHeight(140);
        tempStage.setWidth(800);
        tempStage.setResizable(false);
        HBox hBox = new HBox();
        Scene scene = new Scene(hBox);
        if (isFirstPlayerTurn) {
            hBox.getChildren().addAll(MatchTable.randomSelectedCards(matchTable.getSecondPlayerInPlayCards(), 3));
        } else {
            hBox.getChildren().addAll(MatchTable.randomSelectedCards(matchTable.getFirstPlayerInPlayCards(), 3));
        }
        tempStage.setScene(scene);
        tempStage.show();
        matchTable.updatePoints();

    }

    public static void MakeCommanderOfRedRidersWindow(boolean isFirstPlayerTurn) {
        tempStage = new Stage();
        tempStage.setHeight(140);
        tempStage.setWidth(800);
        tempStage.setResizable(false);
        isRedRider = true;
        HBox hBox = new HBox();
        Scene scene = new Scene(hBox);
        ArrayList<Card> weatherCards = new ArrayList<>();
        if (isFirstPlayerTurn) {
            for (Card card : matchTable.getFirstPlayerDeckCards()) {
                if (card instanceof SpecialCard) {
                    if (!(Objects.equals(card.getName(), "Commander’s horn") ||
                            Objects.equals(card.getName(), "Scorch") ||
                            Objects.equals(card.getName(), "Mardroeme"))
                    ) {
                        weatherCards.add(card);
                    }

                }
            }
        } else {
            for (Card card : matchTable.getSecondPlayerDeckCards()) {
                if (card instanceof SpecialCard) {
                    if (!(Objects.equals(card.getName(), "Commander’s horn") ||
                            Objects.equals(card.getName(), "Scorch") ||
                            Objects.equals(card.getName(), "Mardroeme"))
                    ) {
                        weatherCards.add(card);
                    }

                }
            }
        }
        InitiateOnCardClick(hBox, scene, weatherCards);
        matchTable.updatePoints();

    }

    public static void MakeKingOfWildHuntWindow(boolean isFirstPlayerTurn) {
        MakeMedicWindow(isFirstPlayerTurn);
        matchTable.updatePoints();

    }

    public static void MakeDestroyerOfWorldsWindow(boolean isFirstPlayerTurn) {
        if (isFirstPlayerTurn) {
            ArrayList<Card> cardsToKill = new ArrayList<>(
                    MatchTable.randomSelectedCards(matchTable.getFirstPlayerInPlayCards(), 2)
            );
            for (Card card : cardsToKill) {
                matchTable.addToDeadCards(0, new CardWrapper(card, Origin.FIRSTPLAYER_INPLAY));
            }
        } else {
            ArrayList<Card> cardsToKill = new ArrayList<>(
                    MatchTable.randomSelectedCards(matchTable.getSecondPlayerInPlayCards(), 2)
            );
            for (Card card : cardsToKill) {
                matchTable.addToDeadCards(1, new CardWrapper(card, Origin.SECONDPLAYER_INPLAY));
            }
        }


        isDestroyer = true;
        tempStage = new Stage();
        tempStage.setHeight(140);
        tempStage.setWidth(800);
        tempStage.setResizable(false);
        HBox hBox = new HBox();
        Scene scene = new Scene(hBox);
        ArrayList<Card> selectedCards;
        if (isFirstPlayerTurn) {
            selectedCards = new ArrayList<>(matchTable.getFirstPlayerDeckCards());
        } else {
            selectedCards = new ArrayList<>(matchTable.getSecondPlayerDeckCards());
        }
        InitiateOnCardClick(hBox, scene, selectedCards);
        matchTable.updatePoints();

    }

    private static void InitiateOnCardClick(HBox hBox, Scene scene, ArrayList<Card> selectedCards) {
        for (Card card : selectedCards) {
            card.setOnMouseClicked(_ -> {
                System.out.println(STR."name:\{card.getName()}");
                CardClickCommand cardClickCommand = new CardClickCommand(card, gameViewController2);
                cardClickCommand.excute();


            });
        }
        hBox.getChildren().addAll(selectedCards);
        isNewWindowOpen = true;
        tempStage.setScene(scene);
        tempStage.show();
        matchTable.updatePoints();

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
            matchTable.updatePoints();
        }
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
            matchTable.updatePoints();

        }
    }

    public static void LeaderAction() {

        if (matchTable.isFirstPlayerTurn() && matchTable.getFirstPlayerLeader() != null) {
            matchTable.leaderAction();
            matchTable.setFirstPlayerLeaderUsed(true);
        } else if (!matchTable.isFirstPlayerTurn() && matchTable.getSecondPlayerLeader() != null) {
            matchTable.leaderAction();
            matchTable.setSecondPlayerLeaderUsed(true);
        }
        matchTable.updatePoints();

    }

    public static void passRound() {

        if (matchTable.isFirstPlayerTurn()) {
            matchTable.pass(0);
        } else {
            matchTable.pass(1);
        }
        matchTable.updatePoints();
        matchTable.endTurn();

    }


    public static void updatePoints() {
        matchTable.updatePoints();
    }

    public static void cheat(String value) {
        int userID = 0;
        if (!matchTable.isFirstPlayerTurn()) userID = 1;
        switch (value) {
            case "give back crystal":
                matchTable.giveBackCrystal(userID);
                break;
            case "win":
                matchTable.win(userID);
                break;
            case "skipTurn":
                matchTable.skipTurn();
                break;
            case "clear Weather":
                matchTable.clearWeather();
                break;
            case "reactivate leader Ability":
                matchTable.GiveBackLeaderAbility(userID);
                break;
            case "OP cards":
                matchTable.makeCardsStronger();
                break;

        }
    }
}