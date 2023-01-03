package lk.java.session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.ejb.Remote;

/**
 *
 * @author Rajitha Yasasri
 */
@Remote
public interface BankBeanRemote {

    void create(String name, String account, String address, String emp_id);

    void edit(String id, String name, String account, String address, String emp_id);

    void delete(String id);

    String load();

    String searchById(String id);

    String searchByName(String name);
    
}
