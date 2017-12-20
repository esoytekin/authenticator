package com.heypulse.api.rest.service;

import com.heypulse.api.rest.entity.AppKey;
import com.heypulse.api.rest.entity.User;

import java.util.List;

/**
 * Created by emrahsoytekin on 22.10.2017.
 */
public interface AppKeyService {
    void saveUser(User user);

    User getUserByUsername(String username);

    List<AppKey> getAppKeysByUsername(String username);

    AppKey getAppKeyById(long id);

    AppKey saveAppKey(AppKey appKey);

    AppKey updateAppKey(AppKey appKey);

    boolean deleteAppKey(AppKey appKey);
}
