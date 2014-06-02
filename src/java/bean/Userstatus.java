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
import javax.persistence.Lob;
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
@Table(name = "userstatus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userstatus.findAll", query = "SELECT u FROM Userstatus u"),
    @NamedQuery(name = "Userstatus.findByStatusID", query = "SELECT u FROM Userstatus u WHERE u.userstatusPK.statusID = :statusID"),
    @NamedQuery(name = "Userstatus.findByUserID", query = "SELECT u FROM Userstatus u WHERE u.userstatusPK.userID = :userID")})
public class Userstatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserstatusPK userstatusPK;
    @Basic(optional = false)
    @Lob
    @Column(name = "statusName")
    private String statusName;
    @Basic(optional = false)
    @Lob
    @Column(name = "statusColor")
    private String statusColor;
    @Lob
    @Column(name = "statusDesc")
    private String statusDesc;
    @JoinColumn(name = "userID", referencedColumnName = "userID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Userstatus() {
    }

    public Userstatus(UserstatusPK userstatusPK) {
        this.userstatusPK = userstatusPK;
    }

    public Userstatus(UserstatusPK userstatusPK, String statusName, String statusColor) {
        this.userstatusPK = userstatusPK;
        this.statusName = statusName;
        this.statusColor = statusColor;
    }

    public Userstatus(int statusID, String userID) {
        this.userstatusPK = new UserstatusPK(statusID, userID);
    }

    public UserstatusPK getUserstatusPK() {
        return userstatusPK;
    }

    public void setUserstatusPK(UserstatusPK userstatusPK) {
        this.userstatusPK = userstatusPK;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusColor() {
        return statusColor;
    }

    public void setStatusColor(String statusColor) {
        this.statusColor = statusColor;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userstatusPK != null ? userstatusPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userstatus)) {
            return false;
        }
        Userstatus other = (Userstatus) object;
        if ((this.userstatusPK == null && other.userstatusPK != null) || (this.userstatusPK != null && !this.userstatusPK.equals(other.userstatusPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Userstatus[ userstatusPK=" + userstatusPK + " ]";
    }
    
}
