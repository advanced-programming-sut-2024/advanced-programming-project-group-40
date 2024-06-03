package enums.AlertInfo.messages;

public enum ProfileMenuMessages {
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
