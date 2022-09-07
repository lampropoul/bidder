package com.bluebanana.bidder.models;

import java.util.ArrayList;
import java.util.List;

public class Campaign {
    
    private String id;
    
    private String name;
    
    private Double price;
    
    private String adm;
    
    private List<String> targetedCountries = new ArrayList<>();
    
    public String getId() {
        return id;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(final String name) {
        this.name = name;
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
    
    public List<String> getTargetedCountries() {
        return targetedCountries;
    }
    
    public void setTargetedCountries(final List<String> targetedCountries) {
        this.targetedCountries = targetedCountries;
    }
    
}
