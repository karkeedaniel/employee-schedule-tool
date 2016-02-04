package com.psu.est.service;

import com.psu.est.dao.interfaces.EmployeeAccountDao;
import com.psu.est.model.EmployeeAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by danielkarkee on 2/3/16.
 */
@Service
public class EmployeeAccountService {

    @Autowired
    private EmployeeAccountDao employeeAccountDao;

    public List<EmployeeAccount> getAll() {
        return employeeAccountDao.getAll();
    }
}
