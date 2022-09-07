package com.bluebanana.bidder.models;

import com.bluebanana.bidder.models.enums.OperatingSystem;
import lombok.Data;

@Data
public class Device {

    private OperatingSystem operatingSystem;
    private Geo geo;

}
