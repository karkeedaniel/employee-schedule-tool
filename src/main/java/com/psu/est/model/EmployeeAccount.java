package com.psu.est.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.psu.est.model.interfaces.DomainObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * Created by danielkarkee on 2/3/16.
 */
@Entity
@Table(name = "employee_account", schema = "est")
public class EmployeeAccount implements DomainObject {

    private int employeeId;
    private String username;
    private String password;
    private Employee employee;

    @Id
    @Column(name = "employee_id", nullable = false)
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name="property", value="employee"))
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 45)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    @Basic
    @Column(name = "password", nullable = false, length = 80)
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonIgnore
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "EmployeeAccount{" +
                "employeeId=" + employeeId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
