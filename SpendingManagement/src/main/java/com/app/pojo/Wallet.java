package com.app.pojo;

import java.io.Serializable;
import java.util.Set;
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
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "wallet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wallet.findAll", query = "SELECT w FROM Wallet w"),
    @NamedQuery(name = "Wallet.findById", query = "SELECT w FROM Wallet w WHERE w.id = :id"),
    @NamedQuery(name = "Wallet.findByName", query = "SELECT w FROM Wallet w WHERE w.name = :name"),
    @NamedQuery(name = "Wallet.findByTotalMoney", query = "SELECT w FROM Wallet w WHERE w.totalMoney = :totalMoney")})
public class Wallet implements Serializable {

    @Column(name = "owner")
    private Integer owner;

    @OneToMany(mappedBy = "walletId")
    private Set<UserWallet> userWalletSet;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_money")
    private Double totalMoney;
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    @ManyToOne
//    private User userId;
    @OneToMany(mappedBy = "walletId")
    private Set<Transaction> transactionSet;
    
    @Transient
    private String userWalletTemp;
    public String getUserWalletTemp() {
        return userWalletTemp;
    }
    
    public void setUserWalletTemp(String userWalletTemp) {
        this.userWalletTemp = userWalletTemp;
    } 

    public Wallet() {
    }

    public Wallet(Integer id) {
        this.id = id;
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

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

//    public User getUserId() {
//        return userId;
//    }
//
//    public void setUserId(User userId) {
//        this.userId = userId;
//    }

    @XmlTransient
    public Set<Transaction> getTransactionSet() {
        return transactionSet;
    }

    public void setTransactionSet(Set<Transaction> transactionSet) {
        this.transactionSet = transactionSet;
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
        if (!(object instanceof Wallet)) {
            return false;
        }
        Wallet other = (Wallet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.pojo.Wallet[ id=" + id + " ]";
    }

    @XmlTransient
    public Set<UserWallet> getUserWalletSet() {
        return userWalletSet;
    }

    public void setUserWalletSet(Set<UserWallet> userWalletSet) {
        this.userWalletSet = userWalletSet;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }
    
}
