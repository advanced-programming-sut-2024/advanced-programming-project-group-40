package controllers.MenuController;

import models.MatchTable;
import models.User;
import models.UserInputHandler.CardClickCommand;
import models.cards.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameMenuControllerTest {
    @Test
    void changeTurnShit() {
        User user1 = new User("username", "password", "What is your pet's name?", "Fluffy", true);
        User user2 = new User("username2", "password", "What is your pet's name?", "Fluffy", true);
        assertEquals(user2, GameMenuController.changeTurnShit(user1, user2));
        assertNotEquals(user1, GameMenuController.changeTurnShit(user1, user2));
    }
    @Test
    void changeShitTurn(){
        User user1 = new User("username", "password", "What is your pet's name?", "Fluffy", true);
        User user2 = new User("username2", "password", "What is your pet's name?", "Fluffy", true);
        assertEquals(user2, GameMenuController.changeShitTurn(user1, user2));
        assertNotEquals(user1, GameMenuController.changeShitTurn(user1, user2));
    }

}