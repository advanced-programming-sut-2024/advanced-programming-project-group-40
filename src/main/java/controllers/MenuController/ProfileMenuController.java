package controllers.MenuController;

import Server.Messages.Client.RequestMessage;
import Server.Messages.MessageType;
import controllers.Utility;
import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.ProfileMenuMessages;
import javafx.scene.control.Alert;
import models.AlertMaker;
import models.Game;

public class ProfileMenuController extends UserInfoController {

    public static AlertMaker search(String target) {
        if (Utility.getUser(target) == null)
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.PROFILE_MENU.toString(), ProfileMenuMessages.USER_NOT_FOUND.toString());
        return new AlertMaker(Alert.AlertType.CONFIRMATION, AlertHeader.PROFILE_MENU.toString(), ProfileMenuMessages.SEE_PROFILE.toString());
    }


    public static void sendRequest(String target) {
        RequestMessage requestMessage = new RequestMessage(Game.getLoggedInUser().getUsername(), target, MessageType.SEND_FOLLOW_REQUEST);
    }

}
