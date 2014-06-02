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
@Table(name = "mobilenetworkstandard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mobilenetworkstandard.findAll", query = "SELECT m FROM Mobilenetworkstandard m"),
    @NamedQuery(name = "Mobilenetworkstandard.findByMobileNetworkStandardID", query = "SELECT m FROM Mobilenetworkstandard m WHERE m.mobileNetworkStandardID = :mobileNetworkStandardID"),
    @NamedQuery(name = "Mobilenetworkstandard.findByMobileNetworkStandardName", query = "SELECT m FROM Mobilenetworkstandard m WHERE m.mobileNetworkStandardName = :mobileNetworkStandardName"),
    @NamedQuery(name = "Mobilenetworkstandard.findByMobileNetworkStandardType", query = "SELECT m FROM Mobilenetworkstandard m WHERE m.mobileNetworkStandardType = :mobileNetworkStandardType")})
public class Mobilenetworkstandard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mobileNetworkStandardID")
    private Integer mobileNetworkStandardID;
    @Basic(optional = false)
    @Column(name = "mobileNetworkStandardName")
    private String mobileNetworkStandardName;
    @Basic(optional = false)
    @Column(name = "mobileNetworkStandardType")
    private String mobileNetworkStandardType;
    @JoinTable(name = "mobile_mobilenetworkstandard", joinColumns = {
        @JoinColumn(name = "mobileNetworkStandardID", referencedColumnName = "mobileNetworkStandardID")}, inverseJoinColumns = {
        @JoinColumn(name = "mobileID", referencedColumnName = "mobileID")})
    @ManyToMany
    private Collection<Mobile> mobileCollection;

    public Mobilenetworkstandard() {
    }

    public Mobilenetworkstandard(Integer mobileNetworkStandardID) {
        this.mobileNetworkStandardID = mobileNetworkStandardID;
    }

    public Mobilenetworkstandard(Integer mobileNetworkStandardID, String mobileNetworkStandardName, String mobileNetworkStandardType) {
        this.mobileNetworkStandardID = mobileNetworkStandardID;
        this.mobileNetworkStandardName = mobileNetworkStandardName;
        this.mobileNetworkStandardType = mobileNetworkStandardType;
    }

    public Integer getMobileNetworkStandardID() {
        return mobileNetworkStandardID;
    }

    public void setMobileNetworkStandardID(Integer mobileNetworkStandardID) {
        this.mobileNetworkStandardID = mobileNetworkStandardID;
    }

    public String getMobileNetworkStandardName() {
        return mobileNetworkStandardName;
    }

    public void setMobileNetworkStandardName(String mobileNetworkStandardName) {
        this.mobileNetworkStandardName = mobileNetworkStandardName;
    }

    public String getMobileNetworkStandardType() {
        return mobileNetworkStandardType;
    }

    public void setMobileNetworkStandardType(String mobileNetworkStandardType) {
        this.mobileNetworkStandardType = mobileNetworkStandardType;
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
        hash += (mobileNetworkStandardID != null ? mobileNetworkStandardID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mobilenetworkstandard)) {
            return false;
        }
        Mobilenetworkstandard other = (Mobilenetworkstandard) object;
        if ((this.mobileNetworkStandardID == null && other.mobileNetworkStandardID != null) || (this.mobileNetworkStandardID != null && !this.mobileNetworkStandardID.equals(other.mobileNetworkStandardID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Mobilenetworkstandard[ mobileNetworkStandardID=" + mobileNetworkStandardID + " ]";
    }
    
}
