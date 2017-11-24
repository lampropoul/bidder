package com.bluebanana.bidder.models;

public class Geo {

    private String country;
    private Double lat;
    private Double lon;

    public Geo() {
    }

    public Geo(String country, Double lat, Double lon) {
        this.country = country;
        this.lat = lat;
        this.lon = lon;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
