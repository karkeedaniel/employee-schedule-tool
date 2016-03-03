package com.psu.est.model;

import com.psu.est.model.JobAssignment;
import com.psu.est.model.interfaces.DomainObject;

import java.time.LocalTime;
import java.util.List;

/**
 * Created by Nana on 3/4/2016.
 */
public class Schedule implements DomainObject{

    private LocalTime startTime;
    private Location location;
    private JobAssignment jobAssignment;
    private List<Employee> numberOfEmployees;

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public JobAssignment getJobAssignment() {
        return jobAssignment;
    }

    public void setJobAssignment(JobAssignment jobAssignment) {
        this.jobAssignment = jobAssignment;
    }

    public List<Employee> getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(List<Employee> numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }
}
