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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "document")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Document.findAll", query = "SELECT d FROM Document d"),
    @NamedQuery(name = "Document.findByDocumentId", query = "SELECT d FROM Document d WHERE d.documentId = :documentId"),
    @NamedQuery(name = "Document.findByDescription", query = "SELECT d FROM Document d WHERE d.description = :description"),
    @NamedQuery(name = "Document.findByMediaPath", query = "SELECT d FROM Document d WHERE d.mediaPath = :mediaPath"),
    @NamedQuery(name = "Document.findByMediaType", query = "SELECT d FROM Document d WHERE d.mediaType = :mediaType")})
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "document_id")
    private Integer documentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "description")
    private String description;
    @Size(max = 100)
    @Column(name = "media_path")
    private String mediaPath;
    @Size(max = 45)
    @Column(name = "media_type")
    private String mediaType;
    @JoinColumn(name = "certificate_id", referencedColumnName = "certificate_id")
    @ManyToOne
    private Certificate certificateId;
    @JoinColumn(name = "required_documents_id", referencedColumnName = "required_documents_id")
    @ManyToOne(optional = false)
    private RequiredDocuments requiredDocumentsId;

    public Document() {
    }

    public Document(Integer documentId) {
        this.documentId = documentId;
    }

    public Document(Integer documentId, String description) {
        this.documentId = documentId;
        this.description = description;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Certificate getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Certificate certificateId) {
        this.certificateId = certificateId;
    }

    public RequiredDocuments getRequiredDocumentsId() {
        return requiredDocumentsId;
    }

    public void setRequiredDocumentsId(RequiredDocuments requiredDocumentsId) {
        this.requiredDocumentsId = requiredDocumentsId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documentId != null ? documentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Document)) {
            return false;
        }
        Document other = (Document) object;
        if ((this.documentId == null && other.documentId != null) || (this.documentId != null && !this.documentId.equals(other.documentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.alma.models.Document[ documentId=" + documentId + " ]";
    }
    
}
