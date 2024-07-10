package views.ViewController;

import controllers.DataSaver;
import javafx.scene.input.MouseEvent;
import models.Game;
import views.*;

public class MainViewController {
    public void goToGameMenu(MouseEvent mouseEvent) {
        try {
            new PreGameMenu().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void goToProfileMenu(MouseEvent mouseEvent) {
        try {
            new ProfileMenu().start(Game.stage);
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
            new ScoreBoard().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void goToEliminationCup(MouseEvent mouseEvent) {
        try {
            new EliminationCupMenu().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
