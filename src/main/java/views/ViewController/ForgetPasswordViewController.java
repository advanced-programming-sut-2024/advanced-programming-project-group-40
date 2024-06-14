package views.ViewController;

import controllers.ForgetPasswordController;
import controllers.SignUpMenuController;
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
import models.User;

public class ForgetPasswordViewController {
    @FXML
    private Button submit;
    @FXML
    private VBox thirdVbox;
    @FXML
    private Label errorLabel;
    @FXML
    private Label errorLabel2;
    @FXML
    private Button change;
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
        newPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean validPassword = SignUpMenuController.isPasswordValid(newPassword.getText());
            boolean weakPassword = SignUpMenuController.isPasswordWeak(newPassword.getText());
            boolean weakAndShortPassword = SignUpMenuController.isPasswordShort(newPassword.getText());

            if (!validPassword) {
                ErrorMaker.setError(errorLabel, errorLabel2, newPassword, SignUpMenuMessages.INVALID_PASSWORD.toString(), SignUpMenuMessages.PASSWORD_REQUIREMENTS.toString());
            } else if (weakAndShortPassword) {
                ErrorMaker.setError(errorLabel, errorLabel2, newPassword, SignUpMenuMessages.WEAK_PASSWORD.toString(), SignUpMenuMessages.SHORT_PASSWORD.toString());
            } else if (weakPassword) {
                ErrorMaker.setError(errorLabel, errorLabel2, newPassword, SignUpMenuMessages.WEAK_PASSWORD.toString(), SignUpMenuMessages.PASSWORD_REQUIREMENTS.toString());
            } else {
                validPass = true;
                ErrorMaker.removeError(errorLabel, errorLabel2, newPassword);
            }

        });
    }

    public void submit1Clicked() {
        if (!username.getText().equals("sarina")) {
            // todo
//        if ((user = Game.getUserByName(username.getText())) == null) {
            new AlertMaker(Alert.AlertType.ERROR, AlertHeader.SIGN_IN.toString(), LoginMenuMessages.INCORRECT_USERNAME.toString()).showAlert();
        } else {
            firstVBox.setVisible(false);
            secondVBox.setVisible(true);
            setSecurityQuestion();
        }
//        }
    }

    public void setSecurityQuestion() {
        securityQuestion.setText(SecurityQuestion.getQuestion(user.getSecurityQuestionNumber()));
    }

    public void submitAnswerClicked() {
        if (!ForgetPasswordController.isAnswerValid(user, answer.getText())) {
            new AlertMaker(Alert.AlertType.ERROR, AlertHeader.SIGN_IN.toString(), LoginMenuMessages.INCORRECT_SECURITY_ANSWER.toString()).showAlert();
        } else {
            submit.setDisable(true);
            thirdVbox.setVisible(true);
        }
    }

    public void changeClicked() {
        if (validPass) {
            // todo go to main menu
        }
    }

    public void getRandomPassword(MouseEvent mouseEvent) {
        String randomNewPassword = SignUpMenuController.getRandomPassword();
        AlertMaker alert = new AlertMaker(Alert.AlertType.CONFIRMATION, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.RANDOM_PASSWORD.toString() + randomNewPassword);
        randomPassword.setDisable(true);
        alert.showAlert();
        if (alert.isOK()) {
            changeClicked();
        } else {
            randomPassword.setDisable(false);
        }
    }
}



