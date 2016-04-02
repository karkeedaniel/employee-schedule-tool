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
import java.time.*;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import static org.junit.Assert.*;

/**
 * Created by gorzelic on 2/28/2016.
 */
public class JobDaoImplTest extends CommonTest {

    private static final Logger logger = LogManager.getLogger(EmployeeDaoImplTest.class);
    private int location_id1, location_id2;
    private java.sql.Date dateTest = new Date(Calendar.getInstance().getTimeInMillis());  // initializes to current
    private java.util.Date dateTest1 = new Date(Calendar.getInstance().getTimeInMillis());
    private Timestamp date = new Timestamp(Calendar.getInstance().getTimeInMillis());
    private Timestamp timestamp = new Timestamp(LocalDateTime.now().toInstant(ZoneOffset.of("-0400")).toEpochMilli());
    private Timestamp timestamp1 = new Timestamp(ZonedDateTime.now().toInstant().toEpochMilli());
    private Timestamp timestamp2 = new Timestamp(ZonedDateTime.of(2016,4,15,13,30,0,0,ZoneId.systemDefault()).toInstant().toEpochMilli());
    private ZoneId thisZoneId = ZoneId.systemDefault();  // or ZoneId.of("America/NewYork");
    private OffsetTime  thisOffsetTime = OffsetTime.now(thisZoneId);
    private ZoneOffset thisZoneOffset = thisOffsetTime.getOffset();
    private Timestamp timestamp3 = new Timestamp(LocalDateTime.of(2016,4,15,13,30).toInstant(thisZoneOffset).toEpochMilli());

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
            job.setJobBookDate(timestamp3);
            job.setJobDuration(60);
            job.setJobName("McDonalds");
            job.setJobPhone("610-555-1212");
            job.setJobState("new");
            job.setSiteContactName("John Smith");
            job.setSiteContactPhone("610-555-1234");
            Calendar calendar = Calendar.getInstance();
            calendar.set(2016,4,15, 16, 15);
            job.setJobDate(new Timestamp(calendar.getTimeInMillis()));
            //java.util.Date utilDate = new java.util.Date();
            //utilDate.setTime(calendar.getTimeInMillis());
            //job.setJobDate(new java.sql.Date(utilDate.getTime()));
            job.setJobLevel(1);
            jobDao.persist(job);
            Job otherJob = jobDao.get(job.getJobId());
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(otherJob.getJobBookDate().getTime()),thisZoneOffset);
            ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(otherJob.getJobBookDate().getTime()),thisZoneOffset);
            ZonedDateTime zonedDateTime1 = ZonedDateTime.ofInstant(Instant.ofEpochSecond(otherJob.getJobBookDate().getTime()/1000),thisZoneOffset);
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
            job.setJobDate(new Timestamp(calendar.getTimeInMillis()));
            jobDao.persist(job);
            calendar.set(2016,4,1);
            Timestamp newDate = new Timestamp(calendar.getTimeInMillis());
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
            job.setJobDate(new Timestamp(calendar.getTimeInMillis()));
            jobDao.saveOrUpdate(job);
            calendar.set(2016,4,1);
            Timestamp newDate = new Timestamp(calendar.getTimeInMillis());
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
            job.setJobDate(new Timestamp(calendar.getTimeInMillis()));
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
            job.setJobDate(new Timestamp(calendar.getTimeInMillis()));
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
            job.setJobDate(new Timestamp(calendar.getTimeInMillis()));
            jobDao.persist(job);
            List<Job> jobList1 = jobDao.getAll();
            assertTrue(jobList.size()+1 == jobList1.size());
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

}