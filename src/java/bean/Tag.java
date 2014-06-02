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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alphawong
 */
@Entity
@Table(name = "tag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t"),
    @NamedQuery(name = "Tag.findByTagID", query = "SELECT t FROM Tag t WHERE t.tagID = :tagID"),
    @NamedQuery(name = "Tag.findByCreationTime", query = "SELECT t FROM Tag t WHERE t.creationTime = :creationTime")})
public class Tag implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tagID")
    private Integer tagID;
    @Basic(optional = false)
    @Column(name = "creationTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    @JoinColumn(name = "storeID", referencedColumnName = "storeID")
    @ManyToOne(optional = false)
    private Store storeID;
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private User userID;

    public Tag() {
    }

    public Tag(Integer tagID) {
        this.tagID = tagID;
    }

    public Tag(Integer tagID, Date creationTime) {
        this.tagID = tagID;
        this.creationTime = creationTime;
    }

    public Integer getTagID() {
        return tagID;
    }

    public void setTagID(Integer tagID) {
        this.tagID = tagID;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Store getStoreID() {
        return storeID;
    }

    public void setStoreID(Store storeID) {
        this.storeID = storeID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tagID != null ? tagID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tag)) {
            return false;
        }
        Tag other = (Tag) object;
        if ((this.tagID == null && other.tagID != null) || (this.tagID != null && !this.tagID.equals(other.tagID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Tag[ tagID=" + tagID + " ]";
    }
    
}
