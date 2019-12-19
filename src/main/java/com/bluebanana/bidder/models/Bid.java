package com.bluebanana.bidder.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Bid {

    private String campaignId;
    private Double price;
    private String adm;

    public Double getPrice() {
        return price;
    }
}
