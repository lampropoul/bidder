package com.bluebanana.bidder.helpers;

import com.bluebanana.bidder.models.Campaign;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Custom class to mock the Campaign API
 */
@Component
@RequiredArgsConstructor
public class MockCampaignAPI {

    private final ResourceLoader resourceLoader;

    public Campaign[] getAllCampaigns() throws IOException {
        String resourceFilename = "mock-campaign-api-response.json";
        Resource resource = resourceLoader.getResource("classpath:" + resourceFilename);
        return new ObjectMapper().readValue(resource.getFile(), Campaign[].class);
    }

//    // Inject from properties file
//    @Value("${campaign.mock.url}")
//    private String injectedMockUrl;
//
//    private String mockUrl;
//
//    /**
//     * Load campaign mock URL
//     *
//     * @throws IOException
//     */
//    @PostConstruct
//    public void loadCampaignUrl() throws IOException {
//        if (injectedMockUrl == null) { // if injection has failed then load properties manually
//            Properties p = new Properties();
//            p.load(new FileInputStream("src/main/resources/application.properties"));
//            mockUrl = p.getProperty("campaign.mock.url");
//        } else {
//            mockUrl = injectedMockUrl;
//        }
//    }
//
//    /**
//     * Get all running Campaigns from Mock Campaign API
//     *
//     * @return An array of Campaign objects
//     */
//    public Campaign[] getAllCampaigns() {
//        return new RestTemplate().getForObject(mockUrl, Campaign[].class);
//    }
}
