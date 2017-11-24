package com.bluebanana.bidder.models;

public class BidRequest {

    private String id;
    private App app;
    private Device device;

    public BidRequest() {
    }

    public BidRequest(String id, App app, Device device) {
        this.id = id;
        this.app = app;
        this.device = device;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
