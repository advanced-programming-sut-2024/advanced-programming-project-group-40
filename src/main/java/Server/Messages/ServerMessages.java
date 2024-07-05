package Server.Messages;

public class ServerMessages {
    private boolean success;
    private String additionalInfo;

    public ServerMessages (boolean success, String info) {
        this.success = success;
        this.additionalInfo = info;
    }

    public boolean wasSuccessfull () {
        return this.success;
    }

    public String getAdditionalInfo () {
        return this.additionalInfo;
    }
}
