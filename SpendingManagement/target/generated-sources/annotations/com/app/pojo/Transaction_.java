package com.app.pojo;

import com.app.pojo.Items;
import com.app.pojo.Wallet;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-08-12T16:08:04")
@StaticMetamodel(Transaction.class)
public class Transaction_ { 

    public static volatile SingularAttribute<Transaction, Date> date;
    public static volatile SingularAttribute<Transaction, String> note;
    public static volatile SingularAttribute<Transaction, Items> itemId;
    public static volatile SingularAttribute<Transaction, Double> amount;
    public static volatile SingularAttribute<Transaction, Wallet> walletTransactionId;
    public static volatile SingularAttribute<Transaction, Integer> id;

}