/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author kpalmall
 */
public class SenderMail {
    private static final String sender = "cuentaempresarial526@gmail.com";
    private static final String password = "drcsfkzfhgjxwtjm";

    private static Properties setPropertiesSenderMail() {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");
        return properties;
    }

    private static Session sessionGetDefaultInstance(Properties properties) {
        return Session.getDefaultInstance(properties);
    }

    private static MimeMultipart setContentHtml(String msg) throws MessagingException {
        String text = "Usted a solicitado una nueva contraseña: ".concat(msg);
        BodyPart html = new MimeBodyPart();
        html.setContent("<br><h1>BOTICA CAUTIVO - Recuperación de contraseña</h1><br><h3>" + text + "</h3><br>", "text/html");

        MimeMultipart mp = new MimeMultipart();
        mp.addBodyPart(html);
        return mp;
    }

    private static MimeMessage paramsForMail(Session session, String destinatario, String Asunto, String msg) throws AddressException, MessagingException {
        MimeMessage mail = new MimeMessage(session);
        mail.setFrom(new InternetAddress(sender));
        mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        mail.setSubject(Asunto);
        mail.setText(msg);

        mail.setContent(setContentHtml(msg));
        return mail;
    }

    public static String sendMail(String destinatario, String Asunto, String msg) throws Exception {
        try {
            Session session = sessionGetDefaultInstance(setPropertiesSenderMail());
            MimeMessage mail = paramsForMail(session, destinatario, Asunto, msg);

            try (Transport transport = session.getTransport("smtp")) {
                transport.connect(sender, password);
                transport.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            }

            return Constans.Constan.send_email;
        } catch (MessagingException e) {
            return e.getMessage();
        }
    }
}
