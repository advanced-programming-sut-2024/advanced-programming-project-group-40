package controllers.MenuController;

import models.User;

public class ForgetPasswordController  {
    public static boolean isAnswerValid(User user, String answer) {
        return answer.equals(user.getSecurityAnswer());
    }

}
