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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alphawong
 */
@Entity
@Table(name = "wlantype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wlantype.findAll", query = "SELECT w FROM Wlantype w"),
    @NamedQuery(name = "Wlantype.findByWLANTypeID", query = "SELECT w FROM Wlantype w WHERE w.wLANTypeID = :wLANTypeID"),
    @NamedQuery(name = "Wlantype.findByWLANTypeName", query = "SELECT w FROM Wlantype w WHERE w.wLANTypeName = :wLANTypeName")})
public class Wlantype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WLANTypeID")
    private Integer wLANTypeID;
    @Basic(optional = false)
    @Column(name = "WLANTypeName")
    private String wLANTypeName;
    @JoinTable(name = "mobile_wlantype", joinColumns = {
        @JoinColumn(name = "WLANTypeID", referencedColumnName = "WLANTypeID")}, inverseJoinColumns = {
        @JoinColumn(name = "mobileID", referencedColumnName = "mobileID")})
    @ManyToMany
    private Collection<Mobile> mobileCollection;

    public Wlantype() {
    }

    public Wlantype(Integer wLANTypeID) {
        this.wLANTypeID = wLANTypeID;
    }

    public Wlantype(Integer wLANTypeID, String wLANTypeName) {
        this.wLANTypeID = wLANTypeID;
        this.wLANTypeName = wLANTypeName;
    }

    public Integer getWLANTypeID() {
        return wLANTypeID;
    }

    public void setWLANTypeID(Integer wLANTypeID) {
        this.wLANTypeID = wLANTypeID;
    }

    public String getWLANTypeName() {
        return wLANTypeName;
    }

    public void setWLANTypeName(String wLANTypeName) {
        this.wLANTypeName = wLANTypeName;
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
        hash += (wLANTypeID != null ? wLANTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wlantype)) {
            return false;
        }
        Wlantype other = (Wlantype) object;
        if ((this.wLANTypeID == null && other.wLANTypeID != null) || (this.wLANTypeID != null && !this.wLANTypeID.equals(other.wLANTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Wlantype[ wLANTypeID=" + wLANTypeID + " ]";
    }
    
}
