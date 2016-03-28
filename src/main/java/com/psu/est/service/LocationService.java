package com.psu.est.service;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.*;
import com.psu.est.dao.interfaces.LocationDao;
import com.psu.est.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by gorzelic on 2/7/2016.
 */
@Service
public class LocationService {
    @Autowired
    private LocationDao locationDao;

    public double distance(Location location1, Location location2) {
        if (Double.isNaN(location1.getLatitude()) || Double.isNaN(location2.getLatitude())) {
            return Double.NaN;
        }
        double radius = 3959; // earth radius in miles = 6371 km
        double latDelta = Math.toRadians(location2.getLatitude() - location1.getLatitude());
        double lngDelta = Math.toRadians(location2.getLongitude() - location1.getLongitude());
        double latSinDelta = Math.sin(latDelta / 2);
        double lngSinDelta = Math.sin(lngDelta / 2);
        double x = Math.pow(latSinDelta, 2) + Math.pow(lngSinDelta, 2)
                * Math.cos(Math.toRadians(location1.getLatitude())) * Math.cos(Math.toRadians(location2.getLatitude()));
        double arc = 2 * Math.atan2(Math.sqrt(x), Math.sqrt(1 - x));
        double arcLength = arc * radius;

        return arcLength;
    }

    public void updateCurrent(Location location, double newLatitude, double newLongitude) {
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyD8IrKPJmes8IhgTRFuDQSyRu7OX72hBd8");
        GeocodingResult[] jsonResponse = new GeocodingResult[0];
        LatLng geometry = new LatLng(newLatitude, newLongitude);
        try {
            jsonResponse = GeocodingApi.reverseGeocode(context,
                    geometry).await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        location.setLatitude(jsonResponse[0].geometry.location.lat);
        location.setLongitude(jsonResponse[0].geometry.location.lng);
        location.setFormattedAddress(jsonResponse[0].formattedAddress);

        String ZipCodeExtension = null;
        for (AddressComponent component : jsonResponse[0].addressComponents) {
            AddressComponentType type1 = component.types[0];
            if (component.types[0] == AddressComponentType.STREET_NUMBER)
                location.setStreetNumber(component.shortName);
            else if (component.types[0] == AddressComponentType.ROUTE)
                location.setStreet(component.shortName);
            else if (component.types[0] == AddressComponentType.LOCALITY)
                location.setCity(component.shortName);
            else if (component.types[0] == AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_1)
                location.setState(component.shortName);
            else if (component.types[0] == AddressComponentType.POSTAL_CODE)
                location.setZip(component.shortName);
            else if (component.types[0] == AddressComponentType.POSTAL_CODE_SUFFIX)
                ZipCodeExtension = "-" + component.shortName;
        }
        if (ZipCodeExtension != null && !ZipCodeExtension.isEmpty() && location.getZip() != null && !location.getZip().isEmpty())
            location.setZip(location.getZip() + ZipCodeExtension);
    }

    public void toCoordinates() {
    }

    public void resolveAddress(Location location) {
        if (location.getFormattedAddress() == null || location.getFormattedAddress().isEmpty()) {
            String addrNoZip = location.getStreetNumber() + " " + location.getStreet() + ", " +
                    location.getCity() + ", " + location.getState();

            if (location.getZip() != null && !location.getZip().isEmpty()) {
                location.setFormattedAddress(addrNoZip + " " + location.getZip());
            } else {
                location.setFormattedAddress(addrNoZip);
            }
        }

        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyD8IrKPJmes8IhgTRFuDQSyRu7OX72hBd8");
        GeocodingResult[] jsonResponse = new GeocodingResult[0];
        try {
            jsonResponse = GeocodingApi.geocode(context,
                    location.getFormattedAddress()).await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        location.setLatitude(jsonResponse[0].geometry.location.lat);
        location.setLongitude(jsonResponse[0].geometry.location.lng);
        location.setFormattedAddress(jsonResponse[0].formattedAddress);

        String ZipCodeExtension = null;
        for (AddressComponent component : jsonResponse[0].addressComponents) {
            AddressComponentType type1 = component.types[0];
            if (component.types[0] == AddressComponentType.STREET_NUMBER)
                location.setStreetNumber(component.shortName);
            else if (component.types[0] == AddressComponentType.ROUTE)
                location.setStreet(component.shortName);
            else if (component.types[0] == AddressComponentType.LOCALITY)
                location.setCity(component.shortName);
            else if (component.types[0] == AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_1)
                location.setState(component.shortName);
            else if (component.types[0] == AddressComponentType.POSTAL_CODE)
                location.setZip(component.shortName);
            else if (component.types[0] == AddressComponentType.POSTAL_CODE_SUFFIX)
                ZipCodeExtension = "-" + component.shortName;
        }
        if (ZipCodeExtension != null && !ZipCodeExtension.isEmpty() && location.getZip() != null && !location.getZip().isEmpty())
            location.setZip(location.getZip() + ZipCodeExtension);
    }

    public void RemoveLocationsOutsideOf(List<Location> locations, Location from, double distance) {
        if (distance < 0) {
            // tbd log error
            return;
        }
        if (from.getFormattedAddress() == null || from.getFormattedAddress().isEmpty()) {
            resolveAddress(from);
            if (from.getLocationId() > 0) {
                // take this opportunty to save results (don't waste calls to map service)
                Location location = locationDao.get(from.getLocationId());
                if (location != null) {
                    locationDao.update(from);
                }
            }
        }

        for (Location thisLocation : locations) {
            if (thisLocation.getFormattedAddress() == null || thisLocation.getFormattedAddress().isEmpty()) {
                resolveAddress(thisLocation);
                // again, do not wast calls to map service, more concise than above
                if ((thisLocation.getLocationId() > 0) && (locationDao.get(from.getLocationId()) != null)) {
                    locationDao.update(thisLocation);
                }
            }

            if (distance(from, thisLocation) > distance) {
                locations.remove(thisLocation);
            }
        }
    }

    public class RouteMetric{
        Double distInMiles;
        int travTimInMins;
        RouteMetric(){
            distInMiles=0.0;
            travTimInMins = 0;
        }

        RouteMetric(Double distInMiles, int travTimInMins){
            this.distInMiles = distInMiles;
            this.travTimInMins = travTimInMins;
        }

        RouteMetric(DirectionsLeg leg)
        {
            distInMiles =  leg.distance.inMeters /1609.34;
            travTimInMins = (int) (leg.duration.inSeconds / 60);
        }
    }

    public List<Location> GetBestRoute(Location begin, Location end, List<Location> wayPoints, Map<Integer, RouteMetric> routeMetrics) {
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyD8IrKPJmes8IhgTRFuDQSyRu7OX72hBd8");

        LatLng geoBegin = new LatLng(begin.getLatitude(), begin.getLongitude());
        LatLng geoEnd = new LatLng(end.getLatitude(), end.getLongitude());
        // not sure what we are going to use from these, but put into arrays so can keep track of order
        List<LatLng> geoWayPoints = new ArrayList<LatLng>();
        List<Integer> idxWayPoints =  new ArrayList<Integer>();
        // need index to keep track of waypoints
        for (int i=0; i<wayPoints.size(); i++)
        {
            LatLng waypoint = new LatLng(wayPoints.get(i).getLatitude(), wayPoints.get(i).getLongitude());
            geoWayPoints.add(i,waypoint);
            idxWayPoints.add(i,wayPoints.get(i).getLocationId());
        }
        String[] strWayPoints = new String[geoWayPoints.size()];
        for (int i=0; i<strWayPoints.length; i++)
        {
            strWayPoints[i] = geoWayPoints.get(i).toUrlValue();
        }
        DirectionsResult result = null;
        try {
            result = DirectionsApi.newRequest(context)
                    .origin(geoBegin).destination(geoEnd)
                    .optimizeWaypoints(true)
                    .waypoints(strWayPoints)
                    .await();

        } catch (Exception e) {
            e.printStackTrace();
        }
        // loop for debugging only - only not asking for multiple alternative routes
        // not sure why we would have more than one route returned
        DirectionsRoute[] routes = new DirectionsRoute[5];
        for (int i=0; i< result.routes.length; i++)
        {
            routes[i] = result.routes[i];
        }
        // build up best rout with metrics between each node
        List<Location> bestRoute = new ArrayList<>();
        // add start
        bestRoute.add(0,begin);
        Location lastStop = begin;
        int lastStopIdx = 0; int thisStopIdx = 0; Location thisStop = null;
        // last distance is between last waypoint to end
        for (int i=0; i<result.routes[0].legs.length; i++)
        {
            if (i<result.routes[0].waypointOrder.length) {
                thisStopIdx = result.routes[0].waypointOrder[i];
                // add this stop
                thisStop = wayPoints.get(thisStopIdx);
            }
            else
            {
                thisStop = end;
                thisStopIdx = lastStopIdx + 1;
            }
            bestRoute.add(i+1,thisStop);
            // add metrics between last stop and this stop, leg
            DirectionsLeg thisLeg = result.routes[0].legs[i];
            Double thisLegDistInMiles =  thisLeg.distance.inMeters / 1609.34;
            int thisLedDurationInMins = (int) (thisLeg.duration.inSeconds / 60);
            RouteMetric thisLegMetrics = new RouteMetric(thisLegDistInMiles,thisLedDurationInMins);
            Integer metricKey = GenUniqueKey(lastStop.getLocationId(), thisStop.getLocationId());
            routeMetrics.put(metricKey,thisLegMetrics);
            // this is now the last stop
            lastStop = thisStop;
            lastStopIdx = thisStopIdx;
        }

        return bestRoute;
    }

    public List<Location> SolveTSP(Location employeeHome, List<Location> wayPoints, Map<Integer, RouteMetric> travelTimes) {
        return GetBestRoute(employeeHome,employeeHome,wayPoints,travelTimes);
    }

    // generate a unique key for 2 locations
    static Integer GenUniqueKey(Integer location1ID, Integer location2ID)
    {
        // a cheap way
        //a >= b ? a * a + a + b : a + b * b;  where a, b >= 0, aka Szudzik's function
        Integer ret;
        ret= (location1ID >= location2ID)? (location1ID * location1ID)+ location1ID + location2ID :
                (location2ID * location2ID)+ location1ID;
        return ret;

    }

}

