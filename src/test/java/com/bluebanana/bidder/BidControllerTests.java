package com.bluebanana.bidder;

import com.bluebanana.bidder.controllers.BidController;
import com.bluebanana.bidder.enums.Os;
import com.bluebanana.bidder.helpers.CampaignHelpers;
import com.bluebanana.bidder.models.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BidControllerTests {

    BidController bidController = new BidController();

    @Test
    public void test1_respondWithBid() throws IOException {
        Geo geo = new Geo("USA", 0.0, 0.0);
        Device device = new Device(Os.Android, geo);
        App app = new App("e7fe51ce-4f63-7687-6353-ff0961c2cb0d", "Morecast Weather");
        BidRequest mockBidRequest = new BidRequest("e7fe51ce4f6376876353ff0961c2cb0d", app, device);

        Campaign mockCampaign = CampaignHelpers.getHighestPayingCampaign(geo.getCountry());
        BidResponse bidResponse = bidController.bid(mockBidRequest, mockCampaign);
        if (bidResponse.getBid().getPrice() != 1.23) {
            assert false;
            return;
        }
        assert true;
    }
}
