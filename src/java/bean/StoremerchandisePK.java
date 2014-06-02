/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author alphawong
 */
@Embeddable
public class StoremerchandisePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "storeID")
    private int storeID;
    @Basic(optional = false)
    @Column(name = "merchandiseID")
    private int merchandiseID;
    @Basic(optional = false)
    @Column(name = "categoryID")
    private int categoryID;
    @Basic(optional = false)
    @Column(name = "styleID")
    private int styleID;

    public StoremerchandisePK() {
    }

    public StoremerchandisePK(int storeID, int merchandiseID, int categoryID, int styleID) {
        this.storeID = storeID;
        this.merchandiseID = merchandiseID;
        this.categoryID = categoryID;
        this.styleID = styleID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getMerchandiseID() {
        return merchandiseID;
    }

    public void setMerchandiseID(int merchandiseID) {
        this.merchandiseID = merchandiseID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getStyleID() {
        return styleID;
    }

    public void setStyleID(int styleID) {
        this.styleID = styleID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) storeID;
        hash += (int) merchandiseID;
        hash += (int) categoryID;
        hash += (int) styleID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoremerchandisePK)) {
            return false;
        }
        StoremerchandisePK other = (StoremerchandisePK) object;
        if (this.storeID != other.storeID) {
            return false;
        }
        if (this.merchandiseID != other.merchandiseID) {
            return false;
        }
        if (this.categoryID != other.categoryID) {
            return false;
        }
        if (this.styleID != other.styleID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.StoremerchandisePK[ storeID=" + storeID + ", merchandiseID=" + merchandiseID + ", categoryID=" + categoryID + ", styleID=" + styleID + " ]";
    }
    
}
