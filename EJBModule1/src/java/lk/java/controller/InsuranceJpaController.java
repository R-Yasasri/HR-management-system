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
import lk.java.entity.Insurance;

/**
 *
 * @author Rajitha Yasasri
 */
public class InsuranceJpaController implements Serializable {

    public InsuranceJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Insurance insurance) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Employee employeeIdemployee = insurance.getEmployeeIdemployee();
            if (employeeIdemployee != null) {
                employeeIdemployee = em.getReference(employeeIdemployee.getClass(), employeeIdemployee.getIdemployee());
                insurance.setEmployeeIdemployee(employeeIdemployee);
            }
            em.persist(insurance);
            if (employeeIdemployee != null) {
                employeeIdemployee.getInsuranceList().add(insurance);
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

    public void edit(Insurance insurance) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Insurance persistentInsurance = em.find(Insurance.class, insurance.getIdinsurance());
            Employee employeeIdemployeeOld = persistentInsurance.getEmployeeIdemployee();
            Employee employeeIdemployeeNew = insurance.getEmployeeIdemployee();
            if (employeeIdemployeeNew != null) {
                employeeIdemployeeNew = em.getReference(employeeIdemployeeNew.getClass(), employeeIdemployeeNew.getIdemployee());
                insurance.setEmployeeIdemployee(employeeIdemployeeNew);
            }
            insurance = em.merge(insurance);
            if (employeeIdemployeeOld != null && !employeeIdemployeeOld.equals(employeeIdemployeeNew)) {
                employeeIdemployeeOld.getInsuranceList().remove(insurance);
                employeeIdemployeeOld = em.merge(employeeIdemployeeOld);
            }
            if (employeeIdemployeeNew != null && !employeeIdemployeeNew.equals(employeeIdemployeeOld)) {
                employeeIdemployeeNew.getInsuranceList().add(insurance);
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
                Integer id = insurance.getIdinsurance();
                if (findInsurance(id) == null) {
                    throw new NonexistentEntityException("The insurance with id " + id + " no longer exists.");
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
            Insurance insurance;
            try {
                insurance = em.getReference(Insurance.class, id);
                insurance.getIdinsurance();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The insurance with id " + id + " no longer exists.", enfe);
            }
            Employee employeeIdemployee = insurance.getEmployeeIdemployee();
            if (employeeIdemployee != null) {
                employeeIdemployee.getInsuranceList().remove(insurance);
                employeeIdemployee = em.merge(employeeIdemployee);
            }
            em.remove(insurance);
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

    public List<Insurance> findInsuranceEntities() {
        return findInsuranceEntities(true, -1, -1);
    }

    public List<Insurance> findInsuranceEntities(int maxResults, int firstResult) {
        return findInsuranceEntities(false, maxResults, firstResult);
    }

    private List<Insurance> findInsuranceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Insurance.class));
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

    public Insurance findInsurance(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Insurance.class, id);
        } finally {
            em.close();
        }
    }

    public int getInsuranceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Insurance> rt = cq.from(Insurance.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public List<Insurance> findInsuranceByProvider(String provider) {
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Insurance.findByProvider").setParameter("provider", "%" + provider + "%").getResultList();
    }
}
