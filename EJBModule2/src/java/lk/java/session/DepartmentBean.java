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
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import lk.java.controller.DepartmentJpaController;
import lk.java.entity.Department;
import lk.java.interceptors.DepartmentInterceptor;

/**
 *
 * @author Rajitha Yasasri
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@Interceptors(DepartmentInterceptor.class)
public class DepartmentBean implements DepartmentBeanRemote, DepartmentBeanLocal {

    @Resource
    UserTransaction utx;

    @Override
    public void create(String name) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        Department dep = new Department();
        dep.setName(name);
        try {

            DepartmentJpaController djc = new DepartmentJpaController(utx, emf);

            djc.create(dep);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(String id, String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        Integer depId = Integer.parseInt(id);

        try {

            DepartmentJpaController djc = new DepartmentJpaController(utx, emf);

            Department dep = djc.findDepartment(depId);
            dep.setName(name);
            
            djc.edit(dep);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        Integer depId = Integer.parseInt(id);
        Department d = new Department();
        d.setIddepartment(depId);

        try {

            DepartmentJpaController djc = new DepartmentJpaController(utx, emf);
            djc.destroy(depId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String searchById(String id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        String msg = "not found";
        try {

            Integer deptId = Integer.parseInt(id);

            DepartmentJpaController djc = new DepartmentJpaController(utx, emf);

            Department findDepartment = djc.findDepartment(deptId);

            findDepartment.setShiftList(null);

            Gson gson = new Gson();
            msg = gson.toJson(findDepartment);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public String load() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        String msg = "not found";
        try {

            DepartmentJpaController djc = new DepartmentJpaController(utx, emf);

            List<Department> findDepartmentEntities = djc.findDepartmentEntities();

            List list = new ArrayList();
            for (Department findDepartmentEntity : findDepartmentEntities) {

                findDepartmentEntity.setShiftList(null);
                list.add(findDepartmentEntity);
            }

            Gson gson = new Gson();
            msg = gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public Department searchByIdLocal(String id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        Department dept = null;
        try {

            Integer deptId = Integer.parseInt(id);

            DepartmentJpaController djc = new DepartmentJpaController(utx, emf);

            dept = djc.findDepartment(deptId);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dept;
    }

    @Override
    public String searchByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        String msg = "not found";
        try {

            DepartmentJpaController djc = new DepartmentJpaController(utx, emf);

            List<Department> findDepartmentByName = djc.findDepartmentByName(name);
            
            List list=new ArrayList();
            for (Department department : findDepartmentByName) {
                
                department.setShiftList(null);
                list.add(department);
            }
            
            Gson gson=new Gson();
            msg=gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

}
