package com.bluebanana.bidder.controllers;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluebanana.bidder.helpers.CampaignHelper;
import com.bluebanana.bidder.models.Bid;
import com.bluebanana.bidder.models.BidRequest;
import com.bluebanana.bidder.models.BidResponse;
import com.bluebanana.bidder.models.Campaign;

@RestController
@RequestMapping("/bid")
public class BidController {
    
    private final CampaignHelper campaignHelper;
    
    @Autowired
    public BidController(final CampaignHelper campaignHelper) {
        this.campaignHelper = campaignHelper;
    }
    
    /**
     * @param bidRequest The request from the client
     *
     * @return A BidResponse with the corresponding body
     * or null if no Campaign was found, so no bid is returned
     * and the HTTP response code is 404
     */
    @PostMapping
    public ResponseEntity<BidResponse> bid(@RequestBody BidRequest bidRequest) throws IOException {
        AtomicReference<ResponseEntity<BidResponse>> responseEntity = new AtomicReference<>();
        Optional<Campaign> highestPayingCampaign = campaignHelper.getHighestPayingCampaign(bidRequest.device().geo().country());
        highestPayingCampaign.ifPresentOrElse(
            campaign -> {
                Bid bid = new Bid(campaign.id(), campaign.price(), campaign.adm());
                responseEntity.set(new ResponseEntity<>(new BidResponse(bidRequest.id(), bid), HttpStatus.OK));
            },
            () -> responseEntity.set(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
        return responseEntity.get();
    }
    
}
