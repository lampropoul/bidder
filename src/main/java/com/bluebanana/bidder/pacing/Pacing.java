package com.bluebanana.bidder.pacing;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.bluebanana.bidder.helpers.CampaignHelper;
import com.bluebanana.bidder.helpers.MockCampaignAPI;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Pacing {
    
    @Value("${global.pacing.limit}")
    public int globalPacingLimit;
    
    private Map<String, Integer> campaignsToNumOfBids;
    
    private final MockCampaignAPI api;
    
    @Autowired
    public Pacing(final MockCampaignAPI api) {
        this.api = api;
    }
    
    /**
     * Initialize pacing properties
     */
    @PostConstruct
    public void resetAndLoadPacingProperties() throws IOException {
        campaignsToNumOfBids = new HashMap<>();
        Arrays
            .stream(api.getAllCampaigns())
            .forEach(campaign -> campaignsToNumOfBids.put(campaign.getId(), 0));
    }
    
    /**
     * This method runs every minute just to reset the number of bids for every campaign that were made in the last globalPacingLimit millis
     */
    @Scheduled(fixedRate = 60_000)
    public void resetLimits() {
        log.info("Resetting number of bids (=0) in the current time frame for all campaigns...");
        campaignsToNumOfBids
            .keySet()
            .forEach(campaignId -> campaignsToNumOfBids.replace(campaignId, 0));
        log.info("Done.");
    }
    
    /**
     * Method to filter all the campaigns that reached the pacing limit
     *
     * @param campaignId The id of the respective mockCampaign
     *
     * @return true if the limit did not get reached
     *
     * @see CampaignHelper#getHighestPayingCampaign(String)
     */
    public boolean campaignDidNotReachPacingLimit(String campaignId) {
        return campaignsToNumOfBids.get(campaignId) < globalPacingLimit;
    }
    
    public Map<String, Integer> getCampaignsToNumOfBids() {
        return campaignsToNumOfBids;
    }
    
}
