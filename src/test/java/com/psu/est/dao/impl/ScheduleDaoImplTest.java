package com.psu.est.dao.impl;

import com.psu.est.dao.common.CommonTest;
import com.psu.est.dao.interfaces.EmployeeDao;
import com.psu.est.dao.interfaces.JobDao;
import com.psu.est.dao.interfaces.ScheduleDao;
import com.psu.est.model.Employee;
import com.psu.est.model.Job;
import com.psu.est.model.Schedule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by danielkarkee on 4/15/16.
 */
public class ScheduleDaoImplTest extends CommonTest {

    private static final Logger logger = LogManager.getLogger(ScheduleDaoImplTest.class);

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private JobDao jobDao;

    @Autowired
    private ScheduleDao scheduleDao;

    @Test
    public void testPersist() throws Exception {
        try {
            Employee employee = employeeDao.get(20);
            assertNotNull(employee);
            Job job = jobDao.get(41);
            assertNotNull(job);
            Schedule schedule = new Schedule();
            schedule.setEmployeeId(employee.getEmployeeId());
            schedule.setJobId(job.getJobId());
            schedule.setType("JOB");
            Calendar calendar = Calendar.getInstance();
            calendar.set(2016, Calendar.APRIL, 15, 12, 0, 0);
            schedule.setStartTime(new Timestamp(calendar.getTimeInMillis()));
            calendar.set(2016, Calendar.APRIL, 15, 13, 0, 0);
            schedule.setEndTime(new Timestamp(calendar.getTimeInMillis()));
            schedule.setDuration(60);
            schedule.setTravelTime(30);
            scheduleDao.persist(schedule);
            assertNotNull(schedule);
            assertTrue(schedule.getScheduleId() != 0);
            logger.info(schedule.toString());
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testGetScheduleByIntervalAndEmployeeID() throws Exception {

    }

    @Test
    public void testGetScheduleByJobId() throws Exception {

    }

    @Test
    public void testRemoveScheduleByIntervalAndEmployeeID() throws Exception {

    }

    @Test
    public void testRemoveScheduleByIntervalEmployeeIDAndJobType() throws Exception {

    }

    @Test
    public void testGetByEmployeeIdAndStartTime() throws Exception {
        try {
            Employee employee = employeeDao.get(20);
            assertNotNull(employee);
            Calendar calendar = Calendar.getInstance();
            calendar.set(2016, Calendar.APRIL, 15);
            Timestamp startDate = new Timestamp(calendar.getTimeInMillis());
            List<Schedule> scheduleList = scheduleDao.getByEmployeeIdAndStartTime(employee.getEmployeeId(), startDate);
            assertNotNull(scheduleList);
            logger.info("Size: " + scheduleList.size());
            for (Schedule schedule : scheduleList) {
                logger.info(schedule.toString());
            }
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }
}