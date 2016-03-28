package com.psu.est.dao.impl;

import com.psu.est.dao.common.GenericDaoImpl;
import com.psu.est.dao.interfaces.EmployeeAccountDao;
import com.psu.est.model.Employee;
import com.psu.est.model.EmployeeAccount;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public EmployeeAccount getByUsername(String username) throws DataAccessException {
        return (EmployeeAccount) sessionFactory.getCurrentSession()
                .createQuery("from EmployeeAccount where username = :username")
                .setParameter("username", username).uniqueResult();
    }

    @Override
    public List<EmployeeAccount> GetEmployeeAccountsByRole(String role)
    {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(EmployeeAccount.class);
        if (role != null){
            // using like until we move to enums for role
            String rolelike = '%' + role + '%';
            criteria.add(Restrictions.ilike("role",rolelike));
            return criteria.list();
        }
        else return null;
    }
}
