package views.ViewController;

import controllers.MenuController.SignUpMenuController;
import enums.AlertInfo.messages.SignUpMenuMessages;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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

    private HashMap<Integer,Boolean> validFiled = new HashMap<>();

    {
        validFiled.put(1,false);
        validFiled.put(2,false);
        validFiled.put(3,false);
        validFiled.put(4,false);
    }

    public void initialize(){
        newPassword.setDisable(true);

        // Add a listener to the first text field
        newUsername.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean validUsername = SignUpMenuController.isUsernameValid(newUsername.getText());
            boolean uniqueUsername = SignUpMenuController.isUsernameUnique(newPassword.getText());
            // Enable or disable the second text field based on the content of the first text field
            if ((validUsername && uniqueUsername) || newUsername.getText().isEmpty()) {
                ErrorMaker.removeError(usernameError);
                validFiled.put(1,true);
            }  else {
                validFiled.put(1,false);
                if (!validUsername)
                    ErrorMaker.setError(usernameError, SignUpMenuMessages.INVALID_USER.toString());
                else
                    ErrorMaker.setError(usernameError, SignUpMenuMessages.DUPLICATE_USER.toString());
            }
        });


        newEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            if (SignUpMenuController.isEmailValid(newEmail.getText()) || newEmail.getText().isEmpty()) {
                validFiled.put(2, true);
                ErrorMaker.removeError(emailError);
            } else {
                validFiled.put(2, false);
                ErrorMaker.setError(emailError, SignUpMenuMessages.INVALID_EMAIL.toString());
            }
        });

        oldPassword.textProperty().addListener((observable, oldValue, oldValue) -> {
            boolean validPassword = SignUpMenuController.isPasswordValid(oldPassword.getText());
            boolean weakPassword = SignUpMenuController.isPasswordWeak(oldPassword.getText());
            boolean weakAndShortPassword = SignUpMenuController.isPasswordShort(oldPassword.getText());

            validFiled.put(3, false);
            if (oldPassword.getText().isEmpty()) {
                ErrorMaker.removeError(oldPassError);
            } else if (!validPassword) {
                ErrorMaker.setError(oldPassError, SignUpMenuMessages.INVALID_PASSWORD.toString());
            } else if (weakAndShortPassword) {
                ErrorMaker.setError(oldPassError, SignUpMenuMessages.SHORT_PASSWORD.toString());
            } else if (weakPassword) {
                ErrorMaker.setError(oldPassError, SignUpMenuMessages.PASSWORD_REQUIREMENTS.toString());
            } else {
                validFiled.put(3, true);
                newPassword.setDisable(false);
                ErrorMaker.removeError(oldPassError);
            }
        });

        newPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (SignUpMenuController.isPasswordTheSame(newPassword.getText(), passwordConfirmation.getText()) || passwordConfirmation.getText().isEmpty()) {
                validFiled.put(4, true);
                ErrorMaker.removeError(confirmationError);
            } else {
                validFiled.put(4, false);
                ErrorMaker.setError(confirmationError, SignUpMenuMessages.WRONG_PASS_CONFIRMATION.toString());
            }
        });
    }


    public void changeClicked(MouseEvent mouseEvent) {
        newUsername.getText().equals(Game.getLoggedInUser().getUsername());
    }
}
