package com.psu.est.service;

import com.psu.est.dao.common.CommonTest;
import com.psu.est.dao.interfaces.EmployeeAccountDao;
import com.psu.est.dao.interfaces.EmployeeDao;
import com.psu.est.dao.interfaces.JobDao;
import com.psu.est.dao.interfaces.LocationDao;
import com.psu.est.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by gorzelic on 3/30/2016.
 */
public class ScheduleServiceTest extends CommonTest {

    private static final Logger logger = LogManager.getLogger(ScheduleServiceTest.class);

    //@Autowired
    //private LocationDao locationDao;
    @Autowired
    private LocationService locationService;
    @Autowired
    private LocationDao locationDao;
    @Autowired
    private EmployeeAccountDao employeeAccountDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private JobDao jobDao;
    @Autowired
    private ScheduleService scheduleService;

    @Before
    public void setUp() throws Exception{}


    private void SetUp4Employees() throws Exception {
        // setup 3 employees and accounts
        Location location1 = new Location();
        location1.setStreetNumber("725");
        location1.setStreet("S. Atherton");
        location1.setCity("State College");
        location1.setState("PA");
        locationService.resolveAddress(location1);
        locationDao.persist(location1);
        Employee employee1 = new Employee();
        employee1.setBaseLocationId(location1.getLocationId());
        employee1.setFirstName("Dave");
        employee1.setLastName("Gorzelic");
        employee1.setContact("6105551212");
        employee1.setEmail("djg@est.com");
        employee1.setGender("Male");
        employee1.setCreatedBy("srg");
        employee1.setDateCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        employee1.setDob(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        employeeDao.persist(employee1);
        EmployeeAccount employeeAccount1 = new EmployeeAccount();
        employeeAccount1.setUsername("djg");
        employeeAccount1.setPassword("password");
        employeeAccount1.setRole("TECHNICIAN");
        employeeAccount1.setEmployeeId(employee1.getEmployeeId());
        employeeAccount1.setCreatedBy("srg");
        employeeAccount1.setDateCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        employeeAccountDao.persist(employeeAccount1);
        Location location2 = new Location();
        location2.setStreetNumber("442");
        location2.setStreet("Arkansas St.");
        location2.setCity("San Francisco");
        location2.setState("CA");
        locationService.resolveAddress(location2);
        locationDao.persist(location2);
        Employee employee2 = new Employee();
        employee2.setBaseLocationId(location2.getLocationId());
        employee2.setFirstName("Pat");
        employee2.setLastName("Gorzelic");
        employee2.setContact("6105551313");
        employee2.setEmail("phg@est.com");
        employee2.setGender("Male");
        employee2.setCreatedBy("srg");
        employee2.setDateCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        employee2.setDob(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        employeeDao.persist(employee2);
        EmployeeAccount employeeAccount2 = new EmployeeAccount();
        employeeAccount2.setUsername("phg");
        employeeAccount2.setPassword("password");
        employeeAccount2.setRole("TECHNICIAN");
        employeeAccount2.setEmployeeId(employee2.getEmployeeId());
        employeeAccount2.setCreatedBy("srg");
        employeeAccount2.setDateCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        employeeAccountDao.persist(employeeAccount2);
        Location location3 = new Location();
        location3.setStreetNumber("3150");
        location3.setStreet("Avenue of the Stars.");
        location3.setCity("Frisco");
        location3.setState("TX");
        locationService.resolveAddress(location3);
        locationDao.persist(location3);
        Employee employee3 = new Employee();
        employee3.setBaseLocationId(location3.getLocationId());
        employee3.setFirstName("Jackie");
        employee3.setLastName("Gorzelic");
        employee3.setContact("6105551414");
        employee3.setEmail("jag@est.com");
        employee3.setGender("Female");
        employee3.setCreatedBy("srg");
        employee3.setDateCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        employee3.setDob(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        employeeDao.persist(employee3);
        EmployeeAccount employeeAccount3 = new EmployeeAccount();
        employeeAccount3.setUsername("jag");
        employeeAccount3.setPassword("password");
        employeeAccount3.setRole("TECHNICIAN");
        employeeAccount3.setEmployeeId(employee3.getEmployeeId());
        employeeAccount3.setCreatedBy("srg");
        employeeAccount3.setDateCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        employeeAccountDao.persist(employeeAccount3);
        Location location4 = new Location();
        location4.setStreetNumber("2501");
        location4.setStreet("Coit Rd");
        location4.setCity("Plano");
        location4.setState("TX");
        locationService.resolveAddress(location4);
        locationDao.persist(location4);
        Employee employee4 = new Employee();
        employee4.setBaseLocationId(location4.getLocationId());
        employee4.setFirstName("Jackie");
        employee4.setLastName("Neighbor");
        employee4.setContact("6105551515");
        employee4.setEmail("jng@est.com");
        employee4.setGender("Female");
        employee4.setCreatedBy("srg");
        employee4.setDateCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        employee4.setDob(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        employeeDao.persist(employee4);
        EmployeeAccount employeeAccount4 = new EmployeeAccount();
        employeeAccount4.setUsername("jng");
        employeeAccount4.setPassword("password");
        employeeAccount4.setRole("TECHNICIAN");
        employeeAccount4.setEmployeeId(employee4.getEmployeeId());
        employeeAccount4.setCreatedBy("srg");
        employeeAccount4.setDateCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        employeeAccountDao.persist(employeeAccount4);
    }

    private void SetUpJobsInSanFran(Timestamp jobDate) throws Exception {
        Timestamp bookDate = new Timestamp(ZonedDateTime.now().toInstant().toEpochMilli());
        Location location1 = new Location();
        location1.setStreetNumber("5454");
        location1.setStreet("Mission St");
        location1.setCity("San Francisco");
        location1.setState("CA");
        locationService.resolveAddress(location1);
        locationDao.persist(location1);
        Job job1 = new Job();
        job1.setJobLocation(location1.getLocationId());
        job1.setJobBookDate(bookDate);
        job1.setJobDuration(120);
        job1.setJobName("McDonalds");
        job1.setJobPhone("610-555-1212");
        job1.setJobState("UNASSIGNED");
        job1.setSiteContactName("John Smith");
        job1.setSiteContactPhone("610-555-1234");
        Calendar calendar = Calendar.getInstance();
        job1.setJobDate(jobDate);
        jobDao.persist(job1);
        Location location2 = new Location();
        location2.setStreetNumber("125");
        location2.setStreet("Monterey Rd");
        location2.setCity("Pacifica");
        location2.setState("CA");
        locationService.resolveAddress(location2);
        locationDao.persist(location2);
        Job job2 = new Job();
        job2.setJobLocation(location2.getLocationId());
        job2.setJobBookDate(bookDate);
        job2.setJobDuration(120);
        job2.setJobName("McDonalds");
        job2.setJobPhone("610-555-1212");
        job2.setJobState("UNASSIGNED");
        job2.setSiteContactName("John Smith");
        job2.setSiteContactPhone("610-555-1234");
        job2.setJobDate(jobDate);
        jobDao.persist(job2);
        Location location3 = new Location();
        location3.setStreetNumber("715");
        location3.setStreet("Central Ave");
        location3.setCity("Alameda");
        location3.setState("CA");
        locationService.resolveAddress(location3);
        locationDao.persist(location3);
        Job job3 = new Job();
        job3.setJobLocation(location3.getLocationId());
        job3.setJobBookDate(bookDate);
        job3.setJobDuration(120);
        job3.setJobName("McDonalds");
        job3.setJobPhone("610-555-1212");
        job3.setJobState("UNASSIGNED");
        job3.setSiteContactName("John Smith");
        job3.setSiteContactPhone("610-555-1234");
        job3.setJobDate(jobDate);
        jobDao.persist(job3);
    }

    private void SetUpJobsInFrisco(Timestamp jobDate) throws Exception {
        Timestamp bookDate = new Timestamp(ZonedDateTime.now().toInstant().toEpochMilli());
        Location location1 = new Location();
        location1.setStreetNumber("8970");
        location1.setStreet("Preston Rd");
        location1.setCity("Frisco");
        location1.setState("TX");
        locationService.resolveAddress(location1);
        locationDao.persist(location1);
        Job job1 = new Job();
        job1.setJobLocation(location1.getLocationId());
        job1.setJobBookDate(bookDate);
        job1.setJobDuration(120);
        job1.setJobName("McDonalds");
        job1.setJobPhone("610-555-1212");
        job1.setJobState("UNASSIGNED");
        job1.setSiteContactName("John Smith");
        job1.setSiteContactPhone("610-555-1234");
        Calendar calendar = Calendar.getInstance();
        job1.setJobDate(jobDate);
        jobDao.persist(job1);
        Location location2 = new Location();
        location2.setStreetNumber("4915");
        location2.setStreet("Main St");
        location2.setCity("The Colony");
        location2.setState("TX");
        locationService.resolveAddress(location2);
        locationDao.persist(location2);
        Job job2 = new Job();
        job2.setJobLocation(location2.getLocationId());
        job2.setJobBookDate(bookDate);
        job2.setJobDuration(120);
        job2.setJobName("McDonalds");
        job2.setJobPhone("610-555-1212");
        job2.setJobState("UNASSIGNED");
        job2.setSiteContactName("John Smith");
        job2.setSiteContactPhone("610-555-1234");
        job2.setJobDate(jobDate);
        jobDao.persist(job2);
        Location location3 = new Location();
        location3.setStreetNumber("6001");
        location3.setStreet("W Parker Rd");
        location3.setCity("Plano");
        location3.setState("TX");
        locationService.resolveAddress(location3);
        locationDao.persist(location3);
        Job job3 = new Job();
        job3.setJobLocation(location3.getLocationId());
        job3.setJobBookDate(bookDate);
        job3.setJobDuration(120);
        job3.setJobName("McDonalds");
        job3.setJobPhone("610-555-1212");
        job3.setJobState("UNASSIGNED");
        job3.setSiteContactName("John Smith");
        job3.setSiteContactPhone("610-555-1234");
        job3.setJobDate(jobDate);
        jobDao.persist(job3);
    }

    private void SetUpJobsInStateCollege(Timestamp jobDate) throws Exception {
        Timestamp bookDate = new Timestamp(ZonedDateTime.now().toInstant().toEpochMilli());
        Location location1 = new Location();
        location1.setStreetNumber("442");
        location1.setStreet("E College Ave");
        location1.setCity("State College");
        location1.setState("PA");
        locationService.resolveAddress(location1);
        locationDao.persist(location1);
        Job job1 = new Job();
        job1.setJobLocation(location1.getLocationId());
        job1.setJobBookDate(bookDate);
        job1.setJobDuration(120);
        job1.setJobName("McDonalds");
        job1.setJobPhone("610-555-1212");
        job1.setJobState("UNASSIGNED");
        job1.setSiteContactName("John Smith");
        job1.setSiteContactPhone("610-555-1234");
        Calendar calendar = Calendar.getInstance();
        job1.setJobDate(jobDate);
        jobDao.persist(job1);
        Location location2 = new Location();
        location2.setStreetNumber("2167");
        location2.setStreet("S Atherton St");
        location2.setCity("State College");
        location2.setState("PA");
        locationService.resolveAddress(location2);
        locationDao.persist(location2);
        Job job2 = new Job();
        job2.setJobLocation(location2.getLocationId());
        job2.setJobBookDate(bookDate);
        job2.setJobDuration(120);
        job2.setJobName("McDonalds");
        job2.setJobPhone("610-555-1212");
        job2.setJobState("UNASSIGNED");
        job2.setSiteContactName("John Smith");
        job2.setSiteContactPhone("610-555-1234");
        job2.setJobDate(jobDate);
        jobDao.persist(job2);
        Location location3 = new Location();
        location3.setStreetNumber("2821");
        location3.setStreet("E College Ave");
        location3.setCity("State College");
        location3.setState("PA");
        locationService.resolveAddress(location3);
        locationDao.persist(location3);
        Job job3 = new Job();
        job3.setJobLocation(location3.getLocationId());
        job3.setJobBookDate(bookDate);
        job3.setJobDuration(120);
        job3.setJobName("McDonalds");
        job3.setJobPhone("610-555-1212");
        job3.setJobState("UNASSIGNED");
        job3.setSiteContactName("John Smith");
        job3.setSiteContactPhone("610-555-1234");
        job3.setJobDate(jobDate);
        jobDao.persist(job3);
    }

    @Test
    public void testScheduleUnAssignedJobs()
    {
        try {
                SetUp4Employees();
                LocalDate jobDate = LocalDate.of(2016,4,15);
                Timestamp jobTimestampFromLocalDate = scheduleService.GetTimestamp(jobDate);
                SetUpJobsInFrisco(scheduleService.TruncateToDate(jobTimestampFromLocalDate));
                SetUpJobsInSanFran(jobTimestampFromLocalDate);
                SetUpJobsInStateCollege(jobTimestampFromLocalDate);
                jobDate = LocalDate.of(2016,4,15);
                ZonedDateTime startTime = scheduleService.GetZonedDateTime(scheduleService.GetTimestamp(jobDate));
                ZonedDateTime endTime = startTime.plusHours(24);
                List<Job> unassignedJobs = scheduleService.ScheduleUnAssignedJobs(startTime,  endTime);
                assertTrue(unassignedJobs.isEmpty());
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testScheduleUnAssignedJobs1()
    {
        try
        {
            SetUp4Employees();
            LocalDate jobDate = LocalDate.of(2016,4,15);
            Timestamp jobTimestampFromLocalDate = scheduleService.GetTimestamp(jobDate);
            SetUpJobsInFrisco(scheduleService.TruncateToDate(jobTimestampFromLocalDate));
            LocalDateTime jobDateTime = LocalDateTime.of(2016,4,15,0,0);
            Timestamp jobTimestampFromLocalDateTime = scheduleService.GetTimestamp(jobDateTime);
            SetUpJobsInSanFran(jobTimestampFromLocalDateTime);
            LocalDateTime jobDateTime1130 = LocalDateTime.of(2016,4,15,11,30);
            Timestamp jobTimestampFromLocalDateTime1130 = scheduleService.GetTimestamp(jobDateTime1130);
            Timestamp jobTimestampFromLocalDateTime1130trunc = scheduleService.TruncateToDate(jobTimestampFromLocalDateTime1130);
            SetUpJobsInStateCollege(jobTimestampFromLocalDateTime1130trunc);
            jobDate = LocalDate.of(2016,4,15);
            ZonedDateTime startTime = scheduleService.GetZonedDateTime(scheduleService.GetTimestamp(jobDate));
            ZonedDateTime endTime = startTime.plusHours(24);
            List<Job> unassignedJobs = scheduleService.ScheduleUnAssignedJobs(startTime,  endTime);
            assertTrue(unassignedJobs.isEmpty());
            } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testGetScheduleByDateAndEmployeeID() throws Exception
    {
        LocalDate jobDate = LocalDate.of(2016,4,15);
        ZonedDateTime startTime = scheduleService.GetZonedDateTime(scheduleService.GetTimestamp(jobDate));
        ZonedDateTime endTime = startTime.plusHours(24);
        List<Employee> employees = employeeDao.getListByRole("Technician");
        for (Employee employee : employees)
        {
            System.out.println("\n****************************************************************");
            System.out.println(employee.getFirstName()+" "+ employee.getLastName()+" for "+jobDate.toString());
            List<Schedule> employeeAssignments = scheduleService.GetScheduleByDateAndEmployeeID(jobDate,employee.getEmployeeId());
            for(Schedule assignment : employeeAssignments)
            {
                System.out.println("****************");
                String type = assignment.getType();
                if (type.equals("JOB"))
                {
                    Job job = jobDao.get(assignment.getJobId());
                    Location jobLocation = locationDao.get(job.getJobLocation());
                    System.out.println(assignment.getType()+" "+job.getJobName());
                    System.out.println(jobLocation.getFormattedAddress());
                    System.out.println("Estimated Travel Time (minutes): "+assignment.getTravelTime()+" Scheduled Start: "+
                            scheduleService.GetLocalTime(assignment.getStartTime()));
                    System.out.println("Scheduled Duration (minutes): "+ assignment.getDuration()+" Scheduled Finish: "+
                            scheduleService.GetLocalTime(assignment.getEndTime()));
                }
                else
                {
                    System.out.println(assignment.getType()+" Start: "+scheduleService.GetLocalTime(assignment.getStartTime()));
                    System.out.println("Scheduled Duration (minutes): "+ assignment.getDuration()+" Scheduled Finish: "+
                            scheduleService.GetLocalTime(assignment.getEndTime()));
                }

            }
        }


    }
}