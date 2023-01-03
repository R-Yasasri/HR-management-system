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
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import lk.java.controller.ProjectJpaController;
import lk.java.entity.Project;

/**
 *
 * @author Rajitha Yasasri
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ProjectBean implements ProjectBeanRemote, ProjectBeanLocal {

    @Resource
    UserTransaction utx;

    @Override
    public void create(String name, String description) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        try {

            Project p = new Project();
            p.setDescription(description);
            p.setName(name);

            ProjectJpaController pjc = new ProjectJpaController(utx, emf);

            pjc.create(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(String id, String name, String description) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        try {

            int i = Integer.parseInt(id);

            ProjectJpaController pjc = new ProjectJpaController(utx, emf);
            Project p = pjc.findProject(i);

            p.setDescription(description);
            p.setName(name);

            pjc.edit(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String load() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        String msg = "not found";

        try {

            ProjectJpaController pjc = new ProjectJpaController(utx, emf);
            List<Project> findProjectEntities = pjc.findProjectEntities();

            List list = new ArrayList();
            for (Project project : findProjectEntities) {

                project.setShiftList(null);
                list.add(project);
            }

            Gson gson = new Gson();
            msg = gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public void delete(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        ProjectJpaController pjc = new ProjectJpaController(utx, emf);
        try {

            Integer i = Integer.parseInt(id);

            pjc.destroy(i);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String SearchById(String id) {

        String msg = "not found";

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        ProjectJpaController pjc = new ProjectJpaController(utx, emf);

        try {

            Integer i = Integer.parseInt(id);

            Project project = pjc.findProject(i);
            project.setShiftList(null);

            Gson gson = new Gson();
            msg = gson.toJson(project);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public String searchByName(String name) {

        String msg = "not found";

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        ProjectJpaController pjc = new ProjectJpaController(utx, emf);

        try {

            List<Project> findProjectByName = pjc.findProjectByName(name);
            Gson gson = new Gson();

            List list = new ArrayList();
            for (Project project : findProjectByName) {
                project.setShiftList(null);
                list.add(project);
            }

            msg = gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public Project searchProjectByIdLocal(String id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        ProjectJpaController pjc = new ProjectJpaController(utx, emf);

        Project p = null;

        try {

            Integer i = Integer.parseInt(id);
            p = pjc.findProject(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

}
