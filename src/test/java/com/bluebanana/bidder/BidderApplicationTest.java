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
        MockCampaignAPI.loadCampaignUrl();
        MockCampaignAPI.getAllCampaigns();
        Pacing.resetAndLoadPacingProperties();
    }

    @After
    public void tearDown() {
        Pacing.resetLimits();
    }

}