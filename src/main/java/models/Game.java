package models;

import enums.Menu;
import views.PlayMenu;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    public static final Random random = new Random();
    private static final ArrayList<User> allUsers = new ArrayList<>();
    private static User loggedInUser;
    private static Menu currentMenu = Menu.LoginMenu;
    public static ArrayList<User> getUsersRanked(){
        return null;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        Game.loggedInUser = loggedInUser;
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }
}
