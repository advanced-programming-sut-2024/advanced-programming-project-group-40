package views.ViewController;

import controllers.LoginMenuController;
import controllers.SignUpMenuController;
import controllers.UserInfoController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.AlertMaker;

public class SignUpViewController {
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


    @FXML
    public void initialize() {
        // disable the second text field initially
        password.setDisable(true);
        passwordConfirmation.setDisable(true);
        email.setDisable(true);
        nickname.setDisable(true);

        randomPass.setDisable(true);
        signUp.setDisable(true);


        // Add a listener to the first text field
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            // Enable or disable the second text field based on the content of the first text field
            if (SignUpMenuController.isUsernameValid(username.getText()))
                email.setDisable(newValue.trim().isEmpty());

            // Validate the first text field and change its border color if necessary
            validateTextField(username, SignUpMenuController.isUsernameValid(username.getText()));
        });


        email.textProperty().addListener((observable, oldValue, newValue) -> {
            if (SignUpMenuController.isEmailValid(email.getText()))
                nickname.setDisable(newValue.trim().isEmpty());

            validateTextField(email, SignUpMenuController.isEmailValid(email.getText()));
        });


        nickname.textProperty().addListener((observable, oldValue, newValue) -> {
            password.setDisable(newValue.trim().isEmpty());
            randomPass.setDisable(newValue.trim().isEmpty());
        });


        password.textProperty().addListener((observable, oldValue, newValue) -> {
            if (SignUpMenuController.isPasswordValid(password.getText()))
                passwordConfirmation.setDisable(newValue.trim().isEmpty());

            validateTextField(password, SignUpMenuController.isPasswordValid(email.getText()));
        });

        passwordConfirmation.textProperty().addListener((observable, oldValue, newValue) -> {
            if (SignUpMenuController.isPasswordTheSame(password.getText(), passwordConfirmation.getText()))
                signUp.setDisable(newValue.trim().isEmpty());

            validateTextField(passwordConfirmation, SignUpMenuController.isPasswordTheSame(password.getText(), passwordConfirmation.getText()));
        });
    }

    private void validateTextField(TextField textField, boolean valid) {
        if (valid) {
            textField.getStyleClass().removeAll("error-border");
        } else {
            textField.getStyleClass().add("error-border");
        }
    }

    private void goToQuestionPage() {
        // todo
    }

    public void signUpClicked(MouseEvent mouseEvent) {
        AlertMaker alert = SignUpMenuController.signUp(username.getText(), password.getText(), passwordConfirmation.getText(), nickname.getText(), email.getText());
        alert.showAlert();
        if (alert.isYes())
            goToQuestionPage();
    }
}
