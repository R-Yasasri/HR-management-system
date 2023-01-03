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
public class ShiftDTO {
    private int shiftId;
    private Date start;
    private Date finish;
    private Integer allocatedLeaveDays;
    private Double payment;
    private int locationId;
    private int projectId;
    private int deptId;
    private int empId;

    /**
     * @return the shiftId
     */
    public int getShiftId() {
        return shiftId;
    }

    /**
     * @param shiftId the shiftId to set
     */
    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    /**
     * @return the start
     */
    public Date getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * @return the finish
     */
    public Date getFinish() {
        return finish;
    }

    /**
     * @param finish the finish to set
     */
    public void setFinish(Date finish) {
        this.finish = finish;
    }

    /**
     * @return the allocatedLeaveDays
     */
    public Integer getAllocatedLeaveDays() {
        return allocatedLeaveDays;
    }

    /**
     * @param allocatedLeaveDays the allocatedLeaveDays to set
     */
    public void setAllocatedLeaveDays(Integer allocatedLeaveDays) {
        this.allocatedLeaveDays = allocatedLeaveDays;
    }

    /**
     * @return the payment
     */
    public Double getPayment() {
        return payment;
    }

    /**
     * @param payment the payment to set
     */
    public void setPayment(Double payment) {
        this.payment = payment;
    }

    /**
     * @return the locationId
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * @param locationId the locationId to set
     */
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    /**
     * @return the projectId
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the deptId
     */
    public int getDeptId() {
        return deptId;
    }

    /**
     * @param deptId the deptId to set
     */
    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    /**
     * @return the empId
     */
    public int getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    
}
