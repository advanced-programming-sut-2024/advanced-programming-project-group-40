package views.ViewController;

import controllers.MenuController.ChangeInfoController;
import controllers.MenuController.SignUpMenuController;
import enums.AlertInfo.messages.ChangeInfoMenuMessages;
import enums.AlertInfo.messages.SignUpMenuMessages;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.AlertMaker;
import models.ErrorMaker;
import models.Game;

import java.util.HashMap;

public class ChangeInfoViewController {
    @FXML
    private TextField newUsername;
    @FXML
    private Label usernameError;
    @FXML
    private TextField newEmail;
    @FXML
    private Label emailError;
    @FXML
    private TextField newNickname;
    @FXML
    private PasswordField oldPassword;
    @FXML
    private Label oldPassError;
    @FXML
    private PasswordField newPassword;
    @FXML
    private Label newPasswordError;
    @FXML
    private Button change;

    private HashMap<Integer, Boolean> validFiled = new HashMap<>();

    {
        validFiled.put(1, false);
        validFiled.put(2, false);
        validFiled.put(3, false);
        validFiled.put(4, false);
    }

    public void initialize() {
        newPassword.setDisable(true);

        // Add a listener to the first text field
        newUsername.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean validUsername = ChangeInfoController.isUsernameValid(newUsername.getText());
            boolean uniqueUsername = ChangeInfoController.isUsernameUnique(newPassword.getText());
            // Enable or disable the second text field based on the content of the first text field
            if ((validUsername && uniqueUsername) || newUsername.getText().isEmpty()) {
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


        newEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            if (ChangeInfoController.isEmailValid(newEmail.getText()) || newEmail.getText().isEmpty()) {
                validFiled.put(2, true);
                ErrorMaker.removeError(emailError);
            } else {
                validFiled.put(2, false);
                ErrorMaker.setError(emailError, SignUpMenuMessages.INVALID_EMAIL.toString());
            }
        });

        newPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean validPassword = ChangeInfoController.isPasswordValid(newPassword.getText());
            boolean weakPassword = ChangeInfoController.isPasswordWeak(newPassword.getText());
            boolean weakAndShortPassword = ChangeInfoController.isPasswordShort(newPassword.getText());

            validFiled.put(3, false);
            if (newPassword.getText().isEmpty()) {
                ErrorMaker.removeError(newPasswordError);
            } else if (!validPassword) {
                ErrorMaker.setError(newPasswordError, SignUpMenuMessages.INVALID_PASSWORD.toString());
            } else if (weakAndShortPassword) {
                ErrorMaker.setError(newPasswordError, SignUpMenuMessages.SHORT_PASSWORD.toString());
            } else if (weakPassword) {
                ErrorMaker.setError(newPasswordError, SignUpMenuMessages.PASSWORD_REQUIREMENTS.toString());
            } else {
                validFiled.put(3, true);
                newPassword.setDisable(false);
                ErrorMaker.removeError(newPasswordError);
            }
        });

        oldPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (oldPassword.getText().isEmpty() || ChangeInfoController.correctPassword(oldPassword.getText())) {
                validFiled.put(4, true);
                ErrorMaker.removeError(oldPassError);
            } else {
                validFiled.put(4, false);
                ErrorMaker.setError(oldPassError, ChangeInfoMenuMessages.WRONG_PASSWORD.toString());
            }
        });
    }


    public void changeClicked(MouseEvent mouseEvent) {
        AlertMaker alertMaker = ChangeInfoController.changeCheck(newUsername, newEmail, oldPassword, newPassword);
        alertMaker.showAlert();
    }
}
