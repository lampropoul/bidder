package com.bluebanana.bidder.models;

import com.bluebanana.bidder.models.enums.Os;

public class Device {

    private Os os;
    private Geo geo;

    public Os getOs() {
        return os;
    }

    public void setOs(Os os) {
        this.os = os;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }
}
