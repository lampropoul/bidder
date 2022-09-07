package com.bluebanana.bidder.helpers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.bluebanana.bidder.models.Campaign;

/**
 * Custom class to mock the Campaign API
 */
@Component
public class MockCampaignAPI {
    
    private final ResourceLoader resourceLoader;
    
    @Value("${campaign.mock.file}")
    private String campaignMockFile;
    
    public MockCampaignAPI(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    
    public Campaign[] getAllCampaigns() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + campaignMockFile);
        return new ObjectMapper().readValue(resource.getFile(), Campaign[].class);
    }
    
}
