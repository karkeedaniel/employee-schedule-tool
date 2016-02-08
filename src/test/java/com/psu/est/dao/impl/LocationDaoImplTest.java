package com.psu.est.dao.impl;
import com.psu.est.dao.common.CommonTest;
import com.psu.est.dao.interfaces.LocationDao;
import com.psu.est.model.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.psu.est.dao.common.CommonTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by gorzelic on 2/7/2016.
 */
public class LocationDaoImplTest extends CommonTest {

    private static final Logger logger = LogManager.getLogger(LocationDaoImplTest.class);

    @Autowired
    private LocationDao locationDao;

    @Test
    public void testGetListByZipCode() throws Exception {
        try{
            List<Location> locationList = locationDao.getListByZipCode(20500);
            assertNotNull(locationList);
        } catch (Exception e){
            fail("Exception " + e);
        }
    }

    @Test
    public void testPersist() throws Exception {
        try{
            Location location = new Location();
            location.setStreet("1600 Pennsylvania Ave NW");
            location.setCity("Washington");
            location.setState("DC");
            location.setZip(20500);
            locationDao.persist(location);
            assertNotEquals(0,location.getLocationId());
        }catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testUpdate() throws Exception {
        try{
            Location location = new Location();
            location.setStreet("1600 Pennsylvania Ave NW");
            location.setCity("Washington");
            location.setState("DC");
            location.setZip(20500);
            locationDao.persist(location);
            location.setStreet("5700 Pennsylvania Ave NW");
            locationDao.update(location);
            Location otherLocation = locationDao.get(location.getLocationId());
            assertTrue("5700 Pennsylvania Ave NW".contentEquals(otherLocation.getStreet()));
        }catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        try{
            Location location = new Location();
            location.setStreet("1600 Pennsylvania Ave NW");
            location.setCity("Washington");
            location.setState("DC");
            location.setZip(20500);
            locationDao.saveOrUpdate(location);
            location.setStreet("5700 Pennsylvania Ave NW");
            locationDao.saveOrUpdate(location);
            Location otherLocation = locationDao.get(location.getLocationId());
            assertTrue("5700 Pennsylvania Ave NW".contentEquals(otherLocation.getStreet()));
            assertEquals(otherLocation.getZip(),location.getZip());
        }catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testDelete() throws Exception {
        try{
            Location location = new Location();
            location.setStreet("1600 Pennsylvania Ave NW");
            location.setCity("Washington");
            location.setState("DC");
            location.setZip(20500);
            locationDao.persist(location);
            Location otherLocation = locationDao.get(location.getLocationId());
            locationDao.delete(location);
            assertEquals(otherLocation,location);
        }catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testGet() throws Exception {
        try{
            Location location = new Location();
            location.setStreet("1600 Pennsylvania Ave NW");
            location.setCity("Washington");
            location.setState("DC");
            location.setZip(20500);
            locationDao.persist(location);
            Location otherLocation = locationDao.get(location.getLocationId());
            assertEquals(otherLocation,location);
        }catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testGetAll() throws Exception {
        try {
            List<Location> locationList = locationDao.getAll();
            assertNotNull(locationList);
        } catch (Exception e) {
            fail("Exception: " + e);
        }

    }
}