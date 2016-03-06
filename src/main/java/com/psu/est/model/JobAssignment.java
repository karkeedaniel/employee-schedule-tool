package com.psu.est.model;

import com.psu.est.model.interfaces.DomainObject;
import org.hibernate.annotations.Columns;

import javax.persistence.*;

/**
 * Created by Nana on 3/4/2016.
 */
@Entity
public class JobAssignment implements DomainObject{

    enum JobPriority{
        Low, Medium, High
    }
    private Long assignmentId;
    private Employee employeeId;
    private Job jobId;
    private boolean jobAvailability;
    private Location jobLocation;
    private Double currentJobCost;

    @Id
    @GeneratedValue
    @Column(name = "assignmentId")
    public Long getAssignmentId(){
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId){
        this.assignmentId = assignmentId;
    }
    @Column(name = "employeeId")
    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    @Column(name = "jobId")
    public Job getJobId() {
        return jobId;
    }

    public void setJobId(Job jobId) {
        this.jobId = jobId;
    }

    @Column(name = "jobAvailability")
    public boolean isJobAvailable() {
        return jobAvailability;
    }

    public void setJobAvailability(boolean jobAvailability) {
        this.jobAvailability = jobAvailability;
    }

    @Column(name = "jobLocation")
    public Location getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(Location jobLocation) {
        this.jobLocation = jobLocation;
    }

    //Not so sure if this property is needed
    public Double getCurrentJobCost() {
        return currentJobCost;
    }

    public void setCurrentJobCost(Double currentJobCost) {
        this.currentJobCost = currentJobCost;
    }
}
