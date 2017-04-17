package com.example.anmol.thirstquencher.Model;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.Exclude;

/**
 * The location object for the location of a water source on a map
 * @author Anmol
 * @version 4/2/17
 */

public class Location {
    private double latitude;
    private double longitude;

    /**
     * constructor for location
     */
    public Location() {
    }

    /**
     * constructor for location
     * @param latLng latitude and longitude object
     */
    public Location(LatLng latLng) {
        this.latitude = latLng.latitude;
        this.longitude = latLng.longitude;
    }

    /**
     * constructor for location
     * @param latitude the latitude of the location
     * @param longitude the longatude of the location
     */
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * updates the latitude
     * @param latitude the new latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * updates the longitude
     * @param longitude the new longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * updates the longitude and latitude
     * @param latLng the new longitude and latitude object
     */
    @Exclude
    public void setLocation(LatLng latLng) {
        this.latitude = latLng.latitude;
        this.longitude = latLng.longitude;
    }

    /**
     * Returns the latitude of the Location object
     * @return latitude
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * retruns the longitude of the Location object
     * @return longitude
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * Returns the latitude Longitude object
     * @return LatLng
     */
    @Exclude
    public LatLng getLatLng() {
        return new LatLng(this.latitude, this.longitude);
    }
}
