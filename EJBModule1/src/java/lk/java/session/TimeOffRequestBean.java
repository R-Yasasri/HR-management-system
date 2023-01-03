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
import lk.java.controller.TimeOffRequestJpaController;
import lk.java.dto.TimeOffRequestDTO;
import lk.java.entity.Employee;
import lk.java.entity.TimeOffRequest;

/**
 *
 * @author Rajitha Yasasri
 */
@Stateless

@TransactionManagement(TransactionManagementType.BEAN)
public class TimeOffRequestBean implements TimeOffRequestBeanRemote, TimeOffRequestBeanLocal {

    @EJB
    private EmployeeBeanLocal employeeBean;

    @Resource
    UserTransaction utx;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void create(String reason, String begin, String end, String emp) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        try {

            TimeOffRequestJpaController tjc = new TimeOffRequestJpaController(utx, emf);

            TimeOffRequest t = new TimeOffRequest();
            t.setReason(reason);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date b = sdf.parse(begin);
            Date e = sdf.parse(end);

            Employee employee = employeeBean.searchByIdLocal(emp);

            t.setEmployeeIdemployee(employee);
            t.setBeginTime(b);
            t.setEndTime(e);

            tjc.create(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String id, String reason, String begin, String end, String emp) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        TimeOffRequestJpaController tjc = new TimeOffRequestJpaController(utx, emf);

        try {
            Integer i = Integer.parseInt(id);

            TimeOffRequest t = new TimeOffRequest();
            t.setIdtimeOffRequest(i);

            t.setReason(reason);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date b = sdf.parse(begin);
            Date e = sdf.parse(end);

            t.setBeginTime(b);
            t.setEndTime(e);

            Employee employee = employeeBean.searchByIdLocal(emp);

            t.setEmployeeIdemployee(employee);

            tjc.edit(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        TimeOffRequestJpaController tjc = new TimeOffRequestJpaController(utx, emf);

        try {

            Integer i = Integer.parseInt(id);
            tjc.destroy(i);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String searchByEmployee(String emp_id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        TimeOffRequestJpaController tjc = new TimeOffRequestJpaController(utx, emf);

        String msg = "not found";
        try {

            Employee emp = employeeBean.searchByIdLocal(emp_id);
            System.out.println(emp.getName());
            List<TimeOffRequest> timeOffRequestEntities = emp.getTimeOffRequestList();

            List<TimeOffRequestDTO> timeOffRequestList = new ArrayList();
            for (TimeOffRequest timeOffRequest : timeOffRequestEntities) {

                TimeOffRequestDTO t = new TimeOffRequestDTO();
                t.setBeginTime(timeOffRequest.getBeginTime());
                t.setEmpName(timeOffRequest.getEmployeeIdemployee().getName());
                t.setEndTime(timeOffRequest.getEndTime());
                t.setIdEmp(timeOffRequest.getEmployeeIdemployee().getIdemployee());
                t.setIdTimeOffRequest(timeOffRequest.getIdtimeOffRequest());
                t.setReason(timeOffRequest.getReason());

                timeOffRequestList.add(t);
            }

            Gson gson = new Gson();
            msg = gson.toJson(timeOffRequestList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public String searchById(String id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        TimeOffRequestJpaController tjc = new TimeOffRequestJpaController(utx, emf);

        String msg = "not found";
        Gson gson = new Gson();

        try {

            Integer i = Integer.parseInt(id);
            TimeOffRequest timeOffRequest = tjc.findTimeOffRequest(i);

            TimeOffRequestDTO t = new TimeOffRequestDTO();
            t.setBeginTime(timeOffRequest.getBeginTime());
            t.setEmpName(timeOffRequest.getEmployeeIdemployee().getName());
            t.setEndTime(timeOffRequest.getEndTime());
            t.setIdEmp(timeOffRequest.getEmployeeIdemployee().getIdemployee());
            t.setIdTimeOffRequest(timeOffRequest.getIdtimeOffRequest());
            t.setReason(timeOffRequest.getReason());

            msg = gson.toJson(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public String load() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        TimeOffRequestJpaController tjc = new TimeOffRequestJpaController(utx, emf);

        String msg = "not found";
        Gson gson = new Gson();

        try {

            List<TimeOffRequest> timeOffRequestEntities = tjc.findTimeOffRequestEntities();

            List<TimeOffRequestDTO> timeOffRequestList = new ArrayList();
            for (TimeOffRequest timeOffRequest : timeOffRequestEntities) {

                TimeOffRequestDTO t = new TimeOffRequestDTO();
                t.setBeginTime(timeOffRequest.getBeginTime());
                t.setEmpName(timeOffRequest.getEmployeeIdemployee().getName());
                t.setEndTime(timeOffRequest.getEndTime());
                t.setIdEmp(timeOffRequest.getEmployeeIdemployee().getIdemployee());
                t.setIdTimeOffRequest(timeOffRequest.getIdtimeOffRequest());
                t.setReason(timeOffRequest.getReason());

                timeOffRequestList.add(t);
            }

            msg = gson.toJson(timeOffRequestList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

}
