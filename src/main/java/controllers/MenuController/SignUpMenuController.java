package controllers.MenuController;

import controllers.DataSaver;
import controllers.Generator;
import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.SignUpMenuMessages;
import javafx.scene.control.Alert;
import models.AlertMaker;
import models.Game;
import models.User;
import views.ViewController.SignUpViewController;

public class SignUpMenuController extends UserInfoController {
    public static AlertMaker Continue(String username) {
        if (!isUsernameUnique(username)) {
            String randomUser = Generator.generateUsername(username);
            SignUpViewController.setRandomPass(username);
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.DUPLICATE_USER + randomUser);
        }
        return new AlertMaker(Alert.AlertType.CONFIRMATION, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.CONTINUE.toString());
    }


    public static AlertMaker signUp(String questionNum, String answer, String confirmAnswer) {
        if (!questionNum.matches("[0-9]+"))
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.INVALID_QUESTION_NUMBER.toString());
        int questionNumber = Integer.parseInt(questionNum);
        if (questionNumber > 5 || questionNumber < 1)
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.INVALID_QUESTION_NUMBER.toString());
        if (!answer.equals(confirmAnswer))
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.WRONG_ANSWER_CONFIRMATION.toString());
        Game.getLoggedInUser().setSecurityQuestionNumber(questionNumber);
        Game.getLoggedInUser().setSecurityAnswer(answer);
        Game.addNewUser(Game.getLoggedInUser());
        DataSaver.saveUsers();
        return new AlertMaker(Alert.AlertType.INFORMATION, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.SIGNED_UP_SUCCESSFULLY.toString());
    }


    public static void createUser(String username, String password, String nickname, String email) {
        Game.setLoggedInUser(new User(username, password, email, nickname));
    }


    public static boolean checkLink(String email) {
        // todo ---
//        LinkAuthorization.sendLink(email);
//        return LinkAuthorization.verifyLink();
        return true;
    }
}
