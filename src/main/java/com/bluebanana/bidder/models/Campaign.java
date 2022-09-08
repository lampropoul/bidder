package com.bluebanana.bidder.models;

import java.util.List;

public record Campaign(String id, String name, Double price, String adm, List<String> targetedCountries) {

}
