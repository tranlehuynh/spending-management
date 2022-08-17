package com.app.pojo;

import com.app.pojo.Item;
import com.app.pojo.Wallet;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-08-17T23:59:40")
@StaticMetamodel(Transaction.class)
public class Transaction_ { 

    public static volatile SingularAttribute<Transaction, Date> date;
    public static volatile SingularAttribute<Transaction, String> note;
    public static volatile SingularAttribute<Transaction, Item> itemId;
    public static volatile SingularAttribute<Transaction, Wallet> walletId;
    public static volatile SingularAttribute<Transaction, Double> amount;
    public static volatile SingularAttribute<Transaction, Integer> id;

}