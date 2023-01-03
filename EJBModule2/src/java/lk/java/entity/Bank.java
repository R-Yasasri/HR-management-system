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
import javax.persistence.FetchType;
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
@Table(name = "bank")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bank.findAll", query = "SELECT b FROM Bank b")
    , @NamedQuery(name = "Bank.findByIdbank", query = "SELECT b FROM Bank b WHERE b.idbank = :idbank")
    , @NamedQuery(name = "Bank.findByName", query = "SELECT b FROM Bank b WHERE b.name LIKE :name")//LIKE query
    , @NamedQuery(name = "Bank.findByAccount", query = "SELECT b FROM Bank b WHERE b.account = :account")
    , @NamedQuery(name = "Bank.findByAddress", query = "SELECT b FROM Bank b WHERE b.address = :address")})
public class Bank implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbank")
    private Integer idbank;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "account")
    private String account;
    @Size(max = 200)
    @Column(name = "address")
    private String address;
    @JoinColumn(name = "employee_idemployee", referencedColumnName = "idemployee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employeeIdemployee;

    public Bank() {
    }

    public Bank(Integer idbank) {
        this.idbank = idbank;
    }

    public Integer getIdbank() {
        return idbank;
    }

    public void setIdbank(Integer idbank) {
        this.idbank = idbank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        hash += (idbank != null ? idbank.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bank)) {
            return false;
        }
        Bank other = (Bank) object;
        if ((this.idbank == null && other.idbank != null) || (this.idbank != null && !this.idbank.equals(other.idbank))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lk.java.entity.Bank[ idbank=" + idbank + " ]";
    }
    
}
