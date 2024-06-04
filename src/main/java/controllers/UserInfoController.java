package controllers;

import models.Game;

import java.security.SecureRandom;

public abstract class UserInfoController extends Controller {

    private static SecureRandom random = new SecureRandom();

    protected static boolean isUserNameUnique(String username) {
        return Game.getUserByName(username) == null;
    }

    public static boolean isUsernameValid(String username) {
        return username.matches("[a-zA-Z0-9\\-]+");
    }

    public static boolean isEmailValid(String email) {
        return email.matches("\\S+@(gmail|yahoo|email).com");
    }

    public static boolean isPasswordValid(String password) {
        return password.matches("[0-9a-zA-Z!@#$%^&*\\-]+");
    }

    public static boolean isPasswordShort(String password) {
   return password.length() > 7;
    }

    public static boolean isPasswordWeak(String password) {
        return password.matches("^(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[0-9]+)(?=.*[!@#$%^&*\\-]+)[0-9a-zA-Z!@#$%^&*\\-]+$");
    }

    public static boolean isPasswordTheSame(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public static String getRandomPassword() {
        return generatePassword();
    }

    public static String generateUsername(String username) {
        int number = 0;
        StringBuilder usernameBuilder = new StringBuilder(username);
        while (true) {
            usernameBuilder.append(number);
            if (isUserNameUnique(usernameBuilder.toString())) {
                return usernameBuilder.toString();
            }
        }
    }

    private static String generatePassword() {
        String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
        String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String DIGITS = "0123456789";
        String SPECIAL_CHARS = "!@#$%^&*-";
        String ALL_CHARS = LOWERCASE + UPPERCASE + DIGITS + SPECIAL_CHARS;
        int PASSWORD_LENGTH = 8;


        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        // Ensure the password contains at least one of each required character type
        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        // Fill the remaining characters randomly from the allowed characters
        for (int i = 4; i < PASSWORD_LENGTH; i++) {
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }

        // Shuffle the characters to ensure randomness
        for (int i = 0; i < password.length(); i++) {
            int randomIndex = random.nextInt(password.length());
            char temp = password.charAt(i);
            password.setCharAt(i, password.charAt(randomIndex));
            password.setCharAt(randomIndex, temp);
        }

        return password.toString();
    }
}
