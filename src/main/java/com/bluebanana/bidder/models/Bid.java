package com.bluebanana.bidder.models;

public class Bid {
    
    private String campaignId;
    
    private Double price;
    
    private String adm;
    
    public Bid(final String campaignId, final Double price, final String adm) {
        this.campaignId = campaignId;
        this.price = price;
        this.adm = adm;
    }
    
    public Bid() {
    }
    
    public String getCampaignId() {
        return campaignId;
    }
    
    public void setCampaignId(final String campaignId) {
        this.campaignId = campaignId;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(final Double price) {
        this.price = price;
    }
    
    public String getAdm() {
        return adm;
    }
    
    public void setAdm(final String adm) {
        this.adm = adm;
    }
    
}
