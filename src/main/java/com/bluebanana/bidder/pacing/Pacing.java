package com.bluebanana.bidder.pacing;

import com.bluebanana.bidder.helpers.CampaignHelpers;
import com.bluebanana.bidder.helpers.MockCampaignAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class Pacing {

    @Value("${global.pacing.limit}")
    public static final int GLOBAL_PACING_LIMIT = 1;
    private static final int GLOBAL_PACING_RATE = 60000; // millis
    public static Map<String, Integer> campaignsToBids; // key = campaignId, value = # of bids

    /**
     * Initialize pacing properties
     *
     * @throws IOException
     */
    @PostConstruct
    public static void resetAndLoadPacingProperties() throws IOException {
        campaignsToBids = new HashMap<>();
        Arrays.stream(MockCampaignAPI.getAllCampaigns())
                .forEach(campaign -> campaignsToBids.put(campaign.getId(), 0));
    }

    /**
     * This method runs every GLOBAL_PACING_RATE millis
     * just to reset the number of bids for every campaign
     * that were made in the last GLOBAL_PACING_RATE millis
     */
    @Scheduled(fixedRate = GLOBAL_PACING_RATE)
    public static void resetLimits() {
        log.info("Resetting number of bids (=0) in the current time frame for all campaigns...");
        campaignsToBids
                .keySet()
                .forEach(campaignId -> campaignsToBids.replace(campaignId, 0));
        log.info("Done.");
    }

    /**
     * Method to filter all of the campaigns that reached the pacing limit
     *
     * @param campaignId The id of the respective mockCampaign
     * @return true if the limit did not get reached
     * @see CampaignHelpers#getHighestPayingCampaign(String)
     */
    public static boolean campaignDidNotReachPacingLimit(String campaignId) {
        return campaignsToBids.get(campaignId) < GLOBAL_PACING_LIMIT;
    }
}
