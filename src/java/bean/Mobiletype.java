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
@Table(name = "mobiletype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mobiletype.findAll", query = "SELECT m FROM Mobiletype m"),
    @NamedQuery(name = "Mobiletype.findByMobileTypeID", query = "SELECT m FROM Mobiletype m WHERE m.mobileTypeID = :mobileTypeID"),
    @NamedQuery(name = "Mobiletype.findByMobileTypeName", query = "SELECT m FROM Mobiletype m WHERE m.mobileTypeName = :mobileTypeName")})
public class Mobiletype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mobileTypeID")
    private Integer mobileTypeID;
    @Basic(optional = false)
    @Column(name = "mobileTypeName")
    private String mobileTypeName;
    @JoinTable(name = "mobile_mobiletype", joinColumns = {
        @JoinColumn(name = "mobileTypeID", referencedColumnName = "mobileTypeID")}, inverseJoinColumns = {
        @JoinColumn(name = "mobileID", referencedColumnName = "mobileID")})
    @ManyToMany
    private Collection<Mobile> mobileCollection;

    public Mobiletype() {
    }

    public Mobiletype(Integer mobileTypeID) {
        this.mobileTypeID = mobileTypeID;
    }

    public Mobiletype(Integer mobileTypeID, String mobileTypeName) {
        this.mobileTypeID = mobileTypeID;
        this.mobileTypeName = mobileTypeName;
    }

    public Integer getMobileTypeID() {
        return mobileTypeID;
    }

    public void setMobileTypeID(Integer mobileTypeID) {
        this.mobileTypeID = mobileTypeID;
    }

    public String getMobileTypeName() {
        return mobileTypeName;
    }

    public void setMobileTypeName(String mobileTypeName) {
        this.mobileTypeName = mobileTypeName;
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
        hash += (mobileTypeID != null ? mobileTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mobiletype)) {
            return false;
        }
        Mobiletype other = (Mobiletype) object;
        if ((this.mobileTypeID == null && other.mobileTypeID != null) || (this.mobileTypeID != null && !this.mobileTypeID.equals(other.mobileTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Mobiletype[ mobileTypeID=" + mobileTypeID + " ]";
    }
    
}
