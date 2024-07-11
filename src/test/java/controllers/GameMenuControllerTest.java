package controllers;

import models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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