/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.session;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import lk.java.controller.DisciplinaryHistoryJpaController;
import lk.java.dto.DisciplinaryHistoryDTO;
import lk.java.entity.DisciplinaryHistory;
import lk.java.entity.Employee;

/**
 *
 * @author Rajitha Yasasri
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class DisciplinaryHistoryBean implements DisciplinaryHistoryBeanRemote, DisciplinaryHistoryBeanLocal {

    @EJB
    private EmployeeBeanLocal employeeBean;

    @Resource
    UserTransaction utx;

    @Override
    public void create(String description, String action_taken, String empId) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");
        DisciplinaryHistoryJpaController djc = new DisciplinaryHistoryJpaController(utx, emf);

        try {

            DisciplinaryHistory d = new DisciplinaryHistory();
            d.setActionTaken(action_taken);
            d.setDescription(description);

            Employee emp = employeeBean.searchByIdLocal(empId);
            System.out.println(emp.getName());

            d.setEmployeeIdemployee(emp);

            djc.create(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(String id, String description, String action_taken, String empId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");
        DisciplinaryHistoryJpaController djc = new DisciplinaryHistoryJpaController(utx, emf);

        try {

            DisciplinaryHistory d = new DisciplinaryHistory();
            d.setActionTaken(action_taken);
            d.setDescription(description);

            Employee emp = employeeBean.searchByIdLocal(empId);
            System.out.println(emp.getName());

            d.setEmployeeIdemployee(emp);

            Integer i = Integer.parseInt(id);
            d.setIddisciplinaryHistory(i);

            djc.edit(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String load() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");
        DisciplinaryHistoryJpaController djc = new DisciplinaryHistoryJpaController(utx, emf);

        String msg = "not found";

        Gson gson = new Gson();

        try {

            List<DisciplinaryHistory> disciplinaryHistoryList = djc.findDisciplinaryHistoryEntities();

            List list = new ArrayList<>();
            for (DisciplinaryHistory disciplinaryHistory : disciplinaryHistoryList) {

                DisciplinaryHistoryDTO d = new DisciplinaryHistoryDTO();
                d.setActionTaken(disciplinaryHistory.getActionTaken());
                d.setDescription(disciplinaryHistory.getDescription());
                d.setEmpName(disciplinaryHistory.getEmployeeIdemployee().getName());
                d.setId(disciplinaryHistory.getIddisciplinaryHistory());
                d.setIdEmp(disciplinaryHistory.getEmployeeIdemployee().getIdemployee());

                list.add(d);
            }

            msg = gson.toJson(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public void delete(String id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");
        DisciplinaryHistoryJpaController djc = new DisciplinaryHistoryJpaController(utx, emf);

        try {

            Integer i = Integer.parseInt(id);

            djc.destroy(i);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String searchById(String id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");
        DisciplinaryHistoryJpaController djc = new DisciplinaryHistoryJpaController(utx, emf);

        String msg = "not found";

        Gson gson = new Gson();
        try {

            Integer i = Integer.parseInt(id);

            DisciplinaryHistory disciplinaryHistory = djc.findDisciplinaryHistory(i);

            DisciplinaryHistoryDTO d = new DisciplinaryHistoryDTO();
            d.setActionTaken(disciplinaryHistory.getActionTaken());
            d.setDescription(disciplinaryHistory.getDescription());
            d.setEmpName(disciplinaryHistory.getEmployeeIdemployee().getName());
            d.setId(disciplinaryHistory.getIddisciplinaryHistory());
            d.setIdEmp(disciplinaryHistory.getEmployeeIdemployee().getIdemployee());

            msg = gson.toJson(d);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public String searchByEmployee(String empId) {

        String msg = "not found";

        Gson gson = new Gson();

        try {

            Employee emp = employeeBean.searchByIdLocal(empId);

            List<DisciplinaryHistory> disciplinaryHistoryList = emp.getDisciplinaryHistoryList();
            List list = new ArrayList<>();
            for (DisciplinaryHistory disciplinaryHistory : disciplinaryHistoryList) {

                DisciplinaryHistoryDTO d = new DisciplinaryHistoryDTO();
                d.setActionTaken(disciplinaryHistory.getActionTaken());
                d.setDescription(disciplinaryHistory.getDescription());
                d.setEmpName(disciplinaryHistory.getEmployeeIdemployee().getName());
                d.setId(disciplinaryHistory.getIddisciplinaryHistory());
                d.setIdEmp(disciplinaryHistory.getEmployeeIdemployee().getIdemployee());

                list.add(d);
            }

            msg = gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public String searchByActionTaken(String action_taken) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");
        DisciplinaryHistoryJpaController djc = new DisciplinaryHistoryJpaController(utx, emf);

        String msg = "not found";

        Gson gson = new Gson();

        try {

            List<DisciplinaryHistory> disciplinaryHistoryList = djc.findDisciplinaryHistoryByActionTaken(action_taken);

            List list = new ArrayList<>();
            for (DisciplinaryHistory disciplinaryHistory : disciplinaryHistoryList) {

                DisciplinaryHistoryDTO d = new DisciplinaryHistoryDTO();
                d.setActionTaken(disciplinaryHistory.getActionTaken());
                d.setDescription(disciplinaryHistory.getDescription());
                d.setEmpName(disciplinaryHistory.getEmployeeIdemployee().getName());
                d.setId(disciplinaryHistory.getIddisciplinaryHistory());
                d.setIdEmp(disciplinaryHistory.getEmployeeIdemployee().getIdemployee());

                list.add(d);
            }

            msg = gson.toJson(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

}
