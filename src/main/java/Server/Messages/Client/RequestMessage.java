package Server.Messages.Client;

import Server.Messages.MessageType;

public class RequestMessage extends ClientMessages {
    private String originUser;
    private String destinationUser;

    public RequestMessage(String originUser, String destinationUser, MessageType type) {
        this.type = type;
        this.originUser = originUser;
        this.destinationUser = destinationUser;
    }

    public String getOriginUser() {
        return originUser;
    }

    public String getDestinationUser() {
        return destinationUser;
    }
}
