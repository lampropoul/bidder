package com.bluebanana.bidder.controllers;

import com.bluebanana.bidder.helpers.CampaignHelpers;
import com.bluebanana.bidder.models.Bid;
import com.bluebanana.bidder.models.BidRequest;
import com.bluebanana.bidder.models.BidResponse;
import com.bluebanana.bidder.models.Campaign;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class BidController {

    @RequestMapping(value = "/bid", method = POST)
    public BidResponse bid(@RequestBody BidRequest bidRequest, Campaign campaign) throws IOException {
        if (campaign.getId() == null) {
            campaign = CampaignHelpers.getHighestPayingCampaign(bidRequest.getDevice().getGeo().getCountry());
        }
        Bid bid = new Bid(campaign.getId(), campaign.getPrice(), campaign.getAdm());
        return new BidResponse(bidRequest.getId(), bid);
    }

}