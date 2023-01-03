/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.session;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import lk.java.controller.BankJpaController;
import lk.java.dto.BankDTO;
import lk.java.entity.Bank;
import lk.java.entity.Employee;

/**
 *
 * @author Rajitha Yasasri
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class BankBean implements BankBeanRemote, BankBeanLocal {

    @EJB
    private EmployeeBeanLocal employeeBean;

    @Resource
    UserTransaction utx;

    @Override
    public void create(String name, String account, String address, String emp_id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        Bank b = new Bank();
        b.setAccount(account);
        b.setAddress(address);
        b.setName(name);

        BankJpaController bjc = new BankJpaController(utx, emf);
        try {
            Employee emp = employeeBean.searchByIdLocal(emp_id);
            b.setEmployeeIdemployee(emp);
            bjc.create(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(String id, String name, String account, String address, String emp_id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        int i = Integer.parseInt(id);

        Bank b = new Bank();
        b.setIdbank(i);
        b.setAccount(account);
        b.setAddress(address);
        b.setName(name);

        BankJpaController bjc = new BankJpaController(utx, emf);
        try {
            Employee emp = employeeBean.searchByIdLocal(emp_id);
            b.setEmployeeIdemployee(emp);
            bjc.edit(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        BankJpaController bjc = new BankJpaController(utx, emf);
        try {

            int i = Integer.parseInt(id);

            bjc.destroy(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String load() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        BankJpaController bjc = new BankJpaController(utx, emf);
        Gson gson = new Gson();

        String msg = "not found";

        try {

            List<Bank> findBankEntities = bjc.findBankEntities();

            List<BankDTO> newBankList = new ArrayList<BankDTO>();
            for (Bank bank : findBankEntities) {

                BankDTO bto = new BankDTO();
                bto.setAccount(bank.getAccount());
                bto.setAddress(bank.getAddress());
                bto.setEmpName(bank.getEmployeeIdemployee().getName());
                bto.setIdBank(bank.getIdbank());
                bto.setIdEmp(bank.getEmployeeIdemployee().getIdemployee());
                bto.setName(bank.getName());

                newBankList.add(bto);
            }

            msg = gson.toJson(newBankList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public String searchById(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        BankJpaController bjc = new BankJpaController(utx, emf);
        Gson gson = new Gson();

        String msg = "not found";

        try {

            Integer i = Integer.parseInt(id);

            Bank bank = bjc.findBank(i);

            BankDTO bto = new BankDTO();
            bto.setAccount(bank.getAccount());
            bto.setAddress(bank.getAddress());
            bto.setEmpName(bank.getEmployeeIdemployee().getName());
            bto.setIdBank(bank.getIdbank());
            bto.setIdEmp(bank.getEmployeeIdemployee().getIdemployee());
            bto.setName(bank.getName());

            msg = gson.toJson(bto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public String searchByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        BankJpaController bjc = new BankJpaController(utx, emf);
        Gson gson = new Gson();

        String msg = "not found";

        try {

            List<Bank> bankList = bjc.findBankByName(name);

            List<BankDTO> newBankList = new ArrayList<BankDTO>();
            for (Bank bank : bankList) {

                BankDTO bto = new BankDTO();
                bto.setAccount(bank.getAccount());
                bto.setAddress(bank.getAddress());
                bto.setEmpName(bank.getEmployeeIdemployee().getName());
                bto.setIdBank(bank.getIdbank());
                bto.setIdEmp(bank.getEmployeeIdemployee().getIdemployee());
                bto.setName(bank.getName());

                newBankList.add(bto);
            }
            msg = gson.toJson(newBankList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

}
