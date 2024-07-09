package controllers.MenuController;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import enums.AlertInfo.messages.ChangeInfoMenuMessages;
import javafx.scene.control.Alert;
import models.AlertMaker;
import models.Game;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javafx.scene.control.TextField;

class ChangeInfoControllerTest {
    @Test
    void changePassword() {
        User user = new User("username", "password", "What is your pet's name?", "Fluffy", true);
        ChangeInfoController.changePassword(user, "newPassword");
        assertEquals("newPassword", user.getPassword());
    }
}
