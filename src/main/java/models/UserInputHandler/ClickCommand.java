
package models.UserInputHandler;

import controllers.MenuController.GameMenuController;
import javafx.css.Match;
import models.MatchTable;
import models.cards.Card;

import javafx.scene.input.MouseEvent;
import views.ViewController.GameViewController;

public class ClickCommand extends Command {
    private final Card card;
    private final String parent;
    private final GameViewController gameViewController;

    public ClickCommand(Card card, String parent, GameViewController gameViewController) {
        this.card = card;
        this.parent = parent;
        this.gameViewController = gameViewController;
    }


    @Override
    public void excute() {

        GameMenuController.ClickedOnCard(card,gameViewController);
    }
}

