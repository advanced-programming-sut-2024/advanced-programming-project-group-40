package enums.AlertInfo.messages;

public enum PreGameMenuMessages {
    NOT_ENOUGH_CARDS("Your competitor doesn't have enough unit cards"),
    TOO_MUCH_SPECIAL_CARDS("Your competitor has too many special cards"),
    INVALID_COMPETITOR_USERNAME("Invalid competitor username"),
    YOU_CAN_NOT_PLAY_WITH_YOURSELF("You can't play with yourself"),
    GAME_STARTED("The game has started!"),
    DOWNLOAD_DECK("Deck Downloaded Successfully!"),
    UPLOAD_DECK("Deck Uploaded Successfully!"),
    YOU_DO_NOT_HAVE_ENOUGH_CARDS("You don't have enough unit cards'"),
    GAME_REQUEST_REJECTED("Game request rejected"),
    PUBLIC_GAME("DO you want a public game?"),
    USER_NOT_ONLINE("User is not online"),
    USER_IN_GAME("User is in game"),;
    private final String message;

    PreGameMenuMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
