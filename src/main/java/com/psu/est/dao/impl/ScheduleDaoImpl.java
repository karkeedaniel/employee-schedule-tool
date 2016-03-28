package com.psu.est.dao.impl;

import com.psu.est.dao.common.GenericDaoImpl;
import com.psu.est.dao.interfaces.JobDao;
import com.psu.est.dao.interfaces.ScheduleDao;
import com.psu.est.model.Job;
import com.psu.est.model.Schedule;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by gorzelic on 3/19/2016.
 */

@Repository
@Transactional
public class ScheduleDaoImpl extends GenericDaoImpl<Schedule> implements ScheduleDao {

   // @Autowired
    //private SessionFactory sessionFactory; // this exits in parent

    /* for updating jobs affected by schedule changes */
    @Autowired
    private JobDao jobDao;

    public ScheduleDaoImpl(){
        super(Schedule.class);
    }

    @Override
    public List<Schedule> GetScheduleByIntervalAndEmployeeID(ZonedDateTime startTime, ZonedDateTime endTime, int employee_id)
    {
        // convert java.time to java.sql.Timestamp, note that everything should be UTC in database, and changed
        // to LocalDateTime as required, i.e. should have timezone resolved for each location and persisted
        Timestamp start = new Timestamp(startTime.toInstant().toEpochMilli());
        Timestamp end = new Timestamp(endTime.toInstant().toEpochMilli());
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Schedule.class);
        if (employee_id>=1){
            criteria.add(Restrictions.eq("employeeId",employee_id));
        }
        if (startTime !=null){
            criteria.add(Restrictions.ge("startTime",start));
        }
        if (endTime !=null){
            criteria.add(Restrictions.le("endTime",end));
        }
        criteria.addOrder(Order.asc("startTime"));

        return criteria.list();
    }

    @Override
    public Schedule GetScheduleByJobId(int job_id){
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Schedule.class);
        if (job_id <=0)
        {
            // tbd log an error
            return null;
        }
        criteria.add(Restrictions.eq("job_id",job_id));
        List<Schedule> result = criteria.list();
        if (result.isEmpty())
            return null;
        else if(result.size()>1)
        {   // tbd log an error, but don't fail, remove all but first element
            for (int i=0; i<result.size(); i++)
            {
                if (i>0)
                {
                    this.delete(result.get(i));
                }
            }
        }
        return result.get(0);
    }

    @Override
    public void RemoveScheduleByIntervalAndEmployeeID(ZonedDateTime startTime, ZonedDateTime endTime, int employee_id)
    {
        // convert java.time to java.sql.Timestamp, note that everything should be UTC in database, and changed
        // to LocalDateTime as required, i.e. should have timezone resolved for each location and persisted
        Timestamp start = new Timestamp(startTime.toInstant().toEpochMilli());
        Timestamp end = new Timestamp(endTime.toInstant().toEpochMilli());
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Schedule.class);
        if (employee_id>=1){
            criteria.add(Restrictions.eq("employee_id",employee_id));
        }
        if (startTime !=null){
            criteria.add(Restrictions.ge("start_time",start));
        }
        if (endTime !=null){
            criteria.add(Restrictions.le("end_time",end));
        }
        List<Schedule> results = criteria.list();
        // remove these schedule assignments
        for (Schedule item : results)
        {
            int job_id = (item.getJobId()>0 && item.getType().contentEquals("JOB"))? item.getJobId() : 0;
            this.delete(item);
            // if this was a job (not PTO) go change job state to unassigned
            if (job_id != 0)
            {
                Job job = jobDao.get(job_id);
                if (job != null)
                {
                    job.setJobState("UNASSIGNED");
                    jobDao.update(job);
                }
            }
        }
    }

}
