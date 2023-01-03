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
import lk.java.entity.TimeOffRequest;

/**
 *
 * @author Rajitha Yasasri
 */
public class TimeOffRequestJpaController implements Serializable {

    public TimeOffRequestJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TimeOffRequest timeOffRequest) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Employee employeeIdemployee = timeOffRequest.getEmployeeIdemployee();
            if (employeeIdemployee != null) {
                employeeIdemployee = em.getReference(employeeIdemployee.getClass(), employeeIdemployee.getIdemployee());
                timeOffRequest.setEmployeeIdemployee(employeeIdemployee);
            }
            em.persist(timeOffRequest);
            if (employeeIdemployee != null) {
                employeeIdemployee.getTimeOffRequestList().add(timeOffRequest);
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

    public void edit(TimeOffRequest timeOffRequest) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TimeOffRequest persistentTimeOffRequest = em.find(TimeOffRequest.class, timeOffRequest.getIdtimeOffRequest());
            Employee employeeIdemployeeOld = persistentTimeOffRequest.getEmployeeIdemployee();
            Employee employeeIdemployeeNew = timeOffRequest.getEmployeeIdemployee();
            if (employeeIdemployeeNew != null) {
                employeeIdemployeeNew = em.getReference(employeeIdemployeeNew.getClass(), employeeIdemployeeNew.getIdemployee());
                timeOffRequest.setEmployeeIdemployee(employeeIdemployeeNew);
            }
            timeOffRequest = em.merge(timeOffRequest);
            if (employeeIdemployeeOld != null && !employeeIdemployeeOld.equals(employeeIdemployeeNew)) {
                employeeIdemployeeOld.getTimeOffRequestList().remove(timeOffRequest);
                employeeIdemployeeOld = em.merge(employeeIdemployeeOld);
            }
            if (employeeIdemployeeNew != null && !employeeIdemployeeNew.equals(employeeIdemployeeOld)) {
                employeeIdemployeeNew.getTimeOffRequestList().add(timeOffRequest);
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
                Integer id = timeOffRequest.getIdtimeOffRequest();
                if (findTimeOffRequest(id) == null) {
                    throw new NonexistentEntityException("The timeOffRequest with id " + id + " no longer exists.");
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
            TimeOffRequest timeOffRequest;
            try {
                timeOffRequest = em.getReference(TimeOffRequest.class, id);
                timeOffRequest.getIdtimeOffRequest();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The timeOffRequest with id " + id + " no longer exists.", enfe);
            }
            Employee employeeIdemployee = timeOffRequest.getEmployeeIdemployee();
            if (employeeIdemployee != null) {
                employeeIdemployee.getTimeOffRequestList().remove(timeOffRequest);
                employeeIdemployee = em.merge(employeeIdemployee);
            }
            em.remove(timeOffRequest);
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

    public List<TimeOffRequest> findTimeOffRequestEntities() {
        return findTimeOffRequestEntities(true, -1, -1);
    }

    public List<TimeOffRequest> findTimeOffRequestEntities(int maxResults, int firstResult) {
        return findTimeOffRequestEntities(false, maxResults, firstResult);
    }

    private List<TimeOffRequest> findTimeOffRequestEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TimeOffRequest.class));
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

    public TimeOffRequest findTimeOffRequest(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TimeOffRequest.class, id);
        } finally {
            em.close();
        }
    }

    public int getTimeOffRequestCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TimeOffRequest> rt = cq.from(TimeOffRequest.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
