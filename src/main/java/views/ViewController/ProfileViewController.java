package views.ViewController;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import models.Game;
import models.MatchTable;
import models.User;

import java.util.ArrayList;

public class ProfileViewController {
    @FXML
    private VBox competitor;
    @FXML
    private Label competitorName1;
    @FXML
    private VBox date;
    @FXML
    private VBox competitorPoints1;
    @FXML
    private VBox userPoints1;
    @FXML
    private VBox userPoints2;
    @FXML
    private VBox competitorPoints2;
    @FXML
    private VBox competitorPoints3;
    @FXML
    private VBox userPoints3;
    @FXML
    private VBox userFinalPoints;
    @FXML
    private VBox competitorFinalPoints;
    @FXML
    private VBox winner;
    @FXML
    private Label username;
    @FXML
    private Label nickname;
    @FXML
    private Label highestScore;
    @FXML
    private Label rank;
    @FXML
    private Label numberOfGames;
    @FXML
    private Label draw;
    @FXML
    private Label won;
    @FXML
    private Label lost;

    public void initialize() {
        User user = Game.getLoggedInUser();
        username.setText(user.getUsername());
        nickname.setText(user.getNickname());
        highestScore.setText(Integer.toString(user.getHighestScore()));
        rank.setText(Integer.toString(user.getRank()));
        numberOfGames.setText(Integer.toString(user.getNumberOfMatches()));
        draw.setText(Integer.toString(user.getDraw()));
        won.setText(Integer.toString(user.getWon()));
        lost.setText(Integer.toString(user.getLost()));

        int num = 0;
        for (MatchTable matchTable : user.getMatchesPlayed()) {
            // todo  is competitor == second player?
            Label competitorLabel = new Label(matchTable.getSecondPlayerRowPoints().toString());
            competitorLabel.setPrefHeight(46);
            competitorLabel.setPrefWidth(124);
            competitorLabel.setAlignment(Pos.CENTER);
            competitor.getChildren().add(competitorLabel);

            Label dateLabel = new Label(matchTable.getDate().toString());
            dateLabel.setPrefHeight(46);
            dateLabel.setPrefWidth(124);
            dateLabel.setAlignment(Pos.CENTER);
            date.getChildren().add(dateLabel);

            Label R1 = new Label(matchTable.getSecondPlayerRowPoints().get(0).toString());
            R1.setPrefHeight(46);
            R1.setPrefWidth(124);
            R1.setAlignment(Pos.CENTER);
            competitorPoints1.getChildren().add(R1);

            Label R2 = new Label(matchTable.getSecondPlayerRowPoints().get(1).toString());
            R2.setPrefHeight(46);
            R2.setPrefWidth(124);
            R2.setAlignment(Pos.CENTER);
            competitorPoints2.getChildren().add(R2);

            Label R3 = new Label(matchTable.getSecondPlayerRowPoints().get(2).toString());
            R3.setPrefHeight(46);
            R3.setPrefWidth(124);
            R3.setAlignment(Pos.CENTER);
            competitorPoints3.getChildren().add(R3);






            num++;
            if (num == 5)
                break;
        }

    }
}
