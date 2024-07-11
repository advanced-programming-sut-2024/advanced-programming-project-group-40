package Server.Messages.Client;


import Server.Messages.MessageType;
import models.Game;

public class AcceptRejectRequest extends ClientMessages{
    private final String username;
    private final boolean accept;

    public AcceptRejectRequest(String username, boolean accept) {
        this.token = Game.getLoggedInUser().getUsername();
        this.username = username;
        this.accept = accept;
        this.type = MessageType.ACCEPT_REJECT_REQUEST;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccept() {
        return accept;
    }
}
