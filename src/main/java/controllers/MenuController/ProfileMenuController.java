package controllers.MenuController;

import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.ProfileMenuMessages;
import javafx.scene.control.Alert;
import models.AlertMaker;

public class ProfileMenuController extends UserInfoController {

    public AlertMaker search(String username) {
        if (!isUsernameUnique(username)) {
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.PROFILE_MENU.toString(), ProfileMenuMessages.NOT_FOUND.toString());
        }
        return new AlertMaker(Alert.AlertType.CONFIRMATION, AlertHeader.PROFILE_MENU.toString(), ProfileMenuMessages.SEE_PROFILE.toString());
    }
}
