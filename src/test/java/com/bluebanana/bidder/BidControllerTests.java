package com.bluebanana.bidder;

import com.bluebanana.bidder.controllers.BidController;
import com.bluebanana.bidder.enums.Os;
import com.bluebanana.bidder.helpers.CampaignHelpers;
import com.bluebanana.bidder.models.*;
import com.bluebanana.bidder.pacing.Pacing;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

public class BidControllerTests {

    BidController bidController = new BidController();

    @Before
    public void init() throws IOException {
        new Pacing().loadCampaigns();
    }

    @Test
    public void respondWithBid() throws IOException {
        BidResponse bidResponse = (BidResponse) getRequestMockData("USA");
        if (bidResponse.getBid().getPrice() == 1.23) {
            assert true;
            return;
        }
        assert false;
    }

    @Test
    public void respondWithoutABid() throws IOException {
        if (getRequestMockData("CYP") == null) {
            assert true;
            return;
        }
        assert false;
    }

    @Test
    public void respondWithDifferentBid() throws IOException {
        getRequestMockData("USA"); // price: 1.23
        BidResponse bidResponse = (BidResponse) getRequestMockData("USA");// price should be 0.39
        if (bidResponse.getBid().getPrice() == 0.39) {
            assert true;
            return;
        }
        assert false;
    }

    //    TODO: Implement mocking differently?
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
