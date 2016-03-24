package com.psu.est.service;

import com.psu.est.dao.interfaces.EmployeeDao;
import com.psu.est.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by danielkarkee on 2/9/16.
 */
@Service
public class EmployeeService {

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
     * @param employee
     */
    public void update(Employee employee) {
        employeeDao.update(employee);
    }
}
