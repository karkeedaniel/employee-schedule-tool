package com.psu.est.service;

import com.psu.est.dao.common.CommonTest;
import com.psu.est.dao.interfaces.LocationDao;
import com.psu.est.model.Location;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by gorzelic on 2/7/2016.
 */
public class LocationServiceTest extends CommonTest {

    @Autowired
    private LocationDao locationDao;
    @Autowired
    private LocationService locationService;

    @Test
    public void testDistance() throws Exception {
        try{
            Location location1 = new Location();
            location1.setStreet("1600 Pennsylvania Ave NW");
            location1.setCity("Washington");
            location1.setState("DC");
            location1.setZip(20500);
            Location location2 = new Location();
            location2.setStreet("1600 Pennsylvania Ave NW");
            location2.setCity("Washington");
            location2.setState("DC");
            location2.setZip(20500);
            float distance = locationService.distance(location1,location2);
            assertEquals(0,distance,.0001);
        }catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testUpdateCurrent() throws Exception {
        try{
            Location location = new Location();
            location.setStreet("1600 Pennsylvania Ave NW");
            location.setCity("Washington");
            location.setState("DC");
            location.setZip(20500);
            locationService.updateCurrent();
            assertFalse("Washington".contentEquals(location.getCity()));
        }catch (Exception e) {
            fail("Exception: " + e);}
    }

    @Test
    public void testToCoordinates() throws Exception {
        try{
            Location location = new Location();
            location.setStreet("1600 Pennsylvania Ave NW");
            location.setCity("Washington");
            location.setState("DC");
            location.setZip(20500);
            locationService.toCoordinates();
            assertEquals(location.getLatitude(),38.8987195, .001 );
            assertEquals(location.getLongitude(),-77.0374031, .001 );
        }catch (Exception e) {
            fail("Exception: " + e);}
    }
}