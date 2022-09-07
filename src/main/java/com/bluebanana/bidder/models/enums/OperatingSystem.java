package com.bluebanana.bidder.models.enums;

public enum OperatingSystem {
    
    ANDROID("Android"),
    IOS("iOS");
    
    private final String abbr;
    
    OperatingSystem(final String abbr) {
        this.abbr = abbr;
    }
    
    public String getAbbr() {
        return abbr;
    }
}
