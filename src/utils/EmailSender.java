/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import static java.lang.ProcessBuilder.Redirect.to;
import java.net.Authenticator;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author sarra
 */
public class EmailSender {
    public static void SendEmail(ArrayList<String> to,String subject,String body){
        Properties properties = MailUtil.getSmtpProperties();
        final String username = "sarahhafsi123@gmail.com"; 
        final String password = "Azer2233**";
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
 try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);
            
            // Set the From, To, Subject, and Body of the email
            message.setFrom(new InternetAddress(username));
            Address[] toAddresses = new Address[to.size()];
            for (int i = 0; i < to.size(); i++) {
                toAddresses[i] = new InternetAddress(to.get(i));
            }
            message.setRecipients(Message.RecipientType.TO, toAddresses);

          
            message.setSubject("Annulation de l'événement");
            message.setText("Cher participant,\n\n" + "Nous vous informons que l'événement XYZ prévu le 25 avril 2023 est annulé."
                    + "Veuillez nous excuser pour tout inconvénient causé.\n\n"
                    + "Cordialement,\n"
                    + "L'équipe de gestion des événements WEHEALTH");
            
            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
