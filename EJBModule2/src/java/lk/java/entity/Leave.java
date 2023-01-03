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
@Table(name = "`leave`")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Leave.findAll", query = "SELECT l FROM Leave l")
    , @NamedQuery(name = "Leave.findByIdleave", query = "SELECT l FROM Leave l WHERE l.idleave = :idleave")
    , @NamedQuery(name = "Leave.findByType", query = "SELECT l FROM Leave l WHERE l.type LIKE :type")//LIKE query
    , @NamedQuery(name = "Leave.findByBeginDate", query = "SELECT l FROM Leave l WHERE l.beginDate = :beginDate")
    , @NamedQuery(name = "Leave.findByFinishDate", query = "SELECT l FROM Leave l WHERE l.finishDate = :finishDate")})
public class Leave implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idleave")
    private Integer idleave;
    @Size(max = 45)
    @Column(name = "type")
    private String type;
    @Column(name = "begin_date")
    @Temporal(TemporalType.DATE)
    private Date beginDate;
    @Column(name = "finish_date")
    @Temporal(TemporalType.DATE)
    private Date finishDate;
    @JoinColumn(name = "shift_idshift", referencedColumnName = "idshift")
    @ManyToOne(optional = false)
    private Shift shiftIdshift;

    public Leave() {
    }

    public Leave(Integer idleave) {
        this.idleave = idleave;
    }

    public Integer getIdleave() {
        return idleave;
    }

    public void setIdleave(Integer idleave) {
        this.idleave = idleave;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
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
        hash += (idleave != null ? idleave.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Leave)) {
            return false;
        }
        Leave other = (Leave) object;
        if ((this.idleave == null && other.idleave != null) || (this.idleave != null && !this.idleave.equals(other.idleave))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lk.java.entity.Leave[ idleave=" + idleave + " ]";
    }
    
}
