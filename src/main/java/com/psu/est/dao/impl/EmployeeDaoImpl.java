package com.psu.est.dao.impl;

import com.psu.est.dao.common.GenericDaoImpl;
import com.psu.est.dao.interfaces.EmployeeDao;
import com.psu.est.model.Employee;
import org.springframework.stereotype.Repository;

/**
 * Created by danielkarkee on 2/2/16.
 */
@Repository
public class EmployeeDaoImpl extends GenericDaoImpl<Employee> implements EmployeeDao {

    public EmployeeDaoImpl() {
        super(Employee.class);
    }
}