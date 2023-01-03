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
public interface JobBeanRemote {

    void create(String title, String start, String end, String empId);

    void edit(String id, String title, String start, String end, String empId);

    void delete(String id);

    String searchById(String id);

    String load();

    String searchByTitle(String title);

    String searchByEmployee(String empId);
    
}
