package views.ViewController;

import javafx.scene.input.MouseEvent;
import views.ForgetPasswordMenu;
import views.GameView;
import views.LoginMenu;
import views.MainMenu;

public class MainViewController {
    public void goToGameMenu(MouseEvent mouseEvent) {
        try {
            new GameView().start(MainMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
