package com.heypulse.api.rest;

import com.google.gson.Gson;
import com.heypulse.api.rest.entity.AppKey;
import com.heypulse.api.rest.entity.User;
import com.heypulse.api.rest.service.AppKeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by emrahsoytekin on 22.10.2017.
 */
@Component
@RequestMapping("/totp")
public class AppKeyRest {


    private static final Logger logger = LoggerFactory.getLogger (AppKeyRest.class);

    @Autowired
    private AppKeyService appKeyService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<AppKey> getAppKeys(){
        String username = SecurityContextHolder.getContext ().getAuthentication ().getName ();
        logger.debug ("incoming appkey request from {}", username);

        List<AppKey> appKeys = appKeyService.getAppKeysByUsername (username);
        return appKeys;
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @ResponseBody
    public User getUserDetails(){
        String username = SecurityContextHolder.getContext ().getAuthentication ().getName ();
        User user = appKeyService.getUserByUsername (username);
        return user;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AppKey saveAppKey(@ModelAttribute AppKey appKey){
        String username = SecurityContextHolder.getContext ().getAuthentication ().getName ();
        User user = appKeyService.getUserByUsername (username);
        appKey.setUser (user);
        return appKeyService.saveAppKey (appKey);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AppKey updateAppKey(@RequestBody String json){
        Gson gson = new Gson ();
        AppKey appKey = gson.fromJson (json, AppKey.class);
        String username = SecurityContextHolder.getContext ().getAuthentication ().getName ();
        User user = appKeyService.getUserByUsername (username);
        appKey.setUser (user);
        return appKeyService.updateAppKey (appKey);
    }

    @RequestMapping(value = "/{idParam}",method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteAppKey(@PathVariable("idParam") long idParam) {
        AppKey appKey = new AppKey ();
        appKey.setId (idParam);
        return appKeyService.deleteAppKey (appKey);
    }
}
