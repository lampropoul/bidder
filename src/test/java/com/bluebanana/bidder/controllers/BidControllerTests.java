package com.bluebanana.bidder.controllers;

import com.bluebanana.bidder.models.BidRequest;
import com.bluebanana.bidder.models.BidResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
     * @throws IOException
     */
    @Test
    public void respondWithBid() throws IOException {
        ResponseEntity<BidResponse> response = restTemplate.exchange(
                "http://localhost:" + port + "/bid",
                HttpMethod.POST,
                getRequestEntity("test-case-1-input.json", BidRequest.class),
                BidResponse.class);
        assert response.getBody().getBid().getPrice() == 1.23;
    }

    /**
     * Test 2: Must respond with an empty bid since there are no campaigns running in CYP
     *
     * @throws IOException
     */
//    @Test
//    public void respondWithoutABid() {
//        BidRequest mockBidRequest = getRequestMockData(2);
//        BidResponse bidResponse = bidController.bid(mockBidRequest).getBody();
//        assert bidResponse == null;
//    }

    /**
     * Test 3: Must respond with the second highest bid for a campaign that runs in the USA
     *
     * @throws IOException
     */
//    @Test
//    public void respondWithDifferentBid() {
////        use the same data as the first test case
//        BidRequest mockBidRequest = getRequestMockData(1);
//        int i = pacing.GLOBAL_PACING_LIMIT;
//        while (i > 0) {
//            bidController.bid(mockBidRequest); // price: 1.23
//            i--;
//        }
//        BidResponse bidResponse = bidController.bid(mockBidRequest).getBody();
//        assert bidResponse.getBid().getPrice() == 0.39;
//    }

    /**
     * Method that is used from all tests and constructs BidRequest sample object.
     * This BidRequest should come from 3rd-party ad exchange platform.
     *
     * @param testCase
     */
    private BidRequest getRequestMockData(int testCase) {
        String url = String.format("https://avocarrot.github.io/hiring/back-end/bidder-exercise/test-cases/test-case-%s-input.json", testCase);
        return new RestTemplate().getForObject(url, BidRequest.class);

    }

    <T> HttpEntity<T> getRequestEntity(String resourceFilename, Class<T> classType) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + resourceFilename);
        T entity = objectMapper.readValue(resource.getFile(), classType);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(mediaTypes);
        return new HttpEntity<>(entity, httpHeaders);
    }
}
