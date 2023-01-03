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
import lk.java.controller.exceptions.PreexistingEntityException;
import lk.java.controller.exceptions.RollbackFailureException;
import lk.java.entity.Department;

/**
 *
 * @author Rajitha Yasasri
 */
public class DepartmentJpaController implements Serializable {

    public DepartmentJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Department department) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (department.getShiftList() == null) {
            department.setShiftList(new ArrayList<Shift>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Shift> attachedShiftList = new ArrayList<Shift>();
            for (Shift shiftListShiftToAttach : department.getShiftList()) {
                shiftListShiftToAttach = em.getReference(shiftListShiftToAttach.getClass(), shiftListShiftToAttach.getIdshift());
                attachedShiftList.add(shiftListShiftToAttach);
            }
            department.setShiftList(attachedShiftList);
            em.persist(department);
            for (Shift shiftListShift : department.getShiftList()) {
                Department oldDepartmentIddepartmentOfShiftListShift = shiftListShift.getDepartmentIddepartment();
                shiftListShift.setDepartmentIddepartment(department);
                shiftListShift = em.merge(shiftListShift);
                if (oldDepartmentIddepartmentOfShiftListShift != null) {
                    oldDepartmentIddepartmentOfShiftListShift.getShiftList().remove(shiftListShift);
                    oldDepartmentIddepartmentOfShiftListShift = em.merge(oldDepartmentIddepartmentOfShiftListShift);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findDepartment(department.getIddepartment()) != null) {
                throw new PreexistingEntityException("Department " + department + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Department department) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Department persistentDepartment = em.find(Department.class, department.getIddepartment());
            List<Shift> shiftListOld = persistentDepartment.getShiftList();
            List<Shift> shiftListNew = department.getShiftList();
            List<String> illegalOrphanMessages = null;
            for (Shift shiftListOldShift : shiftListOld) {
                if (!shiftListNew.contains(shiftListOldShift)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Shift " + shiftListOldShift + " since its departmentIddepartment field is not nullable.");
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
            department.setShiftList(shiftListNew);
            department = em.merge(department);
            for (Shift shiftListNewShift : shiftListNew) {
                if (!shiftListOld.contains(shiftListNewShift)) {
                    Department oldDepartmentIddepartmentOfShiftListNewShift = shiftListNewShift.getDepartmentIddepartment();
                    shiftListNewShift.setDepartmentIddepartment(department);
                    shiftListNewShift = em.merge(shiftListNewShift);
                    if (oldDepartmentIddepartmentOfShiftListNewShift != null && !oldDepartmentIddepartmentOfShiftListNewShift.equals(department)) {
                        oldDepartmentIddepartmentOfShiftListNewShift.getShiftList().remove(shiftListNewShift);
                        oldDepartmentIddepartmentOfShiftListNewShift = em.merge(oldDepartmentIddepartmentOfShiftListNewShift);
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
                Integer id = department.getIddepartment();
                if (findDepartment(id) == null) {
                    throw new NonexistentEntityException("The department with id " + id + " no longer exists.");
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
            Department department;
            try {
                department = em.getReference(Department.class, id);
                department.getIddepartment();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The department with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Shift> shiftListOrphanCheck = department.getShiftList();
            for (Shift shiftListOrphanCheckShift : shiftListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Department (" + department + ") cannot be destroyed since the Shift " + shiftListOrphanCheckShift + " in its shiftList field has a non-nullable departmentIddepartment field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(department);
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

    public List<Department> findDepartmentEntities() {
        return findDepartmentEntities(true, -1, -1);
    }

    public List<Department> findDepartmentEntities(int maxResults, int firstResult) {
        return findDepartmentEntities(false, maxResults, firstResult);
    }

    private List<Department> findDepartmentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Department.class));
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

    public Department findDepartment(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Department.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartmentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Department> rt = cq.from(Department.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Department> findDepartmentByName(String name){
        
        EntityManager em = getEntityManager();
        return em.createNamedQuery("Department.findByName").setParameter("name", "%" + name + "%").getResultList();
    }
}
