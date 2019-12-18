package com.bluebanana.bidder.models;

import lombok.Data;

@Data
public class BidRequest {

    private String id;
    private App app;
    private Device device;

}
