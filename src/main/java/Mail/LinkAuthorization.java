package Mail;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class LinkAuthorization {
    private static String longUrl;
    private static String accessToken;
    private static String sentLink;

    // real one
    public static String generateShortLinkReal(String longUrl, String accessToken) {
        String shortUrl = "";
        try {
            URL url = new URL("https://api-ssl.bitly.com/v4/shorten");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            String input = "{\"long_url\":\"" + longUrl + "\"}";
            OutputStream os = connection.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            Scanner scanner = new Scanner(connection.getInputStream(), "UTF-8");
            if (scanner.hasNext()) {
                String response = scanner.nextLine();
                shortUrl = response.split("\"id\":\"")[1].split("\"")[0]; // Extract short URL
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shortUrl;
    }

    public static String generateShortLink(String longUrl, String accessToken) {
        return "http://mockshortlink.com/verification?user=" + longUrl.split("=")[1];
    }


    public static int getLinkClicks(String bitlink, String accessToken) {
        return 1;
    }

    public static int getLinkClicksReal(String bitlink, String accessToken) {
        int clicks = 0;
        try {
            URL url = new URL("https://api-ssl.bitly.com/v4/bitlinks/" + bitlink + "/clicks/summary");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            Scanner scanner = new Scanner(connection.getInputStream(), "UTF-8");
            if (scanner.hasNext()) {
                String response = scanner.nextLine();
                clicks = Integer.parseInt(response.split("\"total_clicks\":")[1].split(",")[0]); // Extract click count
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clicks;
    }


    public static void sendLink(String targetEmail) {
        longUrl = "http://example.com/verification?user=" + targetEmail;
        accessToken = "your-bitly-access-token"; // Replace with your actual Bitly access token

        sentLink = generateShortLink(longUrl, accessToken);
        EmailUtil.sendEmail(targetEmail, "Your 2FA Verification Link", "Click the following link to verify your login: " + sentLink); // Output link to console (for testing purposes)
        System.out.println("Verification link: " + sentLink);
    }

    public static boolean verifyLink() {
        // check if the link has been clicked
        int clickCount = getLinkClicks(sentLink, accessToken);
        if (clickCount > 0) {
            System.out.println("Sign up successful! Link clicked.");
            return true;
        } else {
            System.out.println("Sign up failed. Link not clicked.");
            return false;
        }
    }

}
