package enums.AlertInfo;

public enum AlertHeader {
    SIGN_IN("Sign in"),
    SIGN_UP("Sign up"),

    CHANGE_INFO("")
    ;
    private final String message;

    AlertHeader(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}
