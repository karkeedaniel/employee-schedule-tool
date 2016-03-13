package com.psu.est.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by danielkarkee on 3/2/16.
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/technician", method = RequestMethod.GET)
    public ModelAndView technicianPage() {
        ModelAndView mav = new ModelAndView("technician");
        mav.addObject("user", getPrincipal());
        return mav;
    }

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public ModelAndView resetPasswordPage() {
        return new ModelAndView("reset");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerPage() {
        return new ModelAndView("register");
    }

    private String getPrincipal() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
