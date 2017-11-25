package com.bluebanana.bidder;

import com.bluebanana.bidder.controllers.BidController;
import com.bluebanana.bidder.enums.Os;
import com.bluebanana.bidder.helpers.CampaignHelpers;
import com.bluebanana.bidder.models.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BidControllerTests {

    BidController bidController = new BidController();

    @Test
    public void test1_respondWithBid() throws IOException {
//        TODO: Implement mocking
        Geo mockGeo = new Geo("USA", 0.0, 0.0);
        Device mockDevice = new Device(Os.Android, mockGeo);
        App mockApp = new App("e7fe51ce-4f63-7687-6353-ff0961c2cb0d", "Morecast Weather");
        BidRequest mockBidRequest = new BidRequest("e7fe51ce4f6376876353ff0961c2cb0d", mockApp, mockDevice);

        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();

        Campaign mockCampaign = CampaignHelpers.getHighestPayingCampaign(mockGeo.getCountry());
        BidResponse bidResponse = (BidResponse) bidController.bid(mockBidRequest, mockHttpServletResponse, mockCampaign);
        if (bidResponse.getBid().getPrice() != 1.23) {
            assert false;
            return;
        }
        assert true;
    }

    @Test
    public void test2_respondWithoutABid() throws IOException {
//        TODO: Implement mocking
        Geo mockGeo = new Geo("CYP", 0.0, 0.0);
        Device mockDevice = new Device(Os.Android, mockGeo);
        App mockApp = new App("e7fe51ce-4f63-7687-6353-ff0961c2cb0d", "Morecast Weather");
        BidRequest mockBidRequest = new BidRequest("e7fe51ce4f6376876353ff0961c2cb0d", mockApp, mockDevice);

        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();

        Campaign mockCampaign = CampaignHelpers.getHighestPayingCampaign(mockGeo.getCountry());
        mockHttpServletResponse = (MockHttpServletResponse) bidController.bid(mockBidRequest, mockHttpServletResponse, mockCampaign);
        if (mockHttpServletResponse.getStatus() != HttpServletResponse.SC_NO_CONTENT) {
            assert false;
            return;
        }
        assert true;
    }
}
