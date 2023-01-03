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
public interface LeaveBeanRemote {

    void create(String type, String begin, String finish,String shiftId);

    void edit(String id, String type, String begin, String finish,String shiftId);

    String load();

    void delete(String id);

    String searchLeaveById(String id);

    String searchLeaveByType(String type);
    
}
