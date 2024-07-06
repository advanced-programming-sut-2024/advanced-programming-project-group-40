package enums.AlertInfo.messages;

public enum LoginMenuMessages {
    LOGGED_IN_SUCCESSFULLY("Logged in successfully"),
    INCORRECT_USERNAME("Username is incorrect"),
    INCORRECT_PASSWORD("Password is incorrect"),
    STAY_LOGGED_IN("Do you want to stay logged in?"),
    EMPTY_FILED("You must fill in all fields!"),
    INCORRECT_SECURITY_ANSWER("Security answer is incorrect!");
    private final String message;

    LoginMenuMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
