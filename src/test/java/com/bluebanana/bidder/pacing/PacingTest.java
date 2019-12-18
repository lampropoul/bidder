package com.bluebanana.bidder.pacing;

import com.bluebanana.bidder.BidderApplicationTest;
import com.bluebanana.bidder.helpers.MockCampaignAPI;
import org.junit.Test;

import java.util.Arrays;

import static com.bluebanana.bidder.pacing.Pacing.campaignsToBids;

public class PacingTest extends BidderApplicationTest {

    /**
     * Must set all values of the campaignsToBids map to zero
     *
     * @throws Exception
     */
    @Test
    public void init() throws Exception {
        Pacing.resetAndLoadPacingProperties();
        if (campaignsToBids == null) {
            assert false;
            return;
        }
        Arrays.stream(MockCampaignAPI.getAllCampaigns())
                .forEach(campaign -> {
                    assert campaignsToBids.get(campaign.getId()) == 0;
                });
        assert true;
    }

}