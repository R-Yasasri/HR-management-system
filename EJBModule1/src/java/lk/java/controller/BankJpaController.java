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
import lk.java.entity.Bank;
import lk.java.entity.Employee;

/**
 *
 * @author Rajitha Yasasri
 */
public class BankJpaController implements Serializable {

    public BankJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Bank bank) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Employee employeeIdemployee = bank.getEmployeeIdemployee();
            if (employeeIdemployee != null) {
                employeeIdemployee = em.getReference(employeeIdemployee.getClass(), employeeIdemployee.getIdemployee());
                bank.setEmployeeIdemployee(employeeIdemployee);
            }
            em.persist(bank);
            if (employeeIdemployee != null) {
                employeeIdemployee.getBankList().add(bank);
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

    public void edit(Bank bank) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Bank persistentBank = em.find(Bank.class, bank.getIdbank());
            Employee employeeIdemployeeOld = persistentBank.getEmployeeIdemployee();
            Employee employeeIdemployeeNew = bank.getEmployeeIdemployee();
            if (employeeIdemployeeNew != null) {
                employeeIdemployeeNew = em.getReference(employeeIdemployeeNew.getClass(), employeeIdemployeeNew.getIdemployee());
                bank.setEmployeeIdemployee(employeeIdemployeeNew);
            }
            bank = em.merge(bank);
            if (employeeIdemployeeOld != null && !employeeIdemployeeOld.equals(employeeIdemployeeNew)) {
                employeeIdemployeeOld.getBankList().remove(bank);
                employeeIdemployeeOld = em.merge(employeeIdemployeeOld);
            }
            if (employeeIdemployeeNew != null && !employeeIdemployeeNew.equals(employeeIdemployeeOld)) {
                employeeIdemployeeNew.getBankList().add(bank);
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
                Integer id = bank.getIdbank();
                if (findBank(id) == null) {
                    throw new NonexistentEntityException("The bank with id " + id + " no longer exists.");
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
            Bank bank;
            try {
                bank = em.getReference(Bank.class, id);
                bank.getIdbank();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bank with id " + id + " no longer exists.", enfe);
            }
            Employee employeeIdemployee = bank.getEmployeeIdemployee();
            if (employeeIdemployee != null) {
                employeeIdemployee.getBankList().remove(bank);
                employeeIdemployee = em.merge(employeeIdemployee);
            }
            em.remove(bank);
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

    public List<Bank> findBankEntities() {
        return findBankEntities(true, -1, -1);
    }

    public List<Bank> findBankEntities(int maxResults, int firstResult) {
        return findBankEntities(false, maxResults, firstResult);
    }

    private List<Bank> findBankEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bank.class));
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

    public Bank findBank(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bank.class, id);
        } finally {
            em.close();
        }
    }

    public int getBankCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bank> rt = cq.from(Bank.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Bank> findBankByName(String name) {
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Bank.findByName").setParameter("name", "%" + name + "%").getResultList();
    }

}
