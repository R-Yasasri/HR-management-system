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
import lk.java.controller.TaxJpaController;
import lk.java.dto.TaxDTO;
import lk.java.entity.Employee;
import lk.java.entity.Tax;

/**
 *
 * @author Rajitha Yasasri
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TaxBean implements TaxBeanRemote, TaxBeanLocal {

    @EJB
    private EmployeeBeanLocal employeeBean;

    @Resource
    UserTransaction utx;

    @Override
    public void create(String tax_no, String office, String emp_id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        Tax tax = new Tax();
        tax.setIncomeTaxNo(tax_no);
        tax.setOfficeRegistered(office);

        TaxJpaController tjc = new TaxJpaController(utx, emf);
        try {

            Employee emp = employeeBean.searchByIdLocal(emp_id);

            List<Tax> currentTaxList = emp.getTaxList();

            if (currentTaxList == null || currentTaxList.isEmpty()) { //enforcing one tax per one employee
                tax.setEmployeeIdemployee(emp);
                tjc.create(tax);
            } else {
                edit(tax_no, office, emp_id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(String tax_no, String office, String emp_id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        TaxJpaController tjc = new TaxJpaController(utx, emf);
        try {

            Employee emp = employeeBean.searchByIdLocal(emp_id);

            List<Tax> taxList = emp.getTaxList();
            Tax tax = taxList.get(0);

            tax.setIncomeTaxNo(tax_no);
            tax.setOfficeRegistered(office);

            tjc.edit(tax);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String load() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        TaxJpaController tjc = new TaxJpaController(utx, emf);

        String msg = "not found";

        Gson gson = new Gson();

        try {

            List<Tax> taxList = tjc.findTaxEntities();

            List<TaxDTO> newTaxList = new ArrayList();
            for (Tax tax : taxList) {

                TaxDTO t = new TaxDTO();
                t.setEmpName(tax.getEmployeeIdemployee().getName());
                t.setIdEmp(tax.getEmployeeIdemployee().getIdemployee());
                t.setIdTax(tax.getIdtax());
                t.setIncomeTaxNo(tax.getIncomeTaxNo());
                t.setOfficeRegistered(tax.getOfficeRegistered());
                newTaxList.add(t);
            }

            msg = gson.toJson(newTaxList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public void delete(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        TaxJpaController tjc = new TaxJpaController(utx, emf);

        try {
            Integer i = Integer.parseInt(id);
            tjc.destroy(i);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String searchById(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        TaxJpaController tjc = new TaxJpaController(utx, emf);

        String msg = null;

        Gson gson = new Gson();
        try {
            Integer i = Integer.parseInt(id);
            Tax tax = tjc.findTax(i);

            TaxDTO t = new TaxDTO();
            t.setEmpName(tax.getEmployeeIdemployee().getName());
            t.setIdEmp(tax.getEmployeeIdemployee().getIdemployee());
            t.setIdTax(tax.getIdtax());
            t.setIncomeTaxNo(tax.getIncomeTaxNo());
            t.setOfficeRegistered(tax.getOfficeRegistered());

            msg = gson.toJson(t);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public String searchByTaxNo(String tax_no) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        TaxJpaController tjc = new TaxJpaController(utx, emf);

        String msg = null;

        Gson gson = new Gson();
        try {
            List<Tax> taxList = tjc.findTaxByTaxNo(tax_no);

            List<TaxDTO> newTaxList = new ArrayList();
            for (Tax tax : taxList) {

                TaxDTO t = new TaxDTO();
                t.setEmpName(tax.getEmployeeIdemployee().getName());
                t.setIdEmp(tax.getEmployeeIdemployee().getIdemployee());
                t.setIdTax(tax.getIdtax());
                t.setIncomeTaxNo(tax.getIncomeTaxNo());
                t.setOfficeRegistered(tax.getOfficeRegistered());
                newTaxList.add(t);
            }
            msg = gson.toJson(newTaxList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

}
