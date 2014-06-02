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
public class MessagePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "from")
    private String from;
    @Basic(optional = false)
    @Column(name = "roomID")
    private int roomID;

    public MessagePK() {
    }

    public MessagePK(String from, int roomID) {
        this.from = from;
        this.roomID = roomID;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (from != null ? from.hashCode() : 0);
        hash += (int) roomID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessagePK)) {
            return false;
        }
        MessagePK other = (MessagePK) object;
        if ((this.from == null && other.from != null) || (this.from != null && !this.from.equals(other.from))) {
            return false;
        }
        if (this.roomID != other.roomID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.MessagePK[ from=" + from + ", roomID=" + roomID + " ]";
    }
    
}
