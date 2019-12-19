package com.bluebanana.bidder.controllers;

import com.bluebanana.bidder.helpers.CampaignHelpers;
import com.bluebanana.bidder.models.Bid;
import com.bluebanana.bidder.models.BidRequest;
import com.bluebanana.bidder.models.BidResponse;
import com.bluebanana.bidder.models.Campaign;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BidController {

    /**
     * Method to process POST requests on /bid endpoint
     *
     * @param bidRequest
     * @return A new BidResponse with the corresponding body
     * or null if no Campaign was found, so no bid is returned
     * and the HTTP response code is 204
     */
    @PostMapping("/bid")
    public ResponseEntity<BidResponse> bid(@RequestBody BidRequest bidRequest) {
        Campaign highestPayingCampaign = CampaignHelpers.getHighestPayingCampaign(bidRequest.getDevice().getGeo().getCountry());

        if (highestPayingCampaign.getId() == null) { // not a single suitable Campaign was found
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Bid bid = new Bid(highestPayingCampaign.getId(), highestPayingCampaign.getPrice(), highestPayingCampaign.getAdm());
        return new ResponseEntity<>(new BidResponse(bidRequest.getId(), bid), HttpStatus.OK);
    }

}
