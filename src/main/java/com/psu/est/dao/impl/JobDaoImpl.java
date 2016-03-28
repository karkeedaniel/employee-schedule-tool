package com.psu.est.dao.impl;

import com.psu.est.dao.common.GenericDaoImpl;
import com.psu.est.dao.interfaces.JobDao;
import com.psu.est.model.Job;
import com.psu.est.model.Schedule;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by gorzelic on 2/28/2016.
 */
@Repository
@Transactional
public class JobDaoImpl extends GenericDaoImpl<Job> implements JobDao {

    public JobDaoImpl() {
        super(Job.class);
    }


    @Override
    public List<Job> GetJobsByIntervalAndState(ZonedDateTime startTime, ZonedDateTime endTime, String state)
    {
        // convert java.time to java.sql.Timestamp, note that everything should be UTC in database, and changed
        // to LocalDateTime as required, i.e. should have timezone resolved for each location and persisted
        Timestamp start = new Timestamp(startTime.toInstant().toEpochMilli());
        Timestamp end = new Timestamp(endTime.toInstant().toEpochMilli());
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Job.class);
        //Criteria criteria1 = session.createCriteria(Job.class);
        //List<Job> test = criteria1.list();
        if (state != null){
            // using like until we move to enums for state
            String statelike = '%' + state + '%';
            criteria.add(Restrictions.ilike("jobState",statelike));
        }
        if (startTime !=null){
            criteria.add(Restrictions.ge("jobDate",start));
        }
        if (endTime !=null){
            criteria.add(Restrictions.le("jobDate",end));
        }
        criteria.addOrder(Order.asc("jobDate"));

        return criteria.list();
    }

}
