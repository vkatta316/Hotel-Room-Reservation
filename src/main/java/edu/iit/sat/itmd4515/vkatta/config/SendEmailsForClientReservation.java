/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.config;

import edu.iit.sat.itmd4515.vkatta.web.CustomerBookingController;
import javax.ejb.Stateless;
import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;
import javax.ejb.LocalBean;

/**
 *
 * @author vinaychowdarykatta
 */
@Stateless
@LocalBean
public class SendEmailsForClientReservation {

    static Properties props = System.getProperties();
    
    @Resource(name = "mail/localsmtp")
    Session session;
    
    //static Session l_session = null;

    /**
     * Connect and send smtp.
     *
     * @param serverName the server name / host name
     * @param portNo the port number
     * @param secureConnection the secure connection i.e, STARTTLS or STARTSSL
     * settings keep true or false or never
     * @param userName the user name / User Email id use for login
     * @param password the password of email account
     * @param toEmail the to email / Email address of receiver
     * @param subject the subject of email
     * @param msg the message body of email
     * @return the string which gives response for email is send or not
     */
    public String connectAndSendSmtp(String serverName, String portNo, String secureConnection, String userName, String password, String toEmail, String subject, String msg) {

        System.out.println("Inside connectAndSendSmtp method: ");
        emailSettings(serverName, portNo, secureConnection);
        createSession(userName, password);
        String issend = sendMessage(userName, toEmail, subject, msg);
        return issend;
    }

    public void emailSettings(String host, String port, String secureCon) {
        System.out.println("Inside emailSettings: ");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "false");
        props.put("mail.smtp.port", port);
        if (secureCon.equalsIgnoreCase("tls")) {
            props.put("mail.smtp.starttls.enable", "true");
        } else if (secureCon.equalsIgnoreCase("ssl")) {
            props.put("mail.smtp.startssl.enable", "true");  // make this true if port=465
        } else {
            props.put("mail.smtp.starttls.enable", "false");
            props.put("mail.smtp.startssl.enable", "false");
        }
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

    }

    public void createSession(final String username, final String pass) {

        System.out.println("Inside createSession Method: ");
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, pass);
            }
        });
        session.setDebug(true); // Enable the debug mode
    }

    public String sendMessage(String fromEmail, String toEmail, String subject, String msg) {
        System.out.println("Inside sendMessage Method: ");
        String msgsendresponse = "";
        try {

            MimeMessage message = new MimeMessage(session);
            System.out.println("Sending E-mail From: " + fromEmail);
            //message.setFrom(new InternetAddress(fromEmail));
            message.setFrom(new InternetAddress(fromEmail));

            System.out.println("Sending E-mail To: " + toEmail);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            //message.addRecipient(Message.RecipientType.BCC, new InternetAddress(AppConstants.fromEmail)); 
            message.setSubject(subject);

            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");
            try {
                Transport.send(message);
                msgsendresponse = "Message_Sent";		//Don't change this message String
                System.out.println(msgsendresponse);
            } catch (Exception ex) {
                msgsendresponse = "Email sending failed due to: " + ex.getLocalizedMessage();
                System.out.println(msgsendresponse);		//gives error message for failure

            }

        } catch (MessagingException mex) {
            msgsendresponse = "Error occured in creation of MIME message due to:  " + mex.getLocalizedMessage();
            System.out.println(msgsendresponse);
        } catch (Exception e) {
            e.printStackTrace();
        }//end catch block
        return msgsendresponse;
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        String serverName = "smtp.gmail.com";
        String portNo = "465";
        String secureConnection = "ssl";
        String userName = "itmd542@gmail.com";
        String password = "ajwomlvfoxwsobqk";
        String toEmail = "vinaykatta316@gmail.com";
        String subject = "New Assessment mail";
        String msg = "<h1> This is test mail please ignore... </h1>";

        SendEmailsForClientReservation oe = new SendEmailsForClientReservation();
        oe.connectAndSendSmtp(serverName, portNo, secureConnection, userName, password, toEmail, subject, msg);

    }

}
