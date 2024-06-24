package controllers.MenuController;

import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.SignUpMenuMessages;
import javafx.scene.control.Alert;
import models.AlertMaker;
import models.Game;
import models.User;

public class SignUpMenuController extends UserInfoController {
    public static AlertMaker Continue(String username) {
        if (!isUsernameUnique(username))
            return new AlertMaker(Alert.AlertType.CONFIRMATION, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.DUPLICATE_USER + generateUsername(username));
        return new AlertMaker(Alert.AlertType.CONFIRMATION, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.CONTINUE.toString());
    }


    public static AlertMaker signUp(int questionNumber, String answer, String confirmAnswer) {
        if (questionNumber > 5 || questionNumber < 1)
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.INVALID_QUESTION_NUMBER.toString());
        if (!answer.equals(confirmAnswer))
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.WRONG_ANSWER_CONFIRMATION.toString());
        Game.getLoggedInUser().setSecurityQuestionNumber(questionNumber);
        Game.getLoggedInUser().setSecurityAnswer(answer);
        Game.addNewUser(Game.getLoggedInUser());
        Controller.DataSaver.saveUsers();
        return new AlertMaker(Alert.AlertType.INFORMATION, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.SIGNED_UP_SUCCESSFULLY.toString());
    }


    public static void creatUser(String username, String password, String nickname, String email) {
        Game.setLoggedInUser(new User(username, password, email, nickname));
    }

}