package com.bluebanana.bidder.controllers;

import com.bluebanana.bidder.models.BidRequest;
import com.bluebanana.bidder.models.BidResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BidControllerIntegrationTests {
    
    private final HttpHeaders httpHeaders = new HttpHeaders();
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    private final TestRestTemplate restTemplate = new TestRestTemplate();
    
    @Autowired
    private ResourceLoader resourceLoader;
    
    @LocalServerPort
    private int port;
    
    /**
     * Test 1: Must respond with the highest bid for a campaign that runs in the USA
     *
     * @throws IOException If input file does not exist
     */
    @Test
    @Order(1)
    void respondWithBid() throws IOException {
        // call
        ResponseEntity<BidResponse> response =
            restTemplate.exchange(
                "http://localhost:" + port + "/bid",
                HttpMethod.POST,
                getRequestEntity("test-case-1-input.json"),
                BidResponse.class);
        
        // verify
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull(response.getBody().bid());
        Assertions.assertEquals(1.23, response.getBody().bid().price());
    }
    
    /**
     * Test 2: Must respond with an empty bid since there are no campaigns running in CYP
     *
     * @throws IOException If input file does not exist
     */
    @Test
    @Order(2)
    void respondWithoutABid() throws IOException {
        // call
        ResponseEntity<BidResponse> response =
            restTemplate.exchange(
                "http://localhost:" + port + "/bid",
                HttpMethod.POST,
                getRequestEntity("test-case-2-input.json"),
                BidResponse.class);
        
        // verify
        Assertions.assertNull(response.getBody());
    }
    
    /**
     * Test 3: Must respond with the second-highest bid for a campaign that runs in the USA
     *
     * @throws IOException If input file does not exist
     */
    @Test
    @Order(3)
    void respondWithDifferentBid() throws IOException {
        // call
        ResponseEntity<BidResponse> response = null;
        for (var i = 1; i > 0; i--) {
            response = restTemplate.exchange(
                "http://localhost:" + port + "/bid",
                HttpMethod.POST,
                getRequestEntity("test-case-1-input.json"),
                BidResponse.class);
        }
        
        // verify
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull(response.getBody().bid());
        Assertions.assertEquals(0.39, response.getBody().bid().price());
    }
    
    private HttpEntity<BidRequest> getRequestEntity(final String resourceFilename) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + resourceFilename);
        BidRequest entity = objectMapper.readValue(resource.getFile(), BidRequest.class);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(mediaTypes);
        return new HttpEntity<>(entity, httpHeaders);
    }
    
}
