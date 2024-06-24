package controllers.MenuController;

import models.Result;

public class ProfileMenuController extends UserInfoController {
    public static Result changeUsername(String username) {
        return null;
    }

    public static Result changeNickname(String nickname) {
        return null;
    }

    public static Result changeEmail(String email) {
        return null;
    }

    public static Result changePassword(String newPassword, String oldPassword) {
        return null;
    }
    public static Result showUserInfo(){
        return null;
    }
    public static Result GameHistory(){
        return null;
    }
    public static boolean isUsernameTheSame(String newUsername){
        return true;
    }
    public static boolean isNicknameTheSame(String newNickname){
        return true;
    }
    public static boolean isEmailTheSame(String newEmail){
        return true;
    }
    public static boolean isPasswordTheSame(String newPassword){
        return true;
    }

}
