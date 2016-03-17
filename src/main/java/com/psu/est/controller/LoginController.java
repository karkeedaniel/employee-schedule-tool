package com.psu.est.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by danielkarkee on 3/2/16.
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/technician", method = RequestMethod.GET)
    public ModelAndView technicianPage() {
        return new ModelAndView("technician");
    }

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public ModelAndView resetPasswordPage() {
        return new ModelAndView("reset");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerPage() {
        return new ModelAndView("register");
    }
}
