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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findByFrom", query = "SELECT m FROM Message m WHERE m.messagePK.from = :from"),
    @NamedQuery(name = "Message.findByRoomID", query = "SELECT m FROM Message m WHERE m.messagePK.roomID = :roomID"),
    @NamedQuery(name = "Message.findByPostTime", query = "SELECT m FROM Message m WHERE m.postTime = :postTime")})
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MessagePK messagePK;
    @Lob
    @Column(name = "msgContent")
    private String msgContent;
    @Basic(optional = false)
    @Column(name = "postTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postTime;
    @JoinColumn(name = "roomID", referencedColumnName = "roomID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Chatroom chatroom;
    @JoinColumn(name = "from", referencedColumnName = "userID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Message() {
    }

    public Message(MessagePK messagePK) {
        this.messagePK = messagePK;
    }

    public Message(MessagePK messagePK, Date postTime) {
        this.messagePK = messagePK;
        this.postTime = postTime;
    }

    public Message(String from, int roomID) {
        this.messagePK = new MessagePK(from, roomID);
    }

    public MessagePK getMessagePK() {
        return messagePK;
    }

    public void setMessagePK(MessagePK messagePK) {
        this.messagePK = messagePK;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Chatroom getChatroom() {
        return chatroom;
    }

    public void setChatroom(Chatroom chatroom) {
        this.chatroom = chatroom;
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
        hash += (messagePK != null ? messagePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.messagePK == null && other.messagePK != null) || (this.messagePK != null && !this.messagePK.equals(other.messagePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Message[ messagePK=" + messagePK + " ]";
    }
    
}
