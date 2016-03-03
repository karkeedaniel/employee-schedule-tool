package com.psu.est.dao.impl;

import com.psu.est.dao.common.GenericDaoImpl;
import com.psu.est.dao.interfaces.JobDao;
import com.psu.est.model.Job;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gorzelic on 2/28/2016.
 */
@Repository
@Transactional
public class JobDaoImpl extends GenericDaoImpl<Job> implements JobDao {

    public JobDaoImpl() {
        super(Job.class);
    }

}
