package enums;

public enum SecurityQuestion {
    FIRST_Q("1. When is your father's birthday?"),
    SECOND_Q("2. What is your father's education?"),
    THIRD_Q("3. What is your father's employment status?"),
    FOURTH_Q("4. What is your father's favorite color?"),
    FIFTH_Q("5. What is your father's favorite food?'");
    private final String message;

    SecurityQuestion(String message) {
        this.message = message;
    }

    public static String getQuestion(int number) {
        switch (number) {
            case 1:
                return FIRST_Q.message;
            case 2:
                return SECOND_Q.message;
            case 3:
                return THIRD_Q.message;
            case 4:
                return FOURTH_Q.message;
            case 5:
                return FIFTH_Q.message;
        }
        return null;
    }
}
