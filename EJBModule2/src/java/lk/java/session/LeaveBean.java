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
import lk.java.controller.LeaveJpaController;
import lk.java.dto.LeaveDTO;
import lk.java.entity.Leave;
import lk.java.entity.Shift;

/**
 *
 * @author Rajitha Yasasri
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class LeaveBean implements LeaveBeanRemote, LeaveBeanLocal {

    @EJB
    private ShiftBeanLocal shiftBean;

    @Resource
    UserTransaction utx;

    @Override
    public void create(String type, String begin, String finish, String shiftId) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        try {

            Shift shift = shiftBean.searchShiftByIdLocal(shiftId);

            Leave l = new Leave();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date b = sdf.parse(begin);
            Date f = sdf.parse(finish);

            l.setBeginDate(b);
            l.setFinishDate(f);
            l.setType(type);
            l.setShiftIdshift(shift);

            LeaveJpaController ljc = new LeaveJpaController(utx, emf);
            ljc.create(l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(String id, String type, String begin, String finish, String shiftId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");

        try {

            Integer i = Integer.parseInt(id);

            Shift shift = shiftBean.searchShiftByIdLocal(shiftId);

            LeaveJpaController ljc = new LeaveJpaController(utx, emf);

            Leave l = ljc.findLeave(i);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date b = sdf.parse(begin);
            Date f = sdf.parse(finish);

            l.setBeginDate(b);
            l.setFinishDate(f);
            l.setType(type);
            l.setShiftIdshift(shift);

            ljc.edit(l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String load() {

        String msg = "not found";

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        LeaveJpaController ljc = new LeaveJpaController(utx, emf);

        try {

            Gson gson = new Gson();

            List<Leave> findLeaveEntities = ljc.findLeaveEntities();

            List<LeaveDTO> list = new ArrayList();
            for (Leave leave : findLeaveEntities) {

                LeaveDTO s = new LeaveDTO();

                s.setBegin(leave.getBeginDate());
                s.setFinish(leave.getFinishDate());
                s.setId(leave.getIdleave());
                s.setShiftId(leave.getShiftIdshift().getIdshift());
                s.setType(leave.getType());

                list.add(s);
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
        LeaveJpaController ljc = new LeaveJpaController(utx, emf);

        try {

            Integer i = Integer.parseInt(id);

            ljc.destroy(i);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String searchLeaveById(String id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        LeaveJpaController ljc = new LeaveJpaController(utx, emf);

        String msg = "not found";
        try {

            Integer i = Integer.parseInt(id);

            Leave leave = ljc.findLeave(i);
            
            LeaveDTO s=new LeaveDTO();
            s.setBegin(leave.getBeginDate());
            s.setFinish(leave.getFinishDate());
            s.setId(leave.getIdleave());
            s.setShiftId(leave.getShiftIdshift().getIdshift());
            s.setType(leave.getType());
            
            Gson gson = new Gson();
            msg = gson.toJson(s);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public String searchLeaveByType(String type) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EJBModule2PU");
        LeaveJpaController ljc = new LeaveJpaController(utx, emf);

        String msg = "not found";

        try {

            List<Leave> findLeaveByType = ljc.findLeaveByType(type);

            List<LeaveDTO> list = new ArrayList();
            for (Leave leave : findLeaveByType) {
                LeaveDTO s = new LeaveDTO();

                s.setBegin(leave.getBeginDate());
                s.setFinish(leave.getFinishDate());
                s.setId(leave.getIdleave());
                s.setShiftId(leave.getShiftIdshift().getIdshift());
                s.setType(leave.getType());

                list.add(s);
            }

            Gson gson = new Gson();
            msg = gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msg;
    }

}
