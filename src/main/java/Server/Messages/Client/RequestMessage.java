package Server.Messages.Client;

import Server.Messages.MessageType;

public class RequestMessage extends ClientMessages {
    private String originUsername;
    private String destinationUsername;
    private MessageType subType;

    public RequestMessage(String originUser, String destinationUser, MessageType type) {
        this.type = MessageType.REQUEST;
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

    public MessageType getSubType() {
        return subType;
    }
}
