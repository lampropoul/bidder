package com.bluebanana.bidder.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bid {

    private String campaignId;
    private Double price;
    private String adm;

}
