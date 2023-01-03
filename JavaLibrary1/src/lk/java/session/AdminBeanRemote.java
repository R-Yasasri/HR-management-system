/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.session;

import javax.ejb.Remote;

/**
 *
 * @author Rajitha Yasasri
 */
@Remote
public interface AdminBeanRemote {

    String searchAdminByUserName(String userName);

    boolean update(String oldPassword,String newPassword);
    
}
