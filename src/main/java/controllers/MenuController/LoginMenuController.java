package controllers.MenuController;

import Server.Client;
import Server.ClientHandler;
import Server.Messages.Client.LoginMessages;
import Server.Messages.ServerMessages;
import Server.Server;
import javafx.scene.control.Alert.AlertType;
import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.LoginMenuMessages;
import models.AlertMaker;
import models.Game;
import models.Result;
import models.User;

public class LoginMenuController extends UserInfoController {

    public static AlertMaker signIn(String username, String password) {
        User user;
        if (username.isEmpty() || password.isEmpty())
            return new AlertMaker(AlertType.ERROR, AlertHeader.SIGN_IN.toString(), LoginMenuMessages.EMPTY_FILED.toString());
        LoginMessages loginMessages = new LoginMessages(username, password);
        ServerMessages serverMessages = ClientHandler.client.login(loginMessages);
        String result = serverMessages.getAdditionalInfo();
        boolean success = serverMessages.wasSuccessfull();
        if (!success && result.equals(LoginMenuMessages.INCORRECT_USERNAME.toString()))
            return new AlertMaker(AlertType.ERROR, AlertHeader.SIGN_IN.toString(), LoginMenuMessages.INCORRECT_USERNAME.toString());
        if (!success && result.equals(LoginMenuMessages.INCORRECT_PASSWORD.toString()))
            return new AlertMaker(AlertType.ERROR, AlertHeader.SIGN_IN.toString(), LoginMenuMessages.INCORRECT_PASSWORD.toString());
        user = Game.getUserByName(username);
        Game.setLoggedInUser(user);
        return new AlertMaker(AlertType.CONFIRMATION, AlertHeader.SIGN_IN.toString(), LoginMenuMessages.STAY_LOGGED_IN.toString());
    }

    public static void stayLoggedInSelected() {
        Game.getLoggedInUser().setStayLoggedIn(true);
    }


    public static void setPassword(String password) {

    }


}
