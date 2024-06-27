package controllers.MenuController;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.AlertMaker;
import models.Game;

public class ChangeInfoController {

    public static AlertMaker changeCheck(TextField newUsername, TextField newEmail, TextField oldPassword, TextField newPassword) {
        if (newUsername.getText().equals(Game.getLoggedInUser().getUsername()))
            return  new AlertMaker(Alert.AlertType.ERROR,)
    }
}
