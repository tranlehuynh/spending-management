package com.app.pojo;

import com.app.pojo.Category;
import com.app.pojo.Transaction;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-08-17T14:40:56")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, String> image;
    public static volatile SingularAttribute<Item, String> name;
    public static volatile SetAttribute<Item, Transaction> transactionSet;
    public static volatile SingularAttribute<Item, Integer> id;
    public static volatile SingularAttribute<Item, Category> categoryId;

}