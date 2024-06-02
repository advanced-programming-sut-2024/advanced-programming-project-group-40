package models;

import enums.Menu;
import views.PlayMenu;

import java.util.ArrayList;

public class Game {
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
