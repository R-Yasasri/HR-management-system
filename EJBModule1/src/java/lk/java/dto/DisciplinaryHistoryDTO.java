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
public class DisciplinaryHistoryDTO {

    private int id;
    private String description;
    private String actionTaken;
    private int idEmp;
    private String empName;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the actionTaken
     */
    public String getActionTaken() {
        return actionTaken;
    }

    /**
     * @param actionTaken the actionTaken to set
     */
    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
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
     * @return the emapName
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * @param emapName the emapName to set
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
