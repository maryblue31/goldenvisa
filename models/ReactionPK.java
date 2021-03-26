/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alex
 */
@Embeddable
public class ReactionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "property_id")
    private int propertyId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;

    public ReactionPK() {
    }

    public ReactionPK(int propertyId, int userId) {
        this.propertyId = propertyId;
        this.userId = userId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) propertyId;
        hash += (int) userId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactionPK)) {
            return false;
        }
        ReactionPK other = (ReactionPK) object;
        if (this.propertyId != other.propertyId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.alma.models.ReactionPK[ propertyId=" + propertyId + ", userId=" + userId + " ]";
    }
    
}
