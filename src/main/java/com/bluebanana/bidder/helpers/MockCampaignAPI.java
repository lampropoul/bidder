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

}
