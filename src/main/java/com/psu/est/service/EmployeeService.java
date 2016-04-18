package com.psu.est.service;

import com.psu.est.dao.interfaces.EmployeeDao;
import com.psu.est.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by danielkarkee on 2/9/16.
 */
@Service
public class EmployeeService {

    private static final Logger logger = LogManager.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeDao employeeDao;

    /**
     *
     * @return
     */
    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    /**
     *
     * @param role
     * @return
     */
    public List<Employee> getByNotLikeRole(String role) {
        List<Employee> employeeList = null;
        if ("DIRECTOR".equals(role)) {
            employeeList = employeeDao.getByNotLikeRole(Collections.singletonList(role));
        } else if ("MANAGER".equals(role)) {
            employeeList = employeeDao.getByNotLikeRole(Arrays.asList("DIRECTOR", role));
        }
        return employeeList;
    }
    /**
     *
     * @param employee
     */
    public void update(Employee employee) {
        employeeDao.update(employee);
    }
}
