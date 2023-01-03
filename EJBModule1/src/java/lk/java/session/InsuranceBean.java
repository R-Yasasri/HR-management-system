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
import lk.java.controller.InsuranceJpaController;
import lk.java.dto.InsuranceDTO;
import lk.java.entity.Employee;
import lk.java.entity.Insurance;

/**
 *
 * @author Rajitha Yasasri
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class InsuranceBean implements InsuranceBeanRemote, InsuranceBeanLocal {

    @EJB
    private EmployeeBeanLocal employeeBean;

    @Resource
    UserTransaction utx;

    @Override
    public void create(String provider, String address, String plan, String start, String end, String empId) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        InsuranceJpaController ijc = new InsuranceJpaController(utx, emf);

        Insurance ins = new Insurance();
        ins.setPlan(plan);
        ins.setProvider(provider);
        ins.setProviderAddress(address);

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date s = sdf.parse(start);
            Date e = sdf.parse(end);

            ins.setStartingDate(s);
            ins.setEndDate(e);

            Employee emp = employeeBean.searchByIdLocal(empId);

            ins.setEmployeeIdemployee(emp);

            ijc.create(ins);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(String id, String provider, String address, String plan, String start, String end, String empId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        InsuranceJpaController ijc = new InsuranceJpaController(utx, emf);

        Insurance ins = new Insurance();
        ins.setPlan(plan);
        ins.setProvider(provider);
        ins.setProviderAddress(address);

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date s = sdf.parse(start);
            Date e = sdf.parse(end);

            ins.setStartingDate(s);
            ins.setEndDate(e);

            Employee emp = employeeBean.searchByIdLocal(empId);

            ins.setEmployeeIdemployee(emp);

            Integer i = Integer.parseInt(id);
            ins.setIdinsurance(i);

            ijc.edit(ins);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        InsuranceJpaController ijc = new InsuranceJpaController(utx, emf);

        try {
            Integer i = Integer.parseInt(id);

            ijc.destroy(i);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String load() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        InsuranceJpaController ijc = new InsuranceJpaController(utx, emf);

        String msg = "not found";

        Gson gson = new Gson();
        try {
            List<Insurance> findInsuranceEntities = ijc.findInsuranceEntities();

            List list = new ArrayList();
            for (Insurance ins : findInsuranceEntities) {

                InsuranceDTO idt = new InsuranceDTO();
                idt.setEmpName(ins.getEmployeeIdemployee().getName());
                idt.setEnd(ins.getEndDate());
                idt.setId(ins.getIdinsurance());
                idt.setIdEmp(ins.getEmployeeIdemployee().getIdemployee());
                idt.setPlan(ins.getPlan());
                idt.setProvider(ins.getProvider());
                idt.setProviderAddress(ins.getProviderAddress());
                idt.setStart(ins.getStartingDate());

                list.add(idt);

            }

            msg = gson.toJson(list);
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

            Employee employee = employeeBean.searchByIdLocal(empId);
            System.out.println(employee.getName());

            List<Insurance> insuranceList = employee.getInsuranceList();

            List list = new ArrayList();
            for (Insurance ins : insuranceList) {

                InsuranceDTO idt = new InsuranceDTO();
                idt.setEmpName(ins.getEmployeeIdemployee().getName());
                idt.setEnd(ins.getEndDate());
                idt.setId(ins.getIdinsurance());
                idt.setIdEmp(ins.getEmployeeIdemployee().getIdemployee());
                idt.setPlan(ins.getPlan());
                idt.setProvider(ins.getProvider());
                idt.setProviderAddress(ins.getProviderAddress());
                idt.setStart(ins.getStartingDate());

                list.add(idt);

            }

            msg = gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public String searchByProvider(String provider) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        InsuranceJpaController ijc = new InsuranceJpaController(utx, emf);

        String msg = "not found";

        Gson gson = new Gson();

        try {

            List<Insurance> insuranceList = ijc.findInsuranceByProvider(provider);

            List list = new ArrayList();
            for (Insurance ins : insuranceList) {

                InsuranceDTO idt = new InsuranceDTO();
                idt.setEmpName(ins.getEmployeeIdemployee().getName());
                idt.setEnd(ins.getEndDate());
                idt.setId(ins.getIdinsurance());
                idt.setIdEmp(ins.getEmployeeIdemployee().getIdemployee());
                idt.setPlan(ins.getPlan());
                idt.setProvider(ins.getProvider());
                idt.setProviderAddress(ins.getProviderAddress());
                idt.setStart(ins.getStartingDate());

                list.add(idt);

            }

            msg = gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public String searchById(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        InsuranceJpaController ijc = new InsuranceJpaController(utx, emf);

        String msg = "not found";

        Gson gson = new Gson();

        try {

            Integer i = Integer.parseInt(id);
            Insurance ins = ijc.findInsurance(i);

            InsuranceDTO idt = new InsuranceDTO();
            idt.setEmpName(ins.getEmployeeIdemployee().getName());
            idt.setEnd(ins.getEndDate());
            idt.setId(ins.getIdinsurance());
            idt.setIdEmp(ins.getEmployeeIdemployee().getIdemployee());
            idt.setPlan(ins.getPlan());
            idt.setProvider(ins.getProvider());
            idt.setProviderAddress(ins.getProviderAddress());
            idt.setStart(ins.getStartingDate());

            msg = gson.toJson(idt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

}
