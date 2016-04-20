package com.psu.est.controller;

import com.psu.est.service.EmployeeAccountService;
import com.psu.est.view.Reset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by danielkarkee on 4/20/16.
 */
@RestController
public class ResetController {

    @Autowired
    private EmployeeAccountService employeeAccountService;

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public ModelAndView resetPage() {
        return new ModelAndView("reset");
    }


    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public void register(@RequestBody Reset reset) {
        employeeAccountService.resetPassword(reset);
    }
}
