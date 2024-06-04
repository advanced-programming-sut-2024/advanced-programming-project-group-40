package enums.AlertInfo.messages;

public enum SignUpMenuMessages {
    INVALID_USER("Invalid username format"),
    DUPLICATE_USER("This username already exists. We can recommend you "),
    INVALID_EMAIL("Invalid email format"),
    WEAK_PASSWORD("Weak password!"),
    SHORT_PASSWORD("It is too short"),
    INVALID_PASSWORD("Invalid password!"),
    PASSWORD_REQUIREMENTS("password must consist of at least one number,special character\n                       ,lowercase and uppercase character."),
    WRONG_CONFIRMATION("Confirmation filed doesn't match'"),
    CONTINUE("Are you sure about your information?"),
    RANDOM_PASSWORD("Your password is : "),
    INVALID_QUESTION_NUMBER("Invalid question number"),
    SIGNED_UP_SUCCESSFULLY("Welcome to Gwent card game!")
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
