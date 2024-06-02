package enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum PreGameMenuCommands {
    ;
    private final String pattern;
    PreGameMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMather(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
