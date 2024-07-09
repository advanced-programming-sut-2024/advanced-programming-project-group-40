package controllers.MenuController;

import models.MatchTable;
import models.UserInputHandler.CardClickCommand;
import models.cards.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameMenuControllerTest {

    private MatchTable matchTable;
    private Card card;

    @BeforeEach
    void setUp() {
        card = mock(Card.class);
    }

    @Test
    void testClickedOnCard() {
        GameMenuController.setMatchTable(matchTable);
        GameMenuController.ClickedOnCard(card, null);
        verify(matchTable, times(1)).endTurn();
    }

    @Test
    void testInitiateDeck() {
        GameMenuController.initiateDeck(matchTable);
        verify(matchTable, times(1)).initilizeTable();
    }

    @Test
    void testClickedOnRow() {
        GameMenuController.setMatchTable(matchTable);
        GameMenuController.ClickedOnRow(null);
        verify(matchTable, times(1)).endTurn();
    }

    @Test
    void testClickedOnBoost() {
        GameMenuController.setMatchTable(matchTable);
        GameMenuController.ClickedOnBoost(0);
        verify(matchTable, times(1)).endTurn();
    }

    @Test
    void testClickedOnWeather() {
        GameMenuController.setMatchTable(matchTable);
        GameMenuController.clickedOnWeather();
        verify(matchTable, times(1)).endTurn();
    }

    @Test
    void testLeaderAction() {
        GameMenuController.setMatchTable(matchTable);
        GameMenuController.LeaderAction();
        verify(matchTable, times(1)).leaderAction();
    }

    @Test
    void testPassRound() {
        GameMenuController.setMatchTable(matchTable);
        GameMenuController.passRound();
        verify(matchTable, times(1)).endTurn();
    }

    @Test
    void testUpdatePoints() {
        GameMenuController.setMatchTable(matchTable);
        GameMenuController.updatePoints();
        verify(matchTable, times(1)).updatePoints();
    }
}