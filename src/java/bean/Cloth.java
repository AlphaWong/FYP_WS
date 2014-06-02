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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alphawong
 */
@Entity
@Table(name = "cloth")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cloth.findAll", query = "SELECT c FROM Cloth c"),
    @NamedQuery(name = "Cloth.findByClothID", query = "SELECT c FROM Cloth c WHERE c.clothPK.clothID = :clothID"),
    @NamedQuery(name = "Cloth.findByCategoryID", query = "SELECT c FROM Cloth c WHERE c.clothPK.categoryID = :categoryID"),
    @NamedQuery(name = "Cloth.findBySex", query = "SELECT c FROM Cloth c WHERE c.sex = :sex"),
    @NamedQuery(name = "Cloth.findByFabricContent", query = "SELECT c FROM Cloth c WHERE c.fabricContent = :fabricContent")})
public class Cloth implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ClothPK clothPK;
    @Basic(optional = false)
    @Column(name = "sex")
    private String sex;
    @Basic(optional = false)
    @Lob
    @Column(name = "fabric")
    private String fabric;
    @Basic(optional = false)
    @Column(name = "fabricContent")
    private double fabricContent;
    @Basic(optional = false)
    @Lob
    @Column(name = "occasion")
    private String occasion;
    @Basic(optional = false)
    @Lob
    @Column(name = "edition")
    private String edition;
    @Basic(optional = false)
    @Lob
    @Column(name = "careLabel")
    private String careLabel;
    @Lob
    @Column(name = "thickness")
    private String thickness;
    @Lob
    @Column(name = "threadThickness")
    private String threadThickness;
    @Lob
    @Column(name = "season")
    private String season;
    @Lob
    @Column(name = "basicStyle")
    private String basicStyle;
    @Lob
    @Column(name = "subStyle")
    private String subStyle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cloth")
    private Collection<Uppergarment> uppergarmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cloth")
    private Collection<Lowergarment> lowergarmentCollection;
    @JoinColumns({
        @JoinColumn(name = "clothID", referencedColumnName = "merchandiseID", insertable = false, updatable = false),
        @JoinColumn(name = "categoryID", referencedColumnName = "categoryID", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Merchandise merchandise;

    public Cloth() {
    }

    public Cloth(ClothPK clothPK) {
        this.clothPK = clothPK;
    }

    public Cloth(ClothPK clothPK, String sex, String fabric, double fabricContent, String occasion, String edition, String careLabel) {
        this.clothPK = clothPK;
        this.sex = sex;
        this.fabric = fabric;
        this.fabricContent = fabricContent;
        this.occasion = occasion;
        this.edition = edition;
        this.careLabel = careLabel;
    }

    public Cloth(int clothID, int categoryID) {
        this.clothPK = new ClothPK(clothID, categoryID);
    }

    public ClothPK getClothPK() {
        return clothPK;
    }

    public void setClothPK(ClothPK clothPK) {
        this.clothPK = clothPK;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }

    public double getFabricContent() {
        return fabricContent;
    }

    public void setFabricContent(double fabricContent) {
        this.fabricContent = fabricContent;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getCareLabel() {
        return careLabel;
    }

    public void setCareLabel(String careLabel) {
        this.careLabel = careLabel;
    }

    public String getThickness() {
        return thickness;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
    }

    public String getThreadThickness() {
        return threadThickness;
    }

    public void setThreadThickness(String threadThickness) {
        this.threadThickness = threadThickness;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getBasicStyle() {
        return basicStyle;
    }

    public void setBasicStyle(String basicStyle) {
        this.basicStyle = basicStyle;
    }

    public String getSubStyle() {
        return subStyle;
    }

    public void setSubStyle(String subStyle) {
        this.subStyle = subStyle;
    }

    @XmlTransient
    public Collection<Uppergarment> getUppergarmentCollection() {
        return uppergarmentCollection;
    }

    public void setUppergarmentCollection(Collection<Uppergarment> uppergarmentCollection) {
        this.uppergarmentCollection = uppergarmentCollection;
    }

    @XmlTransient
    public Collection<Lowergarment> getLowergarmentCollection() {
        return lowergarmentCollection;
    }

    public void setLowergarmentCollection(Collection<Lowergarment> lowergarmentCollection) {
        this.lowergarmentCollection = lowergarmentCollection;
    }

    public Merchandise getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(Merchandise merchandise) {
        this.merchandise = merchandise;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clothPK != null ? clothPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cloth)) {
            return false;
        }
        Cloth other = (Cloth) object;
        if ((this.clothPK == null && other.clothPK != null) || (this.clothPK != null && !this.clothPK.equals(other.clothPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Cloth[ clothPK=" + clothPK + " ]";
    }
    
}
