package com.bluebanana.bidder;

import com.bluebanana.bidder.controllers.BidController;
import com.bluebanana.bidder.enums.Os;
import com.bluebanana.bidder.helpers.CampaignHelpers;
import com.bluebanana.bidder.models.*;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;


public class BidControllerTests {

    BidController bidController = new BidController();

    @Test
    public void respondWithBid() throws IOException {
//        TODO: Implement mocking
        BidResponse bidResponse = (BidResponse) getRequestMockData("USA");
        if (bidResponse.getBid().getPrice() == 1.23) {
            assert true;
            return;
        }
        assert false;
    }

    @Test
    public void respondWithoutABid() throws IOException {
//        TODO: Implement mocking
        if (getRequestMockData("CYP") == null) {
            assert true;
            return;
        }
        assert false;
    }

    private Object getRequestMockData(String country) throws IOException {
        Geo mockGeo = new Geo(country, 0.0, 0.0);
        Device mockDevice = new Device(Os.Android, mockGeo);
        App mockApp = new App("e7fe51ce-4f63-7687-6353-ff0961c2cb0d", "Morecast Weather");
        BidRequest mockBidRequest = new BidRequest("e7fe51ce4f6376876353ff0961c2cb0d", mockApp, mockDevice);
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        Campaign mockCampaign = CampaignHelpers.getHighestPayingCampaign(mockGeo.getCountry());
        return bidController.bid(mockBidRequest, mockHttpServletResponse, mockCampaign);
    }
}
