/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "operatingsystem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operatingsystem.findAll", query = "SELECT o FROM Operatingsystem o"),
    @NamedQuery(name = "Operatingsystem.findByOsid", query = "SELECT o FROM Operatingsystem o WHERE o.osid = :osid"),
    @NamedQuery(name = "Operatingsystem.findByOSName", query = "SELECT o FROM Operatingsystem o WHERE o.oSName = :oSName")})
public class Operatingsystem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OSID")
    private Integer osid;
    @Basic(optional = false)
    @Column(name = "OSName")
    private String oSName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "osid")
    private Collection<Mobile> mobileCollection;

    public Operatingsystem() {
    }

    public Operatingsystem(Integer osid) {
        this.osid = osid;
    }

    public Operatingsystem(Integer osid, String oSName) {
        this.osid = osid;
        this.oSName = oSName;
    }

    public Integer getOsid() {
        return osid;
    }

    public void setOsid(Integer osid) {
        this.osid = osid;
    }

    public String getOSName() {
        return oSName;
    }

    public void setOSName(String oSName) {
        this.oSName = oSName;
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
        hash += (osid != null ? osid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operatingsystem)) {
            return false;
        }
        Operatingsystem other = (Operatingsystem) object;
        if ((this.osid == null && other.osid != null) || (this.osid != null && !this.osid.equals(other.osid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Operatingsystem[ osid=" + osid + " ]";
    }
    
}
