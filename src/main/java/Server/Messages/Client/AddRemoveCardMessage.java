package Server.Messages.Client;

import Server.Messages.MessageType;
import models.Game;

public class AddRemoveCardMessage extends ClientMessages {
    private String cardName;
    private boolean add;
    private int cardType;
    private int power;

    public AddRemoveCardMessage(String cardName, int cardType, int power, boolean add) {
        this.token = Game.getLoggedInUser().getUsername();
        this.cardName = cardName;
        this.add = add;
        this.cardType = cardType;
        this.power = power;
        if (add)
            this.type = MessageType.ADD_CARD;
        else
            this.type = MessageType.REMOVE_CARD;
    }

    public String getCardName() {
        return cardName;
    }

    public int getCardType() {
        return cardType;
    }

    public int getPower() {
        return power;
    }
}
