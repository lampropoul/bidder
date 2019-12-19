package com.bluebanana.bidder.controllers;

import com.bluebanana.bidder.helpers.CampaignHelpers;
import com.bluebanana.bidder.models.Bid;
import com.bluebanana.bidder.models.BidRequest;
import com.bluebanana.bidder.models.BidResponse;
import com.bluebanana.bidder.models.Campaign;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/bid")
@RequiredArgsConstructor
public class BidController {

    private final CampaignHelpers campaignHelpers;

    /**
     * Method to process POST requests on /bid endpoint
     *
     * @param bidRequest
     * @return A new BidResponse with the corresponding body
     * or null if no Campaign was found, so no bid is returned
     * and the HTTP response code is 404
     */
    @PostMapping
    public ResponseEntity<BidResponse> bid(@RequestBody BidRequest bidRequest) {
        AtomicReference<ResponseEntity<BidResponse>> responseEntity = new AtomicReference<>();
        Optional<Campaign> highestPayingCampaign = campaignHelpers.getHighestPayingCampaign(bidRequest.getDevice().getGeo().getCountry());
        highestPayingCampaign.ifPresentOrElse(
                (campaign) -> {
                    Bid bid = new Bid(campaign.getId(), campaign.getPrice(), campaign.getAdm());
                    responseEntity.set(new ResponseEntity<>(new BidResponse(bidRequest.getId(), bid), HttpStatus.OK));
                },
                () -> responseEntity.set(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
        return responseEntity.get();
    }

}
