package enums.messages;

public enum GameMenuMessages {
    ;
    private final String message;

    GameMenuMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
