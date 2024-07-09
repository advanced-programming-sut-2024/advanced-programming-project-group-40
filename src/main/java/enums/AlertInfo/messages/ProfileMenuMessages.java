package enums.AlertInfo.messages;

public enum ProfileMenuMessages {
    USER_NOT_FOUND("User not found!"),
    USER_FOUND("User found!"),
    SEE_PROFILE("User founded. Do you want to see profile?"),
    SEND_REQUEST("We will send getUser to this user"),
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
