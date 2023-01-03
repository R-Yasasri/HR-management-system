/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lk.java.entity.Shift;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import lk.java.controller.exceptions.IllegalOrphanException;
import lk.java.controller.exceptions.NonexistentEntityException;
import lk.java.controller.exceptions.RollbackFailureException;
import lk.java.entity.Project;

/**
 *
 * @author Rajitha Yasasri
 */
public class ProjectJpaController implements Serializable {

    public ProjectJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Project project) throws RollbackFailureException, Exception {
        if (project.getShiftList() == null) {
            project.setShiftList(new ArrayList<Shift>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Shift> attachedShiftList = new ArrayList<Shift>();
            for (Shift shiftListShiftToAttach : project.getShiftList()) {
                shiftListShiftToAttach = em.getReference(shiftListShiftToAttach.getClass(), shiftListShiftToAttach.getIdshift());
                attachedShiftList.add(shiftListShiftToAttach);
            }
            project.setShiftList(attachedShiftList);
            em.persist(project);
            for (Shift shiftListShift : project.getShiftList()) {
                Project oldProjectIdprojectOfShiftListShift = shiftListShift.getProjectIdproject();
                shiftListShift.setProjectIdproject(project);
                shiftListShift = em.merge(shiftListShift);
                if (oldProjectIdprojectOfShiftListShift != null) {
                    oldProjectIdprojectOfShiftListShift.getShiftList().remove(shiftListShift);
                    oldProjectIdprojectOfShiftListShift = em.merge(oldProjectIdprojectOfShiftListShift);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Project project) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Project persistentProject = em.find(Project.class, project.getIdproject());
            List<Shift> shiftListOld = persistentProject.getShiftList();
            List<Shift> shiftListNew = project.getShiftList();
            List<String> illegalOrphanMessages = null;
            for (Shift shiftListOldShift : shiftListOld) {
                if (!shiftListNew.contains(shiftListOldShift)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Shift " + shiftListOldShift + " since its projectIdproject field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Shift> attachedShiftListNew = new ArrayList<Shift>();
            for (Shift shiftListNewShiftToAttach : shiftListNew) {
                shiftListNewShiftToAttach = em.getReference(shiftListNewShiftToAttach.getClass(), shiftListNewShiftToAttach.getIdshift());
                attachedShiftListNew.add(shiftListNewShiftToAttach);
            }
            shiftListNew = attachedShiftListNew;
            project.setShiftList(shiftListNew);
            project = em.merge(project);
            for (Shift shiftListNewShift : shiftListNew) {
                if (!shiftListOld.contains(shiftListNewShift)) {
                    Project oldProjectIdprojectOfShiftListNewShift = shiftListNewShift.getProjectIdproject();
                    shiftListNewShift.setProjectIdproject(project);
                    shiftListNewShift = em.merge(shiftListNewShift);
                    if (oldProjectIdprojectOfShiftListNewShift != null && !oldProjectIdprojectOfShiftListNewShift.equals(project)) {
                        oldProjectIdprojectOfShiftListNewShift.getShiftList().remove(shiftListNewShift);
                        oldProjectIdprojectOfShiftListNewShift = em.merge(oldProjectIdprojectOfShiftListNewShift);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = project.getIdproject();
                if (findProject(id) == null) {
                    throw new NonexistentEntityException("The project with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Project project;
            try {
                project = em.getReference(Project.class, id);
                project.getIdproject();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The project with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Shift> shiftListOrphanCheck = project.getShiftList();
            for (Shift shiftListOrphanCheckShift : shiftListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Project (" + project + ") cannot be destroyed since the Shift " + shiftListOrphanCheckShift + " in its shiftList field has a non-nullable projectIdproject field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(project);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Project> findProjectEntities() {
        return findProjectEntities(true, -1, -1);
    }

    public List<Project> findProjectEntities(int maxResults, int firstResult) {
        return findProjectEntities(false, maxResults, firstResult);
    }

    private List<Project> findProjectEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Project.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Project findProject(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Project.class, id);
        } finally {
            em.close();
        }
    }

    public int getProjectCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Project> rt = cq.from(Project.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Project> findProjectByName(String name){
        
         EntityManager em = getEntityManager();
        return em.createNamedQuery("Project.findByName").setParameter("name", "%" + name + "%").getResultList();
    }
}
