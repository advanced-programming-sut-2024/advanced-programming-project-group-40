package models.UserInputHandler;

import controllers.MenuController.GameMenuController;
import enums.Origin;

import java.net.CookieManager;

public class RowClickCommand extends Command {
    private final Origin origin;

    public RowClickCommand(Origin origin) {
        this.origin = origin;
    }

    @Override
    public void excute() {
//        GameMenuController.ClickedOnRow(origin);

    }
}
