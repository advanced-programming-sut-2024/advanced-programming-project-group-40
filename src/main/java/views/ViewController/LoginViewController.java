package views.ViewController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import models.Game;
import views.ForgetPasswordMenu;
import views.MainMenu;
import views.SignUpMenu;

public class LoginViewController {
    public TextField authorizationCode;
    public VBox userPassVbox;
    public VBox authorizationVbox;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void initialize() {
        authorizationVbox.setVisible(false);
    }

    public void signInClicked() {

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

    public void ContinueClicked(MouseEvent mouseEvent) {
        
    }

    public void verifyClicked(MouseEvent mouseEvent) {
    }
}
