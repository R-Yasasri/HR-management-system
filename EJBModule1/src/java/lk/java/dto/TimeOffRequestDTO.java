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
public class TimeOffRequestDTO {

    private int idTimeOffRequest;
    private String reason;
    private Date beginTime;
    private Date endTime;
    private int idEmp;
    private String empName;

    /**
     * @return the idTimeOffRequest
     */
    public int getIdTimeOffRequest() {
        return idTimeOffRequest;
    }

    /**
     * @param idTimeOffRequest the idTimeOffRequest to set
     */
    public void setIdTimeOffRequest(int idTimeOffRequest) {
        this.idTimeOffRequest = idTimeOffRequest;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the beginTime
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * @param beginTime the beginTime to set
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the idEmp
     */
    public int getIdEmp() {
        return idEmp;
    }

    /**
     * @param idEmp the idEmp to set
     */
    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    /**
     * @return the empName
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * @param empName the empName to set
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
