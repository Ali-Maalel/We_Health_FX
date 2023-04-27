/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Properties;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author nours
 */
public class javaMailUtil {
    public static void sendMail(String recepient,String nom) throws Exception{
        System.out.println("preparing...");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        String myAccountEmail = "sarra.hafsi@esprit.tn";
        String password = "azer2233";
        Session session = Session.getInstance(properties,new Authenticator(){
       
            @Override
    
        protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(myAccountEmail, password);
        }
        });
        Message message = prepareMessage(session , myAccountEmail , recepient ,nom);
   Transport.send(message);
        System.out.println("message send..");
    }
    private static Message prepareMessage(Session session , String myAccountEmail , String recepient,String nom){
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient) );
            message.setSubject("Confirmation Evennement");
            message.setText("Votre evenement ".concat(nom).concat(" a été confirmé avec succès. Un lien meet vous sera communiqué ultérieurement. Merci pour votre confiance"));
            return message;
            
        }catch(Exception ex){
            System.out.println("erreur");
        }
        return null;
    }
    
}
