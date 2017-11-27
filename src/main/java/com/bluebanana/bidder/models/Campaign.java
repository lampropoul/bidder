package com.bluebanana.bidder.models;

import java.util.ArrayList;
import java.util.List;

public class Campaign {

    private String id;
    private String name;
    private Double price;
    private String adm;
    private List<String> targetedCountries = new ArrayList<>();

    public Campaign() {
    }

    public String getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public String getAdm() {
        return adm;
    }

    public List<String> getTargetedCountries() {
        return targetedCountries;
    }
}
