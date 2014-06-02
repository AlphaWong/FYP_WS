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
@Table(name = "screenmaterial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Screenmaterial.findAll", query = "SELECT s FROM Screenmaterial s"),
    @NamedQuery(name = "Screenmaterial.findByScreenMaterialID", query = "SELECT s FROM Screenmaterial s WHERE s.screenMaterialID = :screenMaterialID"),
    @NamedQuery(name = "Screenmaterial.findByScreenMaterialName", query = "SELECT s FROM Screenmaterial s WHERE s.screenMaterialName = :screenMaterialName")})
public class Screenmaterial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "screenMaterialID")
    private Integer screenMaterialID;
    @Basic(optional = false)
    @Column(name = "screenMaterialName")
    private String screenMaterialName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "screenMaterialID")
    private Collection<Mobile> mobileCollection;

    public Screenmaterial() {
    }

    public Screenmaterial(Integer screenMaterialID) {
        this.screenMaterialID = screenMaterialID;
    }

    public Screenmaterial(Integer screenMaterialID, String screenMaterialName) {
        this.screenMaterialID = screenMaterialID;
        this.screenMaterialName = screenMaterialName;
    }

    public Integer getScreenMaterialID() {
        return screenMaterialID;
    }

    public void setScreenMaterialID(Integer screenMaterialID) {
        this.screenMaterialID = screenMaterialID;
    }

    public String getScreenMaterialName() {
        return screenMaterialName;
    }

    public void setScreenMaterialName(String screenMaterialName) {
        this.screenMaterialName = screenMaterialName;
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
        hash += (screenMaterialID != null ? screenMaterialID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Screenmaterial)) {
            return false;
        }
        Screenmaterial other = (Screenmaterial) object;
        if ((this.screenMaterialID == null && other.screenMaterialID != null) || (this.screenMaterialID != null && !this.screenMaterialID.equals(other.screenMaterialID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Screenmaterial[ screenMaterialID=" + screenMaterialID + " ]";
    }
    
}
