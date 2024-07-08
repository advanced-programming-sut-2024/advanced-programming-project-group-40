package Server.Messages.Client;

import Server.Messages.MessageType;

public class GetUserMessage extends ClientMessages{
    private String username;

    public GetUserMessage(String username) {
        this.type = MessageType.GET_USER;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
