package com.psu.est.controller;

import com.psu.est.model.Job;
import com.psu.est.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by danielkarkee on 4/17/16.
 */
@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @RequestMapping(value = "/job/update", method = RequestMethod.PUT)
    public Job update(@RequestBody Job job) {
        jobService.update(job);
        return job;
    }
}
