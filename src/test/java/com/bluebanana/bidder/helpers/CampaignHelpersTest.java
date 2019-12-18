package com.bluebanana.bidder.helpers;

import com.bluebanana.bidder.BidderApplicationTest;
import com.bluebanana.bidder.models.Campaign;
import org.junit.Test;

public class CampaignHelpersTest extends BidderApplicationTest {

    @Test
    public void getHighestPayingCampaign() throws Exception {
        Campaign mockCampaign = CampaignHelpers.getHighestPayingCampaign("USA");
        assert mockCampaign.getId() != null;
    }


}
