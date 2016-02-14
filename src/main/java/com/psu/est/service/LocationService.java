package com.psu.est.service;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
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

    public static void updateCurrent(Location location, double newLatitude, double newLongitude)
    {
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyD8IrKPJmes8IhgTRFuDQSyRu7OX72hBd8");
        GeocodingResult[] jsonResponse = new GeocodingResult[0];
        LatLng geometry = new LatLng(newLatitude,newLongitude);
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
        for (AddressComponent component:jsonResponse[0].addressComponents)
        {
            AddressComponentType type1 = component.types[0];
            if (component.types[0] == AddressComponentType.STREET_NUMBER)
                location.setStreetNumber(component.shortName);
            else if(component.types[0] == AddressComponentType.ROUTE  )
                location.setStreet(component.shortName);
            else if(component.types[0] == AddressComponentType.LOCALITY )
                location.setCity(component.shortName);
            else if(component.types[0] == AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_1 )
                location.setState(component.shortName);
            else if(component.types[0] == AddressComponentType.POSTAL_CODE )
                location.setZip(component.shortName);
            else if(component.types[0] == AddressComponentType.POSTAL_CODE_SUFFIX )
                ZipCodeExtension = "-"+component.shortName;
        }
        if (ZipCodeExtension!=null && !ZipCodeExtension.isEmpty() && location.getZip()!=null && !location.getZip().isEmpty())
            location.setZip(location.getZip()+ZipCodeExtension);
    }

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

        location.setLatitude(jsonResponse[0].geometry.location.lat);
        location.setLongitude(jsonResponse[0].geometry.location.lng);
        location.setFormattedAddress(jsonResponse[0].formattedAddress);

        String ZipCodeExtension = null;
        for (AddressComponent component:jsonResponse[0].addressComponents)
        {
            AddressComponentType type1 = component.types[0];
            if (component.types[0] == AddressComponentType.STREET_NUMBER)
                location.setStreetNumber(component.shortName);
            else if(component.types[0] == AddressComponentType.ROUTE  )
                location.setStreet(component.shortName);
            else if(component.types[0] == AddressComponentType.LOCALITY )
                location.setCity(component.shortName);
            else if(component.types[0] == AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_1 )
                location.setState(component.shortName);
            else if(component.types[0] == AddressComponentType.POSTAL_CODE )
                location.setZip(component.shortName);
            else if(component.types[0] == AddressComponentType.POSTAL_CODE_SUFFIX )
                ZipCodeExtension = "-"+component.shortName;
        }
        if (ZipCodeExtension!=null && !ZipCodeExtension.isEmpty() && location.getZip()!=null && !location.getZip().isEmpty())
            location.setZip(location.getZip()+ZipCodeExtension);
    }
}
