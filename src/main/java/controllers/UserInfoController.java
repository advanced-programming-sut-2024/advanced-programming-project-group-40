package controllers;

public abstract class UserInfoController extends Controller{
    public static boolean isUserNameUnique(String username) {
        return true;
    }

    public static boolean isUsernameValid(String username) {
        return true;
    }
    public static boolean isEmailValid(String email){
        return true;
    }
    public static boolean isPasswordValid(String password){
        return true;
    }
    public static boolean isPasswordWeak(String password){
        return true;
    }
    public static boolean isPasswordTheSame(String password, String confirmPassword){
        return true;
    }
    public static String getRandomPassword(){
        return null;
    }
}
