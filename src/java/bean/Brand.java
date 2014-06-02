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
import javax.persistence.Lob;
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
@Table(name = "brand")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Brand.findAll", query = "SELECT b FROM Brand b"),
    @NamedQuery(name = "Brand.findByBrandID", query = "SELECT b FROM Brand b WHERE b.brandID = :brandID"),
    @NamedQuery(name = "Brand.findByBrandRegion", query = "SELECT b FROM Brand b WHERE b.brandRegion = :brandRegion")})
public class Brand implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "brandID")
    private Integer brandID;
    @Basic(optional = false)
    @Lob
    @Column(name = "brandName")
    private String brandName;
    @Lob
    @Column(name = "brandIcon")
    private String brandIcon;
    @Basic(optional = false)
    @Column(name = "brandRegion")
    private String brandRegion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brandID")
    private Collection<Merchandise> merchandiseCollection;

    public Brand() {
    }

    public Brand(Integer brandID) {
        this.brandID = brandID;
    }

    public Brand(Integer brandID, String brandName, String brandRegion) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.brandRegion = brandRegion;
    }

    public Integer getBrandID() {
        return brandID;
    }

    public void setBrandID(Integer brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandIcon() {
        return brandIcon;
    }

    public void setBrandIcon(String brandIcon) {
        this.brandIcon = brandIcon;
    }

    public String getBrandRegion() {
        return brandRegion;
    }

    public void setBrandRegion(String brandRegion) {
        this.brandRegion = brandRegion;
    }

    @XmlTransient
    public Collection<Merchandise> getMerchandiseCollection() {
        return merchandiseCollection;
    }

    public void setMerchandiseCollection(Collection<Merchandise> merchandiseCollection) {
        this.merchandiseCollection = merchandiseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brandID != null ? brandID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Brand)) {
            return false;
        }
        Brand other = (Brand) object;
        if ((this.brandID == null && other.brandID != null) || (this.brandID != null && !this.brandID.equals(other.brandID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Brand[ brandID=" + brandID + " ]";
    }
    
}
