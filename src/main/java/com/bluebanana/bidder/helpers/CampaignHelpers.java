package com.bluebanana.bidder.helpers;

import com.bluebanana.bidder.models.Campaign;
import com.bluebanana.bidder.pacing.Pacing;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.bluebanana.bidder.pacing.Pacing.campaignsToBids;

public class CampaignHelpers {

    private static Campaign[] availableMockCampaigns = new Campaign[0];

    /**
     * The Campaign with the highest price
     *
     * @param country
     * @return The first list item since it is sorted in descending order
     * or an empty Campaign if no Campaign that matches the criteria was found
     * @throws IOException
     */
    public static Campaign getHighestPayingCampaign(String country) throws IOException {
        List<Campaign> campaignList = Arrays.stream(availableMockCampaigns)
                .filter(campaign -> campaign.getTargetedCountries().contains(country))
                .sorted((campaign1, campaign2) -> Double.compare(campaign2.getPrice(), campaign1.getPrice())) // reverse sort (DESC)
                .filter(campaign -> Pacing.campaignDidNotReachPacingLimit(campaign.getId()))
                .collect(Collectors.toList());

        if (campaignList.isEmpty()) {
            return new Campaign();
        } else {
            Campaign campaign = campaignList.get(0);
            Integer numOfBids = campaignsToBids.get(campaign.getId());
            campaignsToBids.replace(campaign.getId(), ++numOfBids);
            return campaign;
        }
    }

    public static Campaign[] getAvailableMockCampaigns() {
        return availableMockCampaigns;
    }

    public static void setAvailableMockCampaigns(Campaign[] availableMockCampaigns) {
        CampaignHelpers.availableMockCampaigns = availableMockCampaigns;
    }
}
