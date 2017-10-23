package com.heypulse.api.rest.dao.impl;

import com.heypulse.api.rest.dao.AppKeyDAO;
import com.heypulse.api.rest.entity.AppKey;
import com.heypulse.api.rest.entity.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by emrahsoytekin on 22.10.2017.
 */
@Repository
@Transactional
public class AppKeyHibernateDAO extends SessionHibernateDAO implements AppKeyDAO{

    @Override
    public void save(AppKey entity) {
        getCurrentSession ().save (entity);
    }

    @Override
    public void update(AppKey entity) {
        getCurrentSession ().update (entity);

    }

    @Override
    public void delete(AppKey entity) {
        getCurrentSession ().delete (entity);

    }

    @Override
    public AppKey getById(Long id) {

        return (AppKey) getCurrentSession ().get (AppKey.class, id);
    }

    @Override
    public List<AppKey> getByUser(User user) {
        Criteria criteria = getCurrentSession ().createCriteria (AppKey.class);
        criteria.add (Restrictions.eq ("user", user));
        criteria.addOrder (Order.asc ("site"));
        return criteria.list ();
    }
}
