package com.bluebanana.bidder.helpers;

import com.bluebanana.bidder.models.Campaign;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Custom class to mock the Campaign API
 */
@Component
public class MockCampaignAPI {

    private static String mockUrl;

    @PostConstruct
    public void init() throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream("src/main/resources/application.properties"));
        mockUrl = p.getProperty("campaign.mock.url");
    }

    /**
     * Get all running Campaigns from Mock Campaign API
     *
     * @return An array of Campaign objects
     * @throws IOException
     */
    public static Campaign[] getAllCampaigns() throws IOException {
        return new RestTemplate().getForObject(mockUrl, Campaign[].class);
    }
}
