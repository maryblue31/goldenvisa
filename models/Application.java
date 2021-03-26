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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "application")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Application.findAll", query = "SELECT a FROM Application a"),
    @NamedQuery(name = "Application.findByApplicationId", query = "SELECT a FROM Application a WHERE a.applicationId = :applicationId"),
    @NamedQuery(name = "Application.findByStatus", query = "SELECT a FROM Application a WHERE a.status = :status"),
    @NamedQuery(name = "Application.findByProtectedMembers", query = "SELECT a FROM Application a WHERE a.protectedMembers = :protectedMembers"),
    @NamedQuery(name = "Application.findByDateOfApplication", query = "SELECT a FROM Application a WHERE a.dateOfApplication = :dateOfApplication")})
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "application_id")
    private Integer applicationId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "protected_members")
    private int protectedMembers;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_of_application")
    @Temporal(TemporalType.DATE)
    private Date dateOfApplication;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationId")
    private Collection<UserServesUser> userServesUserCollection;
    @JoinColumn(name = "property_id", referencedColumnName = "property_id")
    @ManyToOne(optional = false)
    private Property propertyId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationId")
    private Collection<Payment> paymentCollection;

    public Application() {
    }

    public Application(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Application(Integer applicationId, int status, int protectedMembers, Date dateOfApplication) {
        this.applicationId = applicationId;
        this.status = status;
        this.protectedMembers = protectedMembers;
        this.dateOfApplication = dateOfApplication;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProtectedMembers() {
        return protectedMembers;
    }

    public void setProtectedMembers(int protectedMembers) {
        this.protectedMembers = protectedMembers;
    }

    public Date getDateOfApplication() {
        return dateOfApplication;
    }

    public void setDateOfApplication(Date dateOfApplication) {
        this.dateOfApplication = dateOfApplication;
    }

    @XmlTransient
    public Collection<UserServesUser> getUserServesUserCollection() {
        return userServesUserCollection;
    }

    public void setUserServesUserCollection(Collection<UserServesUser> userServesUserCollection) {
        this.userServesUserCollection = userServesUserCollection;
    }

    public Property getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Property propertyId) {
        this.propertyId = propertyId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<Payment> getPaymentCollection() {
        return paymentCollection;
    }

    public void setPaymentCollection(Collection<Payment> paymentCollection) {
        this.paymentCollection = paymentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applicationId != null ? applicationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        if ((this.applicationId == null && other.applicationId != null) || (this.applicationId != null && !this.applicationId.equals(other.applicationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.alma.models.Application[ applicationId=" + applicationId + " ]";
    }
    
}
