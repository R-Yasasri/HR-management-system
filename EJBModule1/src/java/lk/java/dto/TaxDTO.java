/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.dto;

/**
 *
 * @author Rajitha Yasasri
 */
public class TaxDTO {

    private int idTax;
    private String incomeTaxNo;
    private String officeRegistered;
    private int idEmp;
    private String empName;

    /**
     * @return the idTax
     */
    public int getIdTax() {
        return idTax;
    }

    /**
     * @param idTax the idTax to set
     */
    public void setIdTax(int idTax) {
        this.idTax = idTax;
    }

    /**
     * @return the incomeTaxNo
     */
    public String getIncomeTaxNo() {
        return incomeTaxNo;
    }

    /**
     * @param incomeTaxNo the incomeTaxNo to set
     */
    public void setIncomeTaxNo(String incomeTaxNo) {
        this.incomeTaxNo = incomeTaxNo;
    }

    /**
     * @return the officeRegistered
     */
    public String getOfficeRegistered() {
        return officeRegistered;
    }

    /**
     * @param officeRegistered the officeRegistered to set
     */
    public void setOfficeRegistered(String officeRegistered) {
        this.officeRegistered = officeRegistered;
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
