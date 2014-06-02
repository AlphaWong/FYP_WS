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
import javax.persistence.OneToOne;
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
@Table(name = "merchandise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Merchandise.findAll", query = "SELECT m FROM Merchandise m"),
    @NamedQuery(name = "Merchandise.findByMerchandiseID", query = "SELECT m FROM Merchandise m WHERE m.merchandisePK.merchandiseID = :merchandiseID"),
    @NamedQuery(name = "Merchandise.findByCategoryID", query = "SELECT m FROM Merchandise m WHERE m.merchandisePK.categoryID = :categoryID"),
    @NamedQuery(name = "Merchandise.findByMerchandiseName", query = "SELECT m FROM Merchandise m WHERE m.merchandiseName = :merchandiseName"),
    @NamedQuery(name = "Merchandise.findByListingYear", query = "SELECT m FROM Merchandise m WHERE m.listingYear = :listingYear"),
    @NamedQuery(name = "Merchandise.findByReferenceLink", query = "SELECT m FROM Merchandise m WHERE m.referenceLink = :referenceLink")})
public class Merchandise implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MerchandisePK merchandisePK;
    @Basic(optional = false)
    @Column(name = "merchandiseName")
    private String merchandiseName;
    @Lob
    @Column(name = "merchandiseImage")
    private String merchandiseImage;
    @Basic(optional = false)
    @Lob
    @Column(name = "merchandiseDesc")
    private String merchandiseDesc;
    @Column(name = "listingYear")
    @Temporal(TemporalType.DATE)
    private Date listingYear;
    @Column(name = "referenceLink")
    private String referenceLink;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "merchandise")
    private Collection<Mobile> mobileCollection;
    @JoinColumn(name = "brandID", referencedColumnName = "brandID")
    @ManyToOne(optional = false)
    private Brand brandID;
    @JoinColumn(name = "categoryID", referencedColumnName = "categoryID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Category category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "merchandise")
    private Collection<Storemerchandise> storemerchandiseCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "merchandise")
    private Cloth cloth;

    public Merchandise() {
    }

    public Merchandise(MerchandisePK merchandisePK) {
        this.merchandisePK = merchandisePK;
    }

    public Merchandise(MerchandisePK merchandisePK, String merchandiseName, String merchandiseDesc) {
        this.merchandisePK = merchandisePK;
        this.merchandiseName = merchandiseName;
        this.merchandiseDesc = merchandiseDesc;
    }

    public Merchandise(int merchandiseID, int categoryID) {
        this.merchandisePK = new MerchandisePK(merchandiseID, categoryID);
    }

    public MerchandisePK getMerchandisePK() {
        return merchandisePK;
    }

    public void setMerchandisePK(MerchandisePK merchandisePK) {
        this.merchandisePK = merchandisePK;
    }

    public String getMerchandiseName() {
        return merchandiseName;
    }

    public void setMerchandiseName(String merchandiseName) {
        this.merchandiseName = merchandiseName;
    }

    public String getMerchandiseImage() {
        return merchandiseImage;
    }

    public void setMerchandiseImage(String merchandiseImage) {
        this.merchandiseImage = merchandiseImage;
    }

    public String getMerchandiseDesc() {
        return merchandiseDesc;
    }

    public void setMerchandiseDesc(String merchandiseDesc) {
        this.merchandiseDesc = merchandiseDesc;
    }

    public Date getListingYear() {
        return listingYear;
    }

    public void setListingYear(Date listingYear) {
        this.listingYear = listingYear;
    }

    public String getReferenceLink() {
        return referenceLink;
    }

    public void setReferenceLink(String referenceLink) {
        this.referenceLink = referenceLink;
    }

    @XmlTransient
    public Collection<Mobile> getMobileCollection() {
        return mobileCollection;
    }

    public void setMobileCollection(Collection<Mobile> mobileCollection) {
        this.mobileCollection = mobileCollection;
    }

    public Brand getBrandID() {
        return brandID;
    }

    public void setBrandID(Brand brandID) {
        this.brandID = brandID;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @XmlTransient
    public Collection<Storemerchandise> getStoremerchandiseCollection() {
        return storemerchandiseCollection;
    }

    public void setStoremerchandiseCollection(Collection<Storemerchandise> storemerchandiseCollection) {
        this.storemerchandiseCollection = storemerchandiseCollection;
    }

    public Cloth getCloth() {
        return cloth;
    }

    public void setCloth(Cloth cloth) {
        this.cloth = cloth;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (merchandisePK != null ? merchandisePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Merchandise)) {
            return false;
        }
        Merchandise other = (Merchandise) object;
        if ((this.merchandisePK == null && other.merchandisePK != null) || (this.merchandisePK != null && !this.merchandisePK.equals(other.merchandisePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Merchandise[ merchandisePK=" + merchandisePK + " ]";
    }
    
}
