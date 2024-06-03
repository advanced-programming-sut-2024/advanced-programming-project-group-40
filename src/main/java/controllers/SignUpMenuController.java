package controllers;

import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.SignUpMenuMessages;
import javafx.scene.control.Alert;
import models.AlertMaker;
import models.Result;

public class SignUpMenuController extends UserInfoController {
    public static AlertMaker register(String username, String password, String confirmPassword, String nickname, String email){
        if (!isUsernameValid(username))
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.INVALID_USER.toString());
    }
    public static Result pickQuestion(int questionNumber, String answer, String confirmAnswer){
        return null;
    }


}
