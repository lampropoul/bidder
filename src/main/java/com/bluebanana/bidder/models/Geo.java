package com.bluebanana.bidder.models;

public class Geo {
    
    private String country;
    
    private Double lat;
    
    private Double lon;
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(final String country) {
        this.country = country;
    }
    
    public Double getLat() {
        return lat;
    }
    
    public void setLat(final Double lat) {
        this.lat = lat;
    }
    
    public Double getLon() {
        return lon;
    }
    
    public void setLon(final Double lon) {
        this.lon = lon;
    }
    
}
