package com.bluebanana.bidder.pacing;

import com.bluebanana.bidder.helpers.CampaignHelpers;
import com.bluebanana.bidder.helpers.MockCampaignAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class Pacing {

    @Value("${global.pacing.limit}")
    public int GLOBAL_PACING_LIMIT;
    public Map<String, Integer> campaignsToNumOfBids;

    private final MockCampaignAPI api;

    /**
     * Initialize pacing properties
     */
    @PostConstruct
    public void resetAndLoadPacingProperties() {
        campaignsToNumOfBids = new HashMap<>();
        Arrays
                .stream(api.getAllCampaigns())
                .forEach(campaign -> campaignsToNumOfBids.put(campaign.getId(), 0));
    }

    /**
     * This method runs every minute just to reset the number of bids for every campaign that were made in the last GLOBAL_PACING_RATE millis
     */
    @Scheduled(fixedRate = 60000) // TODO, make configurable
    public void resetLimits() {
        log.info("Resetting number of bids (=0) in the current time frame for all campaigns...");
        campaignsToNumOfBids
                .keySet()
                .forEach(campaignId -> campaignsToNumOfBids.replace(campaignId, 0));
        log.info("Done.");
    }

    /**
     * Method to filter all of the campaigns that reached the pacing limit
     *
     * @param campaignId The id of the respective mockCampaign
     * @return true if the limit did not get reached
     * @see CampaignHelpers#getHighestPayingCampaign(String)
     */
    public boolean campaignDidNotReachPacingLimit(String campaignId) {
        return campaignsToNumOfBids.get(campaignId) < GLOBAL_PACING_LIMIT;
    }
}
