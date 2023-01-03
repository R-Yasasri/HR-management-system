/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.session;

import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author Rajitha Yasasri
 */
@Remote
public interface AttendanceBeanRemote {

    void create(String clockIn, String clockOut, String shift);

    void edit(String id, String clockIn, String clockOut, String shift);

    String load();

    void delete(String id);

    String searchById(String id);

    String searchByShift(String shift);
    
}
