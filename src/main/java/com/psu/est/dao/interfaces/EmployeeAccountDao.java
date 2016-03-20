package com.psu.est.dao.interfaces;

import com.psu.est.dao.common.GenericDao;
import com.psu.est.model.EmployeeAccount;
import org.springframework.dao.DataAccessException;

/**
 * Created by danielkarkee on 2/3/16.
 */
public interface EmployeeAccountDao extends GenericDao<EmployeeAccount> {

    /**
     *
     * @param username
     * @param status
     * @return
     * @throws DataAccessException
     */
    EmployeeAccount getByUsernameAndStatus(String username, String status) throws DataAccessException;

    /**
     *
     * @param username
     * @return
     * @throws DataAccessException
     */
    EmployeeAccount getByUsername(String username) throws DataAccessException;
}
