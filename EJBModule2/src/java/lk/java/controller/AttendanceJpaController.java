/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.controller;

import java.io.Serializable;
import java.util.Date;
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
import lk.java.entity.Attendance;
import lk.java.entity.Shift;

/**
 *
 * @author Rajitha Yasasri
 */
public class AttendanceJpaController implements Serializable {

    public AttendanceJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Attendance attendance) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Shift shiftIdshift = attendance.getShiftIdshift();
            if (shiftIdshift != null) {
                shiftIdshift = em.getReference(shiftIdshift.getClass(), shiftIdshift.getIdshift());
                attendance.setShiftIdshift(shiftIdshift);
            }
            em.persist(attendance);
            if (shiftIdshift != null) {
                shiftIdshift.getAttendanceList().add(attendance);
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

    public void edit(Attendance attendance) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Attendance persistentAttendance = em.find(Attendance.class, attendance.getIdattendance());
            Shift shiftIdshiftOld = persistentAttendance.getShiftIdshift();
            Shift shiftIdshiftNew = attendance.getShiftIdshift();
            if (shiftIdshiftNew != null) {
                shiftIdshiftNew = em.getReference(shiftIdshiftNew.getClass(), shiftIdshiftNew.getIdshift());
                attendance.setShiftIdshift(shiftIdshiftNew);
            }
            attendance = em.merge(attendance);
            if (shiftIdshiftOld != null && !shiftIdshiftOld.equals(shiftIdshiftNew)) {
                shiftIdshiftOld.getAttendanceList().remove(attendance);
                shiftIdshiftOld = em.merge(shiftIdshiftOld);
            }
            if (shiftIdshiftNew != null && !shiftIdshiftNew.equals(shiftIdshiftOld)) {
                shiftIdshiftNew.getAttendanceList().add(attendance);
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
                Integer id = attendance.getIdattendance();
                if (findAttendance(id) == null) {
                    throw new NonexistentEntityException("The attendance with id " + id + " no longer exists.");
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
            Attendance attendance;
            try {
                attendance = em.getReference(Attendance.class, id);
                attendance.getIdattendance();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The attendance with id " + id + " no longer exists.", enfe);
            }
            Shift shiftIdshift = attendance.getShiftIdshift();
            if (shiftIdshift != null) {
                shiftIdshift.getAttendanceList().remove(attendance);
                shiftIdshift = em.merge(shiftIdshift);
            }
            em.remove(attendance);
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

    public List<Attendance> findAttendanceEntities() {
        return findAttendanceEntities(true, -1, -1);
    }

    public List<Attendance> findAttendanceEntities(int maxResults, int firstResult) {
        return findAttendanceEntities(false, maxResults, firstResult);
    }

    private List<Attendance> findAttendanceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Attendance.class));
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

    public Attendance findAttendance(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Attendance.class, id);
        } finally {
            em.close();
        }
    }

    public int getAttendanceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Attendance> rt = cq.from(Attendance.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Attendance> findAttendanceByShift(Shift shift) {

        EntityManager em = getEntityManager();
        return em.createNamedQuery("Attendance.findByShift").setParameter("shift", shift).getResultList();
    }
    
    public List<Attendance> findAttendanceByDate(String clockIn) {

        EntityManager em = getEntityManager();
        return em.createNamedQuery("Attendance.findByDate").setParameter("clockIn", clockIn).getResultList();
    }

}
