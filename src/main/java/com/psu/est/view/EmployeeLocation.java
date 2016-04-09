package com.psu.est.view;

import com.psu.est.model.Employee;
import com.psu.est.model.Location;

import java.io.Serializable;

/**
 * Created by danielkarkee on 4/5/16.
 */
public class EmployeeLocation implements Serializable {

    private Employee employee;
    private Location location;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
