package com.psu.est.service;

import com.psu.est.dao.interfaces.JobDao;
import com.psu.est.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by danielkarkee on 4/17/16.
 */
@Service
public class JobService {

    @Autowired
    private JobDao jobDao;

    /**
     *
     * @param job
     */
    public void update(Job job) {
        jobDao.update(job);
    }
}
