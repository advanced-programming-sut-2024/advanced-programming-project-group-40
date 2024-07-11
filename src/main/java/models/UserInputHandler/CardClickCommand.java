package models.UserInputHandler;

import Server.ClientHandler;
import Server.Messages.Client.ClickedOnCardMessages;
import Server.Models.GameBoardVisualData;
import models.cards.Card;

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
        ClickedOnCardMessages messages = new ClickedOnCardMessages(GameBoardVisualData.getCardInfoFromCard(card),isSelectable,parentID);
        ClientHandler.client.clickedOnCard(messages);
        //GameMenuController.ClickedOnCard(GameBoardVisualData.getCardInfoFromCard(card),isSelectable,parentID);
    }
}
