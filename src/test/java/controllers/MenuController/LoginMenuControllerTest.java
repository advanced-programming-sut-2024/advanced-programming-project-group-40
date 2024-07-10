package controllers.MenuController;

import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class LoginMenuControllerTest {

    @Test
    void setPassword() {
        User user =  new User("username", "password", "a", "Fluffy", true);
        LoginMenuController.setPassword("newPassword", user);
        Assertions.assertEquals("newPassword", user.getPassword());
    }
}
