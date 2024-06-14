package views.ViewController;

import controllers.ForgetPasswordController;
import controllers.LoginMenuController;
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
import models.Game;
import models.User;

public class ForgetPasswordViewController {
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
    private User user;

    public void initialize() {
        secondVBox.setVisible(false);
        thirdVbox.setVisible(false);
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
                ErrorMaker.removeError(errorLabel, errorLabel2, newPassword);
                change.setDisable(false);
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
            change.setDisable(true);
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
            thirdVbox.setVisible(true);
        }
    }

    public void changeClicked() {
//        if ()
    }

}



