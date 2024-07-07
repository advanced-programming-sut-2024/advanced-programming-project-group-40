package views.ViewController;

import controllers.Generator;
import controllers.MenuController.SignUpMenuController;
import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.SignUpMenuMessages;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import models.AlertMaker;
import models.ErrorMaker;
import models.Game;
import models.User;
import views.LoginMenu;
import views.SecurityQuestionMenu;

import java.util.ArrayList;
import java.util.HashMap;

public class SignUpViewController {
    public VBox authorizationVbox;
    public VBox signUpVBox;
    @FXML
    private Label usernameError;
    @FXML
    private Label emailError;
    @FXML
    private Label passError;
    @FXML
    private Label confirmationError;
    @FXML
    private TextField nickname;
    @FXML
    private TextField password;
    @FXML
    private TextField passwordConfirmation;
    @FXML
    private TextField email;
    @FXML
    private TextField username;
    private String randomNewPassword;
    private HashMap<Integer, Boolean> validFiled = new HashMap<>();

    {
        validFiled.put(1, false);
        validFiled.put(2, false);
        validFiled.put(3, false);
        validFiled.put(4, false);
    }

    @FXML
    public void initialize() {
        authorizationVbox.setVisible(false);
        passwordConfirmation.setDisable(true);
        randomNewPassword = "";
        // Add a listener to the first text field
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean validUsername = SignUpMenuController.isUsernameValid(username.getText());
            boolean uniqueUsername = SignUpMenuController.isUsernameUnique(username.getText());
            // Enable or disable the second text field based on the content of the first text field
            if ((validUsername && uniqueUsername) || username.getText().isEmpty()) {
                ErrorMaker.removeError(usernameError);
                validFiled.put(1, true);
            } else {
                validFiled.put(1, false);
                if (!validUsername)
                    ErrorMaker.setError(usernameError, SignUpMenuMessages.INVALID_USER.toString());
                else
                    ErrorMaker.setError(usernameError, SignUpMenuMessages.DUPLICATE_USER.toString());
            }
        });


        email.textProperty().addListener((observable, oldValue, newValue) -> {
            if (SignUpMenuController.isEmailValid(email.getText()) || email.getText().isEmpty()) {
                validFiled.put(2, true);
                ErrorMaker.removeError(emailError);
            } else {
                validFiled.put(2, false);
                ErrorMaker.setError(emailError, SignUpMenuMessages.INVALID_EMAIL.toString());
            }
        });

        password.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean validPassword = SignUpMenuController.isPasswordValid(password.getText());
            boolean weakPassword = SignUpMenuController.isPasswordWeak(password.getText());
            boolean weakAndShortPassword = SignUpMenuController.isPasswordShort(password.getText());

            validFiled.put(3, false);
            if (password.getText().isEmpty()) {
                ErrorMaker.removeError(passError);
            } else if (!validPassword) {
                ErrorMaker.setError(passError, SignUpMenuMessages.INVALID_PASSWORD.toString());
            } else if (weakAndShortPassword) {
                ErrorMaker.setError(passError, SignUpMenuMessages.SHORT_PASSWORD.toString());
            } else if (weakPassword) {
                ErrorMaker.setError(passError, SignUpMenuMessages.PASSWORD_REQUIREMENTS.toString());
            } else {
                validFiled.put(3, true);
                passwordConfirmation.setDisable(false);
                ErrorMaker.removeError(passError);
            }
        });

        passwordConfirmation.textProperty().addListener((observable, oldValue, newValue) -> {
            if (SignUpMenuController.isPasswordTheSame(password.getText(), passwordConfirmation.getText()) || passwordConfirmation.getText().isEmpty()) {
                validFiled.put(4, true);
                ErrorMaker.removeError(confirmationError);
            } else {
                validFiled.put(4, false);
                ErrorMaker.setError(confirmationError, SignUpMenuMessages.WRONG_PASS_CONFIRMATION.toString());
            }
        });
    }


    private void goToQuestionPage() {
        try {
            new SecurityQuestionMenu().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void ContinueClicked() {
        if (!validFiled.containsValue(false) && !nickname.getText().isEmpty()) {
            String randomUsername=Generator.getRandomPassword();
            AlertMaker alert = SignUpMenuController.Continue(username.getText(),randomUsername);
            alert.showAlert();
            if (alert.getAlertType().equals(Alert.AlertType.ERROR)) {
                if (alert.isOK()) {
                    SignUpMenuController.createUser(randomUsername, password.getText(), email.getText(), nickname.getText());
                }
            } else {
                if (alert.isOK()) {
                    signUpVBox.setVisible(false);
                    authorizationVbox.setVisible(true);
                    if (SignUpMenuController.checkLink(email.getText())) {
                        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(10), actionEvent -> {
                            goToQuestionPage();
                        }));
                        timer.setCycleCount(1);
                        timer.play();
                    } else {
                        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(10), actionEvent -> {
                            signUpVBox.setVisible(true);
                            authorizationVbox.setVisible(false);
                        }));
                        timer.setCycleCount(1);
                        timer.play();
                    }
                }
            }
        }
    }


    public void getRandomPasswordClicked() {
        randomNewPassword = Generator.getRandomPassword();
        AlertMaker alert = new AlertMaker(Alert.AlertType.CONFIRMATION, AlertHeader.SIGN_UP.toString()
                , SignUpMenuMessages.RANDOM_PASSWORD.toString() + randomNewPassword);
        password.setDisable(true);
        passwordConfirmation.setDisable(true);
        alert.showAlert();
        if (alert.isOK()) {
            password.setText(randomNewPassword);
            password.setDisable(false);
            passwordConfirmation.setText(randomNewPassword);
            passwordConfirmation.setDisable(false);
        } else {
            password.setDisable(false);
        }
    }

    public void goToLoginMenu(MouseEvent mouseEvent) {
        try {
            new LoginMenu().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void setRandomPass(String randomPass) {
        randomPass = randomPass;
    }
}
