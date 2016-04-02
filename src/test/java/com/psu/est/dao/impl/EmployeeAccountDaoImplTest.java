package com.psu.est.dao.impl;

import com.psu.est.dao.common.CommonTest;
import com.psu.est.dao.interfaces.EmployeeAccountDao;
import com.psu.est.dao.interfaces.EmployeeDao;
import com.psu.est.model.Employee;
import com.psu.est.model.EmployeeAccount;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by danielkarkee on 2/3/16.
 */
public class EmployeeAccountDaoImplTest extends CommonTest {

    private static final Logger logger = LogManager.getLogger(EmployeeAccountDaoImplTest.class);

    @Autowired
    private EmployeeAccountDao employeeAccountDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPersist() throws Exception {
        try {
            Employee employee = employeeDao.get(1);
            assertNotNull(employee);
            EmployeeAccount employeeAccount = new EmployeeAccount();
            employeeAccount.setUsername("hs0116");
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            employeeAccount.setPassword(passwordEncoder.encode("test123"));
            employeeAccount.setEmployee(employee);
            employeeAccountDao.persist(employeeAccount);
            assertNotNull(employeeAccount);
            assertNotEquals(0, employeeAccount.getEmployeeId());
            logger.info(employeeAccount.toString());
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }


    @Test
    public void testUpdate() throws Exception {
        EmployeeAccount employeeAccount = employeeAccountDao.get(1);
        assertNotNull(employeeAccount);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        employeeAccount.setPassword(passwordEncoder.encode("test1234"));
        employeeAccountDao.update(employeeAccount);
        assertNotNull(employeeAccount);
        assertNotEquals(0, employeeAccount.getEmployeeId());
        logger.info(employeeAccount.toString());
    }

    @Test
    public void testSaveOrUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testGetAll() {
        try {
            List<EmployeeAccount> employeeAccountList = employeeAccountDao.getAll();
            assertNotNull(employeeAccountList);
            logger.info("Size: " + employeeAccountList.size());
            for (EmployeeAccount employeeAccount: employeeAccountList) {
                logger.info(employeeAccount.toString());
            }
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testGetByUsername() {
        try {
            EmployeeAccount employeeAccount = employeeAccountDao.getByUsername("djk123");
            assertNotNull(employeeAccount);
            logger.info(employeeAccount.toString());
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testGetByUsernameAndStatus() {
        try {
            EmployeeAccount employeeAccount = employeeAccountDao.getByUsernameAndStatus("hs0116", "ACTIVE");
            assertNotNull(employeeAccount);
            logger.info(employeeAccount.toString());
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }

    @Test
    public void testName() throws Exception {
        int i = 0;
        while (i < 10) {
            String password = "test123";
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);

            System.out.println(hashedPassword);
            i++;
        }

    }
}