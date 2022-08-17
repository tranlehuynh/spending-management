//package com.app.repository.impl;
//
//import com.app.pojo.Wallet;
//import com.app.repository.WalletRepository;
//import java.util.List;
//import javax.persistence.Query;
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//@Repository
//@Transactional
//public class WalletRepositoryImpl implements WalletRepository {
//
//    @Autowired
//    private LocalSessionFactoryBean sessionFactory;
//
//    @Override
//    public List<Wallet> getWallets() {
//        Session s = sessionFactory.getObject().getCurrentSession();
//        Query q = s.createQuery("FROM Wallet");
//        return q.getResultList();
//    }
//}
