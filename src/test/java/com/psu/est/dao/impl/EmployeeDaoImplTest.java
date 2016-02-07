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

import java.util.ArrayList;
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
            employee.setFirstName("Daniel");
            employee.setMiddleInitial('J');
            employee.setLastName("Karkee");
            employee.setContact("223293812");
            employeeDao.persist(employee);
            assertNotEquals(0, employee.getEmployeeId());
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testUpdate() throws Exception {

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

    @Test
    public void testGetListByRole() {
        try {
            List<Employee> employeeList = employeeDao.getListByRole("TECHNICIAN");
            assertNotNull(employeeList);
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }
}