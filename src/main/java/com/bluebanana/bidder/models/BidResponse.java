package com.bluebanana.bidder.models;

public class BidResponse {

    private String id;
    private Bid bid;

    public BidResponse(String id, Bid bid) {
        this.id = id;
        this.bid = bid;
    }

    public Bid getBid() {
        return bid;
    }
}
