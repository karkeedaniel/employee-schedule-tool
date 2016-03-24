package com.psu.est.dao.impl;

import com.psu.est.dao.common.CommonTest;
import com.psu.est.dao.interfaces.EmployeeDao;
import com.psu.est.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by danielkarkee on 2/2/16.
 */
public class EmployeeDaoImplTest extends CommonTest {

    private static final Logger logger = LogManager.getLogger(EmployeeDaoImplTest.class);

    @Autowired
    private EmployeeDao employeeDao;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPersist() {
        try {
            Employee employee = new Employee();
            employee.setEmployeeNum("A7304BD");
            employee.setFirstName("Homer");
            employee.setMiddleName("J");
            employee.setLastName("Simpson");
            employee.setContact("458372625");
            employee.setEmail("hjs0116@est.com");
            employee.setGender("Male");
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.set(1982, 5, 15);
            employee.setDob(new Date(calendar.getTimeInMillis()));
            employee.setSsn("123456789");
            employee.setRole("MANAGER");
            employee.setStatus("ACTIVE");
            employeeDao.persist(employee);
            assertNotNull(employee);
            assertNotEquals(0, employee.getEmployeeId());
            logger.info(employee.toString());
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testUpdate() throws Exception {
        try {
            Employee employee = employeeDao.get(1);
            assertNotNull(employee);
            logger.info(employee.toString());
            employee.setEmail("djk0116@est.com");
            employeeDao.update(employee);
            assertEquals("djk0116@est.com", employee.getEmail());
            logger.info(employee.toString());
        } catch (Exception e) {
            fail("Excetion: " + e);
        }

    }

    @Test
    public void testSaveOrUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testGet() throws Exception {
        Employee employee = employeeDao.get(1);
        assertNotNull(employee);
        logger.info(employee);
    }

    @Test
    public void testGetAll() {
        try {
            List<Employee> employeeList = employeeDao.getAll();
            assertNotNull(employeeList);
            logger.info("Size: " + employeeList.size());
            for (Employee employee: employeeList) {
                logger.info(employee.toString());
            }
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }
}