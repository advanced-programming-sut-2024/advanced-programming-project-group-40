package views.ViewController;


import controllers.MenuController.ProfileMenuController;
import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.ProfileMenuMessages;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.AlertMaker;
import models.Game;
import models.User;
import views.*;

import java.util.Objects;


public class ProfileViewController {

    public VBox Following;
    public VBox Followers;
    public TextField targetUser;
    public VBox Requests;
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
            new MainMenu().start(Game.stage);
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
            new ChangeInfoMenu().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void GameHistory(MouseEvent mouseEvent) {
        try {
            new GameHistory().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void search(MouseEvent mouseEvent) throws Exception {
        AlertMaker alertMaker = ProfileMenuController.search(targetUser.getText());
        alertMaker.showAlert();
        if (alertMaker.getAlertType().equals(Alert.AlertType.CONFIRMATION)) {
            if (alertMaker.isOK()) {
                TargetProfileViewController.setTargetUser(Game.getUserByName(targetUser.getText()));
                Stage stage = new Stage();
                new TargetProfile().start(stage);
                stage.setOnCloseRequest((WindowEvent event) -> {
                    sendRequest();
                });
            } else {
                sendRequest();
            }
        }
    }

    private void sendRequest() {
        AlertMaker alertMaker = new AlertMaker(Alert.AlertType.CONFIRMATION, AlertHeader.PROFILE_MENU.toString(), ProfileMenuMessages.SEND_REQUEST.toString());
        alertMaker.showAlert();
        if (alertMaker.isOK())
            ProfileMenuController.sendRequest(Objects.requireNonNull(Game.getUserByName(targetUser.getText())));
    }


    private void creatRequest(){
        User loggedInUser = Game.getLoggedInUser();
        for (User request: loggedInUser.getRequests()){

        }
    }
}
