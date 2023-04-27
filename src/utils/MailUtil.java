/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Properties;

/**
 *
 * @author sarra
 */
public class MailUtil {
   
    public static Properties getSmtpProperties() {  
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP server address
        properties.put("mail.smtp.port", "587"); // Replace with your SMTP server port
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // Replace with your SMTP server address
        return properties;
    }}








           
    
    
       

