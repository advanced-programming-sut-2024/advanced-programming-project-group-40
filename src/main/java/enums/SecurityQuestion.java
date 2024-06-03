package enums;

public enum SecurityQuestion {
    ;
    private final String message;

    SecurityQuestion(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return message;
    }
}
