package com.heypulse.api.rest;

import com.heypulse.api.rest.model.SignElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by emrahsoytekin on 29.10.2017.
 */
@Component
@RequestMapping("/heyPulseLogin")
public class LoginHeyPulseRest {
    private static final Logger logger = LoggerFactory.getLogger (LoginHeyPulseRest.class);
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void signUp(@ModelAttribute SignElement signElement) {
        logger.debug ("incoming heypulse login request: {}", signElement);

    }
}

