package com.psu.est.view;

import com.psu.est.model.Job;
import com.psu.est.model.Location;
import com.psu.est.model.Schedule;

import java.io.Serializable;

/**
 * Created by danielkarkee on 4/13/16.
 */
public class JobSchedule implements Serializable {

    private Job job;
    private Schedule schedule;
    private Location location;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
