package views.ViewController;

import javafx.scene.control.Label;
import models.Game;
import models.User;

public class TargetProfileViewController {
    private static User targetUser;
    public Label username;
    public Label nickname;
    public Label rank;
    public Label highestScore;
    public Label numberOfGames;
    public Label draw;
    public Label lost;
    public Label won;

    public void initialize() {
        username.setText(targetUser.getUsername());
        nickname.setText(targetUser.getNickname());
        highestScore.setText(Integer.toString(targetUser.getHighestScore()));
        rank.setText(Integer.toString(targetUser.getRank()));
        numberOfGames.setText(Integer.toString(targetUser.getNumberOfMatches()));
        draw.setText(Integer.toString(targetUser.getDraw()));
        won.setText(Integer.toString(targetUser.getWon()));
        lost.setText(Integer.toString(targetUser.getLost()));
    }

    public static void setTargetUser(User targetUser) {
        TargetProfileViewController.targetUser = targetUser;
    }
}
