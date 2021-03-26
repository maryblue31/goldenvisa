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
@Table(name = "certificate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Certificate.findAll", query = "SELECT c FROM Certificate c"),
    @NamedQuery(name = "Certificate.findByCertificateId", query = "SELECT c FROM Certificate c WHERE c.certificateId = :certificateId"),
    @NamedQuery(name = "Certificate.findByDescription", query = "SELECT c FROM Certificate c WHERE c.description = :description"),
    @NamedQuery(name = "Certificate.findByDateEstablished", query = "SELECT c FROM Certificate c WHERE c.dateEstablished = :dateEstablished"),
    @NamedQuery(name = "Certificate.findByDateOfExpiration", query = "SELECT c FROM Certificate c WHERE c.dateOfExpiration = :dateOfExpiration")})
public class Certificate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "certificate_id")
    private Integer certificateId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_established")
    @Temporal(TemporalType.DATE)
    private Date dateEstablished;
    @Column(name = "date_of_expiration")
    @Temporal(TemporalType.DATE)
    private Date dateOfExpiration;
    @OneToMany(mappedBy = "certificateId")
    private Collection<Document> documentCollection;
    @JoinColumn(name = "holder_id", referencedColumnName = "holder_id")
    @ManyToOne
    private Holder holderId;

    public Certificate() {
    }

    public Certificate(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public Certificate(Integer certificateId, String description, Date dateEstablished) {
        this.certificateId = certificateId;
        this.description = description;
        this.dateEstablished = dateEstablished;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateEstablished() {
        return dateEstablished;
    }

    public void setDateEstablished(Date dateEstablished) {
        this.dateEstablished = dateEstablished;
    }

    public Date getDateOfExpiration() {
        return dateOfExpiration;
    }

    public void setDateOfExpiration(Date dateOfExpiration) {
        this.dateOfExpiration = dateOfExpiration;
    }

    @XmlTransient
    public Collection<Document> getDocumentCollection() {
        return documentCollection;
    }

    public void setDocumentCollection(Collection<Document> documentCollection) {
        this.documentCollection = documentCollection;
    }

    public Holder getHolderId() {
        return holderId;
    }

    public void setHolderId(Holder holderId) {
        this.holderId = holderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (certificateId != null ? certificateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Certificate)) {
            return false;
        }
        Certificate other = (Certificate) object;
        if ((this.certificateId == null && other.certificateId != null) || (this.certificateId != null && !this.certificateId.equals(other.certificateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.alma.models.Certificate[ certificateId=" + certificateId + " ]";
    }
    
}
