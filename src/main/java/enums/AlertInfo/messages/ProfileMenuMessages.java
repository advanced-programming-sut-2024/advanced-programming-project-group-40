package enums.AlertInfo.messages;

public enum ProfileMenuMessages {
    NOT_FOUND("User not found!"),
    SEE_PROFILE("User founded. Do you want to see profile?"),
    SEND_REQUEST("We will send request to this user"),
    ;
    private final String message;

    ProfileMenuMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
