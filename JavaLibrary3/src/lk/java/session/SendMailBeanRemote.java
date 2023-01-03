/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.session;

import javax.ejb.Remote;
import javax.mail.MessagingException;
import javax.naming.NamingException;

/**
 *
 * @author Rajitha Yasasri
 */
@Remote
public interface SendMailBeanRemote {

    void sendMail(String email, String subject, String body) throws NamingException, MessagingException;

    void sendHTMLMail(String email, String subject, String body) throws NamingException, MessagingException;
}
