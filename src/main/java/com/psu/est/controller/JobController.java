package com.psu.est.controller;

import com.psu.est.model.Job;
import com.psu.est.service.JobService;
import com.psu.est.view.JobWithLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @RequestMapping(value = "/job/get-all", method = RequestMethod.GET)
    public List<Job> getAll() {
        return jobService.getAll();
    }

    @RequestMapping(value = "/job/update", method = RequestMethod.PUT)
    public Job update(@RequestBody Job job) {
        jobService.update(job);
        return job;
    }

    @RequestMapping(value = "/job-location/persist", method = RequestMethod.POST)
    public Job persist(@RequestBody JobWithLocation jobWithLocation) {
        jobService.persist(jobWithLocation);
        return jobWithLocation.getJob();
    }
}
