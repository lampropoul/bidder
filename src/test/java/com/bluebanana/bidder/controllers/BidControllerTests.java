package com.bluebanana.bidder.controllers;

import com.bluebanana.bidder.BidderApplicationTest;
import com.bluebanana.bidder.models.BidRequest;
import com.bluebanana.bidder.models.BidResponse;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static com.bluebanana.bidder.pacing.Pacing.GLOBAL_PACING_LIMIT;

@NoArgsConstructor
public class BidControllerTests extends BidderApplicationTest {

    private final BidController bidController = new BidController();

    /**
     * Test 1: Must respond with the highest bid for a campaign that runs in the USA
     *
     * @throws IOException
     */
    @Test
    public void respondWithBid() throws IOException {
        BidResponse bidResponse = bidController.bid(getRequestMockData(1)).getBody();
        assert bidResponse.getBid().getPrice() == 1.23;
    }

    /**
     * Test 2: Must respond with an empty bid since there are no campaigns running in CYP
     *
     * @throws IOException
     */
    @Test
    public void respondWithoutABid() throws IOException {
        BidRequest mockBidRequest = getRequestMockData(2);
        BidResponse bidResponse = bidController.bid(mockBidRequest).getBody();
        assert bidResponse == null;
    }

    /**
     * Test 3: Must respond with the second highest bid for a campaign that runs in the USA
     *
     * @throws IOException
     */
    @Test
    public void respondWithDifferentBid() throws IOException {
//        use the same data as the first test case
        BidRequest mockBidRequest = getRequestMockData(1);
        int i = GLOBAL_PACING_LIMIT;
        while (i > 0) {
            bidController.bid(mockBidRequest); // price: 1.23
            i--;
        }
        BidResponse bidResponse = bidController.bid(mockBidRequest).getBody();
        assert bidResponse.getBid().getPrice() == 0.39;
    }

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
}
