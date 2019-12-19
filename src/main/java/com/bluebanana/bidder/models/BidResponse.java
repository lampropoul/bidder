package com.bluebanana.bidder.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BidResponse {

    private String id;
    private Bid bid;

}
