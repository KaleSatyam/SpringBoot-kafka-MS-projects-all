package com.email.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class emailservice {

    public boolean sendemail(String subject, String message, String to) {

            String from="satyamkalesk12345@gmail.com";
        boolean f=false;
        // taking host

        String host = "smtp.gmail.com";

        // get the system property
        Properties properties = System.getProperties();
        System.out.println(properties);

        // setting important imformation to properties object

        // host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // step 1 to get session object
        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
            //    password pldajcpomqkaruop
                return new PasswordAuthentication("satyamkalesk54321@gmail.com", "pldajcpomqkaruop");
            }

        });
        // enabling debugging faciliity t'o get information on console
        session.setDebug(true);

        // compose the message
        MimeMessage m = new MimeMessage(session);
        try {

            // from email
            m.setFrom(from);

            // to send email
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // adding subject to message
            m.setSubject(subject);

            // adding text to message
            m.setText(message);

            // send
            // step 3: send email
            Transport.send(m);

            System.out.println("sended successfully----------------------enjoy");
            f=true;
        } catch (MessagingException e) {

            e.printStackTrace();
        }
        return f;

    }
}
