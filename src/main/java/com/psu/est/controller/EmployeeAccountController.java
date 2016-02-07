package com.psu.est.controller;

import com.psu.est.model.EmployeeAccount;
import com.psu.est.service.EmployeeAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by danielkarkee on 2/3/16.
 */
@RestController
public class EmployeeAccountController {

    @Autowired
    private EmployeeAccountService employeeAccountService;

    @RequestMapping(value = "/employeeaccount/get-all/", method = RequestMethod.GET)
    public List<EmployeeAccount> getAll() {
        return employeeAccountService.getAll();
    }
}
