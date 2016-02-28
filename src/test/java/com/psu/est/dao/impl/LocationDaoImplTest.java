package com.psu.est.dao.impl;
import com.psu.est.dao.common.CommonTest;
import com.psu.est.dao.interfaces.LocationDao;
import com.psu.est.model.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.psu.est.dao.common.CommonTest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by gorzelic on 2/7/2016.
 */
public class LocationDaoImplTest extends CommonTest {

    private static final Logger logger = LogManager.getLogger(LocationDaoImplTest.class);

    @Autowired
    private LocationDao locationDao;
    //@Autowired
    //private SessionFactory sessionFactory;

    @Test
    public void testGetListByZipCode() throws Exception {
        try{
            List<Location> locationList = locationDao.getListByZipCode("20500");
            assertNotNull(locationList);
        } catch (Exception e){
            fail("Exception " + e);
        }
    }

    @Test
    @Transactional
    public void testLocationPersist() throws Exception {
        try{
            Location location = new Location();
            location.setStreetNumber("1600");
            location.setStreet("Pennsylvania Ave NW");
            location.setCity("Washington");
            location.setState("DC");
            location.setZip("20500");
            locationDao.persist(location);
            //sessionFactory.getCurrentSession().flush(); //debugging test
            assertNotEquals((long) location.getLocationId(), 0L);
        }catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testLocationUpdate() throws Exception {
        try{
            Location location = new Location();
            location.setStreetNumber("1600");
            location.setStreet("Pennsylvania Ave NW");
            location.setCity("Washington");
            location.setState("DC");
            location.setZip("20500");
            locationDao.persist(location);
            location.setStreetNumber("5700");
            locationDao.update(location);
            Location otherLocation = new Location();
            otherLocation = locationDao.get(location.getLocationId());
            assertTrue("5700".contentEquals(otherLocation.getStreetNumber()));
        }catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testLocationSaveOrUpdate() throws Exception {
        try{
            Location location = new Location();
            location.setStreetNumber("1600");
            location.setStreet("Pennsylvania Ave NW");
            location.setCity("Washington");
            location.setState("DC");
            location.setZip("20500");
            locationDao.saveOrUpdate(location);
            location.setStreetNumber("5700");
            locationDao.saveOrUpdate(location);
            Location otherLocation = locationDao.get(location.getLocationId());
            assertTrue("5700".contentEquals(otherLocation.getStreetNumber()));
            assertEquals(otherLocation.getZip(),location.getZip());
        }catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testLocationDelete() throws Exception {
        try{
            Location location = new Location();
            location.setStreetNumber("1600");
            location.setStreet("Pennsylvania Ave NW");
            location.setCity("Washington");
            location.setState("DC");
            location.setZip("20500");
            locationDao.persist(location);
            Location otherLocation = locationDao.get(location.getLocationId());
            locationDao.delete(location);
            location = locationDao.get(otherLocation.getLocationId());
            assertNull(location);
        }catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testLocationGet() throws Exception {
        try{
            Location location = new Location();
            location.setStreetNumber("1600");
            location.setStreet("Pennsylvania Ave NW");
            location.setCity("Washington");
            location.setState("DC");
            location.setZip("20500");
            locationDao.persist(location);
            Location otherLocation = locationDao.get(location.getLocationId());
            assertEquals(otherLocation,location);
        }catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testLocationGetAll() throws Exception {
        try {
            List<Location> locationList = locationDao.getAll();
            assertNotNull(locationList);
            Location location = new Location();
            location.setStreetNumber("1600");
            location.setStreet("Pennsylvania Ave NW");
            location.setCity("Washington");
            location.setState("DC");
            location.setZip("20500");
            locationDao.persist(location);
            List<Location> locationList1 = locationDao.getAll();
            assertTrue(locationList.size()+1 == locationList1.size());
        } catch (Exception e) {
            fail("Exception: " + e);
        }

    }
}