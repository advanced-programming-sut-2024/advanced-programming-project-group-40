package controllers;

import models.Result;

public class LoginMenuController extends Controller{

    public static Result login(String username, String password){
        return null;
    }
    public static Result forgetPassword(String username, int questionNumber, String answer, String newPassword){
        return null;
    }
    public static Result goToRegisterMenu(){
        return null;
    }
    public static boolean isAnswerValid(int questionNumber, String answer){
        return true;
    }
    public static void setPassword(String password){

    }


}
