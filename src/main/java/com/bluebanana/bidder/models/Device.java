package com.bluebanana.bidder.models;

import com.bluebanana.bidder.models.enums.OperatingSystem;

public class Device {
    
    private OperatingSystem operatingSystem;
    
    private Geo geo;
    
    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }
    
    public void setOperatingSystem(final OperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
    
    public Geo getGeo() {
        return geo;
    }
    
    public void setGeo(final Geo geo) {
        this.geo = geo;
    }
    
}
