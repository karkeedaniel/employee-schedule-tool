package com.psu.est.service;

import com.psu.est.dao.interfaces.EmployeeAccountDao;
import com.psu.est.dao.interfaces.EmployeeDao;
import com.psu.est.model.Employee;
import com.psu.est.model.EmployeeAccount;
import com.psu.est.service.exception.ValidationException;
import com.psu.est.view.Registration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * Created by danielkarkee on 4/3/16.
 */
@Service
public class EmployeeAccountService {

    private static final Logger logger = LogManager.getLogger(EmployeeAccountService.class);

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeAccountDao employeeAccountDao;

    public void register(Registration registration) {
        Employee employee = employeeDao.getByEmail(registration.getEmail());
        if(employee != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(employee.getDob().getTime());
            if (registration.getYear() == calendar.getWeekYear() && employee.getEmployeeAccount() == null) {
                EmployeeAccount employeeAccount = new EmployeeAccount();
                employeeAccount.setEmployee(employee);
                employeeAccount.setUsername(registration.getUsername());
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                employeeAccount.setPassword(passwordEncoder.encode(registration.getPassword()));
                employeeAccountDao.persist(employeeAccount);
            } else {
                throw new ValidationException();
            }
        } else {
            throw new ValidationException();
        }
    }
}
