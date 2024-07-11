package controllers.MenuController;

import models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileMenuControllerTest {

    @Test
    void isUserValid() {
        User user = new User("username", "password", "a", "Fluffy", true);
        assertTrue(ProfileMenuController.isUserValid(user));
    }
}