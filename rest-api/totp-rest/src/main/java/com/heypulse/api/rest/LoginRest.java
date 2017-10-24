package com.heypulse.api.rest;

import com.heypulse.api.rest.entity.Role;
import com.heypulse.api.rest.entity.SignElement;
import com.heypulse.api.rest.entity.User;
import com.heypulse.api.rest.entity.UserRole;
import com.heypulse.api.rest.service.HeyPulseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by emrahsoytekin on 22.10.2017.
 */
@Component
@RequestMapping("/login")
public class LoginRest {

    @Autowired
    private HeyPulseService heyPulseService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus (value = HttpStatus.OK)
    public void signUp(@ModelAttribute SignElement signElement) {
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

    }
}
