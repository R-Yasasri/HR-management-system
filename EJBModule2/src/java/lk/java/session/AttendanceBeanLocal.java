/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.session;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import lk.java.entity.Attendance;

/**
 *
 * @author Rajitha Yasasri
 */
@Local
public interface AttendanceBeanLocal {

    List<Attendance> getAttendanceByDate(Date date);
    
}
