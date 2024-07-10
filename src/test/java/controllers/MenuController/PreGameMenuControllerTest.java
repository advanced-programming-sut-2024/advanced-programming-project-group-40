package controllers.MenuController;

import models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PreGameMenuControllerTest {

    @Test
    void isCompetitorValid() {
        User user = new User("username", "password", "a", "Fluffy", true);
        assertFalse(PreGameMenuController.isCompetitorValid(user));
    }
}