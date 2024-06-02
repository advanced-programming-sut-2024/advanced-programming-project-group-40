package enums;

import views.ExitMenu;
import views.LoginMenu;
import views.PlayMenu;

import java.util.Scanner;

public enum Menu {
    LoginMenu(new LoginMenu()),
    Exit(new ExitMenu());

    private final PlayMenu menu;
    Menu(PlayMenu menu){
        this.menu = menu;
    }
    public void checkCommand(Scanner scanner){
        this.menu.check(scanner);
    }
}
