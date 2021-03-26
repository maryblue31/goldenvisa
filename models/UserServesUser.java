/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "user_serves_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserServesUser.findAll", query = "SELECT u FROM UserServesUser u"),
    @NamedQuery(name = "UserServesUser.findByUser1Id", query = "SELECT u FROM UserServesUser u WHERE u.userServesUserPK.user1Id = :user1Id"),
    @NamedQuery(name = "UserServesUser.findByUser2Id", query = "SELECT u FROM UserServesUser u WHERE u.userServesUserPK.user2Id = :user2Id"),
    @NamedQuery(name = "UserServesUser.findByDatetimeHired", query = "SELECT u FROM UserServesUser u WHERE u.datetimeHired = :datetimeHired")})
public class UserServesUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserServesUserPK userServesUserPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datetime_hired")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetimeHired;
    @JoinColumn(name = "application_id", referencedColumnName = "application_id")
    @ManyToOne(optional = false)
    private Application applicationId;
    @JoinColumn(name = "user1_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "user2_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user1;

    public UserServesUser() {
    }

    public UserServesUser(UserServesUserPK userServesUserPK) {
        this.userServesUserPK = userServesUserPK;
    }

    public UserServesUser(UserServesUserPK userServesUserPK, Date datetimeHired) {
        this.userServesUserPK = userServesUserPK;
        this.datetimeHired = datetimeHired;
    }

    public UserServesUser(int user1Id, int user2Id) {
        this.userServesUserPK = new UserServesUserPK(user1Id, user2Id);
    }

    public UserServesUserPK getUserServesUserPK() {
        return userServesUserPK;
    }

    public void setUserServesUserPK(UserServesUserPK userServesUserPK) {
        this.userServesUserPK = userServesUserPK;
    }

    public Date getDatetimeHired() {
        return datetimeHired;
    }

    public void setDatetimeHired(Date datetimeHired) {
        this.datetimeHired = datetimeHired;
    }

    public Application getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Application applicationId) {
        this.applicationId = applicationId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userServesUserPK != null ? userServesUserPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserServesUser)) {
            return false;
        }
        UserServesUser other = (UserServesUser) object;
        if ((this.userServesUserPK == null && other.userServesUserPK != null) || (this.userServesUserPK != null && !this.userServesUserPK.equals(other.userServesUserPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.alma.models.UserServesUser[ userServesUserPK=" + userServesUserPK + " ]";
    }
    
}
