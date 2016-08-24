import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

class GmailReportSender {

    private Message message;
    private BodyPart messageBodyPart;
    private static final String SENDER_EMAIL = "eto.shef@gmail.com";
    private static final String SENDER_PASSWORD = "tekstolit1983";

    GmailReportSender()
    {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });

        message = new MimeMessage(session);
    }

    void setReceiver(String receiver)
    {
        try {
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    void setTopicAndBody(String topic, String messageBody)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        try {
            message.setSubject(String.format("%s (%s)", topic, dateFormat.format(new Date())));
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(messageBody, "text/html; charset=utf-8");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    void addAttachment(String attachment)
    {
        Multipart multipart = new MimeMultipart();

        try {
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachment);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(attachment);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    void send()
    {
        try {
            Transport.send(message);
            System.out.println("Automation report has been sent successfully...");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}