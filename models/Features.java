/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.models;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "features")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Features.findAll", query = "SELECT f FROM Features f"),
    @NamedQuery(name = "Features.findByFeaturesId", query = "SELECT f FROM Features f WHERE f.featuresId = :featuresId"),
    @NamedQuery(name = "Features.findByAirconditioning", query = "SELECT f FROM Features f WHERE f.airconditioning = :airconditioning"),
    @NamedQuery(name = "Features.findByWasher", query = "SELECT f FROM Features f WHERE f.washer = :washer"),
    @NamedQuery(name = "Features.findBySauna", query = "SELECT f FROM Features f WHERE f.sauna = :sauna"),
    @NamedQuery(name = "Features.findByWindowcoverings", query = "SELECT f FROM Features f WHERE f.windowcoverings = :windowcoverings"),
    @NamedQuery(name = "Features.findByTvcable", query = "SELECT f FROM Features f WHERE f.tvcable = :tvcable"),
    @NamedQuery(name = "Features.findByLaundry", query = "SELECT f FROM Features f WHERE f.laundry = :laundry"),
    @NamedQuery(name = "Features.findByBarbeque", query = "SELECT f FROM Features f WHERE f.barbeque = :barbeque"),
    @NamedQuery(name = "Features.findByDryer", query = "SELECT f FROM Features f WHERE f.dryer = :dryer"),
    @NamedQuery(name = "Features.findByGym", query = "SELECT f FROM Features f WHERE f.gym = :gym"),
    @NamedQuery(name = "Features.findByTennis", query = "SELECT f FROM Features f WHERE f.tennis = :tennis"),
    @NamedQuery(name = "Features.findByRefrigerator", query = "SELECT f FROM Features f WHERE f.refrigerator = :refrigerator"),
    @NamedQuery(name = "Features.findByLawn", query = "SELECT f FROM Features f WHERE f.lawn = :lawn"),
    @NamedQuery(name = "Features.findBySwimmingpool", query = "SELECT f FROM Features f WHERE f.swimmingpool = :swimmingpool"),
    @NamedQuery(name = "Features.findByOutdoorshower", query = "SELECT f FROM Features f WHERE f.outdoorshower = :outdoorshower"),
    @NamedQuery(name = "Features.findByGolf", query = "SELECT f FROM Features f WHERE f.golf = :golf")})
public class Features implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "features_id")
    private Integer featuresId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "airconditioning")
    private int airconditioning;
    @Basic(optional = false)
    @NotNull
    @Column(name = "washer")
    private int washer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sauna")
    private int sauna;
    @Basic(optional = false)
    @NotNull
    @Column(name = "windowcoverings")
    private int windowcoverings;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tvcable")
    private int tvcable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "laundry")
    private int laundry;
    @Basic(optional = false)
    @NotNull
    @Column(name = "barbeque")
    private int barbeque;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dryer")
    private int dryer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gym")
    private int gym;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tennis")
    private int tennis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "refrigerator")
    private int refrigerator;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lawn")
    private int lawn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "swimmingpool")
    private int swimmingpool;
    @Basic(optional = false)
    @NotNull
    @Column(name = "outdoorshower")
    private int outdoorshower;
    @Basic(optional = false)
    @NotNull
    @Column(name = "golf")
    private int golf;
    @JoinColumn(name = "property_id", referencedColumnName = "property_id")
    @ManyToOne(optional = false)
    private Property propertyId;

    public Features() {
    }

    public Features(Integer featuresId) {
        this.featuresId = featuresId;
    }

    public Features(Integer featuresId, int airconditioning, int washer, int sauna, int windowcoverings, int tvcable, int laundry, int barbeque, int dryer, int gym, int tennis, int refrigerator, int lawn, int swimmingpool, int outdoorshower, int golf) {
        this.featuresId = featuresId;
        this.airconditioning = airconditioning;
        this.washer = washer;
        this.sauna = sauna;
        this.windowcoverings = windowcoverings;
        this.tvcable = tvcable;
        this.laundry = laundry;
        this.barbeque = barbeque;
        this.dryer = dryer;
        this.gym = gym;
        this.tennis = tennis;
        this.refrigerator = refrigerator;
        this.lawn = lawn;
        this.swimmingpool = swimmingpool;
        this.outdoorshower = outdoorshower;
        this.golf = golf;
    }

    public Integer getFeaturesId() {
        return featuresId;
    }

    public void setFeaturesId(Integer featuresId) {
        this.featuresId = featuresId;
    }

    public int getAirconditioning() {
        return airconditioning;
    }

    public void setAirconditioning(int airconditioning) {
        this.airconditioning = airconditioning;
    }

    public int getWasher() {
        return washer;
    }

    public void setWasher(int washer) {
        this.washer = washer;
    }

    public int getSauna() {
        return sauna;
    }

    public void setSauna(int sauna) {
        this.sauna = sauna;
    }

    public int getWindowcoverings() {
        return windowcoverings;
    }

    public void setWindowcoverings(int windowcoverings) {
        this.windowcoverings = windowcoverings;
    }

    public int getTvcable() {
        return tvcable;
    }

    public void setTvcable(int tvcable) {
        this.tvcable = tvcable;
    }

    public int getLaundry() {
        return laundry;
    }

    public void setLaundry(int laundry) {
        this.laundry = laundry;
    }

    public int getBarbeque() {
        return barbeque;
    }

    public void setBarbeque(int barbeque) {
        this.barbeque = barbeque;
    }

    public int getDryer() {
        return dryer;
    }

    public void setDryer(int dryer) {
        this.dryer = dryer;
    }

    public int getGym() {
        return gym;
    }

    public void setGym(int gym) {
        this.gym = gym;
    }

    public int getTennis() {
        return tennis;
    }

    public void setTennis(int tennis) {
        this.tennis = tennis;
    }

    public int getRefrigerator() {
        return refrigerator;
    }

    public void setRefrigerator(int refrigerator) {
        this.refrigerator = refrigerator;
    }

    public int getLawn() {
        return lawn;
    }

    public void setLawn(int lawn) {
        this.lawn = lawn;
    }

    public int getSwimmingpool() {
        return swimmingpool;
    }

    public void setSwimmingpool(int swimmingpool) {
        this.swimmingpool = swimmingpool;
    }

    public int getOutdoorshower() {
        return outdoorshower;
    }

    public void setOutdoorshower(int outdoorshower) {
        this.outdoorshower = outdoorshower;
    }

    public int getGolf() {
        return golf;
    }

    public void setGolf(int golf) {
        this.golf = golf;
    }

    public Property getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Property propertyId) {
        this.propertyId = propertyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (featuresId != null ? featuresId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Features)) {
            return false;
        }
        Features other = (Features) object;
        if ((this.featuresId == null && other.featuresId != null) || (this.featuresId != null && !this.featuresId.equals(other.featuresId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.alma.models.Features[ featuresId=" + featuresId + " ]";
    }
    
}
