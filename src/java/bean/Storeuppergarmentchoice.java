/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alphawong
 */
@Entity
@Table(name = "storeuppergarmentchoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Storeuppergarmentchoice.findAll", query = "SELECT s FROM Storeuppergarmentchoice s"),
    @NamedQuery(name = "Storeuppergarmentchoice.findByStoreID", query = "SELECT s FROM Storeuppergarmentchoice s WHERE s.storeuppergarmentchoicePK.storeID = :storeID"),
    @NamedQuery(name = "Storeuppergarmentchoice.findByUpperGarmentID", query = "SELECT s FROM Storeuppergarmentchoice s WHERE s.storeuppergarmentchoicePK.upperGarmentID = :upperGarmentID"),
    @NamedQuery(name = "Storeuppergarmentchoice.findByStyleID", query = "SELECT s FROM Storeuppergarmentchoice s WHERE s.storeuppergarmentchoicePK.styleID = :styleID"),
    @NamedQuery(name = "Storeuppergarmentchoice.findByUpperGarmentSizeID", query = "SELECT s FROM Storeuppergarmentchoice s WHERE s.storeuppergarmentchoicePK.upperGarmentSizeID = :upperGarmentSizeID"),
    @NamedQuery(name = "Storeuppergarmentchoice.findByUpperGarmentSize", query = "SELECT s FROM Storeuppergarmentchoice s WHERE s.upperGarmentSize = :upperGarmentSize"),
    @NamedQuery(name = "Storeuppergarmentchoice.findByQuantity", query = "SELECT s FROM Storeuppergarmentchoice s WHERE s.quantity = :quantity")})
public class Storeuppergarmentchoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StoreuppergarmentchoicePK storeuppergarmentchoicePK;
    @Basic(optional = false)
    @Column(name = "upperGarmentSize")
    private String upperGarmentSize;
    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;
    @JoinColumns({
        @JoinColumn(name = "storeID", referencedColumnName = "storeID", insertable = false, updatable = false),
        @JoinColumn(name = "upperGarmentID", referencedColumnName = "merchandiseID", insertable = false, updatable = false),
        @JoinColumn(name = "categoryID", referencedColumnName = "categoryID"),
        @JoinColumn(name = "styleID", referencedColumnName = "styleID", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Storemerchandise storemerchandise;

    public Storeuppergarmentchoice() {
    }

    public Storeuppergarmentchoice(StoreuppergarmentchoicePK storeuppergarmentchoicePK) {
        this.storeuppergarmentchoicePK = storeuppergarmentchoicePK;
    }

    public Storeuppergarmentchoice(StoreuppergarmentchoicePK storeuppergarmentchoicePK, String upperGarmentSize, int quantity) {
        this.storeuppergarmentchoicePK = storeuppergarmentchoicePK;
        this.upperGarmentSize = upperGarmentSize;
        this.quantity = quantity;
    }

    public Storeuppergarmentchoice(int storeID, int upperGarmentID, int styleID, int upperGarmentSizeID) {
        this.storeuppergarmentchoicePK = new StoreuppergarmentchoicePK(storeID, upperGarmentID, styleID, upperGarmentSizeID);
    }

    public StoreuppergarmentchoicePK getStoreuppergarmentchoicePK() {
        return storeuppergarmentchoicePK;
    }

    public void setStoreuppergarmentchoicePK(StoreuppergarmentchoicePK storeuppergarmentchoicePK) {
        this.storeuppergarmentchoicePK = storeuppergarmentchoicePK;
    }

    public String getUpperGarmentSize() {
        return upperGarmentSize;
    }

    public void setUpperGarmentSize(String upperGarmentSize) {
        this.upperGarmentSize = upperGarmentSize;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Storemerchandise getStoremerchandise() {
        return storemerchandise;
    }

    public void setStoremerchandise(Storemerchandise storemerchandise) {
        this.storemerchandise = storemerchandise;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeuppergarmentchoicePK != null ? storeuppergarmentchoicePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Storeuppergarmentchoice)) {
            return false;
        }
        Storeuppergarmentchoice other = (Storeuppergarmentchoice) object;
        if ((this.storeuppergarmentchoicePK == null && other.storeuppergarmentchoicePK != null) || (this.storeuppergarmentchoicePK != null && !this.storeuppergarmentchoicePK.equals(other.storeuppergarmentchoicePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Storeuppergarmentchoice[ storeuppergarmentchoicePK=" + storeuppergarmentchoicePK + " ]";
    }
    
}
