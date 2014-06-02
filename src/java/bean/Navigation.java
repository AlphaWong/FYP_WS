/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alphawong
 */
@Entity
@Table(name = "navigation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Navigation.findAll", query = "SELECT n FROM Navigation n"),
    @NamedQuery(name = "Navigation.findByNavigationID", query = "SELECT n FROM Navigation n WHERE n.navigationID = :navigationID"),
    @NamedQuery(name = "Navigation.findByNavigationName", query = "SELECT n FROM Navigation n WHERE n.navigationName = :navigationName")})
public class Navigation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "navigationID")
    private Integer navigationID;
    @Basic(optional = false)
    @Column(name = "navigationName")
    private String navigationName;
    @ManyToMany(mappedBy = "navigationCollection")
    private Collection<Mobile> mobileCollection;

    public Navigation() {
    }

    public Navigation(Integer navigationID) {
        this.navigationID = navigationID;
    }

    public Navigation(Integer navigationID, String navigationName) {
        this.navigationID = navigationID;
        this.navigationName = navigationName;
    }

    public Integer getNavigationID() {
        return navigationID;
    }

    public void setNavigationID(Integer navigationID) {
        this.navigationID = navigationID;
    }

    public String getNavigationName() {
        return navigationName;
    }

    public void setNavigationName(String navigationName) {
        this.navigationName = navigationName;
    }

    @XmlTransient
    public Collection<Mobile> getMobileCollection() {
        return mobileCollection;
    }

    public void setMobileCollection(Collection<Mobile> mobileCollection) {
        this.mobileCollection = mobileCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (navigationID != null ? navigationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Navigation)) {
            return false;
        }
        Navigation other = (Navigation) object;
        if ((this.navigationID == null && other.navigationID != null) || (this.navigationID != null && !this.navigationID.equals(other.navigationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Navigation[ navigationID=" + navigationID + " ]";
    }
    
}
