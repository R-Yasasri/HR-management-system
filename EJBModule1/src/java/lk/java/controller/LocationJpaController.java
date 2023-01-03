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
import lk.java.entity.Location;

/**
 *
 * @author Rajitha Yasasri
 */
public class LocationJpaController implements Serializable {

    public LocationJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Location location) throws RollbackFailureException, Exception {
        if (location.getShiftList() == null) {
            location.setShiftList(new ArrayList<Shift>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Shift> attachedShiftList = new ArrayList<Shift>();
            for (Shift shiftListShiftToAttach : location.getShiftList()) {
                shiftListShiftToAttach = em.getReference(shiftListShiftToAttach.getClass(), shiftListShiftToAttach.getIdshift());
                attachedShiftList.add(shiftListShiftToAttach);
            }
            location.setShiftList(attachedShiftList);
            em.persist(location);
            for (Shift shiftListShift : location.getShiftList()) {
                Location oldLocationIdlocationOfShiftListShift = shiftListShift.getLocationIdlocation();
                shiftListShift.setLocationIdlocation(location);
                shiftListShift = em.merge(shiftListShift);
                if (oldLocationIdlocationOfShiftListShift != null) {
                    oldLocationIdlocationOfShiftListShift.getShiftList().remove(shiftListShift);
                    oldLocationIdlocationOfShiftListShift = em.merge(oldLocationIdlocationOfShiftListShift);
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

    public void edit(Location location) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Location persistentLocation = em.find(Location.class, location.getIdlocation());
            List<Shift> shiftListOld = persistentLocation.getShiftList();
            List<Shift> shiftListNew = location.getShiftList();
            List<String> illegalOrphanMessages = null;
            for (Shift shiftListOldShift : shiftListOld) {
                if (!shiftListNew.contains(shiftListOldShift)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Shift " + shiftListOldShift + " since its locationIdlocation field is not nullable.");
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
            location.setShiftList(shiftListNew);
            location = em.merge(location);
            for (Shift shiftListNewShift : shiftListNew) {
                if (!shiftListOld.contains(shiftListNewShift)) {
                    Location oldLocationIdlocationOfShiftListNewShift = shiftListNewShift.getLocationIdlocation();
                    shiftListNewShift.setLocationIdlocation(location);
                    shiftListNewShift = em.merge(shiftListNewShift);
                    if (oldLocationIdlocationOfShiftListNewShift != null && !oldLocationIdlocationOfShiftListNewShift.equals(location)) {
                        oldLocationIdlocationOfShiftListNewShift.getShiftList().remove(shiftListNewShift);
                        oldLocationIdlocationOfShiftListNewShift = em.merge(oldLocationIdlocationOfShiftListNewShift);
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
                Integer id = location.getIdlocation();
                if (findLocation(id) == null) {
                    throw new NonexistentEntityException("The location with id " + id + " no longer exists.");
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
            Location location;
            try {
                location = em.getReference(Location.class, id);
                location.getIdlocation();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The location with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Shift> shiftListOrphanCheck = location.getShiftList();
            for (Shift shiftListOrphanCheckShift : shiftListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Location (" + location + ") cannot be destroyed since the Shift " + shiftListOrphanCheckShift + " in its shiftList field has a non-nullable locationIdlocation field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(location);
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

    public List<Location> findLocationEntities() {
        return findLocationEntities(true, -1, -1);
    }

    public List<Location> findLocationEntities(int maxResults, int firstResult) {
        return findLocationEntities(false, maxResults, firstResult);
    }

    private List<Location> findLocationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Location.class));
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

    public Location findLocation(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Location.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Location> rt = cq.from(Location.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
