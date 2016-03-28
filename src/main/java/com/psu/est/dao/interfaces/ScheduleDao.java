package com.psu.est.dao.interfaces;

import com.psu.est.dao.common.GenericDao;
import com.psu.est.dao.common.GenericDaoImpl;
import com.psu.est.model.Schedule;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by gorzelic on 3/19/2016.
 */
public interface ScheduleDao extends GenericDao<Schedule> {

    public List<Schedule> GetScheduleByIntervalAndEmployeeID(ZonedDateTime startTime, ZonedDateTime endTime, int employee_id);

    public Schedule GetScheduleByJobId(int job_id);

    public void RemoveScheduleByIntervalAndEmployeeID(ZonedDateTime startTime, ZonedDateTime endTime, int employee_id);

}
