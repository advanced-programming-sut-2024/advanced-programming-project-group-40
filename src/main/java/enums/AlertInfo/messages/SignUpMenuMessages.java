package enums.AlertInfo.messages;

public enum SignUpMenuMessages {
    INVALID_USER("Invalid username"),
    DUPLICATE_USER("This username already exists"),
    INVALID_EMAIL("Invalid email"),
    SORT_PASSWORD("Your password is too short"),
    INVALID_PASSWORD("Your password must consist of at least one lowercase and uppercase character and at least one number and special character"),

    ;
    private final String message;

    SignUpMenuMessages(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return message;
    }
}
