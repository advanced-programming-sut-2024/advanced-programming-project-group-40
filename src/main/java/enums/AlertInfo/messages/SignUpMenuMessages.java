package enums.AlertInfo.messages;

public enum SignUpMenuMessages {
    INVALID_USER("Invalid username"),
    DUPLICATE_USER("This username already exists. We can recommend a different one. Do you want that?\n"),
    INVALID_EMAIL("Invalid email"),
    WEAK_PASSWORD("Your password is weak!\n"),
    SHORT_PASSWORD("It is too short"),
    INVALID_PASSWORD("password must consist of at least one lowercase and uppercase character.\nAlso at least one number and special character"),
    WRONG_PASSWORD_CONFIRMATION("The password confirmation is wrong"),
    SIGN_UP_SUCCESSFULLY("Are you sure about your information?");
    private final String message;

    SignUpMenuMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
