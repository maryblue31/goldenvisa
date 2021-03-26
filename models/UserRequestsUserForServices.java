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
@Table(name = "user_requests_user_for_services")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserRequestsUserForServices.findAll", query = "SELECT u FROM UserRequestsUserForServices u"),
    @NamedQuery(name = "UserRequestsUserForServices.findByUser1Id", query = "SELECT u FROM UserRequestsUserForServices u WHERE u.userRequestsUserForServicesPK.user1Id = :user1Id"),
    @NamedQuery(name = "UserRequestsUserForServices.findByUser2Id", query = "SELECT u FROM UserRequestsUserForServices u WHERE u.userRequestsUserForServicesPK.user2Id = :user2Id"),
    @NamedQuery(name = "UserRequestsUserForServices.findByStatus", query = "SELECT u FROM UserRequestsUserForServices u WHERE u.status = :status"),
    @NamedQuery(name = "UserRequestsUserForServices.findByDateOfRequest", query = "SELECT u FROM UserRequestsUserForServices u WHERE u.dateOfRequest = :dateOfRequest")})
public class UserRequestsUserForServices implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserRequestsUserForServicesPK userRequestsUserForServicesPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_of_request")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfRequest;
    @JoinColumn(name = "user1_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "user2_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user1;

    public UserRequestsUserForServices() {
    }

    public UserRequestsUserForServices(UserRequestsUserForServicesPK userRequestsUserForServicesPK) {
        this.userRequestsUserForServicesPK = userRequestsUserForServicesPK;
    }

    public UserRequestsUserForServices(UserRequestsUserForServicesPK userRequestsUserForServicesPK, int status, Date dateOfRequest) {
        this.userRequestsUserForServicesPK = userRequestsUserForServicesPK;
        this.status = status;
        this.dateOfRequest = dateOfRequest;
    }

    public UserRequestsUserForServices(int user1Id, int user2Id) {
        this.userRequestsUserForServicesPK = new UserRequestsUserForServicesPK(user1Id, user2Id);
    }

    public UserRequestsUserForServicesPK getUserRequestsUserForServicesPK() {
        return userRequestsUserForServicesPK;
    }

    public void setUserRequestsUserForServicesPK(UserRequestsUserForServicesPK userRequestsUserForServicesPK) {
        this.userRequestsUserForServicesPK = userRequestsUserForServicesPK;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDateOfRequest() {
        return dateOfRequest;
    }

    public void setDateOfRequest(Date dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
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
        hash += (userRequestsUserForServicesPK != null ? userRequestsUserForServicesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRequestsUserForServices)) {
            return false;
        }
        UserRequestsUserForServices other = (UserRequestsUserForServices) object;
        if ((this.userRequestsUserForServicesPK == null && other.userRequestsUserForServicesPK != null) || (this.userRequestsUserForServicesPK != null && !this.userRequestsUserForServicesPK.equals(other.userRequestsUserForServicesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.alma.models.UserRequestsUserForServices[ userRequestsUserForServicesPK=" + userRequestsUserForServicesPK + " ]";
    }
    
}
