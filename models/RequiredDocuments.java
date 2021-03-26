/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "required_documents")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RequiredDocuments.findAll", query = "SELECT r FROM RequiredDocuments r"),
    @NamedQuery(name = "RequiredDocuments.findByRequiredDocumentsId", query = "SELECT r FROM RequiredDocuments r WHERE r.requiredDocumentsId = :requiredDocumentsId"),
    @NamedQuery(name = "RequiredDocuments.findByStatus", query = "SELECT r FROM RequiredDocuments r WHERE r.status = :status")})
public class RequiredDocuments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "required_documents_id")
    private Integer requiredDocumentsId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requiredDocumentsId")
    private Collection<Document> documentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requiredDocumentsUploaded")
    private Collection<Property> propertyCollection;
    @OneToMany(mappedBy = "requiredDocumentsUploaded")
    private Collection<User> userCollection;

    public RequiredDocuments() {
    }

    public RequiredDocuments(Integer requiredDocumentsId) {
        this.requiredDocumentsId = requiredDocumentsId;
    }

    public RequiredDocuments(Integer requiredDocumentsId, int status) {
        this.requiredDocumentsId = requiredDocumentsId;
        this.status = status;
    }

    public Integer getRequiredDocumentsId() {
        return requiredDocumentsId;
    }

    public void setRequiredDocumentsId(Integer requiredDocumentsId) {
        this.requiredDocumentsId = requiredDocumentsId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Document> getDocumentCollection() {
        return documentCollection;
    }

    public void setDocumentCollection(Collection<Document> documentCollection) {
        this.documentCollection = documentCollection;
    }

    @XmlTransient
    public Collection<Property> getPropertyCollection() {
        return propertyCollection;
    }

    public void setPropertyCollection(Collection<Property> propertyCollection) {
        this.propertyCollection = propertyCollection;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (requiredDocumentsId != null ? requiredDocumentsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequiredDocuments)) {
            return false;
        }
        RequiredDocuments other = (RequiredDocuments) object;
        if ((this.requiredDocumentsId == null && other.requiredDocumentsId != null) || (this.requiredDocumentsId != null && !this.requiredDocumentsId.equals(other.requiredDocumentsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.alma.models.RequiredDocuments[ requiredDocumentsId=" + requiredDocumentsId + " ]";
    }
    
}
