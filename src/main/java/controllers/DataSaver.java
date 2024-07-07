package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.Game;
import models.User;
import models.cards.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataSaver {
    public static final String USERS_DATABASE_PATH = "src/main/resources/DataBase/allUsersData.json";

    public static ArrayList<User> loadUsers() {
        try {
            Gson gson = new Gson();
            String text = new String(Files.readAllBytes(Paths.get(USERS_DATABASE_PATH)));
            ArrayList<User> users = gson.fromJson(text, new TypeToken<List<User>>() {

            }.getType());

            if (users == null)
                return (new ArrayList<>());
            return users;
        } catch (Exception ignored) {
            return null;
        }
    }

    public static void saveUsers() {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(USERS_DATABASE_PATH);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(Game.getAllUsers());
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveDeckCards(ArrayList<String> deckCards, Leader leader) {
        FileWriter fileWriter1;
        FileWriter fileWriter2;
        try {
            fileWriter1 = new FileWriter("src/main/resources/DataBase/deckCards.json", false);
            fileWriter2 = new FileWriter("src/main/resources/DataBase/leader.json", false);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json1 = gson.toJson(deckCards);
            String json2 = gson.toJson(leader.getName());
            fileWriter1.write(json1);
            fileWriter2.write(json2);
            fileWriter1.close();
            fileWriter2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadDeckCards() {
        try {
            Gson gson = new Gson();
            String text1 = new String(Files.readAllBytes(Paths.get("src/main/resources/DataBase/deckCards.json")));
            String text2 = new String(Files.readAllBytes(Paths.get("src/main/resources/DataBase/leader.json")));
            ArrayList<String> deckCards = gson.fromJson(text1, new TypeToken<List<String>>() {
            }.getType());
            String leaderName = gson.fromJson(text2, String.class);
            Leader leader = Leader.getLeaderByName(leaderName);
            User user = Game.getLoggedInUser();
            user.setLeader(leader);
            assert leader != null;
            user.setFaction(leader.getFaction());
            user.getDeckCards().clear();
            int numberOfUnitCards = 0;
            int numberOfSpecialCards = 0;
            int numberOfHeroCards = 0;
            int totalUnitCardsStrength = 0;
            for (String cardName : deckCards) {
                Card card = Card.getCardByName(cardName);
                user.getDeckCards().add(card);
                if (card instanceof UnitCard) {
                    numberOfUnitCards++;
                    totalUnitCardsStrength += ((UnitCard) card).getConstantPower();
                } else if (card instanceof SpecialCard)
                    numberOfSpecialCards++;
                else if (card instanceof Hero) {
                    numberOfHeroCards++;
                    totalUnitCardsStrength += ((Hero) card).getConstantPower();
                }
            }
            user.setNumberOfUnitCards(numberOfUnitCards);
            user.setNumberOfSpecialCards(numberOfSpecialCards);
            user.setNumberOfHeroCards(numberOfHeroCards);
            user.setTotalUnitCardsStrength(totalUnitCardsStrength);
        } catch (
                Exception ignored) {
        }
    }
}