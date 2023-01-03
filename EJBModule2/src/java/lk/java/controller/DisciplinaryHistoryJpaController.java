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
import lk.java.entity.DisciplinaryHistory;
import lk.java.entity.Employee;

/**
 *
 * @author Rajitha Yasasri
 */
public class DisciplinaryHistoryJpaController implements Serializable {

    public DisciplinaryHistoryJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DisciplinaryHistory disciplinaryHistory) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Employee employeeIdemployee = disciplinaryHistory.getEmployeeIdemployee();
            if (employeeIdemployee != null) {
                employeeIdemployee = em.getReference(employeeIdemployee.getClass(), employeeIdemployee.getIdemployee());
                disciplinaryHistory.setEmployeeIdemployee(employeeIdemployee);
            }
            em.persist(disciplinaryHistory);
            if (employeeIdemployee != null) {
                employeeIdemployee.getDisciplinaryHistoryList().add(disciplinaryHistory);
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

    public void edit(DisciplinaryHistory disciplinaryHistory) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            DisciplinaryHistory persistentDisciplinaryHistory = em.find(DisciplinaryHistory.class, disciplinaryHistory.getIddisciplinaryHistory());
            Employee employeeIdemployeeOld = persistentDisciplinaryHistory.getEmployeeIdemployee();
            Employee employeeIdemployeeNew = disciplinaryHistory.getEmployeeIdemployee();
            if (employeeIdemployeeNew != null) {
                employeeIdemployeeNew = em.getReference(employeeIdemployeeNew.getClass(), employeeIdemployeeNew.getIdemployee());
                disciplinaryHistory.setEmployeeIdemployee(employeeIdemployeeNew);
            }
            disciplinaryHistory = em.merge(disciplinaryHistory);
            if (employeeIdemployeeOld != null && !employeeIdemployeeOld.equals(employeeIdemployeeNew)) {
                employeeIdemployeeOld.getDisciplinaryHistoryList().remove(disciplinaryHistory);
                employeeIdemployeeOld = em.merge(employeeIdemployeeOld);
            }
            if (employeeIdemployeeNew != null && !employeeIdemployeeNew.equals(employeeIdemployeeOld)) {
                employeeIdemployeeNew.getDisciplinaryHistoryList().add(disciplinaryHistory);
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
                Integer id = disciplinaryHistory.getIddisciplinaryHistory();
                if (findDisciplinaryHistory(id) == null) {
                    throw new NonexistentEntityException("The disciplinaryHistory with id " + id + " no longer exists.");
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
            DisciplinaryHistory disciplinaryHistory;
            try {
                disciplinaryHistory = em.getReference(DisciplinaryHistory.class, id);
                disciplinaryHistory.getIddisciplinaryHistory();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The disciplinaryHistory with id " + id + " no longer exists.", enfe);
            }
            Employee employeeIdemployee = disciplinaryHistory.getEmployeeIdemployee();
            if (employeeIdemployee != null) {
                employeeIdemployee.getDisciplinaryHistoryList().remove(disciplinaryHistory);
                employeeIdemployee = em.merge(employeeIdemployee);
            }
            em.remove(disciplinaryHistory);
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

    public List<DisciplinaryHistory> findDisciplinaryHistoryEntities() {
        return findDisciplinaryHistoryEntities(true, -1, -1);
    }

    public List<DisciplinaryHistory> findDisciplinaryHistoryEntities(int maxResults, int firstResult) {
        return findDisciplinaryHistoryEntities(false, maxResults, firstResult);
    }

    private List<DisciplinaryHistory> findDisciplinaryHistoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DisciplinaryHistory.class));
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

    public DisciplinaryHistory findDisciplinaryHistory(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DisciplinaryHistory.class, id);
        } finally {
            em.close();
        }
    }

    public int getDisciplinaryHistoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DisciplinaryHistory> rt = cq.from(DisciplinaryHistory.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
