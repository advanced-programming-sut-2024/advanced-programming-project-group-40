package models;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ErrorMaker {
    public static void setError(Label errorLabel,TextField textField, String error) {
        errorLabel.setCenterShape(true);
        errorLabel.setText(error);
    }

    public static void removeError(Label errorLabel, TextField textField) {
        errorLabel.setText("");
    }
}
