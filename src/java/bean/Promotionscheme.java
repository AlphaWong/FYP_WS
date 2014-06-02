/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alphawong
 */
@Entity
@Table(name = "promotionscheme")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promotionscheme.findAll", query = "SELECT p FROM Promotionscheme p"),
    @NamedQuery(name = "Promotionscheme.findByPromotionID", query = "SELECT p FROM Promotionscheme p WHERE p.promotionschemePK.promotionID = :promotionID"),
    @NamedQuery(name = "Promotionscheme.findByStartDate", query = "SELECT p FROM Promotionscheme p WHERE p.startDate = :startDate"),
    @NamedQuery(name = "Promotionscheme.findByEndDate", query = "SELECT p FROM Promotionscheme p WHERE p.endDate = :endDate"),
    @NamedQuery(name = "Promotionscheme.findByStoreID", query = "SELECT p FROM Promotionscheme p WHERE p.promotionschemePK.storeID = :storeID")})
public class Promotionscheme implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PromotionschemePK promotionschemePK;
    @Basic(optional = false)
    @Column(name = "startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @Lob
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "promotionscheme")
    private Collection<Promotionpreference> promotionpreferenceCollection;
    @JoinColumn(name = "storeID", referencedColumnName = "storeID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Store store;

    public Promotionscheme() {
    }

    public Promotionscheme(PromotionschemePK promotionschemePK) {
        this.promotionschemePK = promotionschemePK;
    }

    public Promotionscheme(PromotionschemePK promotionschemePK, Date startDate, Date endDate, String description) {
        this.promotionschemePK = promotionschemePK;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public Promotionscheme(int promotionID, int storeID) {
        this.promotionschemePK = new PromotionschemePK(promotionID, storeID);
    }

    public PromotionschemePK getPromotionschemePK() {
        return promotionschemePK;
    }

    public void setPromotionschemePK(PromotionschemePK promotionschemePK) {
        this.promotionschemePK = promotionschemePK;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Promotionpreference> getPromotionpreferenceCollection() {
        return promotionpreferenceCollection;
    }

    public void setPromotionpreferenceCollection(Collection<Promotionpreference> promotionpreferenceCollection) {
        this.promotionpreferenceCollection = promotionpreferenceCollection;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (promotionschemePK != null ? promotionschemePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promotionscheme)) {
            return false;
        }
        Promotionscheme other = (Promotionscheme) object;
        if ((this.promotionschemePK == null && other.promotionschemePK != null) || (this.promotionschemePK != null && !this.promotionschemePK.equals(other.promotionschemePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Promotionscheme[ promotionschemePK=" + promotionschemePK + " ]";
    }
    
}
