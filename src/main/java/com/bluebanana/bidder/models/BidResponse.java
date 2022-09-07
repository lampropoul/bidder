package com.bluebanana.bidder.models;

public class BidResponse {
    
    private String id;
    
    private Bid bid;
    
    public BidResponse(final String id, final Bid bid) {
        this.id = id;
        this.bid = bid;
    }
    
    public BidResponse() {
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public Bid getBid() {
        return bid;
    }
    
    public void setBid(final Bid bid) {
        this.bid = bid;
    }
    
}
