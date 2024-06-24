package views.ViewController;

import controllers.SignUpMenuController;
import enums.SecurityQuestion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.AlertMaker;

public class SecurityQuestionViewController {
    @FXML
    private Button signUp;
    @FXML
    private TextField confirmation;
    @FXML
    private TextField questionNumber;
    @FXML
    private TextField answer;
    @FXML
    private Label question1;
    @FXML
    private Label question2;
    @FXML
    private Label question3;
    @FXML
    private Label question4;
    @FXML
    private Label question5;

    public void initialize() {
        question1.setText(SecurityQuestion.getQuestion(1));
        question2.setText(SecurityQuestion.getQuestion(2));
        question3.setText(SecurityQuestion.getQuestion(3));
        question4.setText(SecurityQuestion.getQuestion(4));
        question5.setText(SecurityQuestion.getQuestion(5));


    }

    public void signUpClicked() {
        if (!confirmation.getText().isEmpty() && !questionNumber.getText().isEmpty() && !answer.getText().isEmpty()) {

            AlertMaker alertMaker = SignUpMenuController.signUp(Integer.parseInt(questionNumber.getText()), answer.getText(), confirmation.getText());
            alertMaker.showAlert();
        }
    }
}