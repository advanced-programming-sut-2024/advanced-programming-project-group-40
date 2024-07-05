package Server.Messages.Client;

import Server.Messages.MessageType;
import models.User;

public class SignUpMessages extends ClientMessages{

    private User user;
    public SignUpMessages (User user) {
        this.type = MessageType.SIGNUP;
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
