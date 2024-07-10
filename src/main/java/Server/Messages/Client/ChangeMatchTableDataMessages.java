package Server.Messages.Client;

import Server.Client;
import Server.Messages.MessageType;
import models.Game;

public class ChangeMatchTableDataMessages extends ClientMessages {
    private final String message;

    public ChangeMatchTableDataMessages(String message) {
        this.token = Game.getLoggedInUser().getUsername();
        this.type = MessageType.CHANGE_MATCH_TABLE_DATA;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
