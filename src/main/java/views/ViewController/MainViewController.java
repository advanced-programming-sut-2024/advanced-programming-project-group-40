package views.ViewController;

import javafx.scene.input.MouseEvent;
import views.*;

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
            new ProfileMenu().start(MainMenu.stage);
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
