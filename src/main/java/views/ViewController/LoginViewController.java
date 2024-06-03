package views.ViewController;

import controllers.LoginMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import models.AlertMaker;
import models.Game;
import models.Result;

public class LoginViewController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void signInClicked() {
        AlertMaker alert = LoginMenuController.signIn(username.getText(), password.getText());
        alert.showAlert();
        if (alert.isYes())
            LoginMenuController.stayLoggedInSelected();
    }



}
