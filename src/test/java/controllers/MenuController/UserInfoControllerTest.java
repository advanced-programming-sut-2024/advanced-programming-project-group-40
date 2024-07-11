package controllers.MenuController;

import models.Game;
import models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoControllerTest {



    @Test
    void changePassword() {
        User user = new User("username", "password", "What is your pet's name?", "Fluffy", true);
        UserInfoController.changePassword("newPassword", user);
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    void isUsernameUnique() {
    }

    @Test
    void isUsernameValid() {

        assertTrue(UserInfoController.isUsernameValid("username"));
        assertFalse(UserInfoController.isUsernameValid("username!"));
    }

    @Test
    void isEmailValid() {

        assertTrue(UserInfoController.isEmailValid("a@a.com"));
        assertFalse(UserInfoController.isEmailValid("a@a"));

    }

    @Test
    void isPasswordValid() {

        assertTrue(UserInfoController.isPasswordValid("password"));
        assertFalse(UserInfoController.isPasswordValid("][!"));
    }

    @Test
    void isPasswordShort() {
    }

    @Test
    void isPasswordWeak() {
    }

    @Test
    void isPasswordTheSame() {
    }
}