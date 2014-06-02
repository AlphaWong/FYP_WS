/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alphawong
 */
@Entity
@Table(name = "chatroom")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chatroom.findAll", query = "SELECT c FROM Chatroom c"),
    @NamedQuery(name = "Chatroom.findByRoomID", query = "SELECT c FROM Chatroom c WHERE c.roomID = :roomID"),
    @NamedQuery(name = "Chatroom.findByCreationTime", query = "SELECT c FROM Chatroom c WHERE c.creationTime = :creationTime")})
public class Chatroom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "roomID")
    private Integer roomID;
    @Basic(optional = false)
    @Lob
    @Column(name = "roomName")
    private String roomName;
    @Lob
    @Column(name = "roomImage")
    private String roomImage;
    @Lob
    @Column(name = "roomDesc")
    private String roomDesc;
    @Basic(optional = false)
    @Column(name = "creationTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    @JoinColumn(name = "owner", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private User owner;
    @JoinColumn(name = "administrator", referencedColumnName = "userID")
    @ManyToOne
    private User administrator;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chatroom")
    private Collection<Message> messageCollection;

    public Chatroom() {
    }

    public Chatroom(Integer roomID) {
        this.roomID = roomID;
    }

    public Chatroom(Integer roomID, String roomName, Date creationTime) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.creationTime = creationTime;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getAdministrator() {
        return administrator;
    }

    public void setAdministrator(User administrator) {
        this.administrator = administrator;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomID != null ? roomID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chatroom)) {
            return false;
        }
        Chatroom other = (Chatroom) object;
        if ((this.roomID == null && other.roomID != null) || (this.roomID != null && !this.roomID.equals(other.roomID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Chatroom[ roomID=" + roomID + " ]";
    }
    
}
