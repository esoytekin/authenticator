package com.heypulse.api.rest.dao;

import com.heypulse.api.rest.entity.User;

import java.util.List;

/**
 * Created by emrahsoytekin on 22.10.2017.
 */
public interface UserDAO {
    void save(User entity);
    void update(User entity);
    void delete(User entity);
    User getById(Long id);
    User getByUsername(String username);
    List<User> getList();
}
