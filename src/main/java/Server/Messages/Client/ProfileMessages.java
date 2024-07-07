package Server.Messages.Client;

import Server.Messages.MessageType;
import models.User;

public class ProfileMessages extends ClientMessages{

    private User loggedInUser;
    private User targetUser;

    public ProfileMessages(User loggedInUser, User targetUser) {
        this.type = MessageType.PROFILE;
        this.loggedInUser = loggedInUser;
        this.targetUser = targetUser;
    }
}
