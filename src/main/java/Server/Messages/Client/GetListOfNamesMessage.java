package Server.Messages.Client;

import Server.Messages.MessageSubType;
import Server.Messages.MessageType;

public class GetListOfNamesMessage extends ClientMessages {
    private String keyName;
    private MessageSubType subType;

    public GetListOfNamesMessage(String keyword, MessageSubType subType) {
        this.type = MessageType.GET_LIST_OF_NAMES;
        this.keyName = keyword;
        this.subType = subType;
    }

    public String getKeyName() {
        return keyName;
    }

    public MessageSubType getSubType() {
        return subType;
    }
}
