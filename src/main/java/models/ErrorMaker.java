package models;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ErrorMaker {
    public static void setError(Label errorLabel, Label errorLabel2, TextField textField, String error, String error2) {
        textField.getStyleClass().add("error-border");
        errorLabel.setCenterShape(true);
        errorLabel.setText(error);
        errorLabel2.setText(error2);
    }

    public static void removeError(Label errorLabel, Label errorLabel2, TextField textField) {
        textField.getStyleClass().removeAll("error-border");
        errorLabel.setText("");
        errorLabel2.setText("");
    }
}
