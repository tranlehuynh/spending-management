package com.app.pojo;

import com.app.pojo.Category;
import com.app.pojo.Transaction;
import com.app.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-08-12T16:08:04")
@StaticMetamodel(Wallet.class)
public class Wallet_ { 

    public static volatile SingularAttribute<Wallet, String> name;
    public static volatile SingularAttribute<Wallet, Double> totalMoney;
    public static volatile SetAttribute<Wallet, Transaction> transactionSet;
    public static volatile SingularAttribute<Wallet, Integer> id;
    public static volatile SingularAttribute<Wallet, Category> category;
    public static volatile SetAttribute<Wallet, User> userSet;

}