package com.bluebanana.bidder.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidResponse {

    private String id;
    private Bid bid;

}
