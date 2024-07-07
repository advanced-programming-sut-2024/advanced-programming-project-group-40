package Server.Messages.Client;

public class RequestMessage extends ClientMessages{
    private String originUser;
    private String destinationUser;

    public RequestMessage(String originUser, String destinationUser) {
        this.originUser = originUser;
        this.destinationUser = destinationUser;
    }

    public String getOriginUser() {
        return originUser;
    }

    public String getDestinationUser() {
        return destinationUser;
    }
}
