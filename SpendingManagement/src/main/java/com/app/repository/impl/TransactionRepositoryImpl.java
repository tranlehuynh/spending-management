package com.app.repository.impl;

import com.app.pojo.Item;
import com.app.pojo.Transaction;
import com.app.repository.TransactionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@PropertySource("classpath:messages.properties")
@Transactional
public class TransactionRepositoryImpl implements TransactionRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Environment env;

    @Override
    public List<Transaction> getTransactionsPagination(Map<String, String> params, int page, String string) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Transaction> q = b.createQuery(Transaction.class);
        Root root = q.from(Transaction.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Predicate p = b.like(root.get("name").as(String.class), String.format("%%%s%%", kw));
                predicates.add(p);
            }
            q.where(predicates.toArray(Predicate[]::new));
        }
        String temp = "FROM Transaction t WHERE t.walletId.id =" + string;
        Query query = s.createQuery(temp);
        if (page > 0) {
            int size = Integer.parseInt(env.getProperty("page.size").toString());
            int start = (page - 1) * size;
            query.setFirstResult(start);
            query.setMaxResults(size);
        }
        return query.getResultList();
    }

    @Override
    public int countTransactions() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("SELECT COUNT(*) FROM Transaction");
        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addTransaction(Transaction t) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.save(t);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Transaction> getTransactions() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM Transaction");
        return q.getResultList();
    }

    @Override
    @Modifying
    public void updateTransaction(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query query = session.createQuery("UPDATE Transaction SET pending = 1 WHERE id = " + id);
        query.executeUpdate();
    }

    @Override
    @Modifying
    public void deleteTransactionById(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query query = session.createQuery("DELETE FROM Transaction WHERE id =" + id);
        query.executeUpdate();
    }

    @Override
    public List<Object[]> countTransactionsByItem() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rt = q.from(Transaction.class);
        Root ri = q.from(Item.class);

        q.where(b.equal(rt.get("itemId"), ri.get("id")));
        q.multiselect(ri.get("id"), ri.get("name"), b.count(rt.get("id")));
        q.groupBy(ri.get("id"));

        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void deleteTransactionByWalletId(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query query = session.createQuery("DELETE FROM Transaction WHERE walletId.id =" + id);
        query.executeUpdate();
    }

    @Override
    public Transaction getLastTransaction() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM Transaction ORDER BY id DESC");
        query.setMaxResults(1);
        return (Transaction) query.uniqueResult();
    }

    @Override
    public void updateTransactionPending2(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query query = session.createQuery("UPDATE Transaction SET pending = 2 WHERE id = " + id);
        query.executeUpdate();
    }
}
