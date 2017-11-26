package com.bluebanana.bidder;

import com.bluebanana.bidder.controllers.BidController;
import com.bluebanana.bidder.helpers.CampaignHelpers;
import com.bluebanana.bidder.models.*;
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
     *
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        new Pacing().init();
    }

    /**
     *
     * @throws IOException
     */
    @Test
    public void respondWithBid() throws IOException {
        BidResponse bidResponse = (BidResponse) getRequestMockData(1);
        if (bidResponse.getBid().getPrice() == 1.23) {
            assert true;
            return;
        }
        assert false;
    }

    /**
     *
     * @throws IOException
     */
    @Test
    public void respondWithoutABid() throws IOException {
//        TODO: check response code, must be 204
        if (getRequestMockData(2) == null) {
            assert true;
            return;
        }
        assert false;
    }

    /**
     *
     * @throws IOException
     */
    @Test
    public void respondWithDifferentBid() throws IOException {
        int i = GLOBAL_PACING_LIMIT;
        while (i > 0) {
//            use the same data as the first test case
            getRequestMockData(1); // price: 1.23
            i--;
        }
        BidResponse bidResponse = (BidResponse) getRequestMockData(1);// price should be 0.39
        if (bidResponse.getBid().getPrice() == 0.39) {
            assert true;
            return;
        }
        assert false;
    }

    /**
     *
     * @param testCase
     * @return
     * @throws IOException
     */
    private Object getRequestMockData(int testCase) throws IOException {
        String url = String.format("https://avocarrot.github.io/hiring/back-end/bidder-exercise/test-cases/test-case-%s-input.json", String.valueOf(testCase));
        BidRequest mockBidRequest = new RestTemplate().getForObject(url, BidRequest.class);
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        Campaign mockCampaign = CampaignHelpers.getHighestPayingCampaign(mockBidRequest.getDevice().getGeo().getCountry());
        return bidController.bid(mockBidRequest, mockHttpServletResponse, mockCampaign);
    }
}
