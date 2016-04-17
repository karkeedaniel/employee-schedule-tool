package com.psu.est.service;

import com.psu.est.dao.interfaces.JobDao;
import com.psu.est.dao.interfaces.LocationDao;
import com.psu.est.dao.interfaces.ScheduleDao;
import com.psu.est.model.Job;
import com.psu.est.model.Schedule;
import com.psu.est.view.JobSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielkarkee on 4/13/16.
 */
@Service
public class JobScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private JobDao jobDao;

    @Autowired
    private LocationDao locationDao;

    @Transactional
    public List<JobSchedule> getListByEmployeeIdAndStartTime(int employeeId, Timestamp startTime) {
        List<JobSchedule> jobScheduleList = new ArrayList<>();
        List<Schedule> scheduleList = scheduleDao.getByEmployeeIdAndStartTime(employeeId, startTime);
        for (Schedule schedule : scheduleList) {
            JobSchedule jobSchedule = new JobSchedule();
            jobSchedule.setSchedule(schedule);
            Job job = jobDao.get(schedule.getJobId());
            jobSchedule.setJob(job);
            jobSchedule.setLocation(locationDao.get(job.getJobLocation()));
            jobScheduleList.add(jobSchedule);
        }
        return jobScheduleList;
    }
}
