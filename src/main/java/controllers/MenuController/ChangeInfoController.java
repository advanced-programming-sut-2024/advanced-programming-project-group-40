package controllers.MenuController;

import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.ChangeInfoMenuMessages;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.AlertMaker;
import Server.GameServer;
import models.User;

public class ChangeInfoController extends UserInfoController {

    public static AlertMaker changeCheck(TextField newUsername, TextField newEmail, TextField oldPassword, TextField newPassword) {
        if (newUsername.getText().equals(GameServer.getLoggedInUser().getUsername()))
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.CHANGE_INFO.toString(), ChangeInfoMenuMessages.SAME_USERNAME.toString());
        if (newEmail.getText().equals(GameServer.getLoggedInUser().getEmail()))
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.CHANGE_INFO.toString(), ChangeInfoMenuMessages.SAME_EMAIL.toString());
        if (newPassword.getText().equals(GameServer.getLoggedInUser().getPassword()))
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.CHANGE_INFO.toString(), ChangeInfoMenuMessages.SAME_PASSWORD.toString());
        User user = GameServer.getLoggedInUser();
        if (!newUsername.getText().isEmpty())
            user.setUsername(newUsername.getText());
        if (newEmail.getText().isEmpty())
            user.setEmail(newEmail.getText());
        if (newPassword.getText().isEmpty())
            user.setPassword(newPassword.getText());
        return new AlertMaker(Alert.AlertType.INFORMATION, AlertHeader.CHANGE_INFO.toString(), ChangeInfoMenuMessages.SUCCESSFULLY_CHANGED.toString());
    }

    public static boolean correctPassword(String password) {
        return password.equals(GameServer.getLoggedInUser().getPassword());
    }

}
