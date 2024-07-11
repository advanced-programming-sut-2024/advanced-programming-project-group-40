package Server.Messages.Client;

import Server.Messages.MessageSubType;
import Server.Messages.MessageType;

public class EliminationMessage extends ClientMessages {
    private String username;
    private int number;
    private MessageSubType subType;

    public EliminationMessage(String username, MessageSubType subType,int number) {
        type = MessageType.ELIMINATION;
        this.username = username;
        this.subType = subType;
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public MessageSubType getSubType() {
        return subType;
    }

    public int getNumber() {
        return number;
    }
}
