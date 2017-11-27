package com.bluebanana.bidder.helpers;

import com.bluebanana.bidder.BidderApplicationTest;
import com.bluebanana.bidder.models.Campaign;
import org.junit.Test;

public class MockCampaignAPITest extends BidderApplicationTest {

    @Test
    public void getAllCampaigns() throws Exception {
        Campaign[] allCampaigns = MockCampaignAPI.getAllCampaigns();
        if (allCampaigns.length != 0) {
            assert true;
            return;
        }
        assert false;
    }

}