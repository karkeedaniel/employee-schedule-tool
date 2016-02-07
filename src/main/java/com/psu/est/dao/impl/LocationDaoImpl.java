package com.psu.est.dao.impl;

import com.psu.est.dao.common.GenericDaoImpl;
import com.psu.est.dao.interfaces.LocationDao;
import com.psu.est.model.Location;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by danielkarkee on 2/2/16.
 */
@Repository
@Transactional
public class LocationDaoImpl extends GenericDaoImpl<Location> implements LocationDao {

    public LocationDaoImpl() {
        super(Location.class);
    }
}