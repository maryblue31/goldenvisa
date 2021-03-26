/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "reaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reaction.findAll", query = "SELECT r FROM Reaction r"),
    @NamedQuery(name = "Reaction.findByReaction", query = "SELECT r FROM Reaction r WHERE r.reaction = :reaction"),
    @NamedQuery(name = "Reaction.findByPropertyId", query = "SELECT r FROM Reaction r WHERE r.reactionPK.propertyId = :propertyId"),
    @NamedQuery(name = "Reaction.findByUserId", query = "SELECT r FROM Reaction r WHERE r.reactionPK.userId = :userId")})
public class Reaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReactionPK reactionPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reaction")
    private int reaction;
    @JoinColumn(name = "property_id", referencedColumnName = "property_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Property property;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Reaction() {
    }

    public Reaction(ReactionPK reactionPK) {
        this.reactionPK = reactionPK;
    }

    public Reaction(ReactionPK reactionPK, int reaction) {
        this.reactionPK = reactionPK;
        this.reaction = reaction;
    }

    public Reaction(int propertyId, int userId) {
        this.reactionPK = new ReactionPK(propertyId, userId);
    }

    public ReactionPK getReactionPK() {
        return reactionPK;
    }

    public void setReactionPK(ReactionPK reactionPK) {
        this.reactionPK = reactionPK;
    }

    public int getReaction() {
        return reaction;
    }

    public void setReaction(int reaction) {
        this.reaction = reaction;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reactionPK != null ? reactionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reaction)) {
            return false;
        }
        Reaction other = (Reaction) object;
        if ((this.reactionPK == null && other.reactionPK != null) || (this.reactionPK != null && !this.reactionPK.equals(other.reactionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.alma.models.Reaction[ reactionPK=" + reactionPK + " ]";
    }
    
}
