package controllers.MenuController;

import controllers.MenuController.ChangeInfoController;
import enums.AlertInfo.AlertHeader;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChangeInfoControllerTest {
    @Mock
    private User user;

    @Mock
    private Game game;

    @Mock
    private TextField newUsername;

    @Mock
    private TextField newEmail;

    @Mock
    private TextField oldPassword;

    @Mock
    private TextField newPassword;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(Game.getLoggedInUser()).thenReturn(user);
    }

    @Test
    public void testChangeCheckSameUsername() {
        when(user.getUsername()).thenReturn("testUsername");
        when(newUsername.getText()).thenReturn("testUsername");
        AlertMaker result = ChangeInfoController.changeCheck(newUsername, newEmail, oldPassword, newPassword);
        assertEquals(Alert.AlertType.ERROR, result.getAlertType());
        assertEquals(AlertHeader.CHANGE_INFO.toString(), result.getHeaderText());
        assertEquals(ChangeInfoMenuMessages.SAME_USERNAME.toString(), result.getContentText());
    }

    @Test
    public void testChangeCheckSameEmail() {
        when(user.getEmail()).thenReturn("testEmail");
        when(newEmail.getText()).thenReturn("testEmail");
        AlertMaker result = ChangeInfoController.changeCheck(newUsername, newEmail, oldPassword, newPassword);
        assertEquals(Alert.AlertType.ERROR, result.getAlertType());
        assertEquals(AlertHeader.CHANGE_INFO.toString(), result.getHeaderText());
        assertEquals(ChangeInfoMenuMessages.SAME_EMAIL.toString(), result.getContentText());
    }

    @Test
    public void testChangeCheckSamePassword() {
        when(user.getPassword()).thenReturn("testPassword");
        when(newPassword.getText()).thenReturn("testPassword");
        AlertMaker result = ChangeInfoController.changeCheck(newUsername, newEmail, oldPassword, newPassword);
        assertEquals(Alert.AlertType.ERROR, result.getAlertType());
        assertEquals(AlertHeader.CHANGE_INFO.toString(), result.getHeaderText());
        assertEquals(ChangeInfoMenuMessages.SAME_PASSWORD.toString(), result.getContentText());
    }

    @Test
    public void testChangeCheckSuccessfullyChanged() {
        when(user.getUsername()).thenReturn("oldUsername");
        when(user.getEmail()).thenReturn("oldEmail");
        when(user.getPassword()).thenReturn("oldPassword");
        when(newUsername.getText()).thenReturn("newUsername");
        when(newEmail.getText()).thenReturn("newEmail");
        when(oldPassword.getText()).thenReturn("oldPassword");
        when(newPassword.getText()).thenReturn("newPassword");
        AlertMaker result = ChangeInfoController.changeCheck(newUsername, newEmail, oldPassword, newPassword);
        assertEquals(Alert.AlertType.INFORMATION, result.getAlertType());
        assertEquals(AlertHeader.CHANGE_INFO.toString(), result.getHeaderText());
        assertEquals(ChangeInfoMenuMessages.SUCCESSFULLY_CHANGED.toString(), result.getContentText());
    }

    @Test
    public void testCorrectPassword() {
        when(user.getPassword()).thenReturn("testPassword");
        assertTrue(ChangeInfoController.correctPassword("testPassword"));
        assertFalse(ChangeInfoController.correctPassword("wrongPassword"));
    }
}

