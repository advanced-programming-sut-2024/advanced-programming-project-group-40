package views;

import java.util.Scanner;
import java.util.regex.Matcher;

public abstract class PlayMenu {
    protected String input;
    protected Matcher matcher;
    public abstract void check(Scanner scanner);

}
