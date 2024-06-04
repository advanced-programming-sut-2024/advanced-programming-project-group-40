package controllers;

import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.SignUpMenuMessages;
import javafx.scene.control.Alert;
import models.AlertMaker;
import models.Result;

public class SignUpMenuController extends UserInfoController {
    public static AlertMaker signUp(String username, String password, String confirmPassword, String nickname, String email) {
        if (!isUsernameValid(username))
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.INVALID_USER.toString());
        if (!isUsernameUnique(username))
            return new AlertMaker(Alert.AlertType.CONFIRMATION, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.DUPLICATE_USER.toString() + generateUsername(username));
        if (!isEmailValid(email))
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.INVALID_EMAIL.toString());
        if (!isPasswordValid(password))
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.INVALID_PASSWORD.toString());
        if (!isPasswordShort(password))
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.WEAK_PASSWORD.toString() + SignUpMenuMessages.SHORT_PASSWORD);
        if (!isPasswordWeak(password))
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.WEAK_PASSWORD.toString() + SignUpMenuMessages.INVALID_PASSWORD);
        if (!isPasswordTheSame(password, confirmPassword))
            return new AlertMaker(Alert.AlertType.ERROR, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.WRONG_PASSWORD_CONFIRMATION.toString());

        return new AlertMaker(Alert.AlertType.CONFIRMATION, AlertHeader.SIGN_UP.toString(), SignUpMenuMessages.SIGN_UP_SUCCESSFULLY.toString());
    }


    public static Result pickQuestion(int questionNumber, String answer, String confirmAnswer) {
        return null;
    }


}
