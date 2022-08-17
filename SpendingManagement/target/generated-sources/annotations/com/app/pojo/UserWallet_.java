package com.app.pojo;

import com.app.pojo.User;
import com.app.pojo.Wallet;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-08-17T23:59:40")
@StaticMetamodel(UserWallet.class)
public class UserWallet_ { 

    public static volatile SingularAttribute<UserWallet, Wallet> walletId;
    public static volatile SingularAttribute<UserWallet, Integer> id;
    public static volatile SingularAttribute<UserWallet, User> userId;

}