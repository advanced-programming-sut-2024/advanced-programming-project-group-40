package controllers.MenuController;

import Mail.CodeAuthorization;
import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.LoginMenuMessages;
import javafx.scene.control.Alert.AlertType;
import models.AlertMaker;
import models.Game;
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
        return new AlertMaker(AlertType.CONFIRMATION, "", "");
    }

    public static void stayLoggedInSelected() {
        Game.getLoggedInUser().setStayLoggedIn(true);
    }


    public static void setPassword(String password) {

    }

    public static AlertMaker checkCode(String code,String username) {
        if (!CodeAuthorization.verifyCode(code))
            return new AlertMaker(AlertType.ERROR, AlertHeader.SIGN_IN.toString(), LoginMenuMessages.INVALID_VERIFICATION_CODE.toString());
        Game.setLoggedInUser(Game.getUserByName(username));
        return new AlertMaker(AlertType.CONFIRMATION, AlertHeader.SIGN_IN.toString(), LoginMenuMessages.STAY_LOGGED_IN.toString());
    }

}
