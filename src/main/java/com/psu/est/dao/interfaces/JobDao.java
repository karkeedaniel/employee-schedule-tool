package com.psu.est.dao.interfaces;

import com.psu.est.dao.common.GenericDao;
import com.psu.est.model.Job;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by gorzelic on 2/28/2016.
 */
public interface JobDao extends GenericDao<Job>{

    public List<Job> GetJobsByIntervalAndState(ZonedDateTime startTime, ZonedDateTime endTime, String state);

}
