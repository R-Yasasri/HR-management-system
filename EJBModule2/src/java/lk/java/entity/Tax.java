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
@Table(name = "tax")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tax.findAll", query = "SELECT t FROM Tax t")
    , @NamedQuery(name = "Tax.findByIdtax", query = "SELECT t FROM Tax t WHERE t.idtax = :idtax")
    , @NamedQuery(name = "Tax.findByIncomeTaxNo", query = "SELECT t FROM Tax t WHERE t.incomeTaxNo LIKE :incomeTaxNo")//LIKE query
    , @NamedQuery(name = "Tax.findByOfficeRegistered", query = "SELECT t FROM Tax t WHERE t.officeRegistered = :officeRegistered")})
public class Tax implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtax")
    private Integer idtax;
    @Size(max = 20)
    @Column(name = "income_tax_no")
    private String incomeTaxNo;
    @Size(max = 45)
    @Column(name = "office_registered")
    private String officeRegistered;
    @JoinColumn(name = "employee_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee employeeIdemployee;

    public Tax() {
    }

    public Tax(Integer idtax) {
        this.idtax = idtax;
    }

    public Integer getIdtax() {
        return idtax;
    }

    public void setIdtax(Integer idtax) {
        this.idtax = idtax;
    }

    public String getIncomeTaxNo() {
        return incomeTaxNo;
    }

    public void setIncomeTaxNo(String incomeTaxNo) {
        this.incomeTaxNo = incomeTaxNo;
    }

    public String getOfficeRegistered() {
        return officeRegistered;
    }

    public void setOfficeRegistered(String officeRegistered) {
        this.officeRegistered = officeRegistered;
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
        hash += (idtax != null ? idtax.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tax)) {
            return false;
        }
        Tax other = (Tax) object;
        if ((this.idtax == null && other.idtax != null) || (this.idtax != null && !this.idtax.equals(other.idtax))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lk.java.entity.Tax[ idtax=" + idtax + " ]";
    }
    
}
