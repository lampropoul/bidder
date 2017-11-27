package com.bluebanana.bidder.models;

public class Bid {

    private String campainId;
    private Double price;
    private String adm;

    public Bid(String campainId, Double price, String adm) {
        this.campainId = campainId;
        this.price = price;
        this.adm = adm;
    }

    public Double getPrice() {
        return price;
    }
}
