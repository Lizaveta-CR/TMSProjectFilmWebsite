package com.model;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class PlainTextEmailSender {

    public void sendPlainTextEmail(String host, String port,
                                   final String userName, final String password, String toAddress,
                                   String subject, String message) throws AddressException,
            MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };

        Session session = Session.getInstance(properties, auth);

        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setText(message);

        Transport.send(msg);

    }
//    public static void main(String[] args) {
//        String host = "smtp.gmail.com";
//        String port = "587";
//        String mailFrom = "elizaveta.tsvirko@gmail.com";
//        String password = "690337932";
//
//        String mailTo = "galina_giala@mail.ru";
//        String subject = "Hello my friend";
//        String message = "Hi guy, Hope you are doing well. Liza.";
//
//        PlainTextEmailSender mailer = new PlainTextEmailSender();
//
//        try {
//            mailer.sendPlainTextEmail(host, port, mailFrom, password, mailTo,
//                    subject, message);
//            System.out.println("Email sent.");
//        } catch (Exception ex) {
//            System.out.println("Failed to sent email.");
//            ex.printStackTrace();
//        }
//    }
}