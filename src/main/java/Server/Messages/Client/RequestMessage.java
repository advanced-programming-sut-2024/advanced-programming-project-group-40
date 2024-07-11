package Server.Messages.Client;

import Server.Messages.MessageSubType;
import Server.Messages.MessageType;

public class RequestMessage extends ClientMessages {
    private String originUsername;
    private String destinationUsername;
    private MessageSubType subType;

    public RequestMessage(String originUser, String destinationUser, MessageSubType type) {
        this.type = MessageType.FRIEND_REQUEST;
        this.subType = type;
        this.originUsername = originUser;
        this.destinationUsername = destinationUser;
    }

    public String getOriginUsername() {
        return originUsername;
    }

    public String getDestinationUsername() {
        return destinationUsername;
    }

    public MessageSubType getSubType() {
        return subType;
    }
}
