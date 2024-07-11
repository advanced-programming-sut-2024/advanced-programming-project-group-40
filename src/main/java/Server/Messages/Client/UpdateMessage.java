package Server.Messages.Client;

import Server.Messages.MessageSubType;
import Server.Messages.MessageType;
import models.Game;

public class UpdateMessage extends ClientMessages {
    private final String originUsername;
    private final MessageSubType subType;

    public UpdateMessage(String originUser, MessageSubType type) {
        this.token = Game.getLoggedInUser().getUsername();
        this.type = MessageType.UPDATE;
        this.subType = type;
        this.originUsername = originUser;
    }

    public String getOriginUsername() {
        return originUsername;
    }

    public MessageSubType getSubType() {
        return subType;
    }
}
