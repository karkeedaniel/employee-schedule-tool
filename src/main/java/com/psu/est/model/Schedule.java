package com.psu.est.model;

import com.psu.est.model.interfaces.DomainObject;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by gorzelic on 3/19/2016.
 */
@Entity
public class Schedule implements DomainObject {
    private Integer scheduleId;
    private String type;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer duration;
    private Integer travelTime;
    private Integer jobId;
    private Integer employeeId;

    static Integer GenUniqueKey(Integer scheduleID, Integer jobID)
    {
        //a >= b ? a * a + a + b : a + b * b;  where a, b >= 0, aka Szudzik's function
        Integer ret;
        ret= (scheduleID>=jobID)? (scheduleID*scheduleID)+scheduleID+jobID :
                                  (jobID*jobID)+scheduleID;
        return ret;
    }

    @Id
    @GeneratedValue
    @Column(name = "schedule_id", nullable = false)
    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 45)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "start_time", nullable = true)
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time", nullable = true)
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "duration", nullable = true)
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "travel_time", nullable = true)
    public Integer getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Integer travelTime) {
        this.travelTime = travelTime;
    }

    @Basic
    @Column(name = "job_id", nullable = true)
    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "employee_id", nullable = true)
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (scheduleId != null ? !scheduleId.equals(schedule.scheduleId) : schedule.scheduleId != null) return false;
        if (type != null ? !type.equals(schedule.type) : schedule.type != null) return false;
        if (startTime != null ? !startTime.equals(schedule.startTime) : schedule.startTime != null) return false;
        if (endTime != null ? !endTime.equals(schedule.endTime) : schedule.endTime != null) return false;
        if (duration != null ? !duration.equals(schedule.duration) : schedule.duration != null) return false;
        if (travelTime != null ? !travelTime.equals(schedule.travelTime) : schedule.travelTime != null) return false;
        if (jobId != null ? !jobId.equals(schedule.jobId) : schedule.jobId != null) return false;
        if (employeeId != null ? !employeeId.equals(schedule.employeeId) : schedule.employeeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = scheduleId != null ? scheduleId.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (travelTime != null ? travelTime.hashCode() : 0);
        result = 31 * result + (jobId != null ? jobId.hashCode() : 0);
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        return result;
    }
}
