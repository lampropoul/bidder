package com.bluebanana.bidder.models;

public class BidResponse {

    private String id;
    private Bid bid;

    public BidResponse(String id, Bid bid) {
        this.id = id;
        this.bid = bid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }
}
