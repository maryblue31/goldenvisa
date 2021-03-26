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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "digital_key")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DigitalKey.findAll", query = "SELECT d FROM DigitalKey d"),
    @NamedQuery(name = "DigitalKey.findByDigitalKeyId", query = "SELECT d FROM DigitalKey d WHERE d.digitalKeyId = :digitalKeyId")})
public class DigitalKey implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "digital_key_id")
    private Integer digitalKeyId;
    @JoinColumn(name = "user_bought_the_key", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userBoughtTheKey;

    public DigitalKey() {
    }

    public DigitalKey(Integer digitalKeyId) {
        this.digitalKeyId = digitalKeyId;
    }

    public Integer getDigitalKeyId() {
        return digitalKeyId;
    }

    public void setDigitalKeyId(Integer digitalKeyId) {
        this.digitalKeyId = digitalKeyId;
    }

    public User getUserBoughtTheKey() {
        return userBoughtTheKey;
    }

    public void setUserBoughtTheKey(User userBoughtTheKey) {
        this.userBoughtTheKey = userBoughtTheKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (digitalKeyId != null ? digitalKeyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DigitalKey)) {
            return false;
        }
        DigitalKey other = (DigitalKey) object;
        if ((this.digitalKeyId == null && other.digitalKeyId != null) || (this.digitalKeyId != null && !this.digitalKeyId.equals(other.digitalKeyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.alma.models.DigitalKey[ digitalKeyId=" + digitalKeyId + " ]";
    }
    
}
