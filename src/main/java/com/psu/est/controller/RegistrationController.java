package com.psu.est.controller;

import com.psu.est.service.EmployeeAccountService;
import com.psu.est.view.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by danielkarkee on 4/3/16.
 */
@RestController
public class RegistrationController {

    @Autowired
    private EmployeeAccountService employeeAccountService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerPage() {
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@RequestBody Registration registration) {
        employeeAccountService.register(registration);
    }
}
