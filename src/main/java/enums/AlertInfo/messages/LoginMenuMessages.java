package enums.AlertInfo.messages;

public enum LoginMenuMessages {
    INCORRECT_USERNAME("Username is incorrect"),
    INCORRECT_PASSWORD("Password is incorrect"),
    STAY_LOGGED_IN("Do you want to stay logged in?"),
    EMPTY_FILED("You must fill in all fields!"),

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