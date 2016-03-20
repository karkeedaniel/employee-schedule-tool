package com.psu.est.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by danielkarkee on 3/2/16.
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public ModelAndView resetPasswordPage() {
        return new ModelAndView("reset");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerPage() {
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/user-url", method = RequestMethod.GET)
    public ModelMap userAndUrl() {
        ModelMap model = new ModelMap();
        model.addAttribute("user", getPrincipal());
        model.addAttribute("url", getTargetUrl());
        return model;
    }

    @RequestMapping(value = "/mgr", method = RequestMethod.GET)
    public ModelAndView managerPage() {
        return new ModelAndView("mgr");
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

    /*
     * This method extracts the roles of currently logged-in user and returns
     * appropriate URL according to his/her role.
     */
    protected String getTargetUrl() {
        String url = "";
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> roles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        if (isManager(roles)) {
            url = "/mgr";
        }
        return url;
    }

    private boolean isManager(List<String> roles) {
        return roles.contains("MANAGER");
    }
}
