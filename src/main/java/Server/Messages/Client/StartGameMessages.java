package Server.Messages.Client;

import Server.Messages.MessageType;

import java.awt.*;

public class StartGameMessages extends ClientMessages{
    private final String destinationUsername;

    public StartGameMessages(String destinationUsername) {
        this.destinationUsername = destinationUsername;
        this.type = MessageType.SEND_GAME_REQUEST;
    }

    public String getDestinationUsername() {
        return destinationUsername;
    }
}
