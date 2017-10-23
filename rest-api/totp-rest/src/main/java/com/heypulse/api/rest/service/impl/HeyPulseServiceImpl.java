package com.heypulse.api.rest.service.impl;

import com.heypulse.api.rest.dao.AppKeyDAO;
import com.heypulse.api.rest.dao.UserDAO;
import com.heypulse.api.rest.entity.AppKey;
import com.heypulse.api.rest.entity.User;
import com.heypulse.api.rest.service.HeyPulseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by emrahsoytekin on 22.10.2017.
 */
@Component
public class HeyPulseServiceImpl implements HeyPulseService{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AppKeyDAO appKeyDAO;

    @Override
    public void saveUser(User user) {
        userDAO.save (user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.getByUsername (username);
    }

    @Override
    public List<AppKey> getAppKeysByUsername(String username) {
        User user = userDAO.getByUsername (username);
        return appKeyDAO.getByUser (user);
    }


    @Override
    public AppKey saveAppKey(AppKey appKey) {
        appKeyDAO.save (appKey);
        return appKey;
    }

    @Override
    public AppKey updateAppKey(AppKey appKey) {
        appKeyDAO.update (appKey);
        return appKey;
    }

    @Override
    public boolean deleteAppKey(AppKey appKey) {

        appKeyDAO.delete (appKey);
        return true;
    }
}
