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
import lk.java.entity.Leave;
import lk.java.entity.Shift;

/**
 *
 * @author Rajitha Yasasri
 */
public class LeaveJpaController implements Serializable {

    public LeaveJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Leave leave) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Shift shiftIdshift = leave.getShiftIdshift();
            if (shiftIdshift != null) {
                shiftIdshift = em.getReference(shiftIdshift.getClass(), shiftIdshift.getIdshift());
                leave.setShiftIdshift(shiftIdshift);
            }
            em.persist(leave);
            if (shiftIdshift != null) {
                shiftIdshift.getLeaveList().add(leave);
                shiftIdshift = em.merge(shiftIdshift);
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

    public void edit(Leave leave) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Leave persistentLeave = em.find(Leave.class, leave.getIdleave());
            Shift shiftIdshiftOld = persistentLeave.getShiftIdshift();
            Shift shiftIdshiftNew = leave.getShiftIdshift();
            if (shiftIdshiftNew != null) {
                shiftIdshiftNew = em.getReference(shiftIdshiftNew.getClass(), shiftIdshiftNew.getIdshift());
                leave.setShiftIdshift(shiftIdshiftNew);
            }
            leave = em.merge(leave);
            if (shiftIdshiftOld != null && !shiftIdshiftOld.equals(shiftIdshiftNew)) {
                shiftIdshiftOld.getLeaveList().remove(leave);
                shiftIdshiftOld = em.merge(shiftIdshiftOld);
            }
            if (shiftIdshiftNew != null && !shiftIdshiftNew.equals(shiftIdshiftOld)) {
                shiftIdshiftNew.getLeaveList().add(leave);
                shiftIdshiftNew = em.merge(shiftIdshiftNew);
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
                Integer id = leave.getIdleave();
                if (findLeave(id) == null) {
                    throw new NonexistentEntityException("The leave with id " + id + " no longer exists.");
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
            Leave leave;
            try {
                leave = em.getReference(Leave.class, id);
                leave.getIdleave();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The leave with id " + id + " no longer exists.", enfe);
            }
            Shift shiftIdshift = leave.getShiftIdshift();
            if (shiftIdshift != null) {
                shiftIdshift.getLeaveList().remove(leave);
                shiftIdshift = em.merge(shiftIdshift);
            }
            em.remove(leave);
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

    public List<Leave> findLeaveEntities() {
        return findLeaveEntities(true, -1, -1);
    }

    public List<Leave> findLeaveEntities(int maxResults, int firstResult) {
        return findLeaveEntities(false, maxResults, firstResult);
    }

    private List<Leave> findLeaveEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Leave.class));
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

    public Leave findLeave(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Leave.class, id);
        } finally {
            em.close();
        }
    }

    public int getLeaveCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Leave> rt = cq.from(Leave.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Leave> findLeaveByType(String type) {
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Leave.findByType").setParameter("type", "%" + type + "%").getResultList();
    }

}
