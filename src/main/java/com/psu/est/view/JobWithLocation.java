package com.psu.est.view;

import com.psu.est.model.Job;
import com.psu.est.model.Location;

/**
 * Created by gorzelic on 4/17/2016.
 */
public class JobWithLocation {

    private Job job;
    private Location location;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
