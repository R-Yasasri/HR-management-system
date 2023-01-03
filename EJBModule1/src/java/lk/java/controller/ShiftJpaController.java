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
import lk.java.entity.Department;
import lk.java.entity.Employee;
import lk.java.entity.Location;
import lk.java.entity.Project;
import lk.java.entity.Leave;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import lk.java.controller.exceptions.IllegalOrphanException;
import lk.java.controller.exceptions.NonexistentEntityException;
import lk.java.controller.exceptions.RollbackFailureException;
import lk.java.entity.Attendance;
import lk.java.entity.Shift;

/**
 *
 * @author Rajitha Yasasri
 */
public class ShiftJpaController implements Serializable {

    public ShiftJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Shift shift) throws RollbackFailureException, Exception {
        if (shift.getLeaveList() == null) {
            shift.setLeaveList(new ArrayList<Leave>());
        }
        if (shift.getAttendanceList() == null) {
            shift.setAttendanceList(new ArrayList<Attendance>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Department departmentIddepartment = shift.getDepartmentIddepartment();
            if (departmentIddepartment != null) {
                departmentIddepartment = em.getReference(departmentIddepartment.getClass(), departmentIddepartment.getIddepartment());
                shift.setDepartmentIddepartment(departmentIddepartment);
            }
            Employee employeeIdemployee = shift.getEmployeeIdemployee();
            if (employeeIdemployee != null) {
                employeeIdemployee = em.getReference(employeeIdemployee.getClass(), employeeIdemployee.getIdemployee());
                shift.setEmployeeIdemployee(employeeIdemployee);
            }
            Location locationIdlocation = shift.getLocationIdlocation();
            if (locationIdlocation != null) {
                locationIdlocation = em.getReference(locationIdlocation.getClass(), locationIdlocation.getIdlocation());
                shift.setLocationIdlocation(locationIdlocation);
            }
            Project projectIdproject = shift.getProjectIdproject();
            if (projectIdproject != null) {
                projectIdproject = em.getReference(projectIdproject.getClass(), projectIdproject.getIdproject());
                shift.setProjectIdproject(projectIdproject);
            }
            List<Leave> attachedLeaveList = new ArrayList<Leave>();
            for (Leave leaveListLeaveToAttach : shift.getLeaveList()) {
                leaveListLeaveToAttach = em.getReference(leaveListLeaveToAttach.getClass(), leaveListLeaveToAttach.getIdleave());
                attachedLeaveList.add(leaveListLeaveToAttach);
            }
            shift.setLeaveList(attachedLeaveList);
            List<Attendance> attachedAttendanceList = new ArrayList<Attendance>();
            for (Attendance attendanceListAttendanceToAttach : shift.getAttendanceList()) {
                attendanceListAttendanceToAttach = em.getReference(attendanceListAttendanceToAttach.getClass(), attendanceListAttendanceToAttach.getIdattendance());
                attachedAttendanceList.add(attendanceListAttendanceToAttach);
            }
            shift.setAttendanceList(attachedAttendanceList);
            em.persist(shift);
            if (departmentIddepartment != null) {
                departmentIddepartment.getShiftList().add(shift);
                departmentIddepartment = em.merge(departmentIddepartment);
            }
            if (employeeIdemployee != null) {
                employeeIdemployee.getShiftList().add(shift);
                employeeIdemployee = em.merge(employeeIdemployee);
            }
            if (locationIdlocation != null) {
                locationIdlocation.getShiftList().add(shift);
                locationIdlocation = em.merge(locationIdlocation);
            }
            if (projectIdproject != null) {
                projectIdproject.getShiftList().add(shift);
                projectIdproject = em.merge(projectIdproject);
            }
            for (Leave leaveListLeave : shift.getLeaveList()) {
                Shift oldShiftIdshiftOfLeaveListLeave = leaveListLeave.getShiftIdshift();
                leaveListLeave.setShiftIdshift(shift);
                leaveListLeave = em.merge(leaveListLeave);
                if (oldShiftIdshiftOfLeaveListLeave != null) {
                    oldShiftIdshiftOfLeaveListLeave.getLeaveList().remove(leaveListLeave);
                    oldShiftIdshiftOfLeaveListLeave = em.merge(oldShiftIdshiftOfLeaveListLeave);
                }
            }
            for (Attendance attendanceListAttendance : shift.getAttendanceList()) {
                Shift oldShiftIdshiftOfAttendanceListAttendance = attendanceListAttendance.getShiftIdshift();
                attendanceListAttendance.setShiftIdshift(shift);
                attendanceListAttendance = em.merge(attendanceListAttendance);
                if (oldShiftIdshiftOfAttendanceListAttendance != null) {
                    oldShiftIdshiftOfAttendanceListAttendance.getAttendanceList().remove(attendanceListAttendance);
                    oldShiftIdshiftOfAttendanceListAttendance = em.merge(oldShiftIdshiftOfAttendanceListAttendance);
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

    public void edit(Shift shift) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Shift persistentShift = em.find(Shift.class, shift.getIdshift());
            Department departmentIddepartmentOld = persistentShift.getDepartmentIddepartment();
            Department departmentIddepartmentNew = shift.getDepartmentIddepartment();
            Employee employeeIdemployeeOld = persistentShift.getEmployeeIdemployee();
            Employee employeeIdemployeeNew = shift.getEmployeeIdemployee();
            Location locationIdlocationOld = persistentShift.getLocationIdlocation();
            Location locationIdlocationNew = shift.getLocationIdlocation();
            Project projectIdprojectOld = persistentShift.getProjectIdproject();
            Project projectIdprojectNew = shift.getProjectIdproject();
            List<Leave> leaveListOld = persistentShift.getLeaveList();
            List<Leave> leaveListNew = shift.getLeaveList();
            List<Attendance> attendanceListOld = persistentShift.getAttendanceList();
            List<Attendance> attendanceListNew = shift.getAttendanceList();
            List<String> illegalOrphanMessages = null;
            for (Leave leaveListOldLeave : leaveListOld) {
                if (!leaveListNew.contains(leaveListOldLeave)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Leave " + leaveListOldLeave + " since its shiftIdshift field is not nullable.");
                }
            }
            for (Attendance attendanceListOldAttendance : attendanceListOld) {
                if (!attendanceListNew.contains(attendanceListOldAttendance)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Attendance " + attendanceListOldAttendance + " since its shiftIdshift field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (departmentIddepartmentNew != null) {
                departmentIddepartmentNew = em.getReference(departmentIddepartmentNew.getClass(), departmentIddepartmentNew.getIddepartment());
                shift.setDepartmentIddepartment(departmentIddepartmentNew);
            }
            if (employeeIdemployeeNew != null) {
                employeeIdemployeeNew = em.getReference(employeeIdemployeeNew.getClass(), employeeIdemployeeNew.getIdemployee());
                shift.setEmployeeIdemployee(employeeIdemployeeNew);
            }
            if (locationIdlocationNew != null) {
                locationIdlocationNew = em.getReference(locationIdlocationNew.getClass(), locationIdlocationNew.getIdlocation());
                shift.setLocationIdlocation(locationIdlocationNew);
            }
            if (projectIdprojectNew != null) {
                projectIdprojectNew = em.getReference(projectIdprojectNew.getClass(), projectIdprojectNew.getIdproject());
                shift.setProjectIdproject(projectIdprojectNew);
            }
            List<Leave> attachedLeaveListNew = new ArrayList<Leave>();
            for (Leave leaveListNewLeaveToAttach : leaveListNew) {
                leaveListNewLeaveToAttach = em.getReference(leaveListNewLeaveToAttach.getClass(), leaveListNewLeaveToAttach.getIdleave());
                attachedLeaveListNew.add(leaveListNewLeaveToAttach);
            }
            leaveListNew = attachedLeaveListNew;
            shift.setLeaveList(leaveListNew);
            List<Attendance> attachedAttendanceListNew = new ArrayList<Attendance>();
            for (Attendance attendanceListNewAttendanceToAttach : attendanceListNew) {
                attendanceListNewAttendanceToAttach = em.getReference(attendanceListNewAttendanceToAttach.getClass(), attendanceListNewAttendanceToAttach.getIdattendance());
                attachedAttendanceListNew.add(attendanceListNewAttendanceToAttach);
            }
            attendanceListNew = attachedAttendanceListNew;
            shift.setAttendanceList(attendanceListNew);
            shift = em.merge(shift);
            if (departmentIddepartmentOld != null && !departmentIddepartmentOld.equals(departmentIddepartmentNew)) {
                departmentIddepartmentOld.getShiftList().remove(shift);
                departmentIddepartmentOld = em.merge(departmentIddepartmentOld);
            }
            if (departmentIddepartmentNew != null && !departmentIddepartmentNew.equals(departmentIddepartmentOld)) {
                departmentIddepartmentNew.getShiftList().add(shift);
                departmentIddepartmentNew = em.merge(departmentIddepartmentNew);
            }
            if (employeeIdemployeeOld != null && !employeeIdemployeeOld.equals(employeeIdemployeeNew)) {
                employeeIdemployeeOld.getShiftList().remove(shift);
                employeeIdemployeeOld = em.merge(employeeIdemployeeOld);
            }
            if (employeeIdemployeeNew != null && !employeeIdemployeeNew.equals(employeeIdemployeeOld)) {
                employeeIdemployeeNew.getShiftList().add(shift);
                employeeIdemployeeNew = em.merge(employeeIdemployeeNew);
            }
            if (locationIdlocationOld != null && !locationIdlocationOld.equals(locationIdlocationNew)) {
                locationIdlocationOld.getShiftList().remove(shift);
                locationIdlocationOld = em.merge(locationIdlocationOld);
            }
            if (locationIdlocationNew != null && !locationIdlocationNew.equals(locationIdlocationOld)) {
                locationIdlocationNew.getShiftList().add(shift);
                locationIdlocationNew = em.merge(locationIdlocationNew);
            }
            if (projectIdprojectOld != null && !projectIdprojectOld.equals(projectIdprojectNew)) {
                projectIdprojectOld.getShiftList().remove(shift);
                projectIdprojectOld = em.merge(projectIdprojectOld);
            }
            if (projectIdprojectNew != null && !projectIdprojectNew.equals(projectIdprojectOld)) {
                projectIdprojectNew.getShiftList().add(shift);
                projectIdprojectNew = em.merge(projectIdprojectNew);
            }
            for (Leave leaveListNewLeave : leaveListNew) {
                if (!leaveListOld.contains(leaveListNewLeave)) {
                    Shift oldShiftIdshiftOfLeaveListNewLeave = leaveListNewLeave.getShiftIdshift();
                    leaveListNewLeave.setShiftIdshift(shift);
                    leaveListNewLeave = em.merge(leaveListNewLeave);
                    if (oldShiftIdshiftOfLeaveListNewLeave != null && !oldShiftIdshiftOfLeaveListNewLeave.equals(shift)) {
                        oldShiftIdshiftOfLeaveListNewLeave.getLeaveList().remove(leaveListNewLeave);
                        oldShiftIdshiftOfLeaveListNewLeave = em.merge(oldShiftIdshiftOfLeaveListNewLeave);
                    }
                }
            }
            for (Attendance attendanceListNewAttendance : attendanceListNew) {
                if (!attendanceListOld.contains(attendanceListNewAttendance)) {
                    Shift oldShiftIdshiftOfAttendanceListNewAttendance = attendanceListNewAttendance.getShiftIdshift();
                    attendanceListNewAttendance.setShiftIdshift(shift);
                    attendanceListNewAttendance = em.merge(attendanceListNewAttendance);
                    if (oldShiftIdshiftOfAttendanceListNewAttendance != null && !oldShiftIdshiftOfAttendanceListNewAttendance.equals(shift)) {
                        oldShiftIdshiftOfAttendanceListNewAttendance.getAttendanceList().remove(attendanceListNewAttendance);
                        oldShiftIdshiftOfAttendanceListNewAttendance = em.merge(oldShiftIdshiftOfAttendanceListNewAttendance);
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
                Integer id = shift.getIdshift();
                if (findShift(id) == null) {
                    throw new NonexistentEntityException("The shift with id " + id + " no longer exists.");
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
            Shift shift;
            try {
                shift = em.getReference(Shift.class, id);
                shift.getIdshift();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The shift with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Leave> leaveListOrphanCheck = shift.getLeaveList();
            for (Leave leaveListOrphanCheckLeave : leaveListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Shift (" + shift + ") cannot be destroyed since the Leave " + leaveListOrphanCheckLeave + " in its leaveList field has a non-nullable shiftIdshift field.");
            }
            List<Attendance> attendanceListOrphanCheck = shift.getAttendanceList();
            for (Attendance attendanceListOrphanCheckAttendance : attendanceListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Shift (" + shift + ") cannot be destroyed since the Attendance " + attendanceListOrphanCheckAttendance + " in its attendanceList field has a non-nullable shiftIdshift field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Department departmentIddepartment = shift.getDepartmentIddepartment();
            if (departmentIddepartment != null) {
                departmentIddepartment.getShiftList().remove(shift);
                departmentIddepartment = em.merge(departmentIddepartment);
            }
            Employee employeeIdemployee = shift.getEmployeeIdemployee();
            if (employeeIdemployee != null) {
                employeeIdemployee.getShiftList().remove(shift);
                employeeIdemployee = em.merge(employeeIdemployee);
            }
            Location locationIdlocation = shift.getLocationIdlocation();
            if (locationIdlocation != null) {
                locationIdlocation.getShiftList().remove(shift);
                locationIdlocation = em.merge(locationIdlocation);
            }
            Project projectIdproject = shift.getProjectIdproject();
            if (projectIdproject != null) {
                projectIdproject.getShiftList().remove(shift);
                projectIdproject = em.merge(projectIdproject);
            }
            em.remove(shift);
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

    public List<Shift> findShiftEntities() {
        return findShiftEntities(true, -1, -1);
    }

    public List<Shift> findShiftEntities(int maxResults, int firstResult) {
        return findShiftEntities(false, maxResults, firstResult);
    }

    private List<Shift> findShiftEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Shift.class));
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

    public Shift findShift(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Shift.class, id);
        } finally {
            em.close();
        }
    }

    public int getShiftCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Shift> rt = cq.from(Shift.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
