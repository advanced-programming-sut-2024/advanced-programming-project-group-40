
package models.UserInputHandler;

import models.cards.Card;

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
//        GameMenuController.setSelectedCard(card,gameViewController);
    }
}
