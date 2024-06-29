package models;

import javafx.scene.control.Label;

public class ErrorMaker {
    public static void setError(Label errorLabel, String error) {
        errorLabel.setCenterShape(true);
        errorLabel.setText(error);
    }

    public static void removeError(Label errorLabel) {
        errorLabel.setText("");
    }
}
