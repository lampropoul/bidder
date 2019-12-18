package com.bluebanana.bidder.models;

import com.bluebanana.bidder.models.enums.Os;
import lombok.Data;

@Data
public class Device {

    private Os os;
    private Geo geo;

}
