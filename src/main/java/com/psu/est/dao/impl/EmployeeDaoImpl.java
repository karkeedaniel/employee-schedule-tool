package com.psu.est.dao.impl;

import com.psu.est.dao.common.GenericDaoImpl;
import com.psu.est.dao.interfaces.EmployeeDao;
import com.psu.est.model.Employee;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by danielkarkee on 2/2/16.
 */
@Repository
@Transactional
public class EmployeeDaoImpl extends GenericDaoImpl<Employee> implements EmployeeDao {

    public EmployeeDaoImpl() {
        super(Employee.class);
    }

    @Override
    public Employee getByEmployeeNum(String employeeNum) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Employee.class, "employee");
        criteria.add(Restrictions.eq("employeeNum", employeeNum));
        return (Employee) criteria.uniqueResult();
    }

    @Override
    public List<Employee> getByNotLikeRole(List<String> roleList) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Employee.class, "employee");
        criteria.add(Restrictions.not(Restrictions.in("role", roleList)));
        return criteria.list();
    }

}