package controllers.MenuController;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpectatorBoardControllerTest {

    @Test
    void isCommandValid() {
        assertFalse(SpectatorBoardController.isCommandValid("menu show-current"));
        assertTrue(SpectatorBoardController.isCommandValid("message sent"));
    }
}