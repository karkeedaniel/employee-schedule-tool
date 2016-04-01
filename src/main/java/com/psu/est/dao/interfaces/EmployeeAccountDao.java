package com.psu.est.dao.interfaces;

import com.psu.est.dao.common.GenericDao;
import com.psu.est.model.EmployeeAccount;

/**
 * Created by danielkarkee on 2/3/16.
 */
public interface EmployeeAccountDao extends GenericDao<EmployeeAccount> {

    /**
     *
     * @param username
     * @param status
     * @return
     */
    EmployeeAccount getByUsernameAndStatus(String username, String status);

    /**
     *
     * @param username
     * @return
     */
    EmployeeAccount getByUsername(String username);
}
