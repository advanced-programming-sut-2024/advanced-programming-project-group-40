package enums.AlertInfo.messages;

public enum ChangeInfoMenuMessages   {
    WRONG_PASSWORD("Wrong password!"),
    SAME_USERNAME("Your new username is the same as your old username"),
    SAME_EMAIL("Your new email is the same as your old email"),
    SAME_PASSWORD("Your new password is the same as your old password"),
    INVALID_USER("Invalid username format"),
    DUPLICATE_USER("This username has already taken"),
    INVALID_EMAIL("Invalid email format"),
    SHORT_PASSWORD("Weak password!\nIt's too short"),
    INVALID_PASSWORD("Invalid password!"),
    PASSWORD_REQUIREMENTS("Weak password!\nPassword must consist of at\nleast one number,special\ncharacter,lowercase and\nuppercase alphabet."),
    SUCCESSFULLY_CHANGED("Your info changed successfully!")
    ;
    private final String message;

    ChangeInfoMenuMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}
