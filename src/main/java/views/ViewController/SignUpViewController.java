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
    private Label usernameError;
    @FXML
    private Label emailError;
    @FXML
    private Label passError;
    @FXML
    private Label confirmationError;
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
//        password.setDisable(true);
        passwordConfirmation.setDisable(true);
//        email.setDisable(true);
//        nickname.setDisable(true);
//        randomPass.setDisable(true);
//        signUp.setDisable(true);
//        errorLabel.getStyleClass().add("error-label");
//        errorLabel2.getStyleClass().add("error-label");
        randomNewPassword = "";
        // Add a listener to the first text field
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean validUsername = SignUpMenuController.isUsernameValid(username.getText());
            boolean uniqueUsername = SignUpMenuController.isUsernameUnique(username.getText());
            // Enable or disable the second text field based on the content of the first text field
            if ((validUsername && uniqueUsername) || username.getText().isEmpty()) {
                ErrorMaker.removeError(usernameError, username);
            } else {
                if (!validUsername)
                    ErrorMaker.setError(usernameError, username, SignUpMenuMessages.INVALID_USER.toString());
                else
                    ErrorMaker.setError(usernameError, username, SignUpMenuMessages.DUPLICATE_USER.toString());
            }
        });


        email.textProperty().addListener((observable, oldValue, newValue) -> {
            if (SignUpMenuController.isEmailValid(email.getText()) || email.getText().isEmpty()) {
                ErrorMaker.removeError(emailError, email);
            } else {
                ErrorMaker.setError(emailError, email, SignUpMenuMessages.INVALID_EMAIL.toString());
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

            if (password.getText().isEmpty()) {
                ErrorMaker.removeError(passError, password);
            } else if (!validPassword) {
                ErrorMaker.setError(passError, password, SignUpMenuMessages.INVALID_PASSWORD.toString());
            } else if (weakAndShortPassword) {
                ErrorMaker.setError(passError, password, SignUpMenuMessages.SHORT_PASSWORD.toString());
            } else if (weakPassword) {
                ErrorMaker.setError(passError, password, SignUpMenuMessages.PASSWORD_REQUIREMENTS.toString());
            } else {
                passwordConfirmation.setDisable(false);
                ErrorMaker.removeError(passError, password);
            }
        });

        passwordConfirmation.textProperty().addListener((observable, oldValue, newValue) -> {
            if (SignUpMenuController.isPasswordTheSame(password.getText(), passwordConfirmation.getText()) || passwordConfirmation.getText().isEmpty()) {
                ErrorMaker.removeError(confirmationError, passwordConfirmation);
            } else {
                ErrorMaker.setError(confirmationError, passwordConfirmation, SignUpMenuMessages.WRONG_CONFIRMATION.toString());
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
