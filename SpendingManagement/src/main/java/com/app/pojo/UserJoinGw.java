/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.pojo;

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
 * @author Huynh
 */
@Entity
@Table(name = "user_join_gw")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserJoinGw.findAll", query = "SELECT u FROM UserJoinGw u"),
    @NamedQuery(name = "UserJoinGw.findById", query = "SELECT u FROM UserJoinGw u WHERE u.id = :id")})
public class UserJoinGw implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "gw_id", referencedColumnName = "id")
    @ManyToOne
    private GroupWallet gwId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;

    public UserJoinGw() {
    }

    public UserJoinGw(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GroupWallet getGwId() {
        return gwId;
    }

    public void setGwId(GroupWallet gwId) {
        this.gwId = gwId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserJoinGw)) {
            return false;
        }
        UserJoinGw other = (UserJoinGw) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.pojo.UserJoinGw[ id=" + id + " ]";
    }
    
}
