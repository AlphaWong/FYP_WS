/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alphawong
 */
@Entity
@Table(name = "cpumanufacturer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cpumanufacturer.findAll", query = "SELECT c FROM Cpumanufacturer c"),
    @NamedQuery(name = "Cpumanufacturer.findByCPUManufacturerID", query = "SELECT c FROM Cpumanufacturer c WHERE c.cPUManufacturerID = :cPUManufacturerID"),
    @NamedQuery(name = "Cpumanufacturer.findByCPUManufacturerName", query = "SELECT c FROM Cpumanufacturer c WHERE c.cPUManufacturerName = :cPUManufacturerName")})
public class Cpumanufacturer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CPUManufacturerID")
    private Integer cPUManufacturerID;
    @Basic(optional = false)
    @Column(name = "CPUManufacturerName")
    private String cPUManufacturerName;
    @OneToMany(mappedBy = "cPUManufacturerID")
    private Collection<Mobile> mobileCollection;

    public Cpumanufacturer() {
    }

    public Cpumanufacturer(Integer cPUManufacturerID) {
        this.cPUManufacturerID = cPUManufacturerID;
    }

    public Cpumanufacturer(Integer cPUManufacturerID, String cPUManufacturerName) {
        this.cPUManufacturerID = cPUManufacturerID;
        this.cPUManufacturerName = cPUManufacturerName;
    }

    public Integer getCPUManufacturerID() {
        return cPUManufacturerID;
    }

    public void setCPUManufacturerID(Integer cPUManufacturerID) {
        this.cPUManufacturerID = cPUManufacturerID;
    }

    public String getCPUManufacturerName() {
        return cPUManufacturerName;
    }

    public void setCPUManufacturerName(String cPUManufacturerName) {
        this.cPUManufacturerName = cPUManufacturerName;
    }

    @XmlTransient
    public Collection<Mobile> getMobileCollection() {
        return mobileCollection;
    }

    public void setMobileCollection(Collection<Mobile> mobileCollection) {
        this.mobileCollection = mobileCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cPUManufacturerID != null ? cPUManufacturerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cpumanufacturer)) {
            return false;
        }
        Cpumanufacturer other = (Cpumanufacturer) object;
        if ((this.cPUManufacturerID == null && other.cPUManufacturerID != null) || (this.cPUManufacturerID != null && !this.cPUManufacturerID.equals(other.cPUManufacturerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Cpumanufacturer[ cPUManufacturerID=" + cPUManufacturerID + " ]";
    }
    
}
