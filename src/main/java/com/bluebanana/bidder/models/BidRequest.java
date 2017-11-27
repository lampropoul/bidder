package com.bluebanana.bidder.models;

public class BidRequest {

    private String id;
    private App app;
    private Device device;

    public String getId() {
        return id;
    }

    public Device getDevice() {
        return device;
    }
}
