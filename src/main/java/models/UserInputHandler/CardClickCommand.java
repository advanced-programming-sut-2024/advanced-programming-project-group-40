package models.UserInputHandler;

import Server.Models.GameBoardVisualData;
import controllers.MenuController.GameMenuController;
import models.cards.Card;

import views.ViewController.GameViewController;

public class CardClickCommand extends Command {
    private final Card card;
private final boolean isSelectable;
private final String parentID;
    public CardClickCommand(Card card,boolean isSelectable,String parentID) {
        this.card = card;
        this.isSelectable = isSelectable;
        this.parentID = parentID;
    }


    @Override
    public void excute() {
        GameMenuController.ClickedOnCard(GameBoardVisualData.getCardInfoFromCard(card),isSelectable,parentID);
    }
}
