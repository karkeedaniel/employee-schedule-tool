package com.psu.est.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by danielkarkee on 3/2/16.
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelMap userAndUrl() {
        ModelMap model = new ModelMap();
        model.addAttribute("user", getUserName());
        model.addAttribute("role", getRole());
        return model;
    }

    private String getUserName() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    private String getRole() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> roles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return roles.get(0);
    }
}
