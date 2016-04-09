package com.psu.est.controller;

import com.psu.est.model.Employee;
import com.psu.est.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by danielkarkee on 2/9/16.
 */
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employee/get-all", method = RequestMethod.GET)
    public List<Employee> getAll() {
        return employeeService.getByNotLikeRole(getRole());
    }

    @RequestMapping(value = "/employee/update", method = RequestMethod.PUT)
    public Employee update(@RequestBody Employee employee) {
        employeeService.update(employee);
        return employee;
    }

    private String getRole() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> roles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return roles.get(0);
    }
}
