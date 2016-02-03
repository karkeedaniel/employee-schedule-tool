package com.psu.est.model;

import com.psu.est.model.interfaces.DomainObject;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by danielkarkee on 2/2/16.
 */
@Entity
public class Employee implements DomainObject {
    private int employeeId;
    private String firstName;

    @Id
    @Column(name = "employee_id")
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (employeeId != employee.employeeId) return false;
        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = employeeId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        return result;
    }
}
