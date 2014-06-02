/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alphawong
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserID", query = "SELECT u FROM User u WHERE u.userID = :userID"),
    @NamedQuery(name = "User.findByUserName", query = "SELECT u FROM User u WHERE u.userName = :userName"),
    @NamedQuery(name = "User.findBySex", query = "SELECT u FROM User u WHERE u.sex = :sex"),
    @NamedQuery(name = "User.findByAge", query = "SELECT u FROM User u WHERE u.age = :age"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByCredit", query = "SELECT u FROM User u WHERE u.credit = :credit"),
    @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role = :role")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "userID")
    private String userID;
    @Basic(optional = false)
    @Column(name = "userName")
    private String userName;
    @Basic(optional = false)
    @Lob
    @Column(name = "password")
    private String password;
    @Column(name = "sex")
    private String sex;
    @Column(name = "age")
    private Integer age;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Lob
    @Column(name = "avatar")
    private String avatar;
    @Lob
    @Column(name = "userDesc")
    private String userDesc;
    @Basic(optional = false)
    @Column(name = "credit")
    private double credit;
    @Basic(optional = false)
    @Column(name = "role")
    private String role;
    @Lob
    @Column(name = "preference")
    private String preference;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Userstatus> userstatusCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Collection<Chatroom> chatroomCollection;
    @OneToMany(mappedBy = "administrator")
    private Collection<Chatroom> chatroomCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Promotionpreference> promotionpreferenceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Tag> tagCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Store> storeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Message> messageCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Comment> commentCollection;

    public User() {
    }

    public User(String userID) {
        this.userID = userID;
    }

    public User(String userID, String userName, String password, String email, String avatar, double credit, String role) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.credit = credit;
        this.role = role;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    @XmlTransient
    public Collection<Userstatus> getUserstatusCollection() {
        return userstatusCollection;
    }

    public void setUserstatusCollection(Collection<Userstatus> userstatusCollection) {
        this.userstatusCollection = userstatusCollection;
    }

    @XmlTransient
    public Collection<Chatroom> getChatroomCollection() {
        return chatroomCollection;
    }

    public void setChatroomCollection(Collection<Chatroom> chatroomCollection) {
        this.chatroomCollection = chatroomCollection;
    }

    @XmlTransient
    public Collection<Chatroom> getChatroomCollection1() {
        return chatroomCollection1;
    }

    public void setChatroomCollection1(Collection<Chatroom> chatroomCollection1) {
        this.chatroomCollection1 = chatroomCollection1;
    }

    @XmlTransient
    public Collection<Promotionpreference> getPromotionpreferenceCollection() {
        return promotionpreferenceCollection;
    }

    public void setPromotionpreferenceCollection(Collection<Promotionpreference> promotionpreferenceCollection) {
        this.promotionpreferenceCollection = promotionpreferenceCollection;
    }

    @XmlTransient
    public Collection<Tag> getTagCollection() {
        return tagCollection;
    }

    public void setTagCollection(Collection<Tag> tagCollection) {
        this.tagCollection = tagCollection;
    }

    @XmlTransient
    public Collection<Store> getStoreCollection() {
        return storeCollection;
    }

    public void setStoreCollection(Collection<Store> storeCollection) {
        this.storeCollection = storeCollection;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.User[ userID=" + userID + " ]";
    }
    
}
