/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.session;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import lk.java.controller.AdminJpaController;
import lk.java.entity.Admin;

/**
 *
 * @author Rajitha Yasasri
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AdminBean implements AdminBeanRemote, AdminBeanLocal {

    @Resource
    UserTransaction utx;

    @Override
    public String searchAdminByUserName(String userName) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        AdminJpaController ajc = new AdminJpaController(utx, emf);

        String password = null;
        try {

            Admin admin = ajc.findAdminByUserName(userName);

            password = admin.getPassword();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    @Override
    public boolean update(String oldPassword, String newPassword) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule1PU");

        AdminJpaController ajc = new AdminJpaController(utx, emf);

        try {

            Admin admin = ajc.findAdmin(1);

            if (admin.getPassword().equals(oldPassword)) {

                admin.setPassword(newPassword);
                ajc.edit(admin);

                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
