package com.psu.est.service;

import com.psu.est.dao.interfaces.EmployeeAccountDao;
import com.psu.est.model.EmployeeAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by danielkarkee on 2/29/16.
 */
@Service
public class EmployeeDetailService implements UserDetailsService {

    @Autowired
    private EmployeeAccountDao employeeAccountDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeAccount employeeAccount = employeeAccountDao.getByUsername(username);
        List<GrantedAuthority> authorities = buildUserAuthority(employeeAccount.getRole());
        return buildUserForAuthentication(employeeAccount, authorities);
    }

    /**
     *
     * @param employeeAccount
     * @param authorities
     * @return
     */
    private User buildUserForAuthentication(EmployeeAccount employeeAccount, List<GrantedAuthority> authorities) {
        return new User(employeeAccount.getUsername(), employeeAccount.getPassword(),
                true, true, true, true, authorities);
    }

    /**
     *
     * @param employeeRole
     * @return
     */
    private List<GrantedAuthority> buildUserAuthority(String employeeRole) {
        Set<GrantedAuthority> setAuths = new HashSet<>();
        setAuths.add(new SimpleGrantedAuthority(employeeRole));
        return new ArrayList<>(setAuths);
    }

}
