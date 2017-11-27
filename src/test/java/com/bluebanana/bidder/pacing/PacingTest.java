package com.bluebanana.bidder.pacing;

import com.bluebanana.bidder.BidderApplicationTest;
import com.bluebanana.bidder.helpers.CampaignHelpers;
import com.bluebanana.bidder.models.Campaign;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.bluebanana.bidder.pacing.Pacing.GLOBAL_PACING_LIMIT;
import static com.bluebanana.bidder.pacing.Pacing.campaignsToBids;
import static org.junit.Assert.*;

public class PacingTest {
    @Before
    public void setUp() throws Exception {
        new BidderApplicationTest().setUp();
    }

    @After
    public void tearDown() throws Exception {
        new BidderApplicationTest().tearDown();
    }

    /**
     * Must set all values of the campaignsToBids map to zero
     * @throws Exception
     */
    @Test
    public void init() throws Exception {
        new Pacing().init();
        if (campaignsToBids == null) {
            assert false;
            return;
        }
        Arrays.stream(CampaignHelpers.getAvailableMockCampaigns())
                .forEach(campaign -> {
                    if (campaignsToBids.get(campaign.getId()) != 0) {
                        assert false;
                        return;
                    }
                });
        assert true;
    }

//    @Test
    public void resetLimits() throws Exception {

    }

    /**
     * Must check if a campaign has not reached pacing limit
     * @throws Exception
     */
    @Test
    public void campaignDidNotReachPacingLimit() throws Exception {
        new Pacing().resetLimits();

        Optional<Campaign> first = Arrays.stream(CampaignHelpers.getAvailableMockCampaigns()).findFirst();
        if (first == null) {
            assert false;
            return;
        }
        Campaign campaign = first.get();
        Integer numOfBids = campaignsToBids.get(campaign.getId());
        assert numOfBids == 0;
        campaignsToBids.replace(campaign.getId(), GLOBAL_PACING_LIMIT);
        assert !Pacing.campaignDidNotReachPacingLimit(campaign.getId());
    }

}