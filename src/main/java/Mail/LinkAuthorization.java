package Mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

public class LinkAuthorization {

    // real one
    // todo do not delete this
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
    public static void sendEmail(String toEmail, String subject, String body) {
        final String fromEmail = "gwent9922@gmail.com"; // requires valid email id final
        String password = "waog huvg ulou htmh"; // correct password for your email id
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
        props.put("mail.smtp.port", "587"); // TLS Port
        props.put("mail.smtp.auth", "true"); // enable authentication
        props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            msg.setSubject(subject);
            msg.setText(body);
            Transport.send(msg);
            System.out.println("Email Sent Successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static int getLinkClicks(String bitlink, String accessToken) {
        return 1;
    }

    // todo do not delete this
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


    private static String sentLink;

    public static void main(String[] args) {
        String userEmail = "sarina.farzadnasab@gmail.com";
        String longUrl = "http://example.com/verification?user=" + userEmail;
        String accessToken = "your-bitly-access-token"; // Replace with your actual Bitly access token
        // Generate the short link
        sentLink = generateShortLink(longUrl, accessToken);
        sendEmail(userEmail, "Your 2FA Verification Link", "Click the following link to verify your login: " + sentLink); // Output link to console (for testing purposes)
        System.out.println("Verification link: " + sentLink); // Wait and check for link clicks
        try {
            Thread.sleep(60000); // Wait for 1 minute
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // Check if the link has been clicked
        int clickCount = getLinkClicks(sentLink, accessToken);
        if (clickCount > 0) {
            System.out.println("Login successful! Link clicked.");
        } else {
            System.out.println("Login failed. Link not clicked.");
        }
    }
}
