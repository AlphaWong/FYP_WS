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
public class PromotionschemePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "promotionID")
    private int promotionID;
    @Basic(optional = false)
    @Column(name = "storeID")
    private int storeID;

    public PromotionschemePK() {
    }

    public PromotionschemePK(int promotionID, int storeID) {
        this.promotionID = promotionID;
        this.storeID = storeID;
    }

    public int getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) promotionID;
        hash += (int) storeID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PromotionschemePK)) {
            return false;
        }
        PromotionschemePK other = (PromotionschemePK) object;
        if (this.promotionID != other.promotionID) {
            return false;
        }
        if (this.storeID != other.storeID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.PromotionschemePK[ promotionID=" + promotionID + ", storeID=" + storeID + " ]";
    }
    
}
