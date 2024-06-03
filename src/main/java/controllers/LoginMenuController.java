package controllers;

import enums.messages.LoginMenuMessages;
import models.Game;
import models.Result;
import models.User;

public class LoginMenuController extends Controller {

    public static Result signIn(String username, String password) {
        User user;
        if ((user = Game.getUserByName(username)) == null)
            return new Result(false, LoginMenuMessages.INCORRECT_USERNAME.toString());
        if (!user.getPassword().equals(password))
            return new Result(false, LoginMenuMessages.INCORRECT_PASSWORD.toString());
        return new Result(true, LoginMenuMessages.STAY_LOGGED_IN.toString());
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
