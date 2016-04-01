package com.psu.est.service;

import com.psu.est.dao.interfaces.EmployeeDao;
import com.psu.est.model.Employee;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public void persist(Employee employee) {
        String allowedChars = "ABCDEFGRHIJKLMNPQRSTUVWXYZ0123456789";
        String employeeNum;
        do {
            employeeNum = RandomStringUtils.random(7, allowedChars.toCharArray());
        } while (employeeDao.getByEmployeeNum(employeeNum) != null);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        employee.setSsn(encoder.encode(employee.getSsn()));
        employee.setEmployeeNum(employeeNum);
        employeeDao.persist(employee);
    }
}
