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
@Table(name = "chat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chat.findAll", query = "SELECT c FROM Chat c"),
    @NamedQuery(name = "Chat.findByChatId", query = "SELECT c FROM Chat c WHERE c.chatId = :chatId"),
    @NamedQuery(name = "Chat.findByHeader", query = "SELECT c FROM Chat c WHERE c.header = :header"),
    @NamedQuery(name = "Chat.findByDatetimeStarted", query = "SELECT c FROM Chat c WHERE c.datetimeStarted = :datetimeStarted"),
    @NamedQuery(name = "Chat.findByDatetimeUpdated", query = "SELECT c FROM Chat c WHERE c.datetimeUpdated = :datetimeUpdated")})
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "chat_id")
    private Integer chatId;
    @Size(max = 45)
    @Column(name = "header")
    private String header;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datetime_started")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetimeStarted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datetime_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetimeUpdated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conversationId")
    private Collection<Message> messageCollection;
    @JoinColumn(name = "user1_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User user1Id;
    @JoinColumn(name = "user2_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User user2Id;

    public Chat() {
    }

    public Chat(Integer chatId) {
        this.chatId = chatId;
    }

    public Chat(Integer chatId, Date datetimeStarted, Date datetimeUpdated) {
        this.chatId = chatId;
        this.datetimeStarted = datetimeStarted;
        this.datetimeUpdated = datetimeUpdated;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Date getDatetimeStarted() {
        return datetimeStarted;
    }

    public void setDatetimeStarted(Date datetimeStarted) {
        this.datetimeStarted = datetimeStarted;
    }

    public Date getDatetimeUpdated() {
        return datetimeUpdated;
    }

    public void setDatetimeUpdated(Date datetimeUpdated) {
        this.datetimeUpdated = datetimeUpdated;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    public User getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(User user1Id) {
        this.user1Id = user1Id;
    }

    public User getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(User user2Id) {
        this.user2Id = user2Id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chatId != null ? chatId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chat)) {
            return false;
        }
        Chat other = (Chat) object;
        if ((this.chatId == null && other.chatId != null) || (this.chatId != null && !this.chatId.equals(other.chatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.alma.models.Chat[ chatId=" + chatId + " ]";
    }
    
}
