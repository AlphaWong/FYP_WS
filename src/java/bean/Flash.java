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
@Table(name = "flash")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flash.findAll", query = "SELECT f FROM Flash f"),
    @NamedQuery(name = "Flash.findByFlashID", query = "SELECT f FROM Flash f WHERE f.flashID = :flashID"),
    @NamedQuery(name = "Flash.findByFlashName", query = "SELECT f FROM Flash f WHERE f.flashName = :flashName")})
public class Flash implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "flashID")
    private Integer flashID;
    @Basic(optional = false)
    @Column(name = "flashName")
    private String flashName;
    @OneToMany(mappedBy = "flashID")
    private Collection<Mobile> mobileCollection;

    public Flash() {
    }

    public Flash(Integer flashID) {
        this.flashID = flashID;
    }

    public Flash(Integer flashID, String flashName) {
        this.flashID = flashID;
        this.flashName = flashName;
    }

    public Integer getFlashID() {
        return flashID;
    }

    public void setFlashID(Integer flashID) {
        this.flashID = flashID;
    }

    public String getFlashName() {
        return flashName;
    }

    public void setFlashName(String flashName) {
        this.flashName = flashName;
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
        hash += (flashID != null ? flashID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flash)) {
            return false;
        }
        Flash other = (Flash) object;
        if ((this.flashID == null && other.flashID != null) || (this.flashID != null && !this.flashID.equals(other.flashID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Flash[ flashID=" + flashID + " ]";
    }
    
}
