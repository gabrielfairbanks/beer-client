package com.fairbanks.beerclient.config;

import java.net.URI;
import java.util.function.Function;

import org.springframework.web.util.UriBuilder;


public class WebClientProperties {

    public static final String BASE_URL = "http://api.springframework.guru";

    public static final String BEER_V1_PATH = "/api/v1/beer";
    public static final String BEER_V1_BEER_ID_PATH = BEER_V1_PATH + "/{beerId}";
    public static final String BEER_V1_UPC_PATH = "/api/v1/beerUpc/{upc}";
}
