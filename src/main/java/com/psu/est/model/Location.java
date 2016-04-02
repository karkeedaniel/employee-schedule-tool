package com.psu.est.model;

import com.psu.est.model.interfaces.DomainObject;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Location implements DomainObject, Serializable {


    private Integer locationId;
    private Double latitude; // = Double.NaN;
    private Double longitude; //= Double.NaN;
    private String streetNumber;// = null;
    private String street;// = null;
    private String city;// = null;
    private String state;// = null;
    private String zip;// = null;
    private String formattedAddress = null; //unresolved indicator
/*
    @Autowired
    private LocationService locationService;

    public Location()
    {
        super();
        // unresolved flags
        formattedAddress =  null;
        latitude=longitude=Double.NaN;
    }
*/

/*
    public Location(String formattedAddress)
    {
        super();
        this.formattedAddress = formattedAddress;
        locationService.resolveAddress(this);
    }

    public Location(String streetNumber,String street,String city, String state)
    {
        super();
        this.formattedAddress = streetNumber+" "+street+", "+city+", "+state;
        locationService.resolveAddress(this);
    }

    public Location(String streetNumber,String street,String city, String state,String zip)
    {
        super();
        this.formattedAddress = streetNumber+" "+street+", "+city+", "+state+" "+zip;
        locationService.resolveAddress(this);
    }
*/
    @Id
    @GeneratedValue
    @Column(name = "location_id")
    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "latitude")
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "longitude")
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "street_number")
    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Basic
    @Column(name = "street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "zip")
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Basic
    @Column(name = "formatted_address")
    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (locationId != null ? !locationId.equals(location.locationId) : location.locationId != null) return false;
        if (latitude != null ? !latitude.equals(location.latitude) : location.latitude != null) return false;
        if (longitude != null ? !longitude.equals(location.longitude) : location.longitude != null) return false;
        if (streetNumber != null ? !streetNumber.equals(location.streetNumber) : location.streetNumber != null)
            return false;
        if (street != null ? !street.equals(location.street) : location.street != null) return false;
        if (city != null ? !city.equals(location.city) : location.city != null) return false;
        if (state != null ? !state.equals(location.state) : location.state != null) return false;
        if (zip != null ? !zip.equals(location.zip) : location.zip != null) return false;
        if (formattedAddress != null ? !formattedAddress.equals(location.formattedAddress) : location.formattedAddress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = locationId != null ? locationId.hashCode() : 0;
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (streetNumber != null ? streetNumber.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (formattedAddress != null ? formattedAddress.hashCode() : 0);
        return result;
    }
}
