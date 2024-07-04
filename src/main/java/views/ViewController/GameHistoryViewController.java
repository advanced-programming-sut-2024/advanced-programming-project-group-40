package views.ViewController;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import models.Game;
import models.MatchTable;
import models.User;

public class GameHistoryViewController {

    @FXML
    private TextField numberOfGameHistory;
    @FXML
    private VBox competitor;
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
    public void initialize(){
        User user = Game.getLoggedInUser();

        numberOfGameHistory.textProperty().addListener((observable, oldValue, newValue) -> {
            changeChart(user, Integer.parseInt(numberOfGameHistory.getText()));
        });
        changeChart(user, 5);
    }


    private void changeChart(User user, int number) {
        // todo check they are from new to old
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


            Label R1y = new Label(matchTable.getFirstPlayerRowPoints().get(0).toString());
            R1y.setPrefHeight(46);
            R1y.setPrefWidth(124);
            R1y.setAlignment(Pos.CENTER);
            userPoints1.getChildren().add(R1y);

            Label R2y = new Label(matchTable.getFirstPlayerRowPoints().toString());
            R2y.setPrefHeight(46);
            R2y.setPrefWidth(124);
            R2y.setAlignment(Pos.CENTER);
            userPoints2.getChildren().add(R2y);

            Label R3y = new Label(matchTable.getFirstPlayerRowPoints().toString());
            R3y.setPrefHeight(46);
            R3y.setPrefWidth(124);
            R3y.setAlignment(Pos.CENTER);
            userPoints3.getChildren().add(R3y);

            Label F = new Label(Integer.toString(matchTable.getSecondPlayerCurrentPoint()));
            F.setPrefHeight(46);
            F.setPrefWidth(124);
            F.setAlignment(Pos.CENTER);
            competitorFinalPoints.getChildren().add(F);

            // todo check if the final points is the current point
            Label Fy = new Label(Integer.toString(matchTable.getFirstPlayerCurrentPoint()));
            Fy.setPrefHeight(46);
            Fy.setPrefWidth(124);
            Fy.setAlignment(Pos.CENTER);
            userFinalPoints.getChildren().add(Fy);

            // todo a function for winner in match table
            Label W = new Label();
            W.setPrefHeight(46);
            W.setPrefWidth(124);
            W.setAlignment(Pos.CENTER);
            winner.getChildren().add(W);


            number--;
            if (number == 0)
                break;
        }

    }

}
