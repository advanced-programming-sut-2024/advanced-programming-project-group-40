package views.ViewController;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import models.Game;
import models.User;
import views.MainMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScoreBoardViewController {

    @FXML
    private VBox name;
    @FXML
    private VBox wins;

    public void initialize() {
        initializeChart();
    }

    private void initializeChart() {
        ArrayList<User> sortedUsers = Game.getAllUsers();
        Collections.sort(sortedUsers, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return Integer.compare(u2.getWon(), u1.getWon());
            }
        });
        for (User user : sortedUsers) {
            Label username = new Label(user.getUsername());
            username.setPrefHeight(46);
            username.setPrefWidth(124);
            username.setAlignment(Pos.CENTER);
            this.name.getChildren().add(username);

            Label wins = new Label(Integer.toString(user.getWon()));
            wins.setPrefHeight(46);
            wins.setPrefWidth(124);
            wins.setAlignment(Pos.CENTER);
            this.wins.getChildren().add(wins);
        }
    }

    public void goToLoginMenu(MouseEvent mouseEvent) {
        try {
            new MainMenu().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
