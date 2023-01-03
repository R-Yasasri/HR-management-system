/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rajitha Yasasri
 */
@Entity
@Table(name = "salary")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salary.findAll", query = "SELECT s FROM Salary s")
    , @NamedQuery(name = "Salary.findByIdsalary", query = "SELECT s FROM Salary s WHERE s.idsalary = :idsalary")
    , @NamedQuery(name = "Salary.findByMonth", query = "SELECT s FROM Salary s WHERE s.month LIKE :month") //LIKE query
    , @NamedQuery(name = "Salary.findBySalary", query = "SELECT s FROM Salary s WHERE s.salary = :salary")
    , @NamedQuery(name = "Salary.findByStatus", query = "SELECT s FROM Salary s WHERE s.status = :status")})
public class Salary implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsalary")
    private Integer idsalary;
    @Column(name = "month")
    @Temporal(TemporalType.DATE)
    private Date month;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salary")
    private Double salary;
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "employee_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee employeeIdemployee;

    public Salary() {
    }

    public Salary(Integer idsalary) {
        this.idsalary = idsalary;
    }

    public Integer getIdsalary() {
        return idsalary;
    }

    public void setIdsalary(Integer idsalary) {
        this.idsalary = idsalary;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        hash += (idsalary != null ? idsalary.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salary)) {
            return false;
        }
        Salary other = (Salary) object;
        if ((this.idsalary == null && other.idsalary != null) || (this.idsalary != null && !this.idsalary.equals(other.idsalary))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lk.java.entity.Salary[ idsalary=" + idsalary + " ]";
    }
    
}
