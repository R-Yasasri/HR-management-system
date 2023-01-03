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
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import lk.java.controller.AttendanceJpaController;
import lk.java.dto.AttendanceDTO;
import lk.java.entity.Attendance;
import lk.java.entity.Shift;

/**
 *
 * @author Rajitha Yasasri
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AttendanceBean implements AttendanceBeanRemote, AttendanceBeanLocal {

    @EJB
    private ShiftBeanLocal shiftBean;

    @Resource
    UserTransaction utx;

    @Override
    public void create(String clockIn, String clockOut, String shift) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        AttendanceJpaController ajc = new AttendanceJpaController(utx, emf);

        try {

            Attendance a = new Attendance();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            Date cI = sdf.parse(clockIn);
            Date cO = sdf.parse(clockOut);

            a.setClockIn(cI);
            a.setClockOut(cO);

            Shift s = shiftBean.searchShiftByIdLocal(shift);
            a.setShiftIdshift(s);

            ajc.create(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(String id, String clockIn, String clockOut, String shift) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        AttendanceJpaController ajc = new AttendanceJpaController(utx, emf);

        try {

            Integer i = Integer.parseInt(id);
            Attendance a = ajc.findAttendance(i);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date cI = sdf.parse(clockIn);
            Date cO = sdf.parse(clockOut);

            a.setClockIn(cI);
            a.setClockOut(cO);

            Shift s = shiftBean.searchShiftByIdLocal(shift);
            a.setShiftIdshift(s);

            ajc.edit(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String load() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        AttendanceJpaController ajc = new AttendanceJpaController(utx, emf);

        String msg = "not found";

        try {

            Gson gson = new Gson();

            List<Attendance> attendanceList = ajc.findAttendanceEntities();

            List<AttendanceDTO> list = new ArrayList();
            for (Attendance attendance : attendanceList) {

                AttendanceDTO a = new AttendanceDTO();
                a.setAttendanceId(attendance.getIdattendance());
                a.setClockIn(attendance.getClockIn());
                a.setClockOut(attendance.getClockOut());
                a.setIdShift(attendance.getShiftIdshift().getIdshift());

                list.add(a);
            }

            msg = gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public void delete(String id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        AttendanceJpaController ajc = new AttendanceJpaController(utx, emf);

        try {

            Integer i = Integer.parseInt(id);
            ajc.destroy(i);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String searchById(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        AttendanceJpaController ajc = new AttendanceJpaController(utx, emf);

        String msg = "not found";
        try {

            Integer i = Integer.parseInt(id);
            Attendance attendance = ajc.findAttendance(i);

            AttendanceDTO a = new AttendanceDTO();
            a.setAttendanceId(attendance.getIdattendance());
            a.setClockIn(attendance.getClockIn());
            a.setClockOut(attendance.getClockOut());
            a.setIdShift(attendance.getShiftIdshift().getIdshift());

            Gson gson = new Gson();
            msg = gson.toJson(a);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public String searchByShift(String shift) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        AttendanceJpaController ajc = new AttendanceJpaController(utx, emf);

        String msg = "not found";

        try {

            Shift searchShiftByIdLocal = shiftBean.searchShiftByIdLocal(shift);
            List<Attendance> attendanceList = ajc.findAttendanceByShift(searchShiftByIdLocal);

            List<AttendanceDTO> list = new ArrayList();
            for (Attendance attendance : attendanceList) {

                AttendanceDTO a = new AttendanceDTO();
                a.setAttendanceId(attendance.getIdattendance());
                a.setClockIn(attendance.getClockIn());
                a.setClockOut(attendance.getClockOut());
                a.setIdShift(attendance.getShiftIdshift().getIdshift());

                list.add(a);
            }

            Gson gson = new Gson();
            msg = gson.toJson(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public List<Attendance> getAttendanceByDate(Date date) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        AttendanceJpaController ajc = new AttendanceJpaController(utx, emf);

        List<Attendance> list = null;
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String today = sdf.format(date);

            list = ajc.findAttendanceByDate(today);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
