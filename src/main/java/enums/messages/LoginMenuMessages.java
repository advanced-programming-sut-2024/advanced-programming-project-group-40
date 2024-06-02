package enums.messages;

public enum LoginMenuMessages {
    ;
    private final String message;

    LoginMenuMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
