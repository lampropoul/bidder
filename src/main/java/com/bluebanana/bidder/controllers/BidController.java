package com.bluebanana.bidder.controllers;

import com.bluebanana.bidder.helpers.CampaignHelpers;
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

/**
 * Class for controlling REST HTTP requests
 */
@RestController
public class BidController {

    /**
     * Method to process POST requests on /bid endpoint
     *
     * @param bidRequest
     * @param response
     * @return A new BidResponse with the corresponding body
     * or null if no Campaign was found, so no bid is returned
     * and the HTTP response code is 204
     * @throws IOException
     */
    @RequestMapping(value = "/bid", method = POST)
    public Object bid(@RequestBody BidRequest bidRequest, HttpServletResponse response) throws IOException {
        Campaign mockCampaign = CampaignHelpers.getHighestPayingCampaign(bidRequest.getDevice().getGeo().getCountry());

        if (mockCampaign.getId() == null) { // not a single suitable Campaign was found
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return null; // the response is flushed to client so return null instead of the response object
        }

        Bid bid = new Bid(mockCampaign.getId(), mockCampaign.getPrice(), mockCampaign.getAdm());
        return new BidResponse(bidRequest.getId(), bid);
    }

}