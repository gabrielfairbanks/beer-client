package com.fairbanks.beerclient.client;

import com.fairbanks.beerclient.config.WebClientConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BeerClientImplTest {

    BeerClientImpl beerClient;

    @BeforeEach
    void setUp(){
        beerClient = new BeerClientImpl(new WebClientConfig().webClient());

    }

    @Test
    void getBeerById() {
    }


    @Test
    void listBeers() {
    }


    @Test
    void createBeer() {
    }


    @Test
    void updateBeer() {
    }


    @Test
    void deleteBeerById() {
    }


    @Test
    void getBeerByUPC() {
    }
}