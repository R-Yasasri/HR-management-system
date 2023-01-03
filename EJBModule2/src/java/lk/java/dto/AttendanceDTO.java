/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.dto;

import java.util.Date;

/**
 *
 * @author Rajitha Yasasri
 */
public class AttendanceDTO {

    private int attendanceId;
    private Date clockIn;
    private Date clockOut;
    private int idShift;

    /**
     * @return the attendanceId
     */
    public int getAttendanceId() {
        return attendanceId;
    }

    /**
     * @param attendanceId the attendanceId to set
     */
    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    /**
     * @return the clockIn
     */
    public Date getClockIn() {
        return clockIn;
    }

    /**
     * @param clockIn the clockIn to set
     */
    public void setClockIn(Date clockIn) {
        this.clockIn = clockIn;
    }

    /**
     * @return the clockOut
     */
    public Date getClockOut() {
        return clockOut;
    }

    /**
     * @param clockOut the clockOut to set
     */
    public void setClockOut(Date clockOut) {
        this.clockOut = clockOut;
    }

    /**
     * @return the idShift
     */
    public int getIdShift() {
        return idShift;
    }

    /**
     * @param idShift the idShift to set
     */
    public void setIdShift(int idShift) {
        this.idShift = idShift;
    }
}
