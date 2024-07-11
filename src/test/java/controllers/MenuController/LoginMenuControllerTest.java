package controllers.MenuController;

import Server.ClientHandler;
import Server.Messages.Client.LoginMessages;
import Server.Messages.ServerMessages;
import models.Game;
import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginMenuControllerTest {

    @Mock
    private User user;

    @Mock
    private ClientHandler clientHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(Game.getUserByName(anyString())).thenReturn(user);
        when(ClientHandler.client.login(any(LoginMessages.class))).thenReturn(new ServerMessages(true, ""));
    }

    @Test
    void testStayLoggedInSelected() {
        Game.setLoggedInUser(user);
        LoginMenuController.stayLoggedInSelected();
        verify(user, times(1)).setStayLoggedIn(true);
    }

}

