package com.psu.est.controller;

import com.psu.est.model.Employee;
import com.psu.est.service.EmployeeService;
import com.psu.est.view.EmployeeLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by danielkarkee on 4/5/16.
 */
@RestController
public class EmployeeLocationController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employee-location/persist", method = RequestMethod.POST)
    public Employee persist(@RequestBody EmployeeLocation employeeLocation) {
        employeeService.persist(employeeLocation);
        return employeeLocation.getEmployee();
    }
}
