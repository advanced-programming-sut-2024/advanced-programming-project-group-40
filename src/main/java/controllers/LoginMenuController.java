package controllers;

import javafx.scene.control.Alert.AlertType;
import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.LoginMenuMessages;
import models.Alert;
import models.Game;
import models.Result;
import models.User;

public class LoginMenuController extends Controller {

    public static Alert signIn(String username, String password) {
        User user;
        if ((user = Game.getUserByName(username)) == null)
            return new Alert(AlertType.ERROR, AlertHeader.SIGN_IN.toString(), LoginMenuMessages.INCORRECT_USERNAME.toString());
        if (!user.getPassword().equals(password))
            return new Alert(AlertType.ERROR, AlertHeader.SIGN_IN.toString(), LoginMenuMessages.INCORRECT_PASSWORD.toString());
        return new Alert(AlertType.CONFIRMATION, AlertHeader.SIGN_IN.toString(), LoginMenuMessages.STAY_LOGGED_IN.toString());
    }

    public static Result forgetPassword(String username, int questionNumber, String answer, String newPassword) {
        return null;
    }

    public static Result goToRegisterMenu() {
        return null;
    }

    public static boolean isAnswerValid(int questionNumber, String answer) {
        return true;
    }

    public static void setPassword(String password) {

    }


}
