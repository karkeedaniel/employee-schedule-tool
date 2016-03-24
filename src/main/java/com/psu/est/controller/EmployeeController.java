package com.psu.est.controller;

import com.psu.est.model.Employee;
import com.psu.est.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/empModal", method = RequestMethod.GET)
    public ModelAndView employeeModal() {
        return new ModelAndView("empModal");
    }

    @RequestMapping(value = "/addEmpModal", method = RequestMethod.GET)
    public ModelAndView addEmployeeModal() {
        return new ModelAndView("addEmpModal");
    }

    @RequestMapping(value = "/employee/update", method = RequestMethod.POST)
    public Employee update(@RequestBody Employee employee) {
        employeeService.update(employee);
        return employee;
    }


}
