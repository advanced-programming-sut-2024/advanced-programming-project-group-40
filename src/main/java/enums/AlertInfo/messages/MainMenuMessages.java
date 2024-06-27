package enums.AlertInfo.messages;

public enum MainMenuMessages {
    ;
    private final String message;

    MainMenuMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
