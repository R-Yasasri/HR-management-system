/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.session;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;

/**
 *
 * @author Rajitha Yasasri
 */
@Stateless
public class SendMailBean implements SendMailBeanRemote, SendMailBeanLocal {

    @Resource(name = "bcd2mail")
    private Session bcd2mail;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void sendMail(String email, String subject, String body) throws NamingException, MessagingException {
        MimeMessage message = new MimeMessage(bcd2mail);
        message.setSubject(subject);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
        message.setText(body);
        Transport.send(message);
    }
    
    @Override
    public void sendHTMLMail(String email, String subject, String body) throws NamingException, MessagingException {
        MimeMessage message = new MimeMessage(bcd2mail);
        message.setSubject(subject);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
        message.setContent(body, "text/html; charset=utf-8");
        Transport.send(message);
    }
}
