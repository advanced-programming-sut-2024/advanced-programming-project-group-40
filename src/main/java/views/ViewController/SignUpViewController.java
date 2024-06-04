package views.ViewController;

import controllers.SignUpMenuController;
import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.SignUpMenuMessages;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.AlertMaker;

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
                removeError(username);
                email.setDisable(newValue.trim().isEmpty());
            } else {
                if (!validUsername)
                    setError(username, SignUpMenuMessages.INVALID_USER.toString(), "");
                else
                    setError(username, SignUpMenuMessages.DUPLICATE_USER.toString(), "");
            }

        });


        email.textProperty().addListener((observable, oldValue, newValue) -> {
            if (SignUpMenuController.isEmailValid(email.getText())) {
                removeError(email);
                nickname.setDisable(newValue.trim().isEmpty());
            } else {
                setError(email, SignUpMenuMessages.INVALID_EMAIL.toString(), "");
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

            if (validPassword && weakPassword && weakAndShortPassword) {
                removeError(password);
                passwordConfirmation.setDisable(newValue.trim().isEmpty());
            } else {
                if (!validPassword)
                    setError(password, SignUpMenuMessages.INVALID_PASSWORD.toString(), SignUpMenuMessages.PASSWORD_REQUIREMENTS.toString());
                else if (!weakPassword)
                    setError(password, SignUpMenuMessages.WEAK_PASSWORD.toString(), SignUpMenuMessages.PASSWORD_REQUIREMENTS.toString());
                else
                    setError(password, SignUpMenuMessages.WEAK_PASSWORD.toString(), SignUpMenuMessages.SHORT_PASSWORD.toString());
            }
        });

        passwordConfirmation.textProperty().addListener((observable, oldValue, newValue) -> {
            if (SignUpMenuController.isPasswordTheSame(password.getText(), passwordConfirmation.getText())) {
                removeError(passwordConfirmation);
                signUp.setDisable(newValue.trim().isEmpty());
            } else {
                setError(passwordConfirmation, SignUpMenuMessages.WRONG_PASSWORD_CONFIRMATION.toString(), "");
            }
        });
    }

    private void setError(TextField textField, String error, String error2) {
        textField.getStyleClass().add("error-border");
        errorLabel.setCenterShape(true);
        errorLabel.setText(error);
        errorLabel2.setText(error2);
    }

    private void removeError(TextField textField) {
        textField.getStyleClass().removeAll("error-border");
        errorLabel.setText("");
        errorLabel2.setText("");
    }


    private void goToQuestionPage() {
        // todo
    }

    public void signUpClicked() {
        AlertMaker alert = SignUpMenuController.signUp(username.getText(), password.getText(), passwordConfirmation.getText(), nickname.getText(), email.getText());
        alert.showAlert();
        if (alert.isOK())
            goToQuestionPage();
    }


    public void getRandomPasswordClicked() {
        randomNewPassword = SignUpMenuController.getRandomPassword();
        AlertMaker alert = new AlertMaker(Alert.AlertType.CONFIRMATION, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.RANDOM_PASSWORD.toString() + randomNewPassword);
        password.setDisable(true);
        passwordConfirmation.setDisable(true);
        alert.showAlert();
        if (alert.isOK()) {
            System.out.println("p[p[p[");
            password.setText(randomNewPassword);
            password.setDisable(false);
            passwordConfirmation.setText(randomNewPassword);
            passwordConfirmation.setDisable(false);
        } else {
            password.setDisable(false);
        }
    }
}
