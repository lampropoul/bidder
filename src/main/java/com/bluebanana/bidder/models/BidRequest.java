package com.bluebanana.bidder.models;

public class BidRequest {
    
    private String id;
    
    private App app;
    
    private Device device;
    
    public String getId() {
        return id;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public App getApp() {
        return app;
    }
    
    public void setApp(final App app) {
        this.app = app;
    }
    
    public Device getDevice() {
        return device;
    }
    
    public void setDevice(final Device device) {
        this.device = device;
    }
    
}
