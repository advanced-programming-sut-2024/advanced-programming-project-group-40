package enums.AlertInfo.messages;

public enum SignUpMenuMessages {
    INVALID_USER("Invalid username format"),
    DUPLICATE_USER("This username already exists."),
    INVALID_EMAIL("Invalid email format"),
    SHORT_PASSWORD("Weak password!\nIt's too short"),
    INVALID_PASSWORD("Invalid password!"),
    PASSWORD_REQUIREMENTS("Weak password!\nPassword must consist of at\nleast one number,special\ncharacter,lowercase and\nuppercase alphabet."),
    WRONG_PASS_CONFIRMATION("Incompatible passwords!"),
    CONTINUE("Are you sure about your information?"),
    RANDOM_PASSWORD("Your password is : "),
    INVALID_QUESTION_NUMBER("Invalid question number"),
    WRONG_ANSWER_CONFIRMATION("Incompatible answer!"),
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
