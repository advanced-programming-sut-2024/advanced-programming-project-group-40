package views.ViewController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignUpViewController {
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
            email.setDisable(newValue.trim().isEmpty());

            // Validate the first text field and change its border color if necessary
            validateTextField(username);
        });


        // Add a listener to the first text field
        email.textProperty().addListener((observable, oldValue, newValue) -> {
            // Enable or disable the second text field based on the content of the first text field
            nickname.setDisable(newValue.trim().isEmpty());

            // Validate the first text field and change its border color if necessary
            validateTextField(email);
        });


        // Add a listener to the first text field
        nickname.textProperty().addListener((observable, oldValue, newValue) -> {
            // Enable or disable the second text field based on the content of the first text field
            password.setDisable(newValue.trim().isEmpty());
            // Enable or disable the second text field based on the content of the first text field
            randomPass.setDisable(newValue.trim().isEmpty());

            // Validate the first text field and change its border color if necessary
            validateTextField(nickname);
        });


        // Add a listener to the first text field
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            // Enable or disable the second text field based on the content of the first text field
            passwordConfirmation.setDisable(newValue.trim().isEmpty());

            // Validate the first text field and change its border color if necessary
            validateTextField(password);
        });

        // Add a listener to the first text field
        passwordConfirmation.textProperty().addListener((observable, oldValue, newValue) -> {
            // Enable or disable the second text field based on the content of the first text field
            signUp.setDisable(newValue.trim().isEmpty());

            // Validate the first text field and change its border color if necessary
            validateTextField(password);
        });
    }

    private void validateTextField(TextField textField) {
        String text = textField.getText();
        if (!text.matches("\\d*")) { // Check if the text contains only digits
            textField.getStyleClass().add("error-border");
        } else {
            textField.getStyleClass().removeAll("error-border");
        }
    }


}
