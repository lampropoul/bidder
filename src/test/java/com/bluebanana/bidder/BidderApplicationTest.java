package com.bluebanana.bidder;

import com.bluebanana.bidder.helpers.MockCampaignAPI;
import com.bluebanana.bidder.pacing.Pacing;
import org.junit.After;
import org.junit.Before;

public class BidderApplicationTest {
    @Before
    public void setUp() throws Exception {
        new MockCampaignAPI().init();
        MockCampaignAPI.getAllCampaigns();
        new Pacing().init();
    }

    @After
    public void tearDown() throws Exception {

    }

}