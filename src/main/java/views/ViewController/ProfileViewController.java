package views.ViewController;


import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import models.Game;
import models.MatchTable;
import models.User;
import views.*;



public class ProfileViewController {

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


    public void goToLoginMenu(MouseEvent mouseEvent) {
        try {
            new MainMenu().start(ProfileMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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

    }


    public void changeInfo(MouseEvent mouseEvent) {
        try {
            new ChangeInfoMenu().start(ProfileMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
