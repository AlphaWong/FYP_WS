/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author alphawong
 */
@Embeddable
public class CommentPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "userID")
    private String userID;
    @Basic(optional = false)
    @Column(name = "storeID")
    private int storeID;
    @Basic(optional = false)
    @Column(name = "postTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postTime;

    public CommentPK() {
    }

    public CommentPK(String userID, int storeID, Date postTime) {
        this.userID = userID;
        this.storeID = storeID;
        this.postTime = postTime;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        hash += (int) storeID;
        hash += (postTime != null ? postTime.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommentPK)) {
            return false;
        }
        CommentPK other = (CommentPK) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        if (this.storeID != other.storeID) {
            return false;
        }
        if ((this.postTime == null && other.postTime != null) || (this.postTime != null && !this.postTime.equals(other.postTime))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.CommentPK[ userID=" + userID + ", storeID=" + storeID + ", postTime=" + postTime + " ]";
    }
    
}
