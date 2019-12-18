package com.bluebanana.bidder.helpers;

import com.bluebanana.bidder.models.Campaign;
import org.springframework.beans.factory.annotation.Value;
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

    //    Inject from properties file
    @Value("${campaign.mock.url}")
    private static String injectedMockUrl;

    private static String mockUrl;

    /**
     * Load campaign mock URL
     *
     * @throws IOException
     */
    @PostConstruct
    public static void loadCampaignUrl() throws IOException {
        if (injectedMockUrl == null) { // if injection has failed then load properties manually
            Properties p = new Properties();
            p.load(new FileInputStream("src/main/resources/application.properties"));
            mockUrl = p.getProperty("campaign.mock.url");
        } else {
            mockUrl = injectedMockUrl;
        }
    }

    /**
     * Get all running Campaigns from Mock Campaign API
     *
     * @return An array of Campaign objects
     * @throws IOException
     */
    public static Campaign[] getAllCampaigns() {
        return new RestTemplate().getForObject(mockUrl, Campaign[].class);
    }
}
