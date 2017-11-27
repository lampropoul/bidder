package com.bluebanana.bidder.helpers;

import com.bluebanana.bidder.BidderApplicationTest;
import com.bluebanana.bidder.models.Campaign;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CampaignHelpersTest {
    @Before
    public void setUp() throws Exception {
        new BidderApplicationTest().setUp();
    }

    @After
    public void tearDown() throws Exception {
        new BidderApplicationTest().tearDown();
    }

    @Test
    public void getHighestPayingCampaign() throws Exception {
        Campaign mockCampaign = CampaignHelpers.getHighestPayingCampaign("USA");
        assert mockCampaign.getId() != null;
    }


}