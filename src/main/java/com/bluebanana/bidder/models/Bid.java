package com.bluebanana.bidder.models;

public class Bid {

    private String campaignId;
    private Double price;
    private String adm;

    public Bid(String campaignId, Double price, String adm) {
        this.campaignId = campaignId;
        this.price = price;
        this.adm = adm;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
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
