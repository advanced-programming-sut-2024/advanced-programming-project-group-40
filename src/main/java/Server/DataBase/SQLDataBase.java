package Server.DataBase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.User;

import java.sql.*;
import java.util.ArrayList;

public class SQLDataBase {
    private static SQLDataBase instance;
    private static final String URL = "jdbc:sqlite:src/main/resources/DataBase/DataBase.db";
    private Connection connection;
    private Gson gson;
    public static SQLDataBase getInstance() {
        if (instance == null) {
            instance = new SQLDataBase();
        }
        return instance;
    }
    private SQLDataBase() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            connection = DriverManager.getConnection(URL);
            createTables();
        } catch (SQLException e) {
            System.out.println("Error in connecting to database");
            e.printStackTrace();
        }
        gson = new Gson();
    }
    private void createTables(){
        String createUsersTableSql = "CREATE TABLE IF NOT EXISTS USERS (username VARCHAR(255), object TEXT)";
        String createGameHistoriesTableSql = "CREATE TABLE IF NOT EXISTS GAME_HISTORIES (game_token VARCHAR(255), object TEXT)";
        String createGameRecordsTableSql = "CREATE TABLE IF NOT EXISTS GAME_RECORDS (game_token VARCHAR(255), object TEXT)";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createUsersTableSql);
            statement.execute(createGameHistoriesTableSql);
            statement.execute(createGameRecordsTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addUser(User user){
        String insertUserSql = "INSERT INTO USERS (username, object) VALUES (?, ?)";
        try (PreparedStatement insertUserStatement = connection.prepareStatement(insertUserSql)) {
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, gson.toJson(user));
            int rowsInserted = insertUserStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public User getUserWithUsername(String username){
        User user = null;
        String selectUserSql = "SELECT * FROM USERS WHERE username = ?";
        try (PreparedStatement selectUserStatement = connection.prepareStatement(selectUserSql)) {
            selectUserStatement.setString(1, username);
            try (ResultSet resultSet = selectUserStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = gson.fromJson(resultSet.getString("object"), User.class);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public ArrayList<User> getAllUsers(){
        String selectUsersSql = "SELECT * FROM USERS";
        ArrayList<User> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectUsersSql);
            while(resultSet.next()){
                User user = gson.fromJson(resultSet.getString("object"), User.class);
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateUser(User user, String username){
        String updateUserSql = "UPDATE USERS SET object = ? WHERE username = ?";
        try (PreparedStatement updateUserStatement = connection.prepareStatement(updateUserSql)) {
            updateUserStatement.setString(1, gson.toJson(user));
            updateUserStatement.setString(2, username);
            int rowsUpdated = updateUserStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
