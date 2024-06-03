package enums.AlertInfo.messages;

public enum RegisterMenuMessages {
    ;
    private final String message;

    RegisterMenuMessages(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return message;
    }
}
