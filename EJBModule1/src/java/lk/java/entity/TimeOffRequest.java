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
import javax.persistence.Lob;
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
@Table(name = "time_off_request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TimeOffRequest.findAll", query = "SELECT t FROM TimeOffRequest t")
    , @NamedQuery(name = "TimeOffRequest.findByIdtimeOffRequest", query = "SELECT t FROM TimeOffRequest t WHERE t.idtimeOffRequest = :idtimeOffRequest")
    , @NamedQuery(name = "TimeOffRequest.findByBeginTime", query = "SELECT t FROM TimeOffRequest t WHERE t.beginTime = :beginTime")
    , @NamedQuery(name = "TimeOffRequest.findByEndTime", query = "SELECT t FROM TimeOffRequest t WHERE t.endTime = :endTime")})
public class TimeOffRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtime_off_request")
    private Integer idtimeOffRequest;
    @Lob
    @Size(max = 65535)
    @Column(name = "reason")
    private String reason;
    @Column(name = "begin_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date beginTime;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @JoinColumn(name = "employee_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(optional = false)
    private Employee employeeIdemployee;

    public TimeOffRequest() {
    }

    public TimeOffRequest(Integer idtimeOffRequest) {
        this.idtimeOffRequest = idtimeOffRequest;
    }

    public Integer getIdtimeOffRequest() {
        return idtimeOffRequest;
    }

    public void setIdtimeOffRequest(Integer idtimeOffRequest) {
        this.idtimeOffRequest = idtimeOffRequest;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
        hash += (idtimeOffRequest != null ? idtimeOffRequest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TimeOffRequest)) {
            return false;
        }
        TimeOffRequest other = (TimeOffRequest) object;
        if ((this.idtimeOffRequest == null && other.idtimeOffRequest != null) || (this.idtimeOffRequest != null && !this.idtimeOffRequest.equals(other.idtimeOffRequest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lk.java.entity.TimeOffRequest[ idtimeOffRequest=" + idtimeOffRequest + " ]";
    }
    
}
