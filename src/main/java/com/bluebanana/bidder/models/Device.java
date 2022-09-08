package com.bluebanana.bidder.models;

import com.bluebanana.bidder.models.enums.OperatingSystem;

public record Device(OperatingSystem operatingSystem, Geo geo) {

}
