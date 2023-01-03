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
import lk.java.controller.JobJpaController;
import lk.java.dto.JobDTO;
import lk.java.entity.Employee;
import lk.java.entity.Job;

/**
 *
 * @author Rajitha Yasasri
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class JobBean implements JobBeanRemote, JobBeanLocal {

    @EJB
    private EmployeeBeanLocal employeeBean;

    @Resource
    UserTransaction utx;

    @Override
    public void create(String title, String start, String end, String empId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        JobJpaController j = new JobJpaController(utx, emf);

        Job job = new Job();
        job.setTitle(title);

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date s = sdf.parse(start);
            Date e = sdf.parse(end);

            job.setStartDate(s);
            job.setEndDate(e);

            Employee employee = employeeBean.searchByIdLocal(empId);
            System.out.println(employee.getName());

            job.setEmployeeIdemployee(employee);

            j.create(job);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(String id, String title, String start, String end, String empId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        JobJpaController j = new JobJpaController(utx, emf);

        Job job = new Job();
        job.setTitle(title);

        Integer i = Integer.parseInt(id);
        job.setIdjob(i);
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date s = sdf.parse(start);
            Date e = sdf.parse(end);

            job.setStartDate(s);
            job.setEndDate(e);

            Employee employee = employeeBean.searchByIdLocal(empId);
            System.out.println(employee.getName());

            job.setEmployeeIdemployee(employee);

            j.edit(job);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        JobJpaController j = new JobJpaController(utx, emf);

        try {

            Integer i = Integer.parseInt(id);

            j.destroy(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String searchById(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        JobJpaController j = new JobJpaController(utx, emf);

        String msg = "not found";

        Gson gson = new Gson();
        try {
            Integer i = Integer.parseInt(id);
            Job job = j.findJob(i);

            JobDTO jdto = new JobDTO();
            jdto.setId(job.getIdjob());
            jdto.setEnd(job.getEndDate());
            jdto.setEmployeeName(job.getEmployeeIdemployee().getName());
            jdto.setIdEmp(job.getEmployeeIdemployee().getIdemployee());
            jdto.setStart(job.getStartDate());
            jdto.setTitle(job.getTitle());

            msg = gson.toJson(jdto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public String load() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        JobJpaController j = new JobJpaController(utx, emf);

        String msg = "not found";

        Gson gson = new Gson();

        try {

            List<Job> jobList = j.findJobEntities();

            List list = new ArrayList();
            for (Job job : jobList) {

                JobDTO jdto = new JobDTO();
                jdto.setId(job.getIdjob());
                jdto.setEnd(job.getEndDate());
                jdto.setEmployeeName(job.getEmployeeIdemployee().getName());
                jdto.setIdEmp(job.getEmployeeIdemployee().getIdemployee());
                jdto.setStart(job.getStartDate());
                jdto.setTitle(job.getTitle());

                list.add(jdto);
            }

            msg = gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public String searchByTitle(String title) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        JobJpaController j = new JobJpaController(utx, emf);

        String msg = "not found";

        Gson gson = new Gson();

        try {

            List<Job> jobList = j.findJobByTitle(title);

            List list = new ArrayList();
            for (Job job : jobList) {

                JobDTO jdto = new JobDTO();
                jdto.setId(job.getIdjob());
                jdto.setEnd(job.getEndDate());
                jdto.setEmployeeName(job.getEmployeeIdemployee().getName());
                jdto.setIdEmp(job.getEmployeeIdemployee().getIdemployee());
                jdto.setStart(job.getStartDate());
                jdto.setTitle(job.getTitle());

                list.add(jdto);
            }

            msg = gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public String searchByEmployee(String empId) {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        JobJpaController j = new JobJpaController(utx, emf);

        String msg = "not found";

        Gson gson = new Gson();

        try {

             Employee employee = employeeBean.searchByIdLocal(empId);

             List<Job> jobList = employee.getJobList();
             
            List list = new ArrayList();
            for (Job job : jobList) {

                JobDTO jdto = new JobDTO();
                jdto.setId(job.getIdjob());
                jdto.setEnd(job.getEndDate());
                jdto.setEmployeeName(job.getEmployeeIdemployee().getName());
                jdto.setIdEmp(job.getEmployeeIdemployee().getIdemployee());
                jdto.setStart(job.getStartDate());
                jdto.setTitle(job.getTitle());

                list.add(jdto);
            }

            msg = gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

}
