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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alphawong
 */
@Entity
@Table(name = "comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findByUserID", query = "SELECT c FROM Comment c WHERE c.commentPK.userID = :userID"),
    @NamedQuery(name = "Comment.findByStoreID", query = "SELECT c FROM Comment c WHERE c.commentPK.storeID = :storeID"),
    @NamedQuery(name = "Comment.findByPostTime", query = "SELECT c FROM Comment c WHERE c.commentPK.postTime = :postTime"),
    @NamedQuery(name = "Comment.findByRank", query = "SELECT c FROM Comment c WHERE c.rank = :rank")})
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommentPK commentPK;
    @Basic(optional = false)
    @Lob
    @Column(name = "feedback")
    private String feedback;
    @Basic(optional = false)
    @Column(name = "rank")
    private short rank;
    @JoinColumn(name = "storeID", referencedColumnName = "storeID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Store store;
    @JoinColumn(name = "userID", referencedColumnName = "userID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Comment() {
    }

    public Comment(CommentPK commentPK) {
        this.commentPK = commentPK;
    }

    public Comment(CommentPK commentPK, String feedback, short rank) {
        this.commentPK = commentPK;
        this.feedback = feedback;
        this.rank = rank;
    }

    public Comment(String userID, int storeID, Date postTime) {
        this.commentPK = new CommentPK(userID, storeID, postTime);
    }

    public CommentPK getCommentPK() {
        return commentPK;
    }

    public void setCommentPK(CommentPK commentPK) {
        this.commentPK = commentPK;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public short getRank() {
        return rank;
    }

    public void setRank(short rank) {
        this.rank = rank;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
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
        hash += (commentPK != null ? commentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.commentPK == null && other.commentPK != null) || (this.commentPK != null && !this.commentPK.equals(other.commentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Comment[ commentPK=" + commentPK + " ]";
    }
    
}
