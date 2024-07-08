package Server.Messages.Client;

import Server.Messages.MessageType;
import models.Game;

public class AddRemoveCardMessage extends ClientMessages{
    private String cardName;
    private boolean add;
    private boolean unitCard;
    private int power;

    public AddRemoveCardMessage(String cardName, boolean unitCard, int power, boolean add) {
        this.token = Game.getLoggedInUser().getUsername();
        this.cardName = cardName;
        this.add = add;
        if (add)
            this.type = MessageType.ADD_CARD;
        else
            this.type = MessageType.REMOVE_CARD;
    }

    public String getCardName() {
        return cardName;
    }
}
