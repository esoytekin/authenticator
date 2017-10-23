package com.heypulse.api.rest;

import com.google.gson.Gson;
import com.heypulse.api.rest.entity.AppKey;
import com.heypulse.api.rest.entity.User;
import com.heypulse.api.rest.service.HeyPulseService;
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
public class HeyPulseRest {


    private static final Logger logger = LoggerFactory.getLogger (HeyPulseRest.class);

    @Autowired
    private HeyPulseService heyPulseService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<AppKey> getAppKeys(){
        String username = SecurityContextHolder.getContext ().getAuthentication ().getName ();
        logger.debug ("incoming appkey request from {}", username);

        List<AppKey> appKeys = heyPulseService.getAppKeysByUsername (username);
        return appKeys;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AppKey saveAppKey(@ModelAttribute AppKey appKey){
        String username = SecurityContextHolder.getContext ().getAuthentication ().getName ();
        User user = heyPulseService.getUserByUsername (username);
        appKey.setUser (user);
        return heyPulseService.saveAppKey (appKey);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AppKey updateAppKey(@RequestBody String json){
        Gson gson = new Gson ();
        AppKey appKey = gson.fromJson (json, AppKey.class);
        String username = SecurityContextHolder.getContext ().getAuthentication ().getName ();
        User user = heyPulseService.getUserByUsername (username);
        appKey.setUser (user);
        return heyPulseService.updateAppKey (appKey);
    }

    @RequestMapping(value = "/{idParam}",method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteAppKey(@PathVariable("idParam") long idParam) {
        AppKey appKey = new AppKey ();
        appKey.setId (idParam);
        return heyPulseService.deleteAppKey (appKey);
    }
}
