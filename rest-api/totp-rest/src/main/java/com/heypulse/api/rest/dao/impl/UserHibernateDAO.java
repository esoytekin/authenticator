package com.heypulse.api.rest.dao.impl;

import com.heypulse.api.rest.dao.UserDAO;
import com.heypulse.api.rest.entity.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by emrahsoytekin on 22.10.2017.
 */
@Repository
@Transactional
public class UserHibernateDAO extends SessionHibernateDAO implements UserDAO{
    @Override
    public void save(User entity) {
        getCurrentSession ().save (entity);
    }

    @Override
    public void update(User entity) {
        getCurrentSession ().update (entity);

    }

    @Override
    public void delete(User entity) {
        getCurrentSession ().delete (entity);

    }

    @Override
    public User getById(Long id) {
        return (User) getCurrentSession ().get (User.class,id);
    }

    @Override
    public User getByUsername(String username) {
        Criteria criteria = getCurrentSession ().createCriteria (User.class);
        criteria.add (Restrictions.eq ("username", username));
        return (User) criteria.uniqueResult ();
    }

    @Override
    public List<User> getList() {
        Criteria criteria = getCurrentSession ().createCriteria (User.class);
        return criteria.list ();
    }
}
