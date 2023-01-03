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
import lk.java.entity.Tax;

/**
 *
 * @author Rajitha Yasasri
 */
public class TaxJpaController implements Serializable {

    public TaxJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tax tax) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Employee employeeIdemployee = tax.getEmployeeIdemployee();
            if (employeeIdemployee != null) {
                employeeIdemployee = em.getReference(employeeIdemployee.getClass(), employeeIdemployee.getIdemployee());
                tax.setEmployeeIdemployee(employeeIdemployee);
            }
            em.persist(tax);
            if (employeeIdemployee != null) {
                employeeIdemployee.getTaxList().add(tax);
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

    public void edit(Tax tax) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Tax persistentTax = em.find(Tax.class, tax.getIdtax());
            Employee employeeIdemployeeOld = persistentTax.getEmployeeIdemployee();
            Employee employeeIdemployeeNew = tax.getEmployeeIdemployee();
            if (employeeIdemployeeNew != null) {
                employeeIdemployeeNew = em.getReference(employeeIdemployeeNew.getClass(), employeeIdemployeeNew.getIdemployee());
                tax.setEmployeeIdemployee(employeeIdemployeeNew);
            }
            tax = em.merge(tax);
            if (employeeIdemployeeOld != null && !employeeIdemployeeOld.equals(employeeIdemployeeNew)) {
                employeeIdemployeeOld.getTaxList().remove(tax);
                employeeIdemployeeOld = em.merge(employeeIdemployeeOld);
            }
            if (employeeIdemployeeNew != null && !employeeIdemployeeNew.equals(employeeIdemployeeOld)) {
                employeeIdemployeeNew.getTaxList().add(tax);
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
                Integer id = tax.getIdtax();
                if (findTax(id) == null) {
                    throw new NonexistentEntityException("The tax with id " + id + " no longer exists.");
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
            Tax tax;
            try {
                tax = em.getReference(Tax.class, id);
                tax.getIdtax();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tax with id " + id + " no longer exists.", enfe);
            }
            Employee employeeIdemployee = tax.getEmployeeIdemployee();
            if (employeeIdemployee != null) {
                employeeIdemployee.getTaxList().remove(tax);
                employeeIdemployee = em.merge(employeeIdemployee);
            }
            em.remove(tax);
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

    public List<Tax> findTaxEntities() {
        return findTaxEntities(true, -1, -1);
    }

    public List<Tax> findTaxEntities(int maxResults, int firstResult) {
        return findTaxEntities(false, maxResults, firstResult);
    }

    private List<Tax> findTaxEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tax.class));
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

    public Tax findTax(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tax.class, id);
        } finally {
            em.close();
        }
    }

    public int getTaxCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tax> rt = cq.from(Tax.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
     public List<Tax> findTaxByTaxNo(String taxNo) {
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Tax.findByIncomeTaxNo").setParameter("incomeTaxNo", "%" + taxNo + "%").getResultList();
    }
}
