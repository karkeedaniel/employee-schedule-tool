package com.psu.est.dao.impl;

import com.psu.est.dao.common.CommonTest;
import com.psu.est.dao.interfaces.EmployeeAccountDao;
import com.psu.est.model.EmployeeAccount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by danielkarkee on 2/3/16.
 */
public class EmployeeAccountDaoImplTest extends CommonTest {

    @Autowired
    private EmployeeAccountDao employeeAccountDao;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPersist() throws Exception {
        try {
            EmployeeAccount employeeAccount = new EmployeeAccount();
            employeeAccount.setUsername("djk123");
            employeeAccount.setPassword("test123");
            employeeAccount.setRole("ADMIN");
            employeeAccount.setEmployeeId(1);
            employeeAccount.setCreatedBy("djk123");
            employeeAccount.setDateCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            employeeAccountDao.persist(employeeAccount);
            assertNotEquals(0, employeeAccount.getEmployeeAccountId());
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

    }

    @Test
    public void testGetAll() {
        try {
            List<EmployeeAccount> employeeAccountList = employeeAccountDao.getAll();
            assertNotNull(employeeAccountList);
        } catch (Exception e) {
            fail("Exception: " + e);
        }
    }
}