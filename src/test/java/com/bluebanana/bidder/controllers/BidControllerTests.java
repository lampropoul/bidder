package com.bluebanana.bidder.controllers;

import com.bluebanana.bidder.helpers.MockCampaignAPI;
import com.bluebanana.bidder.models.BidRequest;
import com.bluebanana.bidder.models.BidResponse;
import com.bluebanana.bidder.pacing.Pacing;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static com.bluebanana.bidder.pacing.Pacing.GLOBAL_PACING_LIMIT;

public class BidControllerTests {

    BidController bidController = new BidController();

    /**
     * Initialize mock data and (real) properties
     *
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
//        set mock data only for tests
        new MockCampaignAPI().init();
        MockCampaignAPI.getAllCampaigns();
        new Pacing().init();
    }

    /**
     * Test 1: Must respond with the highest bid for a campaign that runs in the USA
     *
     * @throws IOException
     */
    @Test
    public void respondWithBid() throws IOException {
        BidRequest mockBidRequest = getRequestMockData(1);
        BidResponse bidResponse = (BidResponse) bidController.bid(mockBidRequest, new MockHttpServletResponse());
        if (bidResponse.getBid().getPrice() == 1.23) {
            assert true;
            return;
        }
        assert false;
    }

    /**
     * Test 2: Must respond with an empty bid since there are no campaigns running in CYP
     *
     * @throws IOException
     */
    @Test
    public void respondWithoutABid() throws IOException {
        BidRequest mockBidRequest = getRequestMockData(2);
        BidResponse bidResponse = (BidResponse) bidController.bid(mockBidRequest, new MockHttpServletResponse());
        if (bidResponse == null) {
            assert true;
            return;
        }
        assert false;
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
            bidController.bid(mockBidRequest, new MockHttpServletResponse()); // price: 1.23
            i--;
        }
        BidResponse bidResponse = (BidResponse) bidController.bid(mockBidRequest, new MockHttpServletResponse());
        if (bidResponse.getBid().getPrice() == 0.39) {
            assert true;
            return;
        }
        assert false;
    }

    /**
     * Method that is used from all tests and constructs BidRequest sample object.
     * This BidRequest should come from 3rd-party ad exchange platform.
     *
     * @param testCase
     * @return
     * @throws IOException
     */
    private BidRequest getRequestMockData(int testCase) throws IOException {
        String url = String.format("https://avocarrot.github.io/hiring/back-end/bidder-exercise/test-cases/test-case-%s-input.json", String.valueOf(testCase));
        return new RestTemplate().getForObject(url, BidRequest.class);

    }
}
