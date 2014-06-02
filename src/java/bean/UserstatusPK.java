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
public class UserstatusPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "statusID")
    private int statusID;
    @Basic(optional = false)
    @Column(name = "userID")
    private String userID;

    public UserstatusPK() {
    }

    public UserstatusPK(int statusID, String userID) {
        this.statusID = statusID;
        this.userID = userID;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) statusID;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserstatusPK)) {
            return false;
        }
        UserstatusPK other = (UserstatusPK) object;
        if (this.statusID != other.statusID) {
            return false;
        }
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.UserstatusPK[ statusID=" + statusID + ", userID=" + userID + " ]";
    }
    
}
