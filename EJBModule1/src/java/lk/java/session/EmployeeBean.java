/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.session;

import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import lk.java.controller.EmployeeJpaController;
import lk.java.entity.Employee;

/**
 *
 * @author Rajitha Yasasri
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class EmployeeBean implements EmployeeBeanRemote, EmployeeBeanLocal {

    @Resource
    UserTransaction utx;

    @Override
    public void create(String name, String birthday, String nic, String email, String mobile, String address) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        Employee emp = new Employee();
        emp.setName(name);
        emp.setNic(nic);
        emp.setEmail(email);
        emp.setMobile(mobile);
        emp.setAddress(address);

        EmployeeJpaController ejc = new EmployeeJpaController(utx, emf);
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = sdf.parse(birthday);

            emp.setBirthday(parse);

            ejc.create(emp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void edit(String id, String name, String birthday, String nic, String email, String mobile, String address) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

//        System.out.println(name);
//        System.out.println(birthday);
//        System.out.println(nic);
//        System.out.println(email);
//        System.out.println(mobile);
//        System.out.println(address);
        Integer i = Integer.parseInt(id);

        EmployeeJpaController ejc = new EmployeeJpaController(utx, emf);

        Employee emp = ejc.findEmployee(i);
        emp.setName(name);
        emp.setNic(nic);
        emp.setEmail(email);
        emp.setMobile(mobile);
        emp.setAddress(address);
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = sdf.parse(birthday);

            emp.setBirthday(parse);

            ejc.edit(emp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        EmployeeJpaController ejc = new EmployeeJpaController(utx, emf);

        Integer i = Integer.parseInt(id);
        try {

            ejc.destroy(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String load() {

        String msg = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        EmployeeJpaController ejc = new EmployeeJpaController(utx, emf);

        Gson gson = new Gson();

        try {

            List<Employee> list = ejc.findEmployeeEntities();
            
            List<Employee> newList=new ArrayList<Employee>();
            for (Employee employee : list) {
                employee.setBankList(null);
                employee.setDisciplinaryHistoryList(null);
                employee.setInsuranceList(null);
                employee.setJobList(null);
                employee.setPerformanceFeedbackList(null);
                employee.setSalaryList(null);
                employee.setShiftList(null);
                employee.setTaxList(null);
                employee.setTimeOffRequestList(null);
                
                newList.add(employee);
            }
            
            msg = gson.toJson(newList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public String searchById(String id) {

        String msg = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        EmployeeJpaController ejc = new EmployeeJpaController(utx, emf);

        Gson gson = new Gson();
        try {
            Integer i = Integer.parseInt(id);
            Employee employee = ejc.findEmployee(i);

                employee.setBankList(null);
                employee.setDisciplinaryHistoryList(null);
                employee.setInsuranceList(null);
                employee.setJobList(null);
                employee.setPerformanceFeedbackList(null);
                employee.setSalaryList(null);
                employee.setShiftList(null);
                employee.setTaxList(null);
                employee.setTimeOffRequestList(null);
            
            msg = gson.toJson(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;

    }

    @Override
    public Employee searchByIdLocal(String id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        EmployeeJpaController ejc = new EmployeeJpaController(utx, emf);

        Employee emp = null;
        try {
            Integer i = Integer.parseInt(id);
            emp = ejc.findEmployee(i);

            System.out.println(emp.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emp;

    }

    @Override
    public String searchEmployeeByName(String name) {

        String msg = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        EmployeeJpaController ejc = new EmployeeJpaController(utx, emf);

        Gson gson = new Gson();
        try {
            List<Employee> list = ejc.findEmployeeByName(name);

            List<Employee> newList=new ArrayList<Employee>();
            for (Employee employee : list) {
                employee.setBankList(null);
                employee.setDisciplinaryHistoryList(null);
                employee.setInsuranceList(null);
                employee.setJobList(null);
                employee.setPerformanceFeedbackList(null);
                employee.setSalaryList(null);
                employee.setShiftList(null);
                employee.setTaxList(null);
                employee.setTimeOffRequestList(null);
                
                newList.add(employee);
            }
            msg = gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

}
