/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rajitha Yasasri
 */
@Entity
@Table(name = "disciplinary_history")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DisciplinaryHistory.findAll", query = "SELECT d FROM DisciplinaryHistory d")
    , @NamedQuery(name = "DisciplinaryHistory.findByIddisciplinaryHistory", query = "SELECT d FROM DisciplinaryHistory d WHERE d.iddisciplinaryHistory = :iddisciplinaryHistory")
    , @NamedQuery(name = "DisciplinaryHistory.findByActionTaken", query = "SELECT d FROM DisciplinaryHistory d WHERE d.actionTaken LIKE :actionTaken")})//LIKE query
public class DisciplinaryHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddisciplinary_history")
    private Integer iddisciplinaryHistory;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Size(max = 45)
    @Column(name = "action_taken")
    private String actionTaken;
    @JoinColumn(name = "employee_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee employeeIdemployee;

    public DisciplinaryHistory() {
    }

    public DisciplinaryHistory(Integer iddisciplinaryHistory) {
        this.iddisciplinaryHistory = iddisciplinaryHistory;
    }

    public Integer getIddisciplinaryHistory() {
        return iddisciplinaryHistory;
    }

    public void setIddisciplinaryHistory(Integer iddisciplinaryHistory) {
        this.iddisciplinaryHistory = iddisciplinaryHistory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public Employee getEmployeeIdemployee() {
        return employeeIdemployee;
    }

    public void setEmployeeIdemployee(Employee employeeIdemployee) {
        this.employeeIdemployee = employeeIdemployee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddisciplinaryHistory != null ? iddisciplinaryHistory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DisciplinaryHistory)) {
            return false;
        }
        DisciplinaryHistory other = (DisciplinaryHistory) object;
        if ((this.iddisciplinaryHistory == null && other.iddisciplinaryHistory != null) || (this.iddisciplinaryHistory != null && !this.iddisciplinaryHistory.equals(other.iddisciplinaryHistory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lk.java.entity.DisciplinaryHistory[ iddisciplinaryHistory=" + iddisciplinaryHistory + " ]";
    }
    
}
