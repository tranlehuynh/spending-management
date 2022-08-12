/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Huynh
 */
@Entity
@Table(name = "group_wallet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupWallet.findAll", query = "SELECT g FROM GroupWallet g"),
    @NamedQuery(name = "GroupWallet.findById", query = "SELECT g FROM GroupWallet g WHERE g.id = :id"),
    @NamedQuery(name = "GroupWallet.findByName", query = "SELECT g FROM GroupWallet g WHERE g.name = :name"),
    @NamedQuery(name = "GroupWallet.findByDescription", query = "SELECT g FROM GroupWallet g WHERE g.description = :description")})
public class GroupWallet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 100)
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "gwId")
    private Set<UserJoinGw> userJoinGwSet;

    public GroupWallet() {
    }

    public GroupWallet(Integer id) {
        this.id = id;
    }

    public GroupWallet(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Set<UserJoinGw> getUserJoinGwSet() {
        return userJoinGwSet;
    }

    public void setUserJoinGwSet(Set<UserJoinGw> userJoinGwSet) {
        this.userJoinGwSet = userJoinGwSet;
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
        if (!(object instanceof GroupWallet)) {
            return false;
        }
        GroupWallet other = (GroupWallet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.pojo.GroupWallet[ id=" + id + " ]";
    }
    
}
