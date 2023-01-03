/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rajitha Yasasri
 */
@Entity
@Table(name = "employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
    , @NamedQuery(name = "Employee.findByIdemployee", query = "SELECT e FROM Employee e WHERE e.idemployee = :idemployee")
    , @NamedQuery(name = "Employee.findByName", query = "SELECT e FROM Employee e WHERE e.name LIKE :name ")// LIKE query
    , @NamedQuery(name = "Employee.findByBirthday", query = "SELECT e FROM Employee e WHERE e.birthday = :birthday")
    , @NamedQuery(name = "Employee.findByNic", query = "SELECT e FROM Employee e WHERE e.nic = :nic")
    , @NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email")
    , @NamedQuery(name = "Employee.findByMobile", query = "SELECT e FROM Employee e WHERE e.mobile = :mobile")
    , @NamedQuery(name = "Employee.findByAddress", query = "SELECT e FROM Employee e WHERE e.address = :address")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idemployee")
    private Integer idemployee;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Size(max = 12)
    @Column(name = "nic")
    private String nic;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 10)
    @Column(name = "mobile")
    private String mobile;
    @Size(max = 200)
    @Column(name = "address")
    private String address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeIdemployee")
    private List<Insurance> insuranceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeIdemployee")
    private List<DisciplinaryHistory> disciplinaryHistoryList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeIdemployee")
    private List<Shift> shiftList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeIdemployee")
    private List<Tax> taxList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeIdemployee")
    private List<Salary> salaryList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeIdemployee")
    private List<Bank> bankList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeIdemployee")
    private List<PerformanceFeedback> performanceFeedbackList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeIdemployee")
    private List<Job> jobList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeIdemployee")
    private List<TimeOffRequest> timeOffRequestList;

    public Employee() {
    }

    public Employee(Integer idemployee) {
        this.idemployee = idemployee;
    }

    public Integer getIdemployee() {
        return idemployee;
    }

    public void setIdemployee(Integer idemployee) {
        this.idemployee = idemployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlTransient
    public List<Insurance> getInsuranceList() {
        return insuranceList;
    }

    public void setInsuranceList(List<Insurance> insuranceList) {
        this.insuranceList = insuranceList;
    }

    @XmlTransient
    public List<DisciplinaryHistory> getDisciplinaryHistoryList() {
        return disciplinaryHistoryList;
    }

    public void setDisciplinaryHistoryList(List<DisciplinaryHistory> disciplinaryHistoryList) {
        this.disciplinaryHistoryList = disciplinaryHistoryList;
    }

    @XmlTransient
    public List<Shift> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<Shift> shiftList) {
        this.shiftList = shiftList;
    }

    @XmlTransient
    public List<Tax> getTaxList() {
        return taxList;
    }

    public void setTaxList(List<Tax> taxList) {
        this.taxList = taxList;
    }

    @XmlTransient
    public List<Salary> getSalaryList() {
        return salaryList;
    }

    public void setSalaryList(List<Salary> salaryList) {
        this.salaryList = salaryList;
    }

    @XmlTransient
    public List<Bank> getBankList() {
        return bankList;
    }

    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
    }

    @XmlTransient
    public List<PerformanceFeedback> getPerformanceFeedbackList() {
        return performanceFeedbackList;
    }

    public void setPerformanceFeedbackList(List<PerformanceFeedback> performanceFeedbackList) {
        this.performanceFeedbackList = performanceFeedbackList;
    }

    @XmlTransient
    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    @XmlTransient
    public List<TimeOffRequest> getTimeOffRequestList() {
        return timeOffRequestList;
    }

    public void setTimeOffRequestList(List<TimeOffRequest> timeOffRequestList) {
        this.timeOffRequestList = timeOffRequestList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idemployee != null ? idemployee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.idemployee == null && other.idemployee != null) || (this.idemployee != null && !this.idemployee.equals(other.idemployee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lk.java.entity.Employee[ idemployee=" + idemployee + " ]";
    }
    
}
