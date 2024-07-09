package controllers.MenuController;

import models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignUpMenuControllerTest {

    @Test
    void isUserValid() {
        User user = new User("username", "password", "a@a.com", "Fluffy", true);
        assertTrue(SignUpMenuController.isUserValid(user));

    }
}