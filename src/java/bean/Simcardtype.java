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
@Table(name = "simcardtype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Simcardtype.findAll", query = "SELECT s FROM Simcardtype s"),
    @NamedQuery(name = "Simcardtype.findBySIMCardTypeID", query = "SELECT s FROM Simcardtype s WHERE s.sIMCardTypeID = :sIMCardTypeID"),
    @NamedQuery(name = "Simcardtype.findBySIMCardTypeName", query = "SELECT s FROM Simcardtype s WHERE s.sIMCardTypeName = :sIMCardTypeName")})
public class Simcardtype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SIMCardTypeID")
    private Integer sIMCardTypeID;
    @Basic(optional = false)
    @Column(name = "SIMCardTypeName")
    private String sIMCardTypeName;
    @JoinTable(name = "mobile_simcardtype", joinColumns = {
        @JoinColumn(name = "SIMCardTypeID", referencedColumnName = "SIMCardTypeID")}, inverseJoinColumns = {
        @JoinColumn(name = "mobileID", referencedColumnName = "mobileID")})
    @ManyToMany
    private Collection<Mobile> mobileCollection;

    public Simcardtype() {
    }

    public Simcardtype(Integer sIMCardTypeID) {
        this.sIMCardTypeID = sIMCardTypeID;
    }

    public Simcardtype(Integer sIMCardTypeID, String sIMCardTypeName) {
        this.sIMCardTypeID = sIMCardTypeID;
        this.sIMCardTypeName = sIMCardTypeName;
    }

    public Integer getSIMCardTypeID() {
        return sIMCardTypeID;
    }

    public void setSIMCardTypeID(Integer sIMCardTypeID) {
        this.sIMCardTypeID = sIMCardTypeID;
    }

    public String getSIMCardTypeName() {
        return sIMCardTypeName;
    }

    public void setSIMCardTypeName(String sIMCardTypeName) {
        this.sIMCardTypeName = sIMCardTypeName;
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
        hash += (sIMCardTypeID != null ? sIMCardTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Simcardtype)) {
            return false;
        }
        Simcardtype other = (Simcardtype) object;
        if ((this.sIMCardTypeID == null && other.sIMCardTypeID != null) || (this.sIMCardTypeID != null && !this.sIMCardTypeID.equals(other.sIMCardTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Simcardtype[ sIMCardTypeID=" + sIMCardTypeID + " ]";
    }
    
}
