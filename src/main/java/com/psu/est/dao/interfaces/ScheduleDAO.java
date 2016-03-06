package com.psu.est.dao.interfaces;

import com.psu.est.dao.common.GenericDao;
import com.psu.est.model.Employee;
import com.psu.est.model.Schedule;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by Nana on 3/4/2016.
 *
 *
 *
 *
 */
public interface ScheduleDao extends GenericDao<Schedule> {
    List<Schedule> getScheduleByEmployeeID(Employee employeeId) throws DataAccessException;
}
