package views.ViewController;

import controllers.LoginMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.Alert;
import models.Result;

public class LoginViewController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void signInClicked() {
        Alert alert = LoginMenuController.signIn(username.getText(), password.getText());
    }

}
