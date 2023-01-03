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
public interface TimeOffRequestBeanRemote {

    void create(String reason, String begin, String end, String emp);

    void update(String id, String reason, String begin, String end, String emp);

    void delete(String id);

    String searchByEmployee(String emp_id);

    String searchById(String id);

    String load();

}
