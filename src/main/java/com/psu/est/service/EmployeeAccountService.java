package com.psu.est.service;

import com.psu.est.dao.interfaces.EmployeeAccountDao;
import com.psu.est.dao.interfaces.EmployeeDao;
import com.psu.est.model.Employee;
import com.psu.est.model.EmployeeAccount;
import com.psu.est.service.exception.ValidationException;
import com.psu.est.view.Registration;
import com.psu.est.view.Reset;
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

    /**
     *
     * @param registration
     */
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

    /**
     *
     * @param username
     * @return
     */
    public EmployeeAccount getByUsername(String username) {
        return employeeAccountDao.getByUsername(username);
    }

    public void resetPassword(Reset reset) {
        Employee employee = employeeDao.getByEmployeeNum(reset.getEmployeeNum());

        if(resetPasswordValidation(reset, employee)) {
            EmployeeAccount employeeAccount = employee.getEmployeeAccount();
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            employeeAccount.setPassword(passwordEncoder.encode(reset.getPassword()));
            employeeAccountDao.update(employeeAccount);
        } else {
            throw new ValidationException();
        }
    }

    private boolean resetPasswordValidation(Reset reset, Employee employee) {

        if (employee == null)
            return false;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(employee.getDob().getTime());

        if (reset.getYear() != calendar.getWeekYear())
            return false;

        if (employee.getEmployeeAccount() == null)
            return false;

        return employee.getEmployeeAccount().getUsername().equals(reset.getUsername());

    }
}
