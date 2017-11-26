package com.bluebanana.bidder.controllers;

import com.bluebanana.bidder.helpers.CampaignAPI;
import com.bluebanana.bidder.models.Bid;
import com.bluebanana.bidder.models.BidRequest;
import com.bluebanana.bidder.models.BidResponse;
import com.bluebanana.bidder.models.Campaign;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class BidController {

    /**
     * 
     * @param bidRequest
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/bid", method = POST)
    public Object bid(@RequestBody BidRequest bidRequest, HttpServletResponse response) throws IOException {
        Campaign campaign = CampaignAPI.getHighestPayingCampaign(bidRequest.getDevice().getGeo().getCountry());

        if (campaign.getId() == null) { // not a single suitable Campaign was found
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return null;
        }

        Bid bid = new Bid(campaign.getId(), campaign.getPrice(), campaign.getAdm());
        return new BidResponse(bidRequest.getId(), bid);
    }

}