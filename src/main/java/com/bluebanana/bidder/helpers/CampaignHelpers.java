package com.bluebanana.bidder.helpers;

import com.bluebanana.bidder.models.Campaign;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CampaignHelpers {

    /**
     * The Campaign with the highest price
     *
     * @param country
     * @return The first list item since it is sorted in descending order
     * or an empty Campaign if no Campaign that matches the criteria was found
     * @throws IOException
     */
    public static Campaign getHighestPayingCampaign(String country) throws IOException {
        Gson gson = new Gson();
        Campaign[] allCampaigns = gson.fromJson(getAllCampaigns(), Campaign[].class);
        List<Campaign> campaignList = Arrays.stream(allCampaigns)
                .filter(campaign -> campaign.getTargetedCountries().contains(country))
                .sorted((campaign1, campaign2) -> Double.compare(campaign2.getPrice(), campaign1.getPrice())) // reverse sort (DESC)
                .collect(Collectors.toList());

        if (campaignList.isEmpty()) {
            return new Campaign();
        } else {
            return campaignList.get(0);
        }
    }

    /**
     * Get all Campaigns from Mock API
     *
     * @return
     * @throws IOException
     */
    private static String getAllCampaigns() throws IOException {
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
