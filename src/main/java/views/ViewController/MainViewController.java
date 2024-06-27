package views.ViewController;

import javafx.scene.input.MouseEvent;
import models.Game;
import models.User;
import views.ForgetPasswordMenu;
import views.LoginMenu;
import views.MainMenu;

import java.util.ArrayList;

public class MainViewController {
    public void goToGameMenu(MouseEvent mouseEvent) {

    }

    public void goToProfileMenu(MouseEvent mouseEvent) {
        try {
            new ForgetPasswordMenu().start(LoginMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void SignOut(MouseEvent mouseEvent) {
        try {
            new LoginMenu().start(MainMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
