package views;

import Controller.DataSaver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Game;

import java.util.Objects;

import java.util.Scanner;

public class LoginMenu extends PlayMenu {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
//        Game.setAllUsers(Objects.requireNonNull(DataSaver.loadUsers()));
        LoginMenu.stage = stage;
        Pane pane = FXMLLoader.load(LoginMenu.class.getResource("/FXML/LoginMenuFXML.fxml"));
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
