package views.ViewController;

import controllers.LoginMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.AlertMaker;
import views.ForgetPasswordMenu;
import views.LoginMenu;

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

    private void forgotPasswordClicked() {
        try {
            new ForgetPasswordMenu().start(LoginMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
