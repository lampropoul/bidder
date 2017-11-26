package com.bluebanana.bidder.helpers;

import com.bluebanana.bidder.models.Campaign;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Custom class to mock the Campaign API
 */
public class MockCampaignAPI {

    /**
     * Get all running Campaigns from Mock Campaign API
     *
     * @return An array of Campaign objects
     * @throws IOException
     */
    public static Campaign[] getAllCampaigns() throws IOException {
        String mockUrl = "https://avocarrot.github.io/hiring/back-end/bidder-exercise/test-cases/mock-campaign-api-response.json";
        return new RestTemplate().getForObject(mockUrl, Campaign[].class);
    }
}
