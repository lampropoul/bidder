package com.bluebanana.bidder.pacing;


import com.bluebanana.bidder.helpers.CampaignHelpers;
import com.bluebanana.bidder.models.Campaign;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static com.bluebanana.bidder.helpers.CampaignHelpers.getAllCampaigns;


//@PropertySource("classpath:application.properties")
@Component
public class Pacing {

    //    @Autowired
    //    private Environment env;
    private static final int GLOBAL_PACING_LIMIT = 1;
    private static final int GLOBAL_PACING_EXPIRATION = 60000; // millis
    public static Map<String, Integer> campaignsToBids = new HashMap<>(); // key = campaignId, value = # of bids
    private static final Logger log = LoggerFactory.getLogger(Pacing.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * Initialize campaignsToBids map with zeros on all campaignIds
     *
     * @throws IOException
     */
    @PostConstruct
    public void loadCampaigns() throws IOException {
        Campaign[] allCampaigns = new Gson().fromJson(getAllCampaigns(), Campaign[].class);
        Arrays.stream(allCampaigns)
                .forEach(campaign -> campaignsToBids.put(campaign.getId(), 0));
    }

    @Scheduled(fixedRate = GLOBAL_PACING_EXPIRATION)
    public void resetLimits() {
        log.info("{} Resetting number of bids (=0) in the current time frame for all campaigns...", dateFormat.format(new Date()));
        campaignsToBids
                .keySet()
                .stream()
                .forEach(campaignId ->
                        campaignsToBids.replace(campaignId, 0));
        log.info("Done.");
    }

    public static boolean campaignDidNotReachPacingLimit(String campaignId) {
        if (campaignsToBids.get(campaignId) < GLOBAL_PACING_LIMIT) {
            return true;
        }
        return false;
    }

}
