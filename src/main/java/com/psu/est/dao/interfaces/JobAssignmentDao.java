package com.psu.est.dao.interfaces;

import com.psu.est.dao.common.GenericDao;
import com.psu.est.model.JobAssignment;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by Nana on 3/6/2016.
 */
public interface JobAssignmentDao extends GenericDao<JobAssignment>{

    public List<JobAssignment> getAllAssignedJobs(long jobId) throws DataAccessException;
}
