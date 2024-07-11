package views.ViewController;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Game;
import views.MainMenu;
import views.TerminalView;

public class TelevisionViewController {
    public VBox player1;
    public VBox player2;
    public VBox watch;



    public void initialize(){

    }

    public void openTerminal(MouseEvent mouseEvent) {
        try {
            new TerminalView().start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void goToLoginMenu(MouseEvent mouseEvent) {
        try {
            new MainMenu().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
