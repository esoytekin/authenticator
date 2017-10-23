package com.heypulse.api.rest.dao;

import com.heypulse.api.rest.entity.AppKey;
import com.heypulse.api.rest.entity.User;

import java.util.List;

/**
 * Created by emrahsoytekin on 22.10.2017.
 */
public interface AppKeyDAO {
    void save(AppKey entity);
    void update(AppKey entity);
    void delete(AppKey entity);
    AppKey getById(Long id);
    List<AppKey> getByUser(User user);
}
