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
import lk.java.controller.PerformanceFeedbackJpaController;
import lk.java.dto.PerformanceFeedbackDTO;
import lk.java.entity.Employee;
import lk.java.entity.PerformanceFeedback;

/**
 *
 * @author Rajitha Yasasri
 */
@Stateless

@TransactionManagement(TransactionManagementType.BEAN)
public class PerformanceFeedbackBean implements PerformanceFeedbackBeanRemote, PerformanceFeedbackBeanLocal {

    @EJB
    private EmployeeBeanLocal employeeBean;

    @Resource
    UserTransaction utx;

    @Override
    public void create(String description, String person_given_by, String empId) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        PerformanceFeedbackJpaController pfc = new PerformanceFeedbackJpaController(utx, emf);

        PerformanceFeedback p = new PerformanceFeedback();
        p.setDescription(description);
        p.setPersonGivenBy(person_given_by);

        try {

            Employee emp = employeeBean.searchByIdLocal(empId);
            System.out.println(emp.getName());

            p.setEmployeeIdemployee(emp);

            pfc.create(p);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void edit(String id, String description, String person_given_by, String empId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        PerformanceFeedbackJpaController pfc = new PerformanceFeedbackJpaController(utx, emf);

        PerformanceFeedback p = new PerformanceFeedback();
        p.setDescription(description);
        p.setPersonGivenBy(person_given_by);

        Integer i = Integer.parseInt(id);
        p.setIdperformanceFeedback(i);

        try {

            Employee emp = employeeBean.searchByIdLocal(empId);
            System.out.println(emp.getName());

            p.setEmployeeIdemployee(emp);

            pfc.edit(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        PerformanceFeedbackJpaController pfc = new PerformanceFeedbackJpaController(utx, emf);

        try {

            Integer i = Integer.parseInt(id);

            pfc.destroy(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String searchById(String id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        PerformanceFeedbackJpaController pfc = new PerformanceFeedbackJpaController(utx, emf);

        String msg = "not found";

        Gson gson = new Gson();
        try {

            Integer i = Integer.parseInt(id);

            PerformanceFeedback performanceFeedback = pfc.findPerformanceFeedback(i);

            PerformanceFeedbackDTO p = new PerformanceFeedbackDTO();
            p.setDescription(performanceFeedback.getDescription());
            p.setEmployeeId(performanceFeedback.getEmployeeIdemployee().getIdemployee());
            p.setEmployeeName(performanceFeedback.getEmployeeIdemployee().getName());
            p.setId(performanceFeedback.getIdperformanceFeedback());
            p.setPersonGiven(performanceFeedback.getPersonGivenBy());

            msg = gson.toJson(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public String searchByEmployee(String empId) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        PerformanceFeedbackJpaController pfc = new PerformanceFeedbackJpaController(utx, emf);

        String msg = "not found";

        Gson gson = new Gson();

        try {

            Employee employee = employeeBean.searchByIdLocal(empId);

            List<PerformanceFeedback> performanceFeedbackList = employee.getPerformanceFeedbackList();

            List list = new ArrayList<>();
            for (PerformanceFeedback performanceFeedback : performanceFeedbackList) {

                PerformanceFeedbackDTO p = new PerformanceFeedbackDTO();
                p.setDescription(performanceFeedback.getDescription());
                p.setEmployeeId(performanceFeedback.getEmployeeIdemployee().getIdemployee());
                p.setEmployeeName(performanceFeedback.getEmployeeIdemployee().getName());
                p.setId(performanceFeedback.getIdperformanceFeedback());
                p.setPersonGiven(performanceFeedback.getPersonGivenBy());

                list.add(p);
            }

            msg = gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public String searchByPersonGivenBy(String person) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        PerformanceFeedbackJpaController pfc = new PerformanceFeedbackJpaController(utx, emf);

        String msg = "not found";

        Gson gson = new Gson();

        try {

            List<PerformanceFeedback> performanceFeedbackList = pfc.findPerformanceFeedbackByPersonGiven(person);

            List list = new ArrayList<>();
            for (PerformanceFeedback performanceFeedback : performanceFeedbackList) {

                PerformanceFeedbackDTO p = new PerformanceFeedbackDTO();
                p.setDescription(performanceFeedback.getDescription());
                p.setEmployeeId(performanceFeedback.getEmployeeIdemployee().getIdemployee());
                p.setEmployeeName(performanceFeedback.getEmployeeIdemployee().getName());
                p.setId(performanceFeedback.getIdperformanceFeedback());
                p.setPersonGiven(performanceFeedback.getPersonGivenBy());

                list.add(p);
            }

            msg = gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public String load() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        PerformanceFeedbackJpaController pfc = new PerformanceFeedbackJpaController(utx, emf);

        String msg = "not found";

        Gson gson = new Gson();

        try {

            List<PerformanceFeedback> performanceFeedbackList = pfc.findPerformanceFeedbackEntities();

            List list = new ArrayList<>();
            for (PerformanceFeedback performanceFeedback : performanceFeedbackList) {

                PerformanceFeedbackDTO p = new PerformanceFeedbackDTO();
                p.setDescription(performanceFeedback.getDescription());
                p.setEmployeeId(performanceFeedback.getEmployeeIdemployee().getIdemployee());
                p.setEmployeeName(performanceFeedback.getEmployeeIdemployee().getName());
                p.setId(performanceFeedback.getIdperformanceFeedback());
                p.setPersonGiven(performanceFeedback.getPersonGivenBy());

                list.add(p);
            }

            msg = gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

}
