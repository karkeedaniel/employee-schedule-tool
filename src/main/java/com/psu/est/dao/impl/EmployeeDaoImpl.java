package com.psu.est.dao.impl;

import com.psu.est.dao.common.GenericDaoImpl;
import com.psu.est.dao.interfaces.EmployeeAccountDao;
import com.psu.est.dao.interfaces.EmployeeDao;
import com.psu.est.model.Employee;
import com.psu.est.model.EmployeeAccount;
import com.psu.est.model.Schedule;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielkarkee on 2/2/16.
 */
@Repository
@Transactional
public class EmployeeDaoImpl extends GenericDaoImpl<Employee> implements EmployeeDao {

    @Autowired
    EmployeeAccountDao employeeAccountDao;

    public EmployeeDaoImpl() {
        super(Employee.class);
    }

    @Override
    public List<Employee> getListByRole(String role) throws DataAccessException {

        // get a list of accounts with this role
        List<EmployeeAccount> accounts = employeeAccountDao.GetEmployeeAccountsByRole(role);
        if (accounts == null || accounts.isEmpty()) return null;
        // else build list of employeess
        java.util.List<Employee> employees = new ArrayList<Employee>();
        for (EmployeeAccount account : accounts)
        {
            Employee employeeToAdd = this.get(account.getEmployeeId());
            if (employeeToAdd != null )
                employees.add(employeeToAdd);
        }
        return employees;
    }
}