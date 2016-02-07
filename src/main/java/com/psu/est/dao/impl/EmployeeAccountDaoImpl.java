package com.psu.est.dao.impl;

import com.psu.est.dao.common.GenericDaoImpl;
import com.psu.est.dao.interfaces.EmployeeAccountDao;
import com.psu.est.model.EmployeeAccount;
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
}