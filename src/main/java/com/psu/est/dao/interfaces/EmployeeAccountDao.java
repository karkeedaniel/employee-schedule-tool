package com.psu.est.dao.interfaces;

import com.psu.est.dao.common.GenericDao;
import com.psu.est.model.EmployeeAccount;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by danielkarkee on 2/3/16.
 */
public interface EmployeeAccountDao extends GenericDao<EmployeeAccount> {

    /**
     *
     * @param username
     * @return
     * @throws DataAccessException
     */
    public EmployeeAccount getByUsername(String username) throws DataAccessException;

    public List<EmployeeAccount> GetEmployeeAccountsByRole(String role);
}
