package com.app.pojo;

import com.app.pojo.GroupWallet;
import com.app.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-08-12T16:08:04")
@StaticMetamodel(UserJoinGw.class)
public class UserJoinGw_ { 

    public static volatile SingularAttribute<UserJoinGw, Integer> id;
    public static volatile SingularAttribute<UserJoinGw, GroupWallet> gwId;
    public static volatile SingularAttribute<UserJoinGw, User> userId;

}