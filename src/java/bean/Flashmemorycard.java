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
@Table(name = "flashmemorycard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flashmemorycard.findAll", query = "SELECT f FROM Flashmemorycard f"),
    @NamedQuery(name = "Flashmemorycard.findByFlashMemoryCardID", query = "SELECT f FROM Flashmemorycard f WHERE f.flashMemoryCardID = :flashMemoryCardID"),
    @NamedQuery(name = "Flashmemorycard.findByFlashMemoryCardName", query = "SELECT f FROM Flashmemorycard f WHERE f.flashMemoryCardName = :flashMemoryCardName")})
public class Flashmemorycard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "flashMemoryCardID")
    private Integer flashMemoryCardID;
    @Basic(optional = false)
    @Column(name = "flashMemoryCardName")
    private String flashMemoryCardName;
    @OneToMany(mappedBy = "flashMemoryCardID")
    private Collection<Mobile> mobileCollection;

    public Flashmemorycard() {
    }

    public Flashmemorycard(Integer flashMemoryCardID) {
        this.flashMemoryCardID = flashMemoryCardID;
    }

    public Flashmemorycard(Integer flashMemoryCardID, String flashMemoryCardName) {
        this.flashMemoryCardID = flashMemoryCardID;
        this.flashMemoryCardName = flashMemoryCardName;
    }

    public Integer getFlashMemoryCardID() {
        return flashMemoryCardID;
    }

    public void setFlashMemoryCardID(Integer flashMemoryCardID) {
        this.flashMemoryCardID = flashMemoryCardID;
    }

    public String getFlashMemoryCardName() {
        return flashMemoryCardName;
    }

    public void setFlashMemoryCardName(String flashMemoryCardName) {
        this.flashMemoryCardName = flashMemoryCardName;
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
        hash += (flashMemoryCardID != null ? flashMemoryCardID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flashmemorycard)) {
            return false;
        }
        Flashmemorycard other = (Flashmemorycard) object;
        if ((this.flashMemoryCardID == null && other.flashMemoryCardID != null) || (this.flashMemoryCardID != null && !this.flashMemoryCardID.equals(other.flashMemoryCardID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Flashmemorycard[ flashMemoryCardID=" + flashMemoryCardID + " ]";
    }
    
}
