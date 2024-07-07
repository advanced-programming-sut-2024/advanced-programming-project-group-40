package models;

import controllers.DataSaver;
import enums.Menu;
import enums.cards.HeroInfo;
import enums.cards.SpecialCardInfo;
import enums.cards.UnitCardInfo;
import javafx.stage.Stage;
import models.cards.*;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    public static final Random random = new Random();
    private static ArrayList<User> allUsers = DataSaver.loadUsers();
    private static final ArrayList<Card> allCards = Game.setAllCards();
    private static final ArrayList<Leader> allLeaders = new ArrayList<>();
    private static final ArrayList<Card> selectedCards = new ArrayList<>();
    private static User loggedInUser;
    private static Menu currentMenu = Menu.LoginMenu;
    public static Stage stage;


    public static ArrayList<User> getUsersRanked() {
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

    public static User getUserByName(String name) {
        for (User user : allUsers) {
            if (user.getUsername().equals(name))
                return user;
        }
        return null;
    }

    public static void addNewUser(User newUser) {
        allUsers.add(newUser);
    }

    public static void setCurrentMenu(Menu currentMenu) {
        Game.currentMenu = currentMenu;
    }

    public static void setAllUsers(ArrayList<User> allUsers) {
//        if (!allUsers.isEmpty()) {
//            Game.allUsers = allUsers;
//            for (User user : allUsers) {
//                for (String name: user.getDeckCardsName()){
//                    user.setDeckCards(new ArrayList<>());
//                    user.getDeckCards().add(Objects.requireNonNull(Card.getCardByName(name)));
//                }
//            }
//        }
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static ArrayList<Card> setAllCards() {
        ArrayList<Card> allCards = new ArrayList<>();
        for (UnitCardInfo unitCardInfo : UnitCardInfo.values()) {
            UnitCard unitCard = new UnitCard(unitCardInfo);
            allCards.add(new UnitCard(unitCardInfo));
        }
        for (HeroInfo heroInfo : HeroInfo.values()) {
            allCards.add(new Hero(heroInfo));
        }
        for (SpecialCardInfo specialCardInfo : SpecialCardInfo.values()) {
            allCards.add(new SpecialCard(specialCardInfo));
        }
        return allCards;
    }

    public static ArrayList<Card> getAllCards() {
        return allCards;
    }

    public static void addToSelectedCards(Card card) {
        selectedCards.add(card);
    }


    public static ArrayList<Leader> getAllLeaders() {
        return allLeaders;
    }

}
