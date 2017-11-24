package com.bluebanana.bidder;

import com.bluebanana.bidder.controllers.BidController;
import com.bluebanana.bidder.enums.Os;
import com.bluebanana.bidder.models.*;
import com.google.gson.Gson;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BidControllerTests {

    BidController bidController = new BidController();

    @Test
    public void test1_respondWithBid() throws IOException {
        Geo geo = new Geo("USA", 0.0, 0.0);
        Device device = new Device(Os.Android, geo);
        App app = new App("e7fe51ce-4f63-7687-6353-ff0961c2cb0d", "Morecast Weather");
        BidRequest bidRequest = new BidRequest("e7fe51ce4f6376876353ff0961c2cb0d", app, device);

        BidResponse bidResponse = bidController.bid(bidRequest, getHighestPayingCampaign(geo.getCountry()));
        if (bidResponse.getBid().getPrice() != 1.23) {
            assert false;
            return;
        }
        assert true;
    }


    private Campaign getHighestPayingCampaign(String country) throws IOException {
        Gson gson = new Gson();
        Campaign[] campaigns = gson.fromJson(getAllCampaigns(), Campaign[].class);
        List<Campaign> campaignList = Arrays.stream(campaigns)
                .filter(campaign -> campaign.getTargetedCountries().contains(country))
                .sorted((campaign1, campaign2) -> Double.compare(campaign2.getPrice(), campaign1.getPrice())) // reverse sort (DESC)
                .collect(Collectors.toList());

        if (campaignList.isEmpty()) {
            return new Campaign();
        } else {
            return campaignList.get(0);
        }
    }

    private String getAllCampaigns() throws IOException {
        String campaignApiResponse = "https://avocarrot.github.io/hiring/back-end/bidder-exercise/test-cases/mock-campaign-api-response.json";
        URL url = new URL(campaignApiResponse);
        URLConnection conn = url.openConnection();
        // open the stream and put it into BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder jsonContents = new StringBuilder();
        while ((inputLine = br.readLine()) != null) {
            jsonContents.append(inputLine);
        }
        br.close();
        return jsonContents.toString();
    }
}
