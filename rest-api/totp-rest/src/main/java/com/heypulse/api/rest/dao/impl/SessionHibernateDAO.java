package com.heypulse.api.rest.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by emrahsoytekin on 22.10.2017.
 */
public class SessionHibernateDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession ();
    }
}
