package com.bluebanana.bidder.controllers;

import com.bluebanana.bidder.models.BidRequest;
import com.bluebanana.bidder.models.BidResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BidControllerTests {

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
    public void respondWithBid() throws IOException {
        ResponseEntity<BidResponse> response = restTemplate.exchange(
                "http://localhost:" + port + "/bid",
                HttpMethod.POST,
                getRequestEntity("test-case-1-input.json"),
                BidResponse.class);
        assert response.getBody() != null;
        assert response.getBody().getBid().getPrice() == 1.23;
    }

    /**
     * Test 2: Must respond with an empty bid since there are no campaigns running in CYP
     *
     * @throws IOException If input file does not exist
     */
    @Test
    @Order(2)
    public void respondWithoutABid() throws IOException {
        ResponseEntity<BidResponse> response = restTemplate.exchange(
                "http://localhost:" + port + "/bid",
                HttpMethod.POST,
                getRequestEntity("test-case-2-input.json"),
                BidResponse.class);
        assert response.getBody() == null;
    }

    /**
     * Test 3: Must respond with the second highest bid for a campaign that runs in the USA
     *
     * @throws IOException If input file does not exist
     */
    @Test
    @Order(3)
    public void respondWithDifferentBid() throws IOException {
        ResponseEntity<BidResponse> response = null;
        for (int i = 1; i > 0; i--) {
            response = restTemplate.exchange(
                    "http://localhost:" + port + "/bid",
                    HttpMethod.POST,
                    getRequestEntity("test-case-1-input.json"),
                    BidResponse.class);
        }
        assert response != null;
        assert response.getBody() != null;
        assert response.getBody().getBid() != null;
        assert response.getBody().getBid().getPrice() == 0.39;
    }

    private HttpEntity<BidRequest> getRequestEntity(String resourceFilename) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + resourceFilename);
        BidRequest entity = objectMapper.readValue(resource.getFile(), BidRequest.class);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(mediaTypes);
        return new HttpEntity<>(entity, httpHeaders);
    }

}
