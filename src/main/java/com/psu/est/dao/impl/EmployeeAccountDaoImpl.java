package com.psu.est.dao.impl;

import com.psu.est.dao.common.GenericDaoImpl;
import com.psu.est.dao.interfaces.EmployeeAccountDao;
import com.psu.est.model.EmployeeAccount;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by danielkarkee on 2/3/16.
 */
@Repository
@Transactional
public class EmployeeAccountDaoImpl extends GenericDaoImpl<EmployeeAccount> implements EmployeeAccountDao {

    public EmployeeAccountDaoImpl() {
        super(EmployeeAccount.class);
    }

    @Override
    public EmployeeAccount getByUsernameAndStatus(String username, String status) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(EmployeeAccount.class, "employeeAccount");
        criteria.createAlias("employeeAccount.employee", "employee")
                .add(Restrictions.eq("username", username))
                .add(Restrictions.eq("employee.status", status));
        return (EmployeeAccount) criteria.uniqueResult();
    }

    @Override
    public EmployeeAccount getByUsername(String username) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(EmployeeAccount.class, "employeeAccount");
        criteria.createAlias("employeeAccount.employee", "employee")
                .add(Restrictions.eq("username", username));
        return (EmployeeAccount) criteria.uniqueResult();
    }
}
