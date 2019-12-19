package com.bluebanana.bidder.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Campaign {

    private String id;
    private String name;
    private Double price;
    private String adm;
    private List<String> targetedCountries = new ArrayList<>();

}
