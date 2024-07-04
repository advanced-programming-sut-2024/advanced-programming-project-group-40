package views.ViewController;

import controllers.Generator;
import controllers.MenuController.ForgetPasswordController;
import controllers.MenuController.SignUpMenuController;
import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.LoginMenuMessages;
import enums.AlertInfo.messages.SignUpMenuMessages;
import enums.SecurityQuestion;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import models.AlertMaker;
import models.ErrorMaker;
import models.Game;
import models.User;
import views.LoginMenu;
import views.MainMenu;

import java.util.ArrayList;

public class  ForgetPasswordViewController {
    @FXML
    private VBox thirdVbox;
    @FXML
    private Label errorLabel2;
    @FXML
    private Button randomPassword;
    @FXML
    private VBox firstVBox;
    @FXML
    private VBox secondVBox;
    @FXML
    private TextField username;
    @FXML
    private Label securityQuestion;
    @FXML
    private TextField answer;
    @FXML
    private PasswordField newPassword;
    private boolean validPass;
    private User user;

    public void initialize() {
        secondVBox.setVisible(false);
        thirdVbox.setVisible(false);
        validPass = false;
        ArrayList<User> all=Game.getAllUsers();
        for (User user : all) {
            System.out.println(user.getUsername());
        }
        newPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean validPassword = SignUpMenuController.isPasswordValid(newPassword.getText());
            boolean weakPassword = SignUpMenuController.isPasswordWeak(newPassword.getText());
            boolean weakAndShortPassword = SignUpMenuController.isPasswordShort(newPassword.getText());

            if (!validPassword) {
                ErrorMaker.setError( errorLabel2, SignUpMenuMessages.INVALID_PASSWORD.toString());
            } else if (weakAndShortPassword) {
                ErrorMaker.setError( errorLabel2, SignUpMenuMessages.SHORT_PASSWORD.toString());
            } else if (weakPassword) {
                ErrorMaker.setError(errorLabel2,SignUpMenuMessages.PASSWORD_REQUIREMENTS.toString());
            } else {
                validPass = true;
                ErrorMaker.removeError(errorLabel2);
            }

        });
    }

    public void submit1Clicked() {
        if ((user = Game.getUserByName(username.getText())) == null) {
            new AlertMaker(Alert.AlertType.ERROR, AlertHeader.SIGN_IN.toString(), LoginMenuMessages.INCORRECT_USERNAME.toString()).showAlert();
        } else {
            firstVBox.setVisible(false);
            secondVBox.setVisible(true);
            setSecurityQuestion();
        }
    }

    public void setSecurityQuestion() {
        securityQuestion.setText(SecurityQuestion.getQuestion(user.getSecurityQuestionNumber()));
    }

    public void submitAnswerClicked() {
        if (!ForgetPasswordController.isAnswerValid(user, answer.getText())) {
            new AlertMaker(Alert.AlertType.ERROR, AlertHeader.SIGN_IN.toString(), LoginMenuMessages.INCORRECT_SECURITY_ANSWER.toString()).showAlert();
        } else {
            answer.setDisable(true);
            thirdVbox.setVisible(true);
        }
    }

    public void changeClicked() {
        if (validPass) {
            user.setPassword(newPassword.getText());
            Game.setLoggedInUser(user);
            try {
                new MainMenu().start(Game.stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void getRandomPassword(MouseEvent mouseEvent) {
        String randomNewPassword = Generator.getRandomPassword();
        AlertMaker alert = new AlertMaker(Alert.AlertType.CONFIRMATION, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.RANDOM_PASSWORD.toString() + randomNewPassword);
        randomPassword.setDisable(true);
        alert.showAlert();
        if (alert.isOK()) {
            validPass=true;
            newPassword.setText(randomNewPassword);
            changeClicked();
        } else {
            randomPassword.setDisable(false);
        }
    }

    public void goToLoginMenu(MouseEvent mouseEvent) {
        try {
            new LoginMenu().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}



