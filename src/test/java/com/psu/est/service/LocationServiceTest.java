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
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by gorzelic on 2/7/2016.
 */
public class LocationServiceTest extends CommonTest {

    private static final Logger logger = LogManager.getLogger(LocationServiceTest.class);

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

    @Test
    public void testResolveAddress1() throws Exception {
        try {
            Location location = new Location();
            location.setStreetNumber("1103");
            location.setStreet("Raymond Avenue");
            location.setCity("Bethlehem");
            location.setState("PA");
            location.setZip("18018");
            locationService.resolveAddress(location);
            assertEquals(location.getLatitude(), 40.6293099, .001);
            assertEquals(location.getLongitude(), -75.410578, .001);
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testDistance1a() throws Exception {
        try{
            Location location1 = new Location();
            location1.setStreetNumber("1103");
            location1.setStreet("Raymond Avenue");
            location1.setCity("Bethlehem");
            location1.setState("PA");
            location1.setZip("18018");
            locationService.resolveAddress(location1);
            Location location2 = new Location();
            location2.setStreetNumber("1103");
            location2.setStreet("Raymond Avenue");
            location2.setCity("Bethlehem");
            location2.setState("PA");
            location2.setZip("18018");
            locationService.resolveAddress(location2);
            double distance = locationService.distance(location1,location2);
            assertEquals(0,distance,.0001);
        }catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testDistance1b() throws Exception {
        try{
            Location location1 = new Location();
            location1.setStreetNumber("1103");
            location1.setStreet("Raymond Avenue");
            location1.setCity("Bethlehem");
            location1.setState("PA");
            location1.setZip("18018");
            locationService.resolveAddress(location1);
            Location location2 = new Location();
            location2.setStreetNumber("442");
            location2.setStreet("Arkansas St");
            location2.setCity("San Francisco");
            location2.setState("CA");
            locationService.resolveAddress(location2);
            double distance = locationService.distance(location1,location2);
            assertEquals(2494.09976,distance,.0001);
        }catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testUpdateCurrent1() throws Exception {
        try{
            Location location = new Location();
            location.setStreetNumber("1103");
            location.setStreet("Raymond Avenue");
            location.setCity("Bethlehem");
            location.setState("PA");
            location.setZip("18018");
            locationService.resolveAddress(location);
            locationService.updateCurrent(location,38.8976094,-77.0367349);
            assertTrue("Washington".contentEquals(location.getCity()));
        }catch (Exception e) {
            fail("Exception: " + e);}
    }

    @Test
    public void testResolveAddress2() throws Exception {
        try {
            Location location = new Location();
            location.setStreetNumber("1600");
            location.setStreet("Pennsylvania Ave NW");
            location.setCity("Washington");
            location.setState("DC");
            location.setZip("20500");
            locationService.resolveAddress(location);
            assertEquals(location.getLatitude(), 38.8976094, .001);
            assertEquals(location.getLongitude(), -77.0367349, .001);
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testDistance1c() throws Exception {
        try{
            Location location1 = new Location();
            location1.setStreetNumber("1600");
            location1.setStreet("Pennsylvania Ave NW");
            location1.setCity("Washington");
            location1.setState("DC");
            location1.setZip("20500");
            locationService.resolveAddress(location1);
            Location location2 = new Location();
            location2.setStreetNumber("1600");
            location2.setStreet("Pennsylvania Ave NW");
            location2.setCity("Washington");
            location2.setState("DC");
            location2.setZip("20500");
            locationService.resolveAddress(location2);
            double distance = locationService.distance(location1,location2);
            assertEquals(0,distance,.0001);
        }catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testUpdateCurrent2() throws Exception {
        try{
            Location location = new Location();
            location.setStreetNumber("1600");
            location.setStreet("Pennsylvania Ave NW");
            location.setCity("Washington");
            location.setState("DC");
            location.setZip("20500");
            locationService.resolveAddress(location);
            locationService.updateCurrent(location,40.6293099,-75.410578);
            assertTrue("Bethlehem".contentEquals(location.getCity()));
            Location location1 = new Location();
            location1.setStreetNumber("1103");
            location1.setStreet("Raymond Avenue");
            location1.setCity("Bethlehem");
            location1.setState("PA");
            location1.setZip("18018");
            locationService.resolveAddress(location1);
            assertTrue(location1.equals(location));
        }catch (Exception e) {
            fail("Exception: " + e);}
    }

    @Test
    public void testGetBestRoute() throws Exception {
        try{
            Location location1 = new Location();
            location1.setStreetNumber("1103");
            location1.setStreet("Raymond Avenue");
            location1.setCity("Bethlehem");
            location1.setState("PA");
            location1.setZip("18018");
            locationService.resolveAddress(location1);
            Location location2 = new Location();
            location2.setStreetNumber("442");
            location2.setStreet("Arkansas St");
            location2.setCity("San Francisco");
            location2.setState("CA");
            locationService.resolveAddress(location2);
            Location location3 = new Location();
            location3.setStreetNumber("725");
            location3.setStreet("S. Atherton");
            location3.setCity("State College");
            location3.setState("PA");
            locationService.resolveAddress(location3);
            Location location4 = new Location();
            location4.setStreetNumber("3150");
            location4.setStreet("Avenue of the Stars");
            location4.setCity("Frisco");
            location4.setState("TX");
            locationService.resolveAddress(location4);
            locationDao.persist(location1);
            locationDao.persist(location2);
            locationDao.persist(location3);
            locationDao.persist(location4);
            List<Location> wayPoints = new ArrayList<>();
            wayPoints.add(location2);
            wayPoints.add(location3);
            wayPoints.add(location4);
            Map<Integer, LocationService.RouteMetric> travelMetrics = new HashMap<>();
            locationService.SolveTSP(location1,wayPoints,travelMetrics);

            double distance = locationService.distance(location1,location2);
            assertEquals(2494.09976,distance,.0001);
        }catch (Exception e) {
            fail("Exception: " + e);
        }
    }
}