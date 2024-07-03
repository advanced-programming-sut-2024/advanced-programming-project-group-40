package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.User;
import models.Game;
import models.cards.Leader;
import views.ViewController.PreGameViewController;

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
            fileWriter1.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void loadDeckCards() {
        try {
            Gson gson = new Gson();
            String text = new String(Files.readAllBytes(Paths.get("src/main/resources/DataBase/deckCards.json")));
            ArrayList<String> deckCards = gson.fromJson(text, new TypeToken<List<String>>() {
            }.getType());
            if (deckCards == null)
                return;
            PreGameViewController.loadDeck(deckCards);
        } catch (Exception ignored) {
        }
    }
}