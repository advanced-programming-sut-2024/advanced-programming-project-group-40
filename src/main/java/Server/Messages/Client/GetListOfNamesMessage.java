package Server.Messages.Client;

import Server.Messages.MessageSubType;

public class GetListOfNamesMessage extends ClientMessages {
    private String username;
    private MessageSubType subType;

    public GetListOfNamesMessage(String username, MessageSubType subType) {
        this.username = username;
        this.subType = subType;
    }
}
