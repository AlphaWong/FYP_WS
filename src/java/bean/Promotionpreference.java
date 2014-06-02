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
import javax.persistence.JoinColumns;
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
@Table(name = "promotionpreference")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promotionpreference.findAll", query = "SELECT p FROM Promotionpreference p"),
    @NamedQuery(name = "Promotionpreference.findByUserID", query = "SELECT p FROM Promotionpreference p WHERE p.promotionpreferencePK.userID = :userID"),
    @NamedQuery(name = "Promotionpreference.findByPromotionID", query = "SELECT p FROM Promotionpreference p WHERE p.promotionpreferencePK.promotionID = :promotionID"),
    @NamedQuery(name = "Promotionpreference.findByStoreID", query = "SELECT p FROM Promotionpreference p WHERE p.promotionpreferencePK.storeID = :storeID"),
    @NamedQuery(name = "Promotionpreference.findByNotificationSelection", query = "SELECT p FROM Promotionpreference p WHERE p.notificationSelection = :notificationSelection")})
public class Promotionpreference implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PromotionpreferencePK promotionpreferencePK;
    @Basic(optional = false)
    @Column(name = "notificationSelection")
    private boolean notificationSelection;
    @JoinColumns({
        @JoinColumn(name = "promotionID", referencedColumnName = "promotionID", insertable = false, updatable = false),
        @JoinColumn(name = "storeID", referencedColumnName = "storeID", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Promotionscheme promotionscheme;
    @JoinColumn(name = "userID", referencedColumnName = "userID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Promotionpreference() {
    }

    public Promotionpreference(PromotionpreferencePK promotionpreferencePK) {
        this.promotionpreferencePK = promotionpreferencePK;
    }

    public Promotionpreference(PromotionpreferencePK promotionpreferencePK, boolean notificationSelection) {
        this.promotionpreferencePK = promotionpreferencePK;
        this.notificationSelection = notificationSelection;
    }

    public Promotionpreference(String userID, int promotionID, int storeID) {
        this.promotionpreferencePK = new PromotionpreferencePK(userID, promotionID, storeID);
    }

    public PromotionpreferencePK getPromotionpreferencePK() {
        return promotionpreferencePK;
    }

    public void setPromotionpreferencePK(PromotionpreferencePK promotionpreferencePK) {
        this.promotionpreferencePK = promotionpreferencePK;
    }

    public boolean getNotificationSelection() {
        return notificationSelection;
    }

    public void setNotificationSelection(boolean notificationSelection) {
        this.notificationSelection = notificationSelection;
    }

    public Promotionscheme getPromotionscheme() {
        return promotionscheme;
    }

    public void setPromotionscheme(Promotionscheme promotionscheme) {
        this.promotionscheme = promotionscheme;
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
        hash += (promotionpreferencePK != null ? promotionpreferencePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promotionpreference)) {
            return false;
        }
        Promotionpreference other = (Promotionpreference) object;
        if ((this.promotionpreferencePK == null && other.promotionpreferencePK != null) || (this.promotionpreferencePK != null && !this.promotionpreferencePK.equals(other.promotionpreferencePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Promotionpreference[ promotionpreferencePK=" + promotionpreferencePK + " ]";
    }
    
}
