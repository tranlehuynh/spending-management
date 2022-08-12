package com.app.repository.impl;

import com.app.pojo.Category;
import com.app.repository.CategoryRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Category> getCategories() {
        Session s = sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Category");
        return q.getResultList();
    }
    
}
