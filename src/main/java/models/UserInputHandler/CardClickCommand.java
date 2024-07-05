package models.UserInputHandler;

import controllers.MenuController.GameMenuController;
import models.cards.Card;

import views.ViewController.GameViewController;

public class CardClickCommand extends Command {
    private final Card card;
    public CardClickCommand(Card card) {
        this.card = card;
    }


    @Override
    public void excute() {
        GameMenuController.ClickedOnCard(card);
    }
}
