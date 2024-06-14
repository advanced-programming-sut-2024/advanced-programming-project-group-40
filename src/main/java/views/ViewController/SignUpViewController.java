package views.ViewController;

import controllers.SignUpMenuController;
import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.SignUpMenuMessages;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.AlertMaker;
import models.ErrorMaker;
import views.SecurityQuestionMenu;
import views.SignUpMenu;

public class SignUpViewController {
    @FXML
    private Label errorLabel2;
    @FXML
    private Label errorLabel;
    @FXML
    private Button randomPass;
    @FXML
    private Button signUp;
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

    @FXML
    public void initialize() {
        // disable the second text field initially
        password.setDisable(true);
        passwordConfirmation.setDisable(true);
        email.setDisable(true);
        nickname.setDisable(true);
        randomPass.setDisable(true);
        signUp.setDisable(true);
        errorLabel.getStyleClass().add("error-label");
        errorLabel2.getStyleClass().add("error-label");
        randomNewPassword = "";
        // Add a listener to the first text field
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean validUsername = SignUpMenuController.isUsernameValid(username.getText()) || SignUpMenuController.isUsernameUnique(username.getText());
            boolean uniqueUsername = SignUpMenuController.isUsernameUnique(username.getText());
            // Enable or disable the second text field based on the content of the first text field
            if (validUsername && uniqueUsername) {
                ErrorMaker.removeError(errorLabel, errorLabel2, username);
                email.setDisable(newValue.trim().isEmpty());
            } else {
                if (!validUsername)
                    ErrorMaker.setError(errorLabel, errorLabel2, username, SignUpMenuMessages.INVALID_USER.toString(), "");
                else
                    ErrorMaker.setError(errorLabel, errorLabel2, username, SignUpMenuMessages.DUPLICATE_USER.toString(), "");
            }

        });


        email.textProperty().addListener((observable, oldValue, newValue) -> {
            if (SignUpMenuController.isEmailValid(email.getText())) {
                ErrorMaker.removeError(errorLabel, errorLabel2, email);
                nickname.setDisable(newValue.trim().isEmpty());
            } else {
                ErrorMaker.setError(errorLabel, errorLabel2, email, SignUpMenuMessages.INVALID_EMAIL.toString(), "");
            }
        });


        nickname.textProperty().addListener((observable, oldValue, newValue) -> {
            password.setDisable(newValue.trim().isEmpty());
            randomPass.setDisable(newValue.trim().isEmpty());
        });


        password.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean validPassword = SignUpMenuController.isPasswordValid(password.getText());
            boolean weakPassword = SignUpMenuController.isPasswordWeak(password.getText());
            boolean weakAndShortPassword = SignUpMenuController.isPasswordShort(password.getText());


            if (!validPassword) {
                ErrorMaker.setError(errorLabel, errorLabel2, password, SignUpMenuMessages.INVALID_PASSWORD.toString(), SignUpMenuMessages.PASSWORD_REQUIREMENTS.toString());
            } else if (weakAndShortPassword) {
                ErrorMaker.setError(errorLabel, errorLabel2, password, SignUpMenuMessages.WEAK_PASSWORD.toString(), SignUpMenuMessages.SHORT_PASSWORD.toString());
            } else if (weakPassword) {
                ErrorMaker.setError(errorLabel, errorLabel2, password, SignUpMenuMessages.WEAK_PASSWORD.toString(), SignUpMenuMessages.PASSWORD_REQUIREMENTS.toString());
            } else {
                ErrorMaker.removeError(errorLabel, errorLabel2, password);
                passwordConfirmation.setDisable(newValue.trim().isEmpty());
            }
        });

        passwordConfirmation.textProperty().addListener((observable, oldValue, newValue) -> {
            if (SignUpMenuController.isPasswordTheSame(password.getText(), passwordConfirmation.getText())) {
                ErrorMaker.removeError(errorLabel, errorLabel2, passwordConfirmation);
                signUp.setDisable(newValue.trim().isEmpty());
            } else {
                ErrorMaker.setError(errorLabel, errorLabel2, passwordConfirmation, SignUpMenuMessages.WRONG_CONFIRMATION.toString(), "");
            }
        });
    }


    private void goToQuestionPage() {
        try {
            new SecurityQuestionMenu().start(SignUpMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void ContinueClicked() {
        AlertMaker alert = SignUpMenuController.Continue(username.getText());
        alert.showAlert();
        if (alert.isOK()) {
            SignUpMenuController.creatUser(username.getText(), password.getText(), email.getText(), nickname.getText());
            goToQuestionPage();
        }
    }


    public void getRandomPasswordClicked() {
        randomNewPassword = SignUpMenuController.getRandomPassword();
        AlertMaker alert = new AlertMaker(Alert.AlertType.CONFIRMATION, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.RANDOM_PASSWORD.toString() + randomNewPassword);
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

}
