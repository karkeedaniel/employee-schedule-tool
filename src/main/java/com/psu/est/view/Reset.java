package com.psu.est.view;

import java.io.Serializable;

/**
 * Created by danielkarkee on 4/20/16.
 */
public class Reset implements Serializable {

    private int year;
    private String employeeNum;
    private String username;
    private String password;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
