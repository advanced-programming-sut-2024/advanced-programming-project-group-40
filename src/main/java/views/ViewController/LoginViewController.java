package views.ViewController;

import controllers.MenuController.LoginMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.AlertMaker;
import models.Game;
import models.User;
import views.*;

public class LoginViewController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void signInClicked() {
        if (username.getText().equals("a") && password.getText().equals("a")) {
            Game.setLoggedInUser(new User("a", "a", "ahay@ahay.com", "ahay"));
        } else {
            AlertMaker alert = LoginMenuController.signIn(username.getText(), password.getText());
            alert.showAlert();
            if (alert.isOK())
                LoginMenuController.stayLoggedInSelected();
            else return;
        }
        goToMainMenu();
    }

    public void goToForgetPassword() {
        try {
            new ForgetPasswordMenu().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void goToSignUpMenu() {
        try {
            new SignUpMenu().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void goToMainMenu() {
        try {
            new MainMenu().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
