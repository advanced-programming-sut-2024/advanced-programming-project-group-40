package views.ViewController;

import controllers.DataSaver;
import javafx.scene.input.MouseEvent;
import views.*;

public class MainViewController {
    public void goToGameMenu(MouseEvent mouseEvent) {
        try {
            new PreGameMenu().start(MainMenu.stage);
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
            DataSaver.saveUsers();
            new LoginMenu().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void goToScoreBoard(MouseEvent mouseEvent) {
        try {
            new ScoreBoard().start(MainMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
