package com.psu.est.service;

import com.psu.est.dao.interfaces.EmployeeDao;
import com.psu.est.dao.interfaces.LocationDao;
import com.psu.est.model.Employee;
import com.psu.est.model.Location;
import com.psu.est.service.exception.InvalidAddressException;
import com.psu.est.view.EmployeeLocation;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by danielkarkee on 4/18/16.
 */
@Service
public class EmployeeLocationService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private LocationDao locationDao;

    @Autowired
    private LocationService locationService;


    @Transactional
    public void persist(EmployeeLocation employeeLocation) throws InvalidAddressException {
        Employee employee = employeeLocation.getEmployee();
        Location location = employeeLocation.getLocation();
        try {
            locationService.resolveAddress(location);
        } catch (Exception e) {
            throw new InvalidAddressException();
        }
        locationDao.persist(location);
        String allowedChars = "ABCDEFGRHIJKLMNPQRSTUVWXYZ0123456789";
        String employeeNum;
        do {
            employeeNum = RandomStringUtils.random(7, allowedChars.toCharArray());
        } while (employeeDao.getByEmployeeNum(employeeNum) != null);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        employee.setSsn(encoder.encode(employee.getSsn()));
        employee.setEmployeeNum(employeeNum);
        employee.setBaseLocationId(location.getLocationId());
        employeeDao.persist(employee);
    }

    /**
     *
     * @param employeeId
     * @return
     */
    public EmployeeLocation getByEmployeeId(int employeeId) {
        EmployeeLocation employeeLocation = new EmployeeLocation();
        Employee employee = employeeDao.get(employeeId);
        employeeLocation.setEmployee(employee);
        if (employee != null && employee.getBaseLocationId() != null && employee.getBaseLocationId() != 0) {
            Location location = locationDao.get(employee.getBaseLocationId());
            employeeLocation.setLocation(location);
        }
        return employeeLocation;
    }
}
