package Server.Messages.Client;

import Server.Messages.MessageType;

public class ClientMessages {
    protected String token;
    protected MessageType type;

    public String getToken() {
        return token;
    }

    public MessageType getType() {
        return type;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
