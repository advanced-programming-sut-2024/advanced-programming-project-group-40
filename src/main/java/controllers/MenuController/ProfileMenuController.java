package controllers.MenuController;

import Server.ClientHandler;
import Server.Messages.ServerMessages;
import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.ProfileMenuMessages;
import javafx.scene.control.Alert;
import models.AlertMaker;
import models.Game;
import models.User;

public class ProfileMenuController extends UserInfoController {
    public static

    public static AlertMaker search(String username) {
        if (isUsernameUnique(username))
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.PROFILE_MENU.toString(), ProfileMenuMessages.NOT_FOUND.toString());
        return new AlertMaker(Alert.AlertType.CONFIRMATION, AlertHeader.PROFILE_MENU.toString(), ProfileMenuMessages.SEE_PROFILE.toString());
    }

    public static void sendRequest(User loggedInuser,User target){
        ProfileMessages profileMessages = new ProfileMessages(loggedInuser, target);
        ServerMessages serverMessages = ClientHandler.client.profile(profileMessages);
        String result = serverMessages.getAdditionalInfo();
        target.getRequests().add(Game.getLoggedInUser());
    }



}
