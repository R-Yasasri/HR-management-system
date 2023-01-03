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
public interface EmployeeBeanRemote {

    void create(String name, String birthday, String nic, String email, String mobile, String address);

    void edit(String id, String name, String birthday, String nic, String email, String mobile, String address);

    void delete(String id);

    String load();

    String searchById(String id);

    String searchEmployeeByName(String name);

}
