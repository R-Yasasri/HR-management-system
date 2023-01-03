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
public interface ShiftBeanRemote {

    void create(String empId, String start, String finish, String leave, String payment, String deptId, String projId, String locId);

    void edit(String shiftId, String empId, String start, String finish, String leave, String payment, String deptId, String projId, String locId);

    void delete(String id);

    String load();

    String searchById(String id);

    String searchByLocation(String id);

    String searchByDepartment(String id);

    String searchByEmployee(String id);

    String searchByProject(String id);
    
}
