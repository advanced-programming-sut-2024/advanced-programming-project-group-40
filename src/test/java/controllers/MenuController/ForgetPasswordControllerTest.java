package controllers.MenuController;

import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForgetPasswordControllerTest {

    private User user;

    @BeforeEach
    void setUp() {
        ForgetPasswordController forgetPasswordController = new ForgetPasswordController();
        user = new User("a", "a","a", "a");
        user.setSecurityAnswer("correctAnswer");
    }

    @Test
    void testIsAnswerValid() {
        assertTrue(ForgetPasswordController.isAnswerValid(user, "correctAnswer"));
        assertFalse(ForgetPasswordController.isAnswerValid(user, "wrongAnswer"));
    }

}