package com.bluebanana.bidder.helpers;

import com.bluebanana.bidder.models.Campaign;
import com.bluebanana.bidder.pacing.Pacing;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class holds all helper methods for the Campaigns
 */
@Component
@RequiredArgsConstructor
public class CampaignHelper {
    
    private final Pacing pacing;
    
    private final MockCampaignAPI api;
    
    /**
     * The Campaign with the highest price
     *
     * @param country Country code using ISO-3166-1-alpha-3.
     *
     * @return The first list item since it is sorted in descending order
     * or an empty Campaign if no Campaign that matches the criteria was found
     */
    public Optional<Campaign> getHighestPayingCampaign(String country) throws IOException {
        List<Campaign> campaignList =
            Arrays.stream(api.getAllCampaigns())
                  .filter(campaign -> campaign.getTargetedCountries().contains(country))
                  .sorted((campaign1, campaign2) -> Double.compare(campaign2.getPrice(), campaign1.getPrice())) // reverse sort (DESC)
                  .filter(campaign -> pacing.campaignDidNotReachPacingLimit(campaign.getId()))
                  .collect(Collectors.toList());
        
        if (campaignList.isEmpty()) {
            return Optional.empty();
        }
        else {
            Campaign campaign = campaignList.get(0);
            Integer numOfBids = pacing.getCampaignsToNumOfBids().get(campaign.getId());
            pacing.getCampaignsToNumOfBids().replace(campaign.getId(), ++numOfBids);
            return Optional.of(campaign);
        }
    }
    
}
