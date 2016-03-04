package com.psu.est.controller;

import com.psu.est.model.Employee;
import com.psu.est.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by danielkarkee on 2/9/16.
 */
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employee/get-all/", method = RequestMethod.GET)
    public List<Employee> getAll() {
        return employeeService.getAll();
    }
}
