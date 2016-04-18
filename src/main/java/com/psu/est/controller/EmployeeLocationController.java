package com.psu.est.controller;

import com.psu.est.model.Employee;
import com.psu.est.service.EmployeeLocationService;
import com.psu.est.view.EmployeeLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by danielkarkee on 4/5/16.
 */
@RestController
public class EmployeeLocationController {

    @Autowired
    private EmployeeLocationService employeeLocationService;

    @RequestMapping(value = "/employee-location/persist", method = RequestMethod.POST)
    public Employee persist(@RequestBody EmployeeLocation employeeLocation) {
        employeeLocationService.persist(employeeLocation);
        return employeeLocation.getEmployee();
    }

    @RequestMapping(value = "/employee-location/get", method = RequestMethod.GET)
    public EmployeeLocation getByEmployeeId(@RequestParam("employeeId") int employeeId) {
        return employeeLocationService.getByEmployeeId(employeeId);
    }
}
