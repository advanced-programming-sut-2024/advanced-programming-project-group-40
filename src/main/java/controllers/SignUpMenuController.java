package controllers;

import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.SignUpMenuMessages;
import javafx.scene.control.Alert;
import models.AlertMaker;
import models.Result;

public class SignUpMenuController extends UserInfoController {
    public static AlertMaker signUp(String username, String password, String confirmPassword, String nickname, String email) {
        if (!isUsernameUnique(username))
            return new AlertMaker(Alert.AlertType.CONFIRMATION, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.DUPLICATE_USER + generateUsername(username));
        return new AlertMaker(Alert.AlertType.CONFIRMATION, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.SIGN_UP_SUCCESSFULLY.toString());
    }


    public static Result pickQuestion(int questionNumber, String answer, String confirmAnswer) {
        return null;
    }


}
