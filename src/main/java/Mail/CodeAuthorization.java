package Mail;

import controllers.Generator;

public class CodeAuthorization {
    private static String sentCode;

    public static void sendCode(String targetEmail) {
        sentCode = Generator.generateCode();
        EmailUtil.sendEmail(targetEmail, "Your 2FA Code", "Your 2FA code is: " + sentCode);
    }

    public static boolean verifyCode(String userInputCode) {
        return sentCode.equals(userInputCode);
    }
}