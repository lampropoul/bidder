package com.bluebanana.bidder.controllers;

import com.bluebanana.bidder.models.Bid;
import com.bluebanana.bidder.models.BidRequest;
import com.bluebanana.bidder.models.BidResponse;
import com.bluebanana.bidder.models.Campaign;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class BidController {

    @RequestMapping(value = "/bid", method = POST)
    public BidResponse bid(@RequestBody BidRequest bidRequest, Campaign campaign) throws IOException {
//        campaign = filterCampaigns(bidRequest.getDevice().getGeo().getCountry());
        Bid bid = new Bid(campaign.getId(), campaign.getPrice(), campaign.getAdm());
        return new BidResponse(bidRequest.getId(), bid);
    }

}