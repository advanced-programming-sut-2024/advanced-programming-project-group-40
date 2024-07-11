package models.UserInputHandler;

import enums.Origin;

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
