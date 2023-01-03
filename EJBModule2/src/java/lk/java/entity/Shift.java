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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rajitha Yasasri
 */
@Entity
@Table(name = "shift")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shift.findAll", query = "SELECT s FROM Shift s")
    , @NamedQuery(name = "Shift.findByIdshift", query = "SELECT s FROM Shift s WHERE s.idshift = :idshift")
    , @NamedQuery(name = "Shift.findByStartDate", query = "SELECT s FROM Shift s WHERE s.startDate = :startDate")
    , @NamedQuery(name = "Shift.findByFinishDate", query = "SELECT s FROM Shift s WHERE s.finishDate = :finishDate")
    , @NamedQuery(name = "Shift.findByAllocatedLeaveDays", query = "SELECT s FROM Shift s WHERE s.allocatedLeaveDays = :allocatedLeaveDays")
    , @NamedQuery(name = "Shift.findByPaymentPerHour", query = "SELECT s FROM Shift s WHERE s.paymentPerHour = :paymentPerHour")
    , @NamedQuery(name = "Shift.findByLocation", query = "SELECT s FROM Shift s WHERE s.locationIdlocation = :location")// Added Query
    , @NamedQuery(name = "Shift.findByProject", query = "SELECT s FROM Shift s WHERE s.projectIdproject = :project")// Added Query
    , @NamedQuery(name = "Shift.findByDepartment", query = "SELECT s FROM Shift s WHERE s.departmentIddepartment = :department") // Added Query
    , @NamedQuery(name = "Shift.findByEmployee", query = "SELECT s FROM Shift s WHERE s.employeeIdemployee = :employee")})// Added Query
public class Shift implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idshift")
    private Integer idshift;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "finish_date")
    @Temporal(TemporalType.DATE)
    private Date finishDate;
    @Column(name = "allocated_leave_days")
    private Integer allocatedLeaveDays;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "payment_per_hour")
    private Double paymentPerHour;
    @JoinColumn(name = "department_iddepartment", referencedColumnName = "iddepartment")
    @ManyToOne(optional = false)
    private Department departmentIddepartment;
    @JoinColumn(name = "employee_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee employeeIdemployee;
    @JoinColumn(name = "location_idlocation", referencedColumnName = "idlocation")
    @ManyToOne(optional = false)
    private Location locationIdlocation;
    @JoinColumn(name = "project_idproject", referencedColumnName = "idproject")
    @ManyToOne(optional = false)
    private Project projectIdproject;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shiftIdshift")
    private List<Leave> leaveList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shiftIdshift")
    private List<Attendance> attendanceList;

    public Shift() {
    }

    public Shift(Integer idshift) {
        this.idshift = idshift;
    }

    public Integer getIdshift() {
        return idshift;
    }

    public void setIdshift(Integer idshift) {
        this.idshift = idshift;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Integer getAllocatedLeaveDays() {
        return allocatedLeaveDays;
    }

    public void setAllocatedLeaveDays(Integer allocatedLeaveDays) {
        this.allocatedLeaveDays = allocatedLeaveDays;
    }

    public Double getPaymentPerHour() {
        return paymentPerHour;
    }

    public void setPaymentPerHour(Double paymentPerHour) {
        this.paymentPerHour = paymentPerHour;
    }

    public Department getDepartmentIddepartment() {
        return departmentIddepartment;
    }

    public void setDepartmentIddepartment(Department departmentIddepartment) {
        this.departmentIddepartment = departmentIddepartment;
    }

    public Employee getEmployeeIdemployee() {
        return employeeIdemployee;
    }

    public void setEmployeeIdemployee(Employee employeeIdemployee) {
        this.employeeIdemployee = employeeIdemployee;
    }

    public Location getLocationIdlocation() {
        return locationIdlocation;
    }

    public void setLocationIdlocation(Location locationIdlocation) {
        this.locationIdlocation = locationIdlocation;
    }

    public Project getProjectIdproject() {
        return projectIdproject;
    }

    public void setProjectIdproject(Project projectIdproject) {
        this.projectIdproject = projectIdproject;
    }

    @XmlTransient
    public List<Leave> getLeaveList() {
        return leaveList;
    }

    public void setLeaveList(List<Leave> leaveList) {
        this.leaveList = leaveList;
    }

    @XmlTransient
    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idshift != null ? idshift.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shift)) {
            return false;
        }
        Shift other = (Shift) object;
        if ((this.idshift == null && other.idshift != null) || (this.idshift != null && !this.idshift.equals(other.idshift))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lk.java.entity.Shift[ idshift=" + idshift + " ]";
    }

}
