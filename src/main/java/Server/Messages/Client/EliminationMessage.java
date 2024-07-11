package Server.Messages.Client;

import Server.Messages.MessageSubType;
import Server.Messages.MessageType;

public class EliminationMessage extends ClientMessages {
    private String username;
    private MessageSubType subType;

    public EliminationMessage(String username, MessageSubType subType) {
        type = MessageType.ELIMINATION;
        this.username = username;
        this.subType = subType;
    }

    public String getUsername() {
        return username;
    }

    public MessageSubType getSubType() {
        return subType;
    }
}
