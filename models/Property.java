/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "property")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Property.findAll", query = "SELECT p FROM Property p"),
    @NamedQuery(name = "Property.findByPropertyId", query = "SELECT p FROM Property p WHERE p.propertyId = :propertyId"),
    @NamedQuery(name = "Property.findByDescription", query = "SELECT p FROM Property p WHERE p.description = :description"),
    @NamedQuery(name = "Property.findByRooms", query = "SELECT p FROM Property p WHERE p.rooms = :rooms"),
    @NamedQuery(name = "Property.findByPrice", query = "SELECT p FROM Property p WHERE p.price = :price"),
    @NamedQuery(name = "Property.findByStatus", query = "SELECT p FROM Property p WHERE p.status = :status"),
    @NamedQuery(name = "Property.findByBathrooms", query = "SELECT p FROM Property p WHERE p.bathrooms = :bathrooms"),
    @NamedQuery(name = "Property.findByTitle", query = "SELECT p FROM Property p WHERE p.title = :title"),
    @NamedQuery(name = "Property.findByType", query = "SELECT p FROM Property p WHERE p.type = :type"),
    @NamedQuery(name = "Property.findByArea", query = "SELECT p FROM Property p WHERE p.area = :area"),
    @NamedQuery(name = "Property.findByDatetimeUploaded", query = "SELECT p FROM Property p WHERE p.datetimeUploaded = :datetimeUploaded"),
    @NamedQuery(name = "Property.findByDatetimeUpdated", query = "SELECT p FROM Property p WHERE p.datetimeUpdated = :datetimeUpdated")})
public class Property implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "property_id")
    private Integer propertyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10000)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rooms")
    private int rooms;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "status")
    private String status;
    @Column(name = "bathrooms")
    private Integer bathrooms;
    @Size(max = 100)
    @Column(name = "title")
    private String title;
    @Size(max = 20)
    @Column(name = "type")
    private String type;
    @Column(name = "area")
    private Integer area;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datetime_uploaded")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetimeUploaded;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datetime_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetimeUpdated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "property")
    private Collection<Reaction> reactionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "propertyId")
    private Collection<Media> mediaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "propertyId")
    private Collection<Features> featuresCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "propertyId")
    private Collection<Application> applicationCollection;
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    @ManyToOne(optional = false)
    private City cityId;
    @JoinColumn(name = "required_documents_uploaded", referencedColumnName = "required_documents_id")
    @ManyToOne(optional = false)
    private RequiredDocuments requiredDocumentsUploaded;
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User ownerId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "propertyId")
    private Collection<Comment> commentCollection;

    public Property() {
    }

    public Property(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Property(Integer propertyId, String description, int rooms, double price, String status, Date datetimeUploaded, Date datetimeUpdated) {
        this.propertyId = propertyId;
        this.description = description;
        this.rooms = rooms;
        this.price = price;
        this.status = status;
        this.datetimeUploaded = datetimeUploaded;
        this.datetimeUpdated = datetimeUpdated;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Date getDatetimeUploaded() {
        return datetimeUploaded;
    }

    public void setDatetimeUploaded(Date datetimeUploaded) {
        this.datetimeUploaded = datetimeUploaded;
    }

    public Date getDatetimeUpdated() {
        return datetimeUpdated;
    }

    public void setDatetimeUpdated(Date datetimeUpdated) {
        this.datetimeUpdated = datetimeUpdated;
    }

    @XmlTransient
    public Collection<Reaction> getReactionCollection() {
        return reactionCollection;
    }

    public void setReactionCollection(Collection<Reaction> reactionCollection) {
        this.reactionCollection = reactionCollection;
    }

    @XmlTransient
    public Collection<Media> getMediaCollection() {
        return mediaCollection;
    }

    public void setMediaCollection(Collection<Media> mediaCollection) {
        this.mediaCollection = mediaCollection;
    }

    @XmlTransient
    public Collection<Features> getFeaturesCollection() {
        return featuresCollection;
    }

    public void setFeaturesCollection(Collection<Features> featuresCollection) {
        this.featuresCollection = featuresCollection;
    }

    @XmlTransient
    public Collection<Application> getApplicationCollection() {
        return applicationCollection;
    }

    public void setApplicationCollection(Collection<Application> applicationCollection) {
        this.applicationCollection = applicationCollection;
    }

    public City getCityId() {
        return cityId;
    }

    public void setCityId(City cityId) {
        this.cityId = cityId;
    }

    public RequiredDocuments getRequiredDocumentsUploaded() {
        return requiredDocumentsUploaded;
    }

    public void setRequiredDocumentsUploaded(RequiredDocuments requiredDocumentsUploaded) {
        this.requiredDocumentsUploaded = requiredDocumentsUploaded;
    }

    public User getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
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
        hash += (propertyId != null ? propertyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Property)) {
            return false;
        }
        Property other = (Property) object;
        if ((this.propertyId == null && other.propertyId != null) || (this.propertyId != null && !this.propertyId.equals(other.propertyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.alma.models.Property[ propertyId=" + propertyId + " ]";
    }
    
}
