package com.psu.est.service;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.psu.est.dao.interfaces.LocationDao;
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

    public static double distance (Location location1, Location location2 )
    {
        if (Double.isNaN(location1.getLatitude()) || Double.isNaN(location2.getLatitude()))
        {
            return Double.NaN;
        }
        double radius = 3959; // earth radius in miles = 6371 km
        double latDelta = Math.toRadians(location2.getLatitude()-location1.getLatitude());
        double lngDelta = Math.toRadians(location2.getLongitude()-location1.getLongitude());
        double latSinDelta = Math.sin(latDelta / 2);
        double lngSinDelta = Math.sin(lngDelta / 2);
        double x = Math.pow(latSinDelta, 2) + Math.pow(lngSinDelta, 2)
                * Math.cos(Math.toRadians(location1.getLatitude())) * Math.cos(Math.toRadians(location2.getLatitude()));
        double arc = 2 * Math.atan2(Math.sqrt(x), Math.sqrt(1-x));
        double arcLength = arc * radius;

        return arcLength;
      }

    public void updateCurrent(){}

    public void toCoordinates() {}

    public static void resolveAddress(Location location){
        if (location.getFormattedAddress()==null || location.getFormattedAddress().isEmpty())
        {
            String addrNoZip = location.getStreetNumber()+" "+location.getStreet()+", "+
                    location.getCity()+", "+location.getState();

            if (location.getZip()!=null && !location.getZip().isEmpty())
            {
                location.setFormattedAddress(addrNoZip + " " + location.getZip());
            }
            else
            {
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
        location.setStreetNumber(jsonResponse[0].addressComponents[0].shortName);
        location.setStreet(jsonResponse[0].addressComponents[1].shortName);
        String temp3 = jsonResponse[0].addressComponents[2].longName;  // locality
        location.setCity(jsonResponse[0].addressComponents[3].longName);
        String temp4 = jsonResponse[0].addressComponents[4].shortName; // county
        location.setState(jsonResponse[0].addressComponents[5].shortName);
        String temp6 = jsonResponse[0].addressComponents[6].longName; //country
        location.setZip(jsonResponse[0].addressComponents[7].shortName);
        String temp8 = jsonResponse[0].addressComponents[8].shortName; //extended zip
        location.setFormattedAddress(jsonResponse[0].formattedAddress);

        location.setLatitude(jsonResponse[0].geometry.location.lat);
        location.setLongitude(jsonResponse[0].geometry.location.lng);
    }
}
