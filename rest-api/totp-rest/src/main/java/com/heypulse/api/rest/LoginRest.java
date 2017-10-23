package com.heypulse.api.rest;

import com.heypulse.api.rest.entity.Role;
import com.heypulse.api.rest.entity.SignElement;
import com.heypulse.api.rest.entity.User;
import com.heypulse.api.rest.entity.UserRole;
import com.heypulse.api.rest.service.HeyPulseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by emrahsoytekin on 22.10.2017.
 */
@Component
@RequestMapping("/login")
public class LoginRest {

    @Autowired
    private HeyPulseService heyPulseService;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    @ResponseBody
    public boolean signUp(@ModelAttribute SignElement signElement) {
        User user = new User ();
        user.setName (signElement.getLgFirstName ());
        user.setSurname (signElement.getLgLastName ());
        user.setUsername (signElement.getLgUsername ());
        user.setEmail (signElement.getLgEmail ());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder ();
        String hashedPassword = passwordEncoder.encode (signElement.getLgPassword ());
        user.setPassword (hashedPassword);

        user.setCreateDate (new Date ());
        user.setEnabled (true);

        UserRole userRole = new UserRole();
        userRole.setUsername (signElement.getLgUsername ());
        userRole.setRole (Role.ROLE_USER);
        user.setUserRole (userRole);

        heyPulseService.saveUser (user);

        return true;

    }
}
