package com.app.pojo;

import com.app.pojo.Transaction;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-08-12T16:08:04")
@StaticMetamodel(Items.class)
public class Items_ { 

    public static volatile SingularAttribute<Items, String> name;
    public static volatile SetAttribute<Items, Transaction> transactionSet;
    public static volatile SingularAttribute<Items, Integer> id;

}