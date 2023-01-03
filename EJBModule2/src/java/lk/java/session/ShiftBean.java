/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.session;

import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import lk.java.controller.ShiftJpaController;
import lk.java.dto.ShiftDTO;
import lk.java.entity.Department;
import lk.java.entity.Employee;
import lk.java.entity.Location;
import lk.java.entity.Project;
import lk.java.entity.Shift;
import lk.java.interceptors.ShiftInterceptor;

/**
 *
 * @author Rajitha Yasasri
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@Interceptors(ShiftInterceptor.class)
public class ShiftBean implements ShiftBeanRemote, ShiftBeanLocal {

    @EJB
    private SendMailBeanRemote sendMailBean;

    @EJB
    private LocationBeanLocal locationBean;

    @EJB
    private ProjectBeanLocal projectBean;

    @EJB
    private DepartmentBeanLocal departmentBean;

    @EJB
    private EmployeeBeanRemote employeeBean;

    @Resource
    UserTransaction utx;

    @Override
    public void create(String empId, String start, String finish, String leave, String payment, String deptId, String projId, String locId) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        Shift s = new Shift();

        try {

            ShiftJpaController sjc = new ShiftJpaController(utx, emf);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(start);
            Date finishDate = sdf.parse(finish);

            Integer leaveDays = Integer.parseInt(leave);

            Double paymentPerHour = Double.parseDouble(payment);

            s.setAllocatedLeaveDays(leaveDays);
            s.setFinishDate(finishDate);
            s.setStartDate(startDate);
            s.setPaymentPerHour(paymentPerHour);

            Department department = departmentBean.searchByIdLocal(deptId);
            s.setDepartmentIddepartment(department);

            String empString = employeeBean.searchById(empId);

            Gson gson = new Gson();
            Employee employee = gson.fromJson(empString, Employee.class);

            s.setEmployeeIdemployee(employee);

            Project project = projectBean.searchProjectByIdLocal(projId);
            s.setProjectIdproject(project);

            Location location = locationBean.searchLocationByIdLocal(locId);
            s.setLocationIdlocation(location);

            sjc.create(s);

            String address = location.getNumber() + "," + location.getStreet() + "," + location.getCity();
            alertEmployee(employee.getEmail(), employee.getName(), start, finish, leave, payment, department.getName(), project.getName(), address);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Shift searchShiftByIdLocal(String id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        Shift shift = null;
        try {
            Integer i = Integer.parseInt(id);

            ShiftJpaController sjc = new ShiftJpaController(utx, emf);
            shift = sjc.findShift(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shift;
    }

    @Override
    public void edit(String shiftId, String empId, String start, String finish, String leave, String payment, String deptId, String projId, String locId) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        ShiftJpaController sjc = new ShiftJpaController(utx, emf);

        try {

            Integer i = Integer.parseInt(shiftId);
            Shift s = sjc.findShift(i);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(start);
            Date finishDate = sdf.parse(finish);

            Integer leaveDays = Integer.parseInt(leave);

            Double paymentPerHour = Double.parseDouble(payment);

            s.setAllocatedLeaveDays(leaveDays);
            s.setFinishDate(finishDate);
            s.setStartDate(startDate);
            s.setPaymentPerHour(paymentPerHour);

            Department department = departmentBean.searchByIdLocal(deptId);
            s.setDepartmentIddepartment(department);

            String empString = employeeBean.searchById(empId);

            Gson gson = new Gson();
            Employee employee = gson.fromJson(empString, Employee.class);

            s.setEmployeeIdemployee(employee);

            Project project = projectBean.searchProjectByIdLocal(projId);
            s.setProjectIdproject(project);

            Location location = locationBean.searchLocationByIdLocal(locId);
            s.setLocationIdlocation(location);

            sjc.edit(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        ShiftJpaController sjc = new ShiftJpaController(utx, emf);

        try {

            Integer i = Integer.parseInt(id);
            sjc.destroy(i);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String load() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        ShiftJpaController sjc = new ShiftJpaController(utx, emf);

        String msg = "not found";
        try {

            List<Shift> findShiftEntities = sjc.findShiftEntities();

            List<ShiftDTO> list = new ArrayList();
            for (Shift shift : findShiftEntities) {
                ShiftDTO s = new ShiftDTO();
                s.setAllocatedLeaveDays(shift.getAllocatedLeaveDays());
                s.setDeptId(shift.getDepartmentIddepartment().getIddepartment());
                s.setEmpId(shift.getEmployeeIdemployee().getIdemployee());
                s.setFinish(shift.getFinishDate());
                s.setLocationId(shift.getLocationIdlocation().getIdlocation());
                s.setPayment(shift.getPaymentPerHour());
                s.setProjectId(shift.getProjectIdproject().getIdproject());
                s.setShiftId(shift.getIdshift());
                s.setStart(shift.getStartDate());

                list.add(s);
            }

            Gson gson = new Gson();
            msg = gson.toJson(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public String searchById(String id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        ShiftJpaController sjc = new ShiftJpaController(utx, emf);

        String msg = "not found";

        try {

            Integer i = Integer.parseInt(id);
            Shift shift = sjc.findShift(i);

            ShiftDTO s = new ShiftDTO();
            s.setAllocatedLeaveDays(shift.getAllocatedLeaveDays());
            s.setDeptId(shift.getDepartmentIddepartment().getIddepartment());
            s.setEmpId(shift.getEmployeeIdemployee().getIdemployee());
            s.setFinish(shift.getFinishDate());
            s.setLocationId(shift.getLocationIdlocation().getIdlocation());
            s.setPayment(shift.getPaymentPerHour());
            s.setProjectId(shift.getProjectIdproject().getIdproject());
            s.setShiftId(shift.getIdshift());
            s.setStart(shift.getStartDate());

            Gson gson = new Gson();
            msg = gson.toJson(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public String searchByLocation(String id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        ShiftJpaController sjc = new ShiftJpaController(utx, emf);

        String msg = "not found";

        try {

            Location location = locationBean.searchLocationByIdLocal(id);
            List<Shift> shiftList = sjc.findByLocation(location);

            List<ShiftDTO> list = new ArrayList();
            for (Shift shift : shiftList) {
                ShiftDTO s = new ShiftDTO();
                s.setAllocatedLeaveDays(shift.getAllocatedLeaveDays());
                s.setDeptId(shift.getDepartmentIddepartment().getIddepartment());
                s.setEmpId(shift.getEmployeeIdemployee().getIdemployee());
                s.setFinish(shift.getFinishDate());
                s.setLocationId(shift.getLocationIdlocation().getIdlocation());
                s.setPayment(shift.getPaymentPerHour());
                s.setProjectId(shift.getProjectIdproject().getIdproject());
                s.setShiftId(shift.getIdshift());
                s.setStart(shift.getStartDate());

                list.add(s);
            }

            Gson gson = new Gson();
            msg = gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public String searchByDepartment(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        ShiftJpaController sjc = new ShiftJpaController(utx, emf);

        String msg = "not found";

        try {

            Department dept = departmentBean.searchByIdLocal(id);
            List<Shift> shiftList = sjc.findByDeparment(dept);

            List<ShiftDTO> list = new ArrayList();
            for (Shift shift : shiftList) {
                ShiftDTO s = new ShiftDTO();
                s.setAllocatedLeaveDays(shift.getAllocatedLeaveDays());
                s.setDeptId(shift.getDepartmentIddepartment().getIddepartment());
                s.setEmpId(shift.getEmployeeIdemployee().getIdemployee());
                s.setFinish(shift.getFinishDate());
                s.setLocationId(shift.getLocationIdlocation().getIdlocation());
                s.setPayment(shift.getPaymentPerHour());
                s.setProjectId(shift.getProjectIdproject().getIdproject());
                s.setShiftId(shift.getIdshift());
                s.setStart(shift.getStartDate());

                list.add(s);
            }

            Gson gson = new Gson();
            msg = gson.toJson(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public String searchByEmployee(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        ShiftJpaController sjc = new ShiftJpaController(utx, emf);

        String msg = "not found";

        try {

            String empString = employeeBean.searchById(id);

            Gson gson = new Gson();
            Employee employee = gson.fromJson(empString, Employee.class);

            if (employee != null) {
                List<Shift> shiftList = sjc.findByEmployee(employee);
                List<ShiftDTO> list = new ArrayList();
                for (Shift shift : shiftList) {
                    ShiftDTO s = new ShiftDTO();
                    s.setAllocatedLeaveDays(shift.getAllocatedLeaveDays());
                    s.setDeptId(shift.getDepartmentIddepartment().getIddepartment());
                    s.setEmpId(shift.getEmployeeIdemployee().getIdemployee());
                    s.setFinish(shift.getFinishDate());
                    s.setLocationId(shift.getLocationIdlocation().getIdlocation());
                    s.setPayment(shift.getPaymentPerHour());
                    s.setProjectId(shift.getProjectIdproject().getIdproject());
                    s.setShiftId(shift.getIdshift());
                    s.setStart(shift.getStartDate());

                    list.add(s);
                }

                msg = gson.toJson(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public String searchByProject(String id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        ShiftJpaController sjc = new ShiftJpaController(utx, emf);

        String msg = "not found";

        try {

            Project project = projectBean.searchProjectByIdLocal(id);
            List<Shift> shiftList = sjc.findByProject(project);

            List<ShiftDTO> list = new ArrayList();
            for (Shift shift : shiftList) {
                ShiftDTO s = new ShiftDTO();
                s.setAllocatedLeaveDays(shift.getAllocatedLeaveDays());
                s.setDeptId(shift.getDepartmentIddepartment().getIddepartment());
                s.setEmpId(shift.getEmployeeIdemployee().getIdemployee());
                s.setFinish(shift.getFinishDate());
                s.setLocationId(shift.getLocationIdlocation().getIdlocation());
                s.setPayment(shift.getPaymentPerHour());
                s.setProjectId(shift.getProjectIdproject().getIdproject());
                s.setShiftId(shift.getIdshift());
                s.setStart(shift.getStartDate());

                list.add(s);
            }

            Gson gson = new Gson();
            msg = gson.toJson(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    public void alertEmployee(String email, String empName, String start, String finish, String leave, String payment, String department, String projectName, String address) {

        String content = "Dear " + empName + ", You have been assigned to " + projectName + " project at " + department + " department from " + start + " to " + finish + "\n\nlocation: " + address + "\nallocated leave days: " + leave + "\npayment: " + payment + "\n\nregards,\nManagement";

        //System.out.println(content);
        try {

            sendMailBean.sendMail(email, "Shift Assignment", content);

        } catch (NamingException | MessagingException ex) {
            ex.printStackTrace();
        }
    }
}
