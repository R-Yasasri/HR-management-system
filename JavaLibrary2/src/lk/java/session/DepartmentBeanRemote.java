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
public interface DepartmentBeanRemote {

    void create(String name);

    void edit(String id, String name);

    void delete(String id);

    String searchById(String id);

    String load();

    String searchByName(String name);
    
}
