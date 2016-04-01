package com.psu.est.dao.interfaces;

import com.psu.est.dao.common.GenericDao;
import com.psu.est.model.Employee;

import java.util.List;

/**
 * Created by danielkarkee on 2/2/16.
 */
public interface EmployeeDao extends GenericDao<Employee> {

    /**
     *
     * @param employeeNum
     * @return
     */
    Employee getByEmployeeNum(String employeeNum);

    /**
     *
     * @param roleList
     * @return
     */
    List<Employee> getByNotLikeRole(List<String> roleList);

}
