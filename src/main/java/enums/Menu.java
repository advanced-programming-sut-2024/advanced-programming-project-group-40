package enums;

import views.LoginMenu;
import views.PlayMenu;

public enum Menu {
    LoginMenu(new LoginMenu()),
;
    private final PlayMenu menu;
    Menu(PlayMenu menu){
        this.menu = menu;
    }
}
