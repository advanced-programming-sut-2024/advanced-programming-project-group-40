package controllers.MenuController;

import controllers.Controller;
import models.Game;
import models.User;

import java.security.SecureRandom;

public abstract class UserInfoController extends Controller {

    public static void changePassword(String password) {
        Game.getLoggedInUser().setPassword(password);
    }

    public static boolean isUsernameUnique(String username) {
        return Game.getUserByName(username) == null;
    }

    public static boolean isUsernameValid(String username) {
        return username.matches("^[a-zA-Z0-9-]+$");
    }

    public static boolean isEmailValid(String email) {
        return email.matches("\\S+@\\S+.com");
    }

    public static boolean isPasswordValid(String password) {
        return password.matches("^[0-9a-zA-Z!@#$%^&*-]+$");
    }

    public static boolean isPasswordShort(String password) {
        return password.length() <= 7;
    }

    public static boolean isPasswordWeak(String password) {
        return !password.matches("^(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[0-9]+)(?=.*[!@#$%^&*\\-]+)[0-9a-zA-Z!@#$%^&*\\-]+$");
    }

    public static boolean isPasswordTheSame(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public static boolean isAnswerValid(User user, String answer) {
        return answer.equals(user.getSecurityAnswer());
    }

}
