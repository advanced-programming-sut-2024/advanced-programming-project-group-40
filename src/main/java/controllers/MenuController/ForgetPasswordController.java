package controllers.MenuController;

import models.User;

public class ForgetPasswordController extends UserInfoController{
    public static boolean isAnswerValid(User user, String answer) {
        return answer.equals(user.getSecurityAnswer());
    }

}
