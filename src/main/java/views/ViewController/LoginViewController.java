package views.ViewController;

import controllers.MenuController.LoginMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.AlertMaker;
import models.Game;
import models.User;
import views.ForgetPasswordMenu;
import views.LoginMenu;
import views.SignUpMenu;

import java.util.ArrayList;

public class LoginViewController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void signInClicked() {
        AlertMaker alert = LoginMenuController.signIn(username.getText(), password.getText());
        alert.showAlert();
        if (alert.isOK())
            LoginMenuController.stayLoggedInSelected();
    }

    public void goToForgetPassword() {
        try {
            new ForgetPasswordMenu().start(LoginMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void goToSignUpMenu(){
        try {
            new SignUpMenu().start(LoginMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
