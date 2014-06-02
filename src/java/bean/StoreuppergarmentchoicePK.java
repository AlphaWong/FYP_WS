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
public class StoreuppergarmentchoicePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "storeID")
    private int storeID;
    @Basic(optional = false)
    @Column(name = "upperGarmentID")
    private int upperGarmentID;
    @Basic(optional = false)
    @Column(name = "styleID")
    private int styleID;
    @Basic(optional = false)
    @Column(name = "upperGarmentSizeID")
    private int upperGarmentSizeID;

    public StoreuppergarmentchoicePK() {
    }

    public StoreuppergarmentchoicePK(int storeID, int upperGarmentID, int styleID, int upperGarmentSizeID) {
        this.storeID = storeID;
        this.upperGarmentID = upperGarmentID;
        this.styleID = styleID;
        this.upperGarmentSizeID = upperGarmentSizeID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getUpperGarmentID() {
        return upperGarmentID;
    }

    public void setUpperGarmentID(int upperGarmentID) {
        this.upperGarmentID = upperGarmentID;
    }

    public int getStyleID() {
        return styleID;
    }

    public void setStyleID(int styleID) {
        this.styleID = styleID;
    }

    public int getUpperGarmentSizeID() {
        return upperGarmentSizeID;
    }

    public void setUpperGarmentSizeID(int upperGarmentSizeID) {
        this.upperGarmentSizeID = upperGarmentSizeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) storeID;
        hash += (int) upperGarmentID;
        hash += (int) styleID;
        hash += (int) upperGarmentSizeID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreuppergarmentchoicePK)) {
            return false;
        }
        StoreuppergarmentchoicePK other = (StoreuppergarmentchoicePK) object;
        if (this.storeID != other.storeID) {
            return false;
        }
        if (this.upperGarmentID != other.upperGarmentID) {
            return false;
        }
        if (this.styleID != other.styleID) {
            return false;
        }
        if (this.upperGarmentSizeID != other.upperGarmentSizeID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.StoreuppergarmentchoicePK[ storeID=" + storeID + ", upperGarmentID=" + upperGarmentID + ", styleID=" + styleID + ", upperGarmentSizeID=" + upperGarmentSizeID + " ]";
    }
    
}
