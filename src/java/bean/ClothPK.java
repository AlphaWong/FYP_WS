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
public class ClothPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "clothID")
    private int clothID;
    @Basic(optional = false)
    @Column(name = "categoryID")
    private int categoryID;

    public ClothPK() {
    }

    public ClothPK(int clothID, int categoryID) {
        this.clothID = clothID;
        this.categoryID = categoryID;
    }

    public int getClothID() {
        return clothID;
    }

    public void setClothID(int clothID) {
        this.clothID = clothID;
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
        hash += (int) clothID;
        hash += (int) categoryID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClothPK)) {
            return false;
        }
        ClothPK other = (ClothPK) object;
        if (this.clothID != other.clothID) {
            return false;
        }
        if (this.categoryID != other.categoryID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.ClothPK[ clothID=" + clothID + ", categoryID=" + categoryID + " ]";
    }
    
}
