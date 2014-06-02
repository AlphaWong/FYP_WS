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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "mobile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mobile.findAll", query = "SELECT m FROM Mobile m"),
    @NamedQuery(name = "Mobile.findByMobileID", query = "SELECT m FROM Mobile m WHERE m.mobileID = :mobileID"),
    @NamedQuery(name = "Mobile.findByScreenSize", query = "SELECT m FROM Mobile m WHERE m.screenSize = :screenSize"),
    @NamedQuery(name = "Mobile.findByResolutionX", query = "SELECT m FROM Mobile m WHERE m.resolutionX = :resolutionX"),
    @NamedQuery(name = "Mobile.findByResolutionY", query = "SELECT m FROM Mobile m WHERE m.resolutionY = :resolutionY"),
    @NamedQuery(name = "Mobile.findByScreenPixelDensity", query = "SELECT m FROM Mobile m WHERE m.screenPixelDensity = :screenPixelDensity"),
    @NamedQuery(name = "Mobile.findByMobileFormFactor", query = "SELECT m FROM Mobile m WHERE m.mobileFormFactor = :mobileFormFactor"),
    @NamedQuery(name = "Mobile.findByCPUModel", query = "SELECT m FROM Mobile m WHERE m.cPUModel = :cPUModel"),
    @NamedQuery(name = "Mobile.findByCPUFrequency", query = "SELECT m FROM Mobile m WHERE m.cPUFrequency = :cPUFrequency"),
    @NamedQuery(name = "Mobile.findByGPUModel", query = "SELECT m FROM Mobile m WHERE m.gPUModel = :gPUModel"),
    @NamedQuery(name = "Mobile.findByRam", query = "SELECT m FROM Mobile m WHERE m.ram = :ram"),
    @NamedQuery(name = "Mobile.findByRom", query = "SELECT m FROM Mobile m WHERE m.rom = :rom"),
    @NamedQuery(name = "Mobile.findBySIMNumber", query = "SELECT m FROM Mobile m WHERE m.sIMNumber = :sIMNumber"),
    @NamedQuery(name = "Mobile.findByBatteryType", query = "SELECT m FROM Mobile m WHERE m.batteryType = :batteryType"),
    @NamedQuery(name = "Mobile.findByBatteryCapacity", query = "SELECT m FROM Mobile m WHERE m.batteryCapacity = :batteryCapacity"),
    @NamedQuery(name = "Mobile.findByTalkTime", query = "SELECT m FROM Mobile m WHERE m.talkTime = :talkTime"),
    @NamedQuery(name = "Mobile.findByStandByTime", query = "SELECT m FROM Mobile m WHERE m.standByTime = :standByTime"),
    @NamedQuery(name = "Mobile.findByFontCameraPixel", query = "SELECT m FROM Mobile m WHERE m.fontCameraPixel = :fontCameraPixel"),
    @NamedQuery(name = "Mobile.findByRearCameraPixel", query = "SELECT m FROM Mobile m WHERE m.rearCameraPixel = :rearCameraPixel"),
    @NamedQuery(name = "Mobile.findByMobileSize", query = "SELECT m FROM Mobile m WHERE m.mobileSize = :mobileSize"),
    @NamedQuery(name = "Mobile.findByWeight", query = "SELECT m FROM Mobile m WHERE m.weight = :weight")})
public class Mobile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "mobileID")
    private Integer mobileID;
    @Basic(optional = false)
    @Column(name = "screenSize")
    private double screenSize;
    @Basic(optional = false)
    @Column(name = "resolutionX")
    private int resolutionX;
    @Basic(optional = false)
    @Column(name = "resolutionY")
    private int resolutionY;
    @Column(name = "screenPixelDensity")
    private Integer screenPixelDensity;
    @Basic(optional = false)
    @Column(name = "mobileFormFactor")
    private String mobileFormFactor;
    @Column(name = "CPUModel")
    private String cPUModel;
    @Column(name = "CPUFrequency")
    private Integer cPUFrequency;
    @Column(name = "GPUModel")
    private String gPUModel;
    @Column(name = "RAM")
    private Integer ram;
    @Column(name = "ROM")
    private Integer rom;
    @Basic(optional = false)
    @Column(name = "SIMNumber")
    private int sIMNumber;
    @Basic(optional = false)
    @Column(name = "batteryType")
    private String batteryType;
    @Basic(optional = false)
    @Column(name = "batteryCapacity")
    private int batteryCapacity;
    @Column(name = "talkTime")
    private Integer talkTime;
    @Column(name = "standByTime")
    private Integer standByTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "fontCameraPixel")
    private Double fontCameraPixel;
    @Column(name = "rearCameraPixel")
    private Double rearCameraPixel;
    @Basic(optional = false)
    @Column(name = "mobileSize")
    private String mobileSize;
    @Basic(optional = false)
    @Column(name = "weight")
    private double weight;
    @ManyToMany(mappedBy = "mobileCollection")
    private Collection<Keyboardtype> keyboardtypeCollection;
    @ManyToMany(mappedBy = "mobileCollection")
    private Collection<Connectivityandsharing> connectivityandsharingCollection;
    @JoinTable(name = "mobile_navigation", joinColumns = {
        @JoinColumn(name = "mobileID", referencedColumnName = "mobileID")}, inverseJoinColumns = {
        @JoinColumn(name = "navigationID", referencedColumnName = "navigationID")})
    @ManyToMany
    private Collection<Navigation> navigationCollection;
    @ManyToMany(mappedBy = "mobileCollection")
    private Collection<Simcardtype> simcardtypeCollection;
    @ManyToMany(mappedBy = "mobileCollection")
    private Collection<Machineinterface> machineinterfaceCollection;
    @JoinTable(name = "mobile_sensortype", joinColumns = {
        @JoinColumn(name = "mobileID", referencedColumnName = "mobileID")}, inverseJoinColumns = {
        @JoinColumn(name = "sensorTypeID", referencedColumnName = "sensorTypeID")})
    @ManyToMany
    private Collection<Sensortype> sensortypeCollection;
    @ManyToMany(mappedBy = "mobileCollection")
    private Collection<Mobilenetworkstandard> mobilenetworkstandardCollection;
    @ManyToMany(mappedBy = "mobileCollection")
    private Collection<Mobiletype> mobiletypeCollection;
    @ManyToMany(mappedBy = "mobileCollection")
    private Collection<Wlantype> wlantypeCollection;
    @JoinColumns({
        @JoinColumn(name = "mobileID", referencedColumnName = "merchandiseID", insertable = false, updatable = false),
        @JoinColumn(name = "categoryID", referencedColumnName = "categoryID")})
    @ManyToOne(optional = false)
    private Merchandise merchandise;
    @JoinColumn(name = "CPUCoreTypeID", referencedColumnName = "CPUCoreTypeID")
    @ManyToOne(optional = false)
    private Cpucoretype cPUCoreTypeID;
    @JoinColumn(name = "CPUManufacturerID", referencedColumnName = "CPUManufacturerID")
    @ManyToOne
    private Cpumanufacturer cPUManufacturerID;
    @JoinColumn(name = "flashID", referencedColumnName = "flashID")
    @ManyToOne
    private Flash flashID;
    @JoinColumn(name = "flashMemoryCardID", referencedColumnName = "flashMemoryCardID")
    @ManyToOne
    private Flashmemorycard flashMemoryCardID;
    @JoinColumn(name = "OSID", referencedColumnName = "OSID")
    @ManyToOne(optional = false)
    private Operatingsystem osid;
    @JoinColumn(name = "screenMaterialID", referencedColumnName = "screenMaterialID")
    @ManyToOne(optional = false)
    private Screenmaterial screenMaterialID;
    @JoinColumn(name = "touchScreenTypeID", referencedColumnName = "touchScreenTypeID")
    @ManyToOne
    private Touchscreentype touchScreenTypeID;

    public Mobile() {
    }

    public Mobile(Integer mobileID) {
        this.mobileID = mobileID;
    }

    public Mobile(Integer mobileID, double screenSize, int resolutionX, int resolutionY, String mobileFormFactor, int sIMNumber, String batteryType, int batteryCapacity, String mobileSize, double weight) {
        this.mobileID = mobileID;
        this.screenSize = screenSize;
        this.resolutionX = resolutionX;
        this.resolutionY = resolutionY;
        this.mobileFormFactor = mobileFormFactor;
        this.sIMNumber = sIMNumber;
        this.batteryType = batteryType;
        this.batteryCapacity = batteryCapacity;
        this.mobileSize = mobileSize;
        this.weight = weight;
    }

    public Integer getMobileID() {
        return mobileID;
    }

    public void setMobileID(Integer mobileID) {
        this.mobileID = mobileID;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public int getResolutionX() {
        return resolutionX;
    }

    public void setResolutionX(int resolutionX) {
        this.resolutionX = resolutionX;
    }

    public int getResolutionY() {
        return resolutionY;
    }

    public void setResolutionY(int resolutionY) {
        this.resolutionY = resolutionY;
    }

    public Integer getScreenPixelDensity() {
        return screenPixelDensity;
    }

    public void setScreenPixelDensity(Integer screenPixelDensity) {
        this.screenPixelDensity = screenPixelDensity;
    }

    public String getMobileFormFactor() {
        return mobileFormFactor;
    }

    public void setMobileFormFactor(String mobileFormFactor) {
        this.mobileFormFactor = mobileFormFactor;
    }

    public String getCPUModel() {
        return cPUModel;
    }

    public void setCPUModel(String cPUModel) {
        this.cPUModel = cPUModel;
    }

    public Integer getCPUFrequency() {
        return cPUFrequency;
    }

    public void setCPUFrequency(Integer cPUFrequency) {
        this.cPUFrequency = cPUFrequency;
    }

    public String getGPUModel() {
        return gPUModel;
    }

    public void setGPUModel(String gPUModel) {
        this.gPUModel = gPUModel;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getRom() {
        return rom;
    }

    public void setRom(Integer rom) {
        this.rom = rom;
    }

    public int getSIMNumber() {
        return sIMNumber;
    }

    public void setSIMNumber(int sIMNumber) {
        this.sIMNumber = sIMNumber;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public Integer getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(Integer talkTime) {
        this.talkTime = talkTime;
    }

    public Integer getStandByTime() {
        return standByTime;
    }

    public void setStandByTime(Integer standByTime) {
        this.standByTime = standByTime;
    }

    public Double getFontCameraPixel() {
        return fontCameraPixel;
    }

    public void setFontCameraPixel(Double fontCameraPixel) {
        this.fontCameraPixel = fontCameraPixel;
    }

    public Double getRearCameraPixel() {
        return rearCameraPixel;
    }

    public void setRearCameraPixel(Double rearCameraPixel) {
        this.rearCameraPixel = rearCameraPixel;
    }

    public String getMobileSize() {
        return mobileSize;
    }

    public void setMobileSize(String mobileSize) {
        this.mobileSize = mobileSize;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @XmlTransient
    public Collection<Keyboardtype> getKeyboardtypeCollection() {
        return keyboardtypeCollection;
    }

    public void setKeyboardtypeCollection(Collection<Keyboardtype> keyboardtypeCollection) {
        this.keyboardtypeCollection = keyboardtypeCollection;
    }

    @XmlTransient
    public Collection<Connectivityandsharing> getConnectivityandsharingCollection() {
        return connectivityandsharingCollection;
    }

    public void setConnectivityandsharingCollection(Collection<Connectivityandsharing> connectivityandsharingCollection) {
        this.connectivityandsharingCollection = connectivityandsharingCollection;
    }

    @XmlTransient
    public Collection<Navigation> getNavigationCollection() {
        return navigationCollection;
    }

    public void setNavigationCollection(Collection<Navigation> navigationCollection) {
        this.navigationCollection = navigationCollection;
    }

    @XmlTransient
    public Collection<Simcardtype> getSimcardtypeCollection() {
        return simcardtypeCollection;
    }

    public void setSimcardtypeCollection(Collection<Simcardtype> simcardtypeCollection) {
        this.simcardtypeCollection = simcardtypeCollection;
    }

    @XmlTransient
    public Collection<Machineinterface> getMachineinterfaceCollection() {
        return machineinterfaceCollection;
    }

    public void setMachineinterfaceCollection(Collection<Machineinterface> machineinterfaceCollection) {
        this.machineinterfaceCollection = machineinterfaceCollection;
    }

    @XmlTransient
    public Collection<Sensortype> getSensortypeCollection() {
        return sensortypeCollection;
    }

    public void setSensortypeCollection(Collection<Sensortype> sensortypeCollection) {
        this.sensortypeCollection = sensortypeCollection;
    }

    @XmlTransient
    public Collection<Mobilenetworkstandard> getMobilenetworkstandardCollection() {
        return mobilenetworkstandardCollection;
    }

    public void setMobilenetworkstandardCollection(Collection<Mobilenetworkstandard> mobilenetworkstandardCollection) {
        this.mobilenetworkstandardCollection = mobilenetworkstandardCollection;
    }

    @XmlTransient
    public Collection<Mobiletype> getMobiletypeCollection() {
        return mobiletypeCollection;
    }

    public void setMobiletypeCollection(Collection<Mobiletype> mobiletypeCollection) {
        this.mobiletypeCollection = mobiletypeCollection;
    }

    @XmlTransient
    public Collection<Wlantype> getWlantypeCollection() {
        return wlantypeCollection;
    }

    public void setWlantypeCollection(Collection<Wlantype> wlantypeCollection) {
        this.wlantypeCollection = wlantypeCollection;
    }

    public Merchandise getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(Merchandise merchandise) {
        this.merchandise = merchandise;
    }

    public Cpucoretype getCPUCoreTypeID() {
        return cPUCoreTypeID;
    }

    public void setCPUCoreTypeID(Cpucoretype cPUCoreTypeID) {
        this.cPUCoreTypeID = cPUCoreTypeID;
    }

    public Cpumanufacturer getCPUManufacturerID() {
        return cPUManufacturerID;
    }

    public void setCPUManufacturerID(Cpumanufacturer cPUManufacturerID) {
        this.cPUManufacturerID = cPUManufacturerID;
    }

    public Flash getFlashID() {
        return flashID;
    }

    public void setFlashID(Flash flashID) {
        this.flashID = flashID;
    }

    public Flashmemorycard getFlashMemoryCardID() {
        return flashMemoryCardID;
    }

    public void setFlashMemoryCardID(Flashmemorycard flashMemoryCardID) {
        this.flashMemoryCardID = flashMemoryCardID;
    }

    public Operatingsystem getOsid() {
        return osid;
    }

    public void setOsid(Operatingsystem osid) {
        this.osid = osid;
    }

    public Screenmaterial getScreenMaterialID() {
        return screenMaterialID;
    }

    public void setScreenMaterialID(Screenmaterial screenMaterialID) {
        this.screenMaterialID = screenMaterialID;
    }

    public Touchscreentype getTouchScreenTypeID() {
        return touchScreenTypeID;
    }

    public void setTouchScreenTypeID(Touchscreentype touchScreenTypeID) {
        this.touchScreenTypeID = touchScreenTypeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mobileID != null ? mobileID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mobile)) {
            return false;
        }
        Mobile other = (Mobile) object;
        if ((this.mobileID == null && other.mobileID != null) || (this.mobileID != null && !this.mobileID.equals(other.mobileID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Mobile[ mobileID=" + mobileID + " ]";
    }
    
}
