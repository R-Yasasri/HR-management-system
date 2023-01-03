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
public interface SalaryBeanRemote {

    void create(String month, String salary, String status, String emp_id);

    void edit(String idSalary, String month, String salary, String status, String emp_id);

    void delete(String idSalary);

    String searchById(String idSalary);

    String load();

    String searchByMonth(String month);

    String searchSalaryByEmployee(String emp_id);
    
}
