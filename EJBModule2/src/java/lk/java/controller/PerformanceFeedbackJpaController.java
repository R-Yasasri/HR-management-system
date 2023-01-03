/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import lk.java.controller.exceptions.NonexistentEntityException;
import lk.java.controller.exceptions.RollbackFailureException;
import lk.java.entity.Employee;
import lk.java.entity.PerformanceFeedback;

/**
 *
 * @author Rajitha Yasasri
 */
public class PerformanceFeedbackJpaController implements Serializable {

    public PerformanceFeedbackJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PerformanceFeedback performanceFeedback) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Employee employeeIdemployee = performanceFeedback.getEmployeeIdemployee();
            if (employeeIdemployee != null) {
                employeeIdemployee = em.getReference(employeeIdemployee.getClass(), employeeIdemployee.getIdemployee());
                performanceFeedback.setEmployeeIdemployee(employeeIdemployee);
            }
            em.persist(performanceFeedback);
            if (employeeIdemployee != null) {
                employeeIdemployee.getPerformanceFeedbackList().add(performanceFeedback);
                employeeIdemployee = em.merge(employeeIdemployee);
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

    public void edit(PerformanceFeedback performanceFeedback) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            PerformanceFeedback persistentPerformanceFeedback = em.find(PerformanceFeedback.class, performanceFeedback.getIdperformanceFeedback());
            Employee employeeIdemployeeOld = persistentPerformanceFeedback.getEmployeeIdemployee();
            Employee employeeIdemployeeNew = performanceFeedback.getEmployeeIdemployee();
            if (employeeIdemployeeNew != null) {
                employeeIdemployeeNew = em.getReference(employeeIdemployeeNew.getClass(), employeeIdemployeeNew.getIdemployee());
                performanceFeedback.setEmployeeIdemployee(employeeIdemployeeNew);
            }
            performanceFeedback = em.merge(performanceFeedback);
            if (employeeIdemployeeOld != null && !employeeIdemployeeOld.equals(employeeIdemployeeNew)) {
                employeeIdemployeeOld.getPerformanceFeedbackList().remove(performanceFeedback);
                employeeIdemployeeOld = em.merge(employeeIdemployeeOld);
            }
            if (employeeIdemployeeNew != null && !employeeIdemployeeNew.equals(employeeIdemployeeOld)) {
                employeeIdemployeeNew.getPerformanceFeedbackList().add(performanceFeedback);
                employeeIdemployeeNew = em.merge(employeeIdemployeeNew);
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
                Integer id = performanceFeedback.getIdperformanceFeedback();
                if (findPerformanceFeedback(id) == null) {
                    throw new NonexistentEntityException("The performanceFeedback with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            PerformanceFeedback performanceFeedback;
            try {
                performanceFeedback = em.getReference(PerformanceFeedback.class, id);
                performanceFeedback.getIdperformanceFeedback();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The performanceFeedback with id " + id + " no longer exists.", enfe);
            }
            Employee employeeIdemployee = performanceFeedback.getEmployeeIdemployee();
            if (employeeIdemployee != null) {
                employeeIdemployee.getPerformanceFeedbackList().remove(performanceFeedback);
                employeeIdemployee = em.merge(employeeIdemployee);
            }
            em.remove(performanceFeedback);
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

    public List<PerformanceFeedback> findPerformanceFeedbackEntities() {
        return findPerformanceFeedbackEntities(true, -1, -1);
    }

    public List<PerformanceFeedback> findPerformanceFeedbackEntities(int maxResults, int firstResult) {
        return findPerformanceFeedbackEntities(false, maxResults, firstResult);
    }

    private List<PerformanceFeedback> findPerformanceFeedbackEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PerformanceFeedback.class));
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

    public PerformanceFeedback findPerformanceFeedback(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PerformanceFeedback.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerformanceFeedbackCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PerformanceFeedback> rt = cq.from(PerformanceFeedback.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
