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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rajitha Yasasri
 */
@Entity
@Table(name = "attendance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attendance.findAll", query = "SELECT a FROM Attendance a")
    , @NamedQuery(name = "Attendance.findByIdattendance", query = "SELECT a FROM Attendance a WHERE a.idattendance = :idattendance")
    , @NamedQuery(name = "Attendance.findByClockIn", query = "SELECT a FROM Attendance a WHERE a.clockIn = :clockIn")
    , @NamedQuery(name = "Attendance.findByClockOut", query = "SELECT a FROM Attendance a WHERE a.clockOut = :clockOut")})
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idattendance")
    private Integer idattendance;
    @Column(name = "clock_in")
    @Temporal(TemporalType.TIMESTAMP)
    private Date clockIn;
    @Column(name = "clock_out")
    @Temporal(TemporalType.TIMESTAMP)
    private Date clockOut;
    @JoinColumn(name = "shift_idshift", referencedColumnName = "idshift")
    @ManyToOne(optional = false)
    private Shift shiftIdshift;

    public Attendance() {
    }

    public Attendance(Integer idattendance) {
        this.idattendance = idattendance;
    }

    public Integer getIdattendance() {
        return idattendance;
    }

    public void setIdattendance(Integer idattendance) {
        this.idattendance = idattendance;
    }

    public Date getClockIn() {
        return clockIn;
    }

    public void setClockIn(Date clockIn) {
        this.clockIn = clockIn;
    }

    public Date getClockOut() {
        return clockOut;
    }

    public void setClockOut(Date clockOut) {
        this.clockOut = clockOut;
    }

    public Shift getShiftIdshift() {
        return shiftIdshift;
    }

    public void setShiftIdshift(Shift shiftIdshift) {
        this.shiftIdshift = shiftIdshift;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idattendance != null ? idattendance.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attendance)) {
            return false;
        }
        Attendance other = (Attendance) object;
        if ((this.idattendance == null && other.idattendance != null) || (this.idattendance != null && !this.idattendance.equals(other.idattendance))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lk.java.entity.Attendance[ idattendance=" + idattendance + " ]";
    }
    
}
