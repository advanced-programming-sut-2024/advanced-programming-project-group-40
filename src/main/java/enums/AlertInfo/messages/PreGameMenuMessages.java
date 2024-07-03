package enums.AlertInfo.messages;

public enum PreGameMenuMessages {
    NOT_ENOUGH_CARDS("Your competitor doesn't have enough unit cards"),
    TOO_MUCH_SPECIAL_CARDS("Your competitor has too many special cards"),
    INVALID_COMPETITOR_USERNAME("Invalid competitor username"),
    YOU_CAN_NOT_PLAY_WITH_YOURSELF("You can't play with yourself"),

    GAME_STARTED("The game has started!"),

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
