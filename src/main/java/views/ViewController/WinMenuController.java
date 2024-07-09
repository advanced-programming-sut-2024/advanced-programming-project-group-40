package views.ViewController;

import controllers.MenuController.GameMenuController;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import models.Game;
import models.MatchTable;
import views.GameView;
import views.MainMenu;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class WinMenuController implements Initializable {
    public Label title;
    public Label remainingLives;
    public Label remainingCards;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (GameMenuController.getMatchTable().getFirstPlayerCrystals() != 0) {
           title.setText(STR."\{GameMenuController.getMatchTable().getFirstPlayer().getUsername()} WON!");
            remainingLives.setText(STR."\{GameMenuController.getMatchTable().getFirstPlayerCrystals()}");
            remainingCards.setText(STR."\{GameMenuController.getMatchTable().getFirstPlayerInPlayCards().size()}");
        }else {
            title.setText(STR."\{GameMenuController.getMatchTable().getSecondPlayer().getUsername()} WON!");
            remainingLives.setText(STR."\{GameMenuController.getMatchTable().getSecondPlayerCrystals()}");
            remainingCards.setText(STR."\{GameMenuController.getMatchTable().getSecondPlayerInPlayCards().size()}");

        }

    }

    public void returnToMain() {
        try {
            new MainMenu().start(Game.stage);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
