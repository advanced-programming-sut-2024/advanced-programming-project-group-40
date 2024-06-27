package controllers.MenuController;

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
        if ((user = Game.getUserByName(username)) == null)
            return new AlertMaker(AlertType.ERROR, AlertHeader.SIGN_IN.toString(), LoginMenuMessages.INCORRECT_USERNAME.toString());
        if (!user.getPassword().equals(password))
            return new AlertMaker(AlertType.ERROR, AlertHeader.SIGN_IN.toString(), LoginMenuMessages.INCORRECT_PASSWORD.toString());
        Game.setLoggedInUser(user);
        return new AlertMaker(AlertType.CONFIRMATION, AlertHeader.SIGN_IN.toString(), LoginMenuMessages.STAY_LOGGED_IN.toString());
    }

    public static void stayLoggedInSelected() {
        Game.getLoggedInUser().setStayLoggedIn(true);
    }


    public static void setPassword(String password) {

    }


}
