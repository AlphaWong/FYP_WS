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
public class MerchandisePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "merchandiseID")
    private int merchandiseID;
    @Basic(optional = false)
    @Column(name = "categoryID")
    private int categoryID;

    public MerchandisePK() {
    }

    public MerchandisePK(int merchandiseID, int categoryID) {
        this.merchandiseID = merchandiseID;
        this.categoryID = categoryID;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) merchandiseID;
        hash += (int) categoryID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MerchandisePK)) {
            return false;
        }
        MerchandisePK other = (MerchandisePK) object;
        if (this.merchandiseID != other.merchandiseID) {
            return false;
        }
        if (this.categoryID != other.categoryID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.MerchandisePK[ merchandiseID=" + merchandiseID + ", categoryID=" + categoryID + " ]";
    }
    
}
