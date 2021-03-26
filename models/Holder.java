/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.models;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "holder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Holder.findAll", query = "SELECT h FROM Holder h"),
    @NamedQuery(name = "Holder.findByHolderId", query = "SELECT h FROM Holder h WHERE h.holderId = :holderId"),
    @NamedQuery(name = "Holder.findByName", query = "SELECT h FROM Holder h WHERE h.name = :name")})
public class Holder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "holder_id")
    private Integer holderId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "holderId")
    private Collection<Certificate> certificateCollection;
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    @ManyToOne(optional = false)
    private City cityId;

    public Holder() {
    }

    public Holder(Integer holderId) {
        this.holderId = holderId;
    }

    public Holder(Integer holderId, String name) {
        this.holderId = holderId;
        this.name = name;
    }

    public Integer getHolderId() {
        return holderId;
    }

    public void setHolderId(Integer holderId) {
        this.holderId = holderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Certificate> getCertificateCollection() {
        return certificateCollection;
    }

    public void setCertificateCollection(Collection<Certificate> certificateCollection) {
        this.certificateCollection = certificateCollection;
    }

    public City getCityId() {
        return cityId;
    }

    public void setCityId(City cityId) {
        this.cityId = cityId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (holderId != null ? holderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Holder)) {
            return false;
        }
        Holder other = (Holder) object;
        if ((this.holderId == null && other.holderId != null) || (this.holderId != null && !this.holderId.equals(other.holderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.alma.models.Holder[ holderId=" + holderId + " ]";
    }
    
}
