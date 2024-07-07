package controllers.MenuController;

import controllers.Utility;
import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.ProfileMenuMessages;
import javafx.scene.control.Alert;
import models.AlertMaker;

public class ProfileMenuController extends UserInfoController {

    public static AlertMaker search(String target) {
        if (Utility.getUser(target) == null)
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.PROFILE_MENU.toString(), ProfileMenuMessages.USER_NOT_FOUND.toString());
        return new AlertMaker(Alert.AlertType.CONFIRMATION, AlertHeader.PROFILE_MENU.toString(), ProfileMenuMessages.SEE_PROFILE.toString());
    }


    public static void sendRequest(String origin, String target) {

    }

}
