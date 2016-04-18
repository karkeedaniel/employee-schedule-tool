package com.psu.est.dao.interfaces;

import com.psu.est.dao.common.GenericDao;
import com.psu.est.model.Job;
import com.psu.est.model.Schedule;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by gorzelic on 3/19/2016.
 */
public interface ScheduleDao extends GenericDao<Schedule> {

    List<Schedule> GetScheduleByIntervalAndEmployeeID(ZonedDateTime startTime, ZonedDateTime endTime, int employee_id);

    Schedule GetScheduleByJobId(int job_id);

    List<Job> RemoveScheduleByIntervalAndEmployeeID(ZonedDateTime startTime, ZonedDateTime endTime, int employee_id);

    void RemoveScheduleByIntervalEmployeeIDAndJobType(ZonedDateTime startTime, ZonedDateTime endTime, int employee_id, String jobType);

    /**
     *
     * @param employeeId
     * @param startTime
     * @return
     */
    List<Schedule> getByEmployeeIdAndStartTime(int employeeId, Timestamp startTime);
}
