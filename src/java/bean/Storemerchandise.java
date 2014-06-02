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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "storemerchandise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Storemerchandise.findAll", query = "SELECT s FROM Storemerchandise s"),
    @NamedQuery(name = "Storemerchandise.findByStoreID", query = "SELECT s FROM Storemerchandise s WHERE s.storemerchandisePK.storeID = :storeID"),
    @NamedQuery(name = "Storemerchandise.findByMerchandiseID", query = "SELECT s FROM Storemerchandise s WHERE s.storemerchandisePK.merchandiseID = :merchandiseID"),
    @NamedQuery(name = "Storemerchandise.findByCategoryID", query = "SELECT s FROM Storemerchandise s WHERE s.storemerchandisePK.categoryID = :categoryID"),
    @NamedQuery(name = "Storemerchandise.findByStyleID", query = "SELECT s FROM Storemerchandise s WHERE s.storemerchandisePK.styleID = :styleID"),
    @NamedQuery(name = "Storemerchandise.findByPrice", query = "SELECT s FROM Storemerchandise s WHERE s.price = :price"),
    @NamedQuery(name = "Storemerchandise.findByMerchandiseImage1", query = "SELECT s FROM Storemerchandise s WHERE s.merchandiseImage1 = :merchandiseImage1"),
    @NamedQuery(name = "Storemerchandise.findByMerchandiseImage2", query = "SELECT s FROM Storemerchandise s WHERE s.merchandiseImage2 = :merchandiseImage2"),
    @NamedQuery(name = "Storemerchandise.findByMerchandiseImage3", query = "SELECT s FROM Storemerchandise s WHERE s.merchandiseImage3 = :merchandiseImage3")})
public class Storemerchandise implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StoremerchandisePK storemerchandisePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Lob
    @Column(name = "color")
    private String color;
    @Basic(optional = false)
    @Column(name = "merchandiseImage1")
    private String merchandiseImage1;
    @Column(name = "merchandiseImage2")
    private String merchandiseImage2;
    @Column(name = "merchandiseImage3")
    private String merchandiseImage3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storemerchandise")
    private Collection<Storeuppergarmentchoice> storeuppergarmentchoiceCollection;
    @JoinColumns({
        @JoinColumn(name = "merchandiseID", referencedColumnName = "merchandiseID", insertable = false, updatable = false),
        @JoinColumn(name = "categoryID", referencedColumnName = "categoryID", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Merchandise merchandise;
    @JoinColumn(name = "storeID", referencedColumnName = "storeID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Store store;

    public Storemerchandise() {
    }

    public Storemerchandise(StoremerchandisePK storemerchandisePK) {
        this.storemerchandisePK = storemerchandisePK;
    }

    public Storemerchandise(StoremerchandisePK storemerchandisePK, String merchandiseImage1) {
        this.storemerchandisePK = storemerchandisePK;
        this.merchandiseImage1 = merchandiseImage1;
    }

    public Storemerchandise(int storeID, int merchandiseID, int categoryID, int styleID) {
        this.storemerchandisePK = new StoremerchandisePK(storeID, merchandiseID, categoryID, styleID);
    }

    public StoremerchandisePK getStoremerchandisePK() {
        return storemerchandisePK;
    }

    public void setStoremerchandisePK(StoremerchandisePK storemerchandisePK) {
        this.storemerchandisePK = storemerchandisePK;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMerchandiseImage1() {
        return merchandiseImage1;
    }

    public void setMerchandiseImage1(String merchandiseImage1) {
        this.merchandiseImage1 = merchandiseImage1;
    }

    public String getMerchandiseImage2() {
        return merchandiseImage2;
    }

    public void setMerchandiseImage2(String merchandiseImage2) {
        this.merchandiseImage2 = merchandiseImage2;
    }

    public String getMerchandiseImage3() {
        return merchandiseImage3;
    }

    public void setMerchandiseImage3(String merchandiseImage3) {
        this.merchandiseImage3 = merchandiseImage3;
    }

    @XmlTransient
    public Collection<Storeuppergarmentchoice> getStoreuppergarmentchoiceCollection() {
        return storeuppergarmentchoiceCollection;
    }

    public void setStoreuppergarmentchoiceCollection(Collection<Storeuppergarmentchoice> storeuppergarmentchoiceCollection) {
        this.storeuppergarmentchoiceCollection = storeuppergarmentchoiceCollection;
    }

    public Merchandise getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(Merchandise merchandise) {
        this.merchandise = merchandise;
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
        hash += (storemerchandisePK != null ? storemerchandisePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Storemerchandise)) {
            return false;
        }
        Storemerchandise other = (Storemerchandise) object;
        if ((this.storemerchandisePK == null && other.storemerchandisePK != null) || (this.storemerchandisePK != null && !this.storemerchandisePK.equals(other.storemerchandisePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Storemerchandise[ storemerchandisePK=" + storemerchandisePK + " ]";
    }
    
}
