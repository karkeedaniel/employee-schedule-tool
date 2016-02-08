package com.psu.est.service;
import com.psu.est.dao.interfaces.EmployeeAccountDao;
import com.psu.est.dao.interfaces.LocationDao;
import com.psu.est.model.EmployeeAccount;
import com.psu.est.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Created by gorzelic on 2/7/2016.
 */
@Service
public class LocationService {
    @Autowired
    private LocationDao locationDao;

    public float distance (Location location1,Location location2 ){
        return -1;
    }

    public void updateCurrent(){}

    public void toCoordinates() {}
}
