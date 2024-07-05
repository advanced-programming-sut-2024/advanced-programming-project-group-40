package controllers.MenuController;

import Server.GameServer;

public abstract class UserInfoController {

    public static void changePassword(String password) {
        GameServer.getLoggedInUser().setPassword(password);
    }

    public static boolean isUsernameUnique(String username) {
        return GameServer.getUserByName(username) == null;
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


}
