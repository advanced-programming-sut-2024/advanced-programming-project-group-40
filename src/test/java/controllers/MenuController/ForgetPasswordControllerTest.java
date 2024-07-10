package controllers.MenuController;

import models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ForgetPasswordControllerTest {

    @Test
    public void testIsAnswerValidReturnsTrueWhenAnswersMatch() {
        // Arrange
        User user = new User("username", "password", "What is your pet's name?", "Fluffy", true);

        boolean isValid = ForgetPasswordController.isAnswerValid(user, "Fluffy");

        assertTrue(isValid, "Answer should be valid when it matches security answer.");
    }

    @Test
    public void testIsAnswerValidReturnsFalseWhenAnswersDoNotMatch() {
        // Arrange
        User user = new User("username", "password", "What is your pet's name?", "Fluffy", true);

        // Act
        boolean isValid = ForgetPasswordController.isAnswerValid(user, "Rex");

        // Assert
        assertFalse(isValid, "Answer should be invalid when it does not match security answer.");
    }
}
