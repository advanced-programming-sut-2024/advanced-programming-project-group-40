package Server.Messages.Client;

import Server.Messages.MessageSubType;
import Server.Messages.MessageType;

public class UpdateMessage extends ClientMessages {
    private final String originUsername;
    private final MessageSubType subType;

    public UpdateMessage(String originUser, MessageSubType type) {
        this.type = MessageType.REQUEST;
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
