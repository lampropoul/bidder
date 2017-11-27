package com.bluebanana.bidder;

import com.bluebanana.bidder.helpers.MockCampaignAPI;
import com.bluebanana.bidder.pacing.Pacing;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

public class BidderApplicationTest {

    /**
     * Initialize mock data and (real) properties
     *
     * @throws IOException
     */
    @Before
    public void setUp() throws Exception {
//        set mock data only for tests
        new MockCampaignAPI().init();
        MockCampaignAPI.getAllCampaigns();
        new Pacing().init();
    }

    @After
    public void tearDown() throws Exception {
        new Pacing().resetLimits();
    }

}