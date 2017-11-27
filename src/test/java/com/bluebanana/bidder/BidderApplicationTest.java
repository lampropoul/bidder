package com.bluebanana.bidder;

import com.bluebanana.bidder.helpers.CampaignHelpers;
import com.bluebanana.bidder.helpers.MockCampaignAPI;
import com.bluebanana.bidder.models.Campaign;
import com.bluebanana.bidder.pacing.Pacing;
import jdk.nashorn.internal.runtime.ECMAException;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class BidderApplicationTest {
    @Before
    public static void setUp() throws Exception {
        CampaignHelpers.setAvailableMockCampaigns(MockCampaignAPI.getAllCampaigns());
        new Pacing().init();
    }

    @After
    public static void tearDown() throws Exception {
        CampaignHelpers.setAvailableMockCampaigns(new Campaign[0]);
    }

}