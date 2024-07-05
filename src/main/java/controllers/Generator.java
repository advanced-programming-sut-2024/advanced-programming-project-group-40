package controllers;

import controllers.MenuController.UserInfoController;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

public class Generator {
    public static SecureRandom random = new SecureRandom();

    public static String getRandomPassword() {
        return generatePassword();
    }

    public static String generateUsername(String username) {
        int number = 0;
        StringBuilder usernameBuilder = new StringBuilder(username);
        while (true) {
            usernameBuilder.append(number);
            if (UserInfoController.isUsernameUnique(usernameBuilder.toString())) {
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


    public static String generateCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // generate a 6-digit number
        return String.valueOf(code);
    }



}



