package enums.AlertInfo.messages;

public enum PreGameMenuMessages {
    ;
    private final String message;

    PreGameMenuMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
