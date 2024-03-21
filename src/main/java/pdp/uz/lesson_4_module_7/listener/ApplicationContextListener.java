package pdp.uz.lesson_4_module_7.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import pdp.uz.lesson_4_module_7.datasource.DatabaseManager;
import pdp.uz.lesson_4_module_7.model.Book;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static pdp.uz.lesson_4_module_7.list.BookList.BOOKS_LIST;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("STARTED");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try (Connection connect = DatabaseManager.connect()) {
            ResultSet resultSet = connect.prepareStatement("select * from book").executeQuery();

            while (resultSet.next()) {
                Properties properties = new Properties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.port", "587");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");

                String username = "kamoliddinabduxalikov02@gmail.com";
                String password = "cilx isbf mhev scrl";

                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(resultSet.getString("email")));
                message.setSubject("Mail Subject");
                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent("The library application has stopped!", "text/html; charset=utf-8");
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);
                message.setContent(multipart);
                Transport.send(message);
            }
        } catch (SQLException | MessagingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("STOPPED");
    }
}
