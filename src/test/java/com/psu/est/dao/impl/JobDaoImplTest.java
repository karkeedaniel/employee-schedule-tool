package com.psu.est.dao.impl;

import com.psu.est.dao.common.CommonTest;
import com.psu.est.dao.interfaces.JobDao;
import com.psu.est.dao.interfaces.LocationDao;
import com.psu.est.model.Job;
import com.psu.est.model.Location;
import com.psu.est.service.LocationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by gorzelic on 2/28/2016.
 */
public class JobDaoImplTest extends CommonTest {

    private static final Logger logger = LogManager.getLogger(EmployeeDaoImplTest.class);
    private int location_id1, location_id2;
    private Date date = new Date(Calendar.getInstance().getTimeInMillis());  // initializes to current


    @Autowired
    private JobDao jobDao;
    @Autowired
    private LocationService locationService;
    @Autowired
    private LocationDao locationDao;


    //@Autowired
    //private SessionFactory sessionFactory;

    @Before
    public void setUp() throws Exception {
        Location location1 = new Location();
        location1.setStreetNumber("1103");
        location1.setStreet("Raymond Avenue");
        location1.setCity("Bethlehem");
        location1.setState("PA");
        location1.setZip("18018");
        locationService.resolveAddress(location1);
        locationDao.saveOrUpdate(location1);
        location_id1 = location1.getLocationId();

        Location location2 = new Location();
        location2.setStreetNumber("442");
        location2.setStreet("Arkansas St");
        location2.setCity("San Francisco");
        location2.setState("CA");
        locationService.resolveAddress(location2);
        locationDao.saveOrUpdate(location2);
        location_id2 = location2.getLocationId();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testJobDaoPersist() throws Exception {
        try {
            Job job = new Job();
            job.setJobLocation(location_id1);
            job.setJobBookDate(date);
            job.setJobDuration(60);
            job.setJobName("McDonalds");
            job.setJobPhone("610-555-1212");
            job.setJobState("new");
            job.setSiteContactName("John Smith");
            job.setSiteContactPhone("610-555-1234");
            Calendar calendar = Calendar.getInstance();
            calendar.set(2016,4,15);
            job.setJobDate(new Date(calendar.getTimeInMillis()));
            //java.util.Date utilDate = new java.util.Date();
            //utilDate.setTime(calendar.getTimeInMillis());
            //job.setJobDate(new java.sql.Date(utilDate.getTime()));
            job.setJobLevel(1);
            jobDao.persist(job);
            assertNotEquals(0,(long)job.getJobId());
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testJobDaoUpdate() throws Exception {
        try {
            Job job = new Job();
            job.setJobLocation(location_id1);
            job.setJobBookDate(date);
            job.setJobDuration(60);
            job.setJobName("McDonalds");
            job.setJobPhone("610-555-1212");
            job.setJobState("new");
            job.setSiteContactName("John Smith");
            job.setSiteContactPhone("610-555-1234");
            job.setJobLevel(1);
            Calendar calendar = Calendar.getInstance();
            calendar.set(2016,4,15);
            job.setJobDate(new Date(calendar.getTimeInMillis()));
            jobDao.persist(job);
            calendar.set(2016,4,1);
            Date newDate = new Date(calendar.getTimeInMillis());
            job.setJobDate(newDate);
            jobDao.update(job);
            job = jobDao.get(job.getJobId());
            assertEquals(job.getJobDate(),newDate );
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testJobDaoSaveOrUpdate() throws Exception {
        try {
            Job job = new Job();
            job.setJobLocation(location_id1);
            job.setJobBookDate(date);
            job.setJobDuration(60);
            job.setJobName("McDonalds");
            job.setJobPhone("610-555-1212");
            job.setJobState("new");
            job.setSiteContactName("John Smith");
            job.setSiteContactPhone("610-555-1234");
            job.setJobLevel(1);
            Calendar calendar = Calendar.getInstance();
            calendar.set(2016,4,15);
            job.setJobDate(new Date(calendar.getTimeInMillis()));
            jobDao.saveOrUpdate(job);
            calendar.set(2016,4,1);
            Date newDate = new Date(calendar.getTimeInMillis());
            job.setJobDate(newDate);
            jobDao.saveOrUpdate(job);
            job = jobDao.get(job.getJobId());
            assertEquals(job.getJobDate(),newDate );
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testJobDaoDelete() throws Exception {
        try {
            Job job = new Job();
            job.setJobLocation(location_id1);
            job.setJobBookDate(date);
            job.setJobDuration(60);
            job.setJobName("McDonalds");
            job.setJobPhone("610-555-1212");
            job.setJobState("new");
            job.setSiteContactName("John Smith");
            job.setSiteContactPhone("610-555-1234");
            job.setJobLevel(1);
            Calendar calendar = Calendar.getInstance();
            calendar.set(2016,4,15);
            job.setJobDate(new Date(calendar.getTimeInMillis()));
            jobDao.saveOrUpdate(job);
            job = jobDao.get(job.getJobId());
            int localJobId = job.getJobId();
            jobDao.delete(job);
            job = jobDao.get(localJobId);
            assertNull(job );
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testJobDaoGet() throws Exception {
        try {
            Job job = new Job();
            job.setJobLocation(location_id1);
            job.setJobBookDate(date);
            job.setJobDuration(60);
            job.setJobName("McDonalds");
            job.setJobPhone("610-555-1212");
            job.setJobState("new");
            job.setSiteContactName("John Smith");
            job.setSiteContactPhone("610-555-1234");
            job.setJobLevel(1);
            Calendar calendar = Calendar.getInstance();
            calendar.set(2016,4,15);
            job.setJobDate(new Date(calendar.getTimeInMillis()));
            jobDao.persist(job);
            int OtherJobId = job.getJobId();
            Job otherJob = jobDao.get(OtherJobId);
            assertEquals(otherJob,job);
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testJobDaoGetAll() throws Exception {
        try{
            List<Job> jobList = jobDao.getAll();
            assertNotNull(jobList);
            Job job = new Job();
            job.setJobLocation(location_id1);
            job.setJobBookDate(date);
            job.setJobDuration(60);
            job.setJobName("McDonalds");
            job.setJobPhone("610-555-1212");
            job.setJobState("new");
            job.setSiteContactName("John Smith");
            job.setSiteContactPhone("610-555-1234");
            job.setJobLevel(1);
            Calendar calendar = Calendar.getInstance();
            calendar.set(2016,4,15);
            job.setJobDate(new Date(calendar.getTimeInMillis()));
            jobDao.persist(job);
            List<Job> jobList1 = jobDao.getAll();
            assertTrue(jobList.size()+1 == jobList1.size());
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

}