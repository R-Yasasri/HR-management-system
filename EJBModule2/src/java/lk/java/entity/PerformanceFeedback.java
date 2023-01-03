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
@Table(name = "performance_feedback")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerformanceFeedback.findAll", query = "SELECT p FROM PerformanceFeedback p")
    , @NamedQuery(name = "PerformanceFeedback.findByIdperformanceFeedback", query = "SELECT p FROM PerformanceFeedback p WHERE p.idperformanceFeedback = :idperformanceFeedback")
    , @NamedQuery(name = "PerformanceFeedback.findByPersonGivenBy", query = "SELECT p FROM PerformanceFeedback p WHERE p.personGivenBy LIKE :personGivenBy")}) //LIKE query
public class PerformanceFeedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idperformance_feedback")
    private Integer idperformanceFeedback;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Size(max = 45)
    @Column(name = "person_given_by")
    private String personGivenBy;
    @JoinColumn(name = "employee_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee employeeIdemployee;

    public PerformanceFeedback() {
    }

    public PerformanceFeedback(Integer idperformanceFeedback) {
        this.idperformanceFeedback = idperformanceFeedback;
    }

    public Integer getIdperformanceFeedback() {
        return idperformanceFeedback;
    }

    public void setIdperformanceFeedback(Integer idperformanceFeedback) {
        this.idperformanceFeedback = idperformanceFeedback;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPersonGivenBy() {
        return personGivenBy;
    }

    public void setPersonGivenBy(String personGivenBy) {
        this.personGivenBy = personGivenBy;
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
        hash += (idperformanceFeedback != null ? idperformanceFeedback.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerformanceFeedback)) {
            return false;
        }
        PerformanceFeedback other = (PerformanceFeedback) object;
        if ((this.idperformanceFeedback == null && other.idperformanceFeedback != null) || (this.idperformanceFeedback != null && !this.idperformanceFeedback.equals(other.idperformanceFeedback))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lk.java.entity.PerformanceFeedback[ idperformanceFeedback=" + idperformanceFeedback + " ]";
    }
    
}
