package com.app.pojo;

import com.app.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2022-08-12T16:08:04")
@StaticMetamodel(Role.class)
public class Role_ { 

    public static volatile SingularAttribute<Role, String> name;
    public static volatile SingularAttribute<Role, String> description;
    public static volatile SingularAttribute<Role, Integer> id;
    public static volatile SingularAttribute<Role, User> user;

}