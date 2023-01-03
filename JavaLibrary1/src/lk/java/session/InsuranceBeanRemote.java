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
public interface InsuranceBeanRemote {

    void create(String provider, String address, String plan, String start, String end, String empId);

    void edit(String id, String provider, String address, String plan, String start, String end, String empId);

    void delete(String id);

    String load();

    String searchByEmployee(String empId);

    String searchByProvider(String provider);

    String searchById(String id);
    
}
