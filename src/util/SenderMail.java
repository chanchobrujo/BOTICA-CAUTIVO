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
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import Constans.Constan;
import java.util.Date;

/**
 *
 * @author kpalmall
 */
public class SenderMail {
    private static final String sender = "cuentaempresarial526@gmail.com";
    private static final String password = "drcsfkzfhgjxwtjm";

    private static MimeMultipart setContentHtml(String description, String msg) throws MessagingException {
        String text = "Usted a solicitado una nueva contraseña: ".concat(msg);
        BodyPart html = new MimeBodyPart();
        html.setContent("<br><h1>BOTICA CAUTIVO - "+description+"</h1><br><h3>" + text + "</h3><br>", "text/html");

        MimeMultipart mp = new MimeMultipart();
        mp.addBodyPart(html);
        return mp;
    }

    private static String sendMail(String description, String destinatario, String Asunto, String msg) throws Exception {
        try { 
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.host", "smtp.gmail.com");
            properties.setProperty("mail.smtp.starttls.enable", "true");
            properties.setProperty("mail.smtp.port", "587");
            properties.setProperty("mail.smtp.auth", "true");
            
            Session session = Session.getDefaultInstance(properties);
            MimeMessage mail = new MimeMessage(session);
            
            mail.setFrom(new InternetAddress(sender));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mail.setSubject(Asunto);
            mail.setText(msg);

            mail.setContent(setContentHtml(description, msg)); 

            try (Transport transport = session.getTransport("smtp")) {
                transport.connect(sender, password);
                transport.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            }

            return Constan.send_email;
        } catch (MessagingException e) {
            return e.getMessage();
        }
    }
    
    public static String assingPassword1(String email, String password) throws Exception{
        try {
            return sendMail("Se le a asignado una contraseña.", email, Constan.recovery_password, password);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String assingPassword(String email, String password) throws Exception{
        try {
            return sendMail("Recuperación de contraseña.", email, Constan.recovery_password, password);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String sendToken(String email) throws Exception{
        try {
            String p = Commons.generatedID();
            return sendMail("Token para validar cambio de contraseña.", email, Constan.token, p).concat(Constan.pipe).concat(p);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public static String alertNewPassword(String email) throws Exception{
        try {
            return sendMail("ALERTA Cambio de contraseña", email, "ALERTA", "Su contraseña a sido cambiada satisfactoriamente.".concat((new Date()).toString()));
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
