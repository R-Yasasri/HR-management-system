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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rajitha Yasasri
 */
@Entity
@Table(name = "insurance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Insurance.findAll", query = "SELECT i FROM Insurance i")
    , @NamedQuery(name = "Insurance.findByIdinsurance", query = "SELECT i FROM Insurance i WHERE i.idinsurance = :idinsurance")
    , @NamedQuery(name = "Insurance.findByProvider", query = "SELECT i FROM Insurance i WHERE i.provider LIKE :provider")//LIKE query
    , @NamedQuery(name = "Insurance.findByProviderAddress", query = "SELECT i FROM Insurance i WHERE i.providerAddress = :providerAddress")
    , @NamedQuery(name = "Insurance.findByPlan", query = "SELECT i FROM Insurance i WHERE i.plan = :plan")
    , @NamedQuery(name = "Insurance.findByStartingDate", query = "SELECT i FROM Insurance i WHERE i.startingDate = :startingDate")
    , @NamedQuery(name = "Insurance.findByEndDate", query = "SELECT i FROM Insurance i WHERE i.endDate = :endDate")})
public class Insurance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinsurance")
    private Integer idinsurance;
    @Size(max = 45)
    @Column(name = "provider")
    private String provider;
    @Size(max = 200)
    @Column(name = "provider_address")
    private String providerAddress;
    @Size(max = 45)
    @Column(name = "plan")
    private String plan;
    @Column(name = "starting_date")
    @Temporal(TemporalType.DATE)
    private Date startingDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @JoinColumn(name = "employee_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee employeeIdemployee;

    public Insurance() {
    }

    public Insurance(Integer idinsurance) {
        this.idinsurance = idinsurance;
    }

    public Integer getIdinsurance() {
        return idinsurance;
    }

    public void setIdinsurance(Integer idinsurance) {
        this.idinsurance = idinsurance;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderAddress() {
        return providerAddress;
    }

    public void setProviderAddress(String providerAddress) {
        this.providerAddress = providerAddress;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        hash += (idinsurance != null ? idinsurance.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Insurance)) {
            return false;
        }
        Insurance other = (Insurance) object;
        if ((this.idinsurance == null && other.idinsurance != null) || (this.idinsurance != null && !this.idinsurance.equals(other.idinsurance))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lk.java.entity.Insurance[ idinsurance=" + idinsurance + " ]";
    }
    
}
