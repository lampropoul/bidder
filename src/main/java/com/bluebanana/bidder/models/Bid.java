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

    public String getCampainId() {
        return campainId;
    }

    public void setCampainId(String campainId) {
        this.campainId = campainId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAdm() {
        return adm;
    }

    public void setAdm(String adm) {
        this.adm = adm;
    }
}
