package views.ViewController;


import controllers.MenuController.ProfileMenuController;
import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.ProfileMenuMessages;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.AlertMaker;
import models.Game;
import models.User;
import views.*;

import java.util.Objects;


public class ProfileViewController {
    //TODO LIst of waits
    public VBox Following;
    public VBox Followers;
    public TextField targetUser;
    public VBox requests;
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
            new GameHistory().start(ProfileMenu.stage);
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


    private void creatRequest() {
        User loggedInUser = Game.getLoggedInUser();
        for (User request : loggedInUser.getRequests()) {
            ImageView imageView2 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/Icnos/reject.png")).toExternalForm()));
            ImageView imageView1 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/Icnos/accept.png")).toExternalForm()));

            // Create the Label
            Label label = new Label(request.getUsername());
            label.setPrefHeight(16.0);
            label.setPrefWidth(116.0);
            label.setFont(new Font(14.0));

            // Create the first ImageView
            imageView1.setFitHeight(34.0);
            imageView1.setFitWidth(30.0);
            imageView1.setPickOnBounds(true);
            imageView1.setPreserveRatio(true);
            imageView1.setTranslateX(-10.0);
            imageView1.setOnMouseClicked(event -> handleClick(event, label, imageView1, imageView2, request));

            // Create the second ImageView
            imageView2.setFitHeight(34.0);
            imageView2.setFitWidth(30.0);
            imageView2.setPickOnBounds(true);
            imageView2.setPreserveRatio(true);
            imageView2.setTranslateX(10.0);
            imageView2.setOnMouseClicked(event -> handleClick(event, label, imageView1, imageView2, request));

            // Create the HBox and add children
            HBox hBox = new HBox();
            hBox.setAlignment(javafx.geometry.Pos.CENTER);
            hBox.setPrefHeight(46.0);
            hBox.setPrefWidth(138.0);
            hBox.getChildren().addAll(label, imageView1, imageView2);
            // Add HBox to VBox
            requests.getChildren().add(hBox);
        }
    }

    private void handleClick(MouseEvent event, Label label, ImageView accept, ImageView reject, User request) {
        ImageView clickedImageView = (ImageView) event.getSource();
        if (clickedImageView.equals(accept)) {
            Game.getLoggedInUser().addFollower(request);
            request.addFollowing(Game.getLoggedInUser());
        }
        Game.getLoggedInUser().getRequests().remove(request);
        requests.getChildren().remove(label.getParent());
    }

    private void creatFollower() {
        User loggedInUser = Game.getLoggedInUser();
        for (User request : loggedInUser.getFollowers()) {
            // Create the Label
            Label label = new Label(request.getUsername());
            label.setPrefHeight(16.0);
            label.setPrefWidth(116.0);
            label.setFont(new Font(14.0));
//            label.setOnMouseClicked(event -> handleClick(event,label));

            Followers.getChildren().add(label);
        }
    }

    private void creatFollowing() {
        User loggedInUser = Game.getLoggedInUser();
        for (User request : loggedInUser.getFollowers()) {
            // Create the Label
            Label label = new Label(request.getUsername());
            label.setPrefHeight(16.0);
            label.setPrefWidth(116.0);
            label.setFont(new Font(14.0));
//            label.setOnMouseClicked(event -> handleClick(event,label));


            Following.getChildren().add(label);
        }
    }

    public void GameRequest(MouseEvent mouseEvent) {
    }
}
