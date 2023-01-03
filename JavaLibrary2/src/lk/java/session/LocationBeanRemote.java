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
public interface LocationBeanRemote {

    void create(String city, String street, String number, String lat, String lon);

    void edit(String id, String city, String street, String number, String lat, String lon);

    String load();

    void delete(String id);

    String searchById(String id);

    String searchByCity(String city);
    
}
