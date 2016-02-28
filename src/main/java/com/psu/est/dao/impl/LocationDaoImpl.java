package com.psu.est.dao.impl;

import com.psu.est.dao.common.GenericDaoImpl;
import com.psu.est.dao.interfaces.LocationDao;
import com.psu.est.model.Location;
import com.sun.javafx.binding.StringFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class LocationDaoImpl extends GenericDaoImpl<Location> implements LocationDao {

    @Autowired
    private SessionFactory sessionFactory;

    // Set type to Location in GenericDaoImpl
    public LocationDaoImpl() {
        super(Location.class);
    }

    @Override
    public List<Location> getListByZipCode(String zipCode)
    {
        //String queryText = String.format("SELECT * FROM est.location where zip like '%{0}%'",zipCode);
        String queryText = "SELECT distinct a FROM location where a.zip like '%"+zipCode+"%'";
        return sessionFactory.getCurrentSession().createQuery(queryText).list();
    }
}