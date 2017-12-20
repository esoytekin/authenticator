package com.heypulse.api.rest;

import com.heypulse.api.rest.entity.User;
import com.heypulse.api.rest.service.AppKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by emrahsoytekin on 14.12.2017.
 */
@Component
@RequestMapping("/user")
public class UserRest {

    @Autowired
    private AppKeyService appKeyService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public User getUserDetails(@PathVariable("id") String id){
        User user = appKeyService.getUserByUsername (id);
        return user;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public User getUserDetail() {

        String username = SecurityContextHolder.getContext ().getAuthentication ().getName ();
        User user = appKeyService.getUserByUsername (username);
        return user;
    }
}
