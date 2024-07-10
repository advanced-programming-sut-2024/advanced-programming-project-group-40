package views.ViewController;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
            // Create a Circle
            Circle circle = new Circle();
            circle.setFill(Color.web("#b1dcd5"));
            circle.setRadius(4.0);
            circle.setStroke(Color.BLACK);

            Label usernames = new Label("Label");
            usernames.setPrefHeight(16.0);
            usernames.setPrefWidth(84.0);
            usernames.setTranslateX(5.0);

            // Create an HBox
            HBox hbox = new HBox();
            hbox.setAlignment(javafx.geometry.Pos.CENTER);
            hbox.setPrefHeight(49.0);
            hbox.setPrefWidth(134.0);

            // Add children to HBox
            hbox.getChildren().addAll(circle, usernames);

            name.getChildren().add(hbox);

            Label score = new Label("Label");
            score.setPrefHeight(49.0);
            score.setPrefWidth(134.0);

            wins.getChildren().add(score);
        }
    }

    public void goToLoginMenu(MouseEvent mouseEvent) {
        try {
            new MainMenu().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static class LoadingScreen1Controller {
        public ProgressBar progressBar;

        public void initialize() throws Exception {
            new Thread(() -> {
                for (int i = 0; i < 40; i++) {
                    try {
                        Thread.sleep(10);
                        addProgress();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(50);
                        addProgress();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 40; i++) {
                    try {
                        Thread.sleep(10);
                        addProgress();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                finish();
            }).start();

        }

        public void addProgress() {
            progressBar.setProgress(progressBar.getProgress() + 0.01);
        }

        public void finish() {
            Platform.runLater(() -> {
                try {
                    new MainMenu().start(Game.stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
