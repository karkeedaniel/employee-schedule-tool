package com.psu.est.dao.interfaces;

import com.psu.est.dao.common.GenericDao;
import com.psu.est.model.Employee;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by danielkarkee on 2/2/16.
 */
public interface EmployeeDao extends GenericDao<Employee> {

    public List<Employee> getListByRole(String role) throws DataAccessException;
}
