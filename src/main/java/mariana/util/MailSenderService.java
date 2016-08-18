package mariana.util;

import mariana.entity.Employee;
import mariana.entity.User;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by mariana on 15.08.2016.
 */
@Component
@PropertySource("classpath:mail.properties")
public class MailSenderService {

        public void sendNewAccountEmail(Employee employee, String password) {
            Session session = configEmailAndGetSession();
            try {
                Transport transport = session.getTransport();

                MimeMessage message = new MimeMessage(session);
                message.setSubject("Cont nou");
                message.setFrom(new InternetAddress("mariana.patriche23@gmail.com"));
                String to = employee.getEmail();
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                String body = "Buna " + employee.getFirstName() + ", Bine ai venit in echipa noastra! " +
                "Contul tau in aplicatia de Project management a fost activat cu urmatoarele credentiale: username: " +
                        employee.getUser().getUsername() + "si parola: " + password + "." +
                "Te rugam sa iti schimbi parola inainte de prima utilizare a contului. Aplicatia poate fi accesata la " +
                        "urmatorul link.  O zi frumoasa!";
                message.setContent(body, "text/html");
                transport.connect();

                transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
                transport.close();

            } catch (Exception e) {
            }
        }

    private Session configEmailAndGetSession(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", 25);
        props.put("mail.smtp.socketFactory.port", 25);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.transport.protocol", "smtp");
        Session session = null;

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("mariana.patriche23", "Clyubavil");
                    }
                });
        return session;
    }
}
