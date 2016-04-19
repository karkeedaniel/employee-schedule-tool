package com.psu.est.service;

import com.psu.est.dao.interfaces.JobDao;
import com.psu.est.dao.interfaces.LocationDao;
import com.psu.est.model.Job;
import com.psu.est.model.Location;
import com.psu.est.service.exception.InvalidAddressException;
import com.psu.est.view.JobWithLocation;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Service
public class JobService {

    private static final Logger logger = LogManager.getLogger(JobService.class);

    @Autowired
    private JobDao jobDao;

    @Autowired
    private LocationDao locationDao;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ScheduleService scheduleService;

    /**
     *
     * @return
     */
    public List<Job> getAll() {
        return jobDao.getAll();
    }

    /**
     *
     * @param role
     * @return
     */
	 /*
    public List<Employee> getByNotLikeRole(String role) {
        List<Employee> employeeList = null;
        if ("DIRECTOR".equals(role)) {
            employeeList = employeeDao.getByNotLikeRole(Collections.singletonList(role));
        } else if ("MANAGER".equals(role)) {
            employeeList = employeeDao.getByNotLikeRole(Arrays.asList("DIRECTOR", role));
        }
        return employeeList;
    }
	*/
    /**
     *
     * @param job
     */
    public void update(Job job) {
        jobDao.update(job);
    }

    @Transactional
    public void persist(JobWithLocation jobWithLocation) throws InvalidAddressException {
        Job  job = jobWithLocation.getJob();
        Location location = jobWithLocation.getLocation();
        try {
            locationService.resolveAddress(location);
        } catch (Exception e) {
            throw new InvalidAddressException();
        }
        locationDao.persist(location);
        // setup job fields that aren't on the form or may need tweaking
        // no reason to have job creation date on the form
        Timestamp bookDate = new Timestamp(ZonedDateTime.now().toInstant().toEpochMilli());
        job.setJobBookDate(bookDate);
        // always need a location reference
        job.setJobLocation(location.getLocationId());
        // a new job is always in the UNASSIGNED state, no need to set on form
        job.setJobState("UNASSIGNED");
        // this may not be necessary, but the jobDate (day we want it performed
        // should be set to 12 Midnight on that day, and can't be sure what is
        // coming to the form, so call to "truncate" the time to midnight
        job.setJobDate(scheduleService.TruncateToDate(job.getJobDate()));
        jobDao.persist(job);
        // autoschedule this job - these 3 lines schedule all "UNASSIGNED" jobs
        // between midnight on the job preferred date, and 24 hrs later
        ZonedDateTime startTime = scheduleService.GetZonedDateTime(job.getJobDate());
        ZonedDateTime endTime = startTime.plusHours(24);
        scheduleService.ScheduleUnAssignedJobs(startTime, endTime);
    }
}
