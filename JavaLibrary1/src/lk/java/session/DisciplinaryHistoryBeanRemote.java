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
public interface DisciplinaryHistoryBeanRemote {

    void create(String description, String action_taken, String empId);

    void edit(String id, String description, String action_taken, String empId);
    
    String load();

    void delete(String id);

    String searchById(String id);

    String searchByEmployee(String empId);

    String searchByActionTaken(String action_taken);
    
}
