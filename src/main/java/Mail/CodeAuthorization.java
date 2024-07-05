package Mail;

import controllers.Generator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class CodeAuthorization {
    private static String sentCode;
    public static void main(String[] args) {
        // Simulate user login
        sentCode = Generator.generateCode();
        sendEmail("sarina.farzadnasab@gmail.com", "Your 2FA Code", "Your 2FA code is: " + sentCode); // Prompt user to enter the code
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
    private static boolean verifyCode(String userInputCode) {
        return sentCode.equals(userInputCode);
    }
}