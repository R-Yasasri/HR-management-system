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
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import lk.java.controller.SalaryJpaController;
import lk.java.dto.SalaryDTO;
import lk.java.entity.Employee;
import lk.java.entity.Salary;

/**
 *
 * @author Rajitha Yasasri
 */
@Stateless

@TransactionManagement(TransactionManagementType.BEAN)
public class SalaryBean implements SalaryBeanRemote, SalaryBeanLocal {

    @EJB
    private EmployeeBeanLocal employeeBean;

    @Resource
    UserTransaction utx;

    @Override
    public void create(String month, String salary, String status, String emp_id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        SalaryJpaController sjc = new SalaryJpaController(utx, emf);

        try {

            Salary sal = new Salary();
            sal.setStatus(status);

            double s = Double.parseDouble(salary);
            sal.setSalary(s);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Date date = sdf.parse(month);
            sal.setMonth(date);

            Employee emp = employeeBean.searchByIdLocal(emp_id);

            sal.setEmployeeIdemployee(emp);

            sjc.create(sal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(String idSalary, String month, String salary, String status, String emp_id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        SalaryJpaController sjc = new SalaryJpaController(utx, emf);

        try {

            Salary sal = new Salary();
            sal.setStatus(status);

            Integer i = Integer.parseInt(idSalary);

            sal.setIdsalary(i);

            Double s = Double.parseDouble(salary);
            sal.setSalary(s);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Date date = sdf.parse(month);
            sal.setMonth(date);

            Employee emp = employeeBean.searchByIdLocal(emp_id);

            sal.setEmployeeIdemployee(emp);

            sjc.edit(sal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String idSalary) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        SalaryJpaController sjc = new SalaryJpaController(utx, emf);

        try {

            Integer i = Integer.parseInt(idSalary);
            sjc.destroy(i);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String searchById(String idSalary) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        SalaryJpaController sjc = new SalaryJpaController(utx, emf);

        String msg = "not found";
        try {

            Integer i = Integer.parseInt(idSalary);
            Salary sal = sjc.findSalary(i);
            SalaryDTO s = new SalaryDTO();
            s.setEmpName(sal.getEmployeeIdemployee().getName());
            s.setIdEmp(sal.getEmployeeIdemployee().getIdemployee());
            s.setIdSalary(sal.getIdsalary());
            s.setMonth(sal.getMonth());
            s.setSalary(sal.getSalary());
            s.setStatus(sal.getStatus());

            Gson gson = new Gson();
            msg = gson.toJson(s);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public String load() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        SalaryJpaController sjc = new SalaryJpaController(utx, emf);

        String msg = "not found";
        try {

            List<Salary> findSalaryEntities = sjc.findSalaryEntities();

            List<SalaryDTO> salaryList = new ArrayList<>();
            for (Salary sal : findSalaryEntities) {

                SalaryDTO s = new SalaryDTO();
                s.setEmpName(sal.getEmployeeIdemployee().getName());
                s.setIdEmp(sal.getEmployeeIdemployee().getIdemployee());
                s.setIdSalary(sal.getIdsalary());
                s.setMonth(sal.getMonth());
                s.setSalary(sal.getSalary());
                s.setStatus(sal.getStatus());
                salaryList.add(s);
            }

            Gson gson = new Gson();
            msg = gson.toJson(salaryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public String searchByMonth(String month) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        SalaryJpaController sjc = new SalaryJpaController(utx, emf);

        String msg = "not found";

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Date date = sdf.parse(month);

            List<Salary> salaries = sjc.findSalariesByMonth(date);

            List<SalaryDTO> salaryList = new ArrayList<>();
            for (Salary sal : salaries) {

                SalaryDTO s = new SalaryDTO();
                s.setEmpName(sal.getEmployeeIdemployee().getName());
                s.setIdEmp(sal.getEmployeeIdemployee().getIdemployee());
                s.setIdSalary(sal.getIdsalary());
                s.setMonth(sal.getMonth());
                s.setSalary(sal.getSalary());
                s.setStatus(sal.getStatus());
                salaryList.add(s);
            }

            Gson gson = new Gson();
            msg = gson.toJson(salaryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public String searchSalaryByEmployee(String emp_id) {

        String msg = "not found";
        try {
            Employee emp = employeeBean.searchByIdLocal(emp_id);

            List<Salary> findSalaryEntities = emp.getSalaryList();

            List<SalaryDTO> salaryList = new ArrayList<>();
            for (Salary sal : findSalaryEntities) {

                SalaryDTO s = new SalaryDTO();
                s.setEmpName(sal.getEmployeeIdemployee().getName());
                s.setIdEmp(sal.getEmployeeIdemployee().getIdemployee());
                s.setIdSalary(sal.getIdsalary());
                s.setMonth(sal.getMonth());
                s.setSalary(sal.getSalary());
                s.setStatus(sal.getStatus());
                salaryList.add(s);
            }

            Gson gson = new Gson();
            msg = gson.toJson(salaryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

}
