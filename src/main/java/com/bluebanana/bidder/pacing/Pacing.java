package com.bluebanana.bidder.pacing;

import com.bluebanana.bidder.helpers.CampaignHelpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Component
public class Pacing {

    public static int GLOBAL_PACING_LIMIT;
    public static int GLOBAL_PACING_LIMIT_DEFAULT = 1;
    private static final int GLOBAL_PACING_RATE = 60000; // millis
    public static Map<String, Integer> campaignsToBids = new HashMap<>(); // key = campaignId, value = # of bids
    private static final Logger log = LoggerFactory.getLogger(Pacing.class);

    /**
     * Initialize pacing properties
     *
     * @throws IOException
     */
    @PostConstruct
    public void init() throws IOException {
        Arrays.stream(CampaignHelpers.getAvailableMockCampaigns())
                .forEach(campaign -> campaignsToBids.put(campaign.getId(), 0));

        Properties properties = new Properties();
        String limit = null;
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
            limit = properties.getProperty("global.pacing.limit");
        } catch (FileNotFoundException ex) {
            System.out.println(String.format("{}: {}", ex.getCause(), ex.getMessage()));
            System.out.println(String.format("Falling back to default pacing limit equal to {}...", GLOBAL_PACING_LIMIT_DEFAULT));
        } finally {
            if (limit == null || "".equals(limit)) {
                GLOBAL_PACING_LIMIT = GLOBAL_PACING_LIMIT_DEFAULT;
            } else {
                GLOBAL_PACING_LIMIT = Integer.valueOf(limit);
            }
        }

    }

    /**
     * This method runs every GLOBAL_PACING_RATE millis
     * just to reset the number of bids for every campaign
     * that were made in the last GLOBAL_PACING_RATE millis
     */
    @Scheduled(fixedRate = GLOBAL_PACING_RATE)
    public void resetLimits() {
        log.info("Resetting number of bids (=0) in the current time frame for all campaigns...");
        campaignsToBids
                .keySet()
                .stream()
                .forEach(campaignId ->
                        campaignsToBids.replace(campaignId, 0));
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
        if (campaignsToBids.get(campaignId) < GLOBAL_PACING_LIMIT) {
            return true;
        }
        return false;
    }
}
