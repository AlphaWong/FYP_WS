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
@Table(name = "connectivityandsharing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Connectivityandsharing.findAll", query = "SELECT c FROM Connectivityandsharing c"),
    @NamedQuery(name = "Connectivityandsharing.findByConnectivityAndSharingID", query = "SELECT c FROM Connectivityandsharing c WHERE c.connectivityAndSharingID = :connectivityAndSharingID"),
    @NamedQuery(name = "Connectivityandsharing.findByConnectivityAndSharingName", query = "SELECT c FROM Connectivityandsharing c WHERE c.connectivityAndSharingName = :connectivityAndSharingName")})
public class Connectivityandsharing implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "connectivityAndSharingID")
    private Integer connectivityAndSharingID;
    @Basic(optional = false)
    @Column(name = "connectivityAndSharingName")
    private String connectivityAndSharingName;
    @JoinTable(name = "mobile_connectivityandsharing", joinColumns = {
        @JoinColumn(name = "connectivityAndSharingID", referencedColumnName = "connectivityAndSharingID")}, inverseJoinColumns = {
        @JoinColumn(name = "mobileID", referencedColumnName = "mobileID")})
    @ManyToMany
    private Collection<Mobile> mobileCollection;

    public Connectivityandsharing() {
    }

    public Connectivityandsharing(Integer connectivityAndSharingID) {
        this.connectivityAndSharingID = connectivityAndSharingID;
    }

    public Connectivityandsharing(Integer connectivityAndSharingID, String connectivityAndSharingName) {
        this.connectivityAndSharingID = connectivityAndSharingID;
        this.connectivityAndSharingName = connectivityAndSharingName;
    }

    public Integer getConnectivityAndSharingID() {
        return connectivityAndSharingID;
    }

    public void setConnectivityAndSharingID(Integer connectivityAndSharingID) {
        this.connectivityAndSharingID = connectivityAndSharingID;
    }

    public String getConnectivityAndSharingName() {
        return connectivityAndSharingName;
    }

    public void setConnectivityAndSharingName(String connectivityAndSharingName) {
        this.connectivityAndSharingName = connectivityAndSharingName;
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
        hash += (connectivityAndSharingID != null ? connectivityAndSharingID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Connectivityandsharing)) {
            return false;
        }
        Connectivityandsharing other = (Connectivityandsharing) object;
        if ((this.connectivityAndSharingID == null && other.connectivityAndSharingID != null) || (this.connectivityAndSharingID != null && !this.connectivityAndSharingID.equals(other.connectivityAndSharingID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Connectivityandsharing[ connectivityAndSharingID=" + connectivityAndSharingID + " ]";
    }
    
}
