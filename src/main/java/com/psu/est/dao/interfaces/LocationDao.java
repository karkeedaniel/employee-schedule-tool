package com.psu.est.dao.interfaces;

import com.psu.est.dao.common.GenericDao;
import com.psu.est.model.Location;

import java.util.List;

/**
 * Created by danielkarkee on 2/2/16.
 */
public interface LocationDao extends GenericDao<Location> {

    /**
     * @param zipCode
     * @return
     */
     List<Location> getListByZipCode(int zipCode);
}
