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
public interface ProjectBeanRemote {

    void create(String name, String description);

    void edit(String id, String name, String description);

    String load();

    void delete(String id);

    String SearchById(String id);

    String searchByName(String name);
    
}
