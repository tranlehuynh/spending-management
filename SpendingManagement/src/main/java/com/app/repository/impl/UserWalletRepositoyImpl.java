package com.app.repository.impl;

import com.app.pojo.UserWallet;
import com.app.repository.UserWalletRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserWalletRepositoyImpl implements UserWalletRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<UserWallet> getUserWallets() {
        Session s = sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM UserWallet");
        return q.getResultList();
    }

    @Override
    public void addUserWallet(UserWallet uw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.save(uw);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
