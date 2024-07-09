package Server.Messages.Client;

import Server.Messages.MessageType;

public class LoginMessages extends ClientMessages{
    private String username;
    private String password;
    public LoginMessages (String username, String password) {
        this.type = MessageType.LOGIN;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
