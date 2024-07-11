package Server.Messages.Client;

import Server.Messages.MessageType;
import Server.Models.GameBoardVisualData;
import enums.cards.CardInfo;
import models.Game;

public class ClickedOnCardMessages extends ClientMessages {
    private final String cardInfoName;
    private final boolean isSelectable;
    private final String parentID;

    public ClickedOnCardMessages(String cardInfoName, boolean isSelectable, String parentID) {
        this.token = Game.getLoggedInUser().getUsername();
        this.type = MessageType.CLICKED_ON_CARD;
        this.cardInfoName = cardInfoName;
        this.isSelectable = isSelectable;
        this.parentID = parentID;

    }

    public String getCardInfo() {
        return cardInfoName;
    }
    public void setType(MessageType q){
        this.type =q;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public String getParentID() {
        return parentID;
    }
}
