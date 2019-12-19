package com.bluebanana.bidder.pacing;

import com.bluebanana.bidder.BidderApplicationTest;
import com.bluebanana.bidder.helpers.MockCampaignAPI;
import org.junit.Test;

import java.util.Arrays;

import static com.bluebanana.bidder.pacing.Pacing.campaignsToNumOfBids;

public class PacingTest extends BidderApplicationTest {

    /**
     * Must set all values of the campaignsToBids map to zero
     *
     * @throws Exception
     */
    @Test
    public void init() throws Exception {
        Pacing.resetAndLoadPacingProperties();
        assert campaignsToNumOfBids != null;
        Arrays
                .stream(MockCampaignAPI.getAllCampaigns())
                .forEach(campaign -> {
                    assert campaignsToNumOfBids.get(campaign.getId()) == 0;
                });
    }

}
