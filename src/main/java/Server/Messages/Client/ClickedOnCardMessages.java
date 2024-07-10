package Server.Messages.Client;

import Server.Messages.MessageType;
import enums.cards.CardInfo;
import models.Game;

public class ClickedOnCardMessages extends ClientMessages {
    private final CardInfo cardInfo;
    private final boolean isSelectable;
    private final String parentID;

    public ClickedOnCardMessages(CardInfo cardInfo, boolean isSelectable, String parentID) {
        this.token = Game.getLoggedInUser().getUsername();
        this.type = MessageType.CLICKED_ON_CARD;
        this.cardInfo = cardInfo;
        this.isSelectable = isSelectable;
        this.parentID = parentID;
    }

    public CardInfo getCardInfo() {
        return cardInfo;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public String getParentID() {
        return parentID;
    }
}
