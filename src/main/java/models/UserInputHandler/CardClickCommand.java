package models.UserInputHandler;

import controllers.MenuController.GameMenuController;
import models.cards.Card;

import views.ViewController.GameViewController;

public class CardClickCommand extends Command {
    private Card card = null;


    /////////////////
    private GameViewController gameViewController;

    public CardClickCommand(Card card, GameViewController gameViewController) {
        this.card = card;
        this.gameViewController = gameViewController;
    }


    @Override
    public void excute() {
//        GameMenuController.ClickedOnCard(card, gameViewController);
    }
}
