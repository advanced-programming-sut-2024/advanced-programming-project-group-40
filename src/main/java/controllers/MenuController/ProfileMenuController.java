package controllers.MenuController;

import Server.ClientHandler;
import Server.Messages.Client.RequestMessage;
import Server.Messages.MessageSubType;
import Server.Messages.ServerMessages;
import controllers.Utilities;
import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.ProfileMenuMessages;
import javafx.scene.control.Alert;
import models.AlertMaker;

public class ProfileMenuController extends UserInfoController {

    public static AlertMaker search(String target) {
        if (Utilities.getUser(target) == null)
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.PROFILE_MENU.toString(), ProfileMenuMessages.USER_NOT_FOUND.toString());
        return new AlertMaker(Alert.AlertType.CONFIRMATION, AlertHeader.PROFILE_MENU.toString(), ProfileMenuMessages.SEE_PROFILE.toString());
    }


    public static void sendRequest(String origin, String target, MessageSubType subType) {
        RequestMessage requestMessage = new RequestMessage(origin, target, subType);
        System.out.println("send request line 23: " + origin + " " + target);
        ServerMessages serverMessages = ClientHandler.client.request(requestMessage);
    }

}
