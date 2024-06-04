package views.ViewController;

import enums.SecurityQuestion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SecurityQuestionViewController {
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
        question1.setText(SecurityQuestion.FIRST_Q.toString());
        question2.setText(SecurityQuestion.SECOND_Q.toString());
        question3.setText(SecurityQuestion.THIRD_Q.toString());
        question4.setText(SecurityQuestion.FOURTH_Q.toString());
        question5.setText(SecurityQuestion.FIFTH_Q.toString());
    }
}
