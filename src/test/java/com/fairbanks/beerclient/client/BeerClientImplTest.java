package com.fairbanks.beerclient.client;

import static org.assertj.core.api.Assertions.assertThat;

import com.fairbanks.beerclient.config.WebClientConfig;
import com.fairbanks.beerclient.model.BeerPagedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;


class BeerClientImplTest {

    BeerClientImpl beerClient;

    @BeforeEach
    void setUp(){
        beerClient = new BeerClientImpl(new WebClientConfig().webClient());
    }

    @Test
    void listBeers() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(null, null, null, null, null);

        BeerPagedList beerPagedList = beerPagedListMono.block();

        assertThat(beerPagedList).isNotNull();
        assertThat(beerPagedList.getContent()).hasSizeGreaterThan(0);
        System.out.println(beerPagedList.toList());
    }

    @Test
    void listBeersPageSize10() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(1, 10, null, null, null);

        BeerPagedList beerPagedList = beerPagedListMono.block();

        assertThat(beerPagedList).isNotNull();
        assertThat(beerPagedList.getContent()).hasSize(10);
        System.out.println(beerPagedList.toList());
    }

    @Test
    void listBeersNoRecords() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(1, 50, null, null, null);

        BeerPagedList beerPagedList = beerPagedListMono.block();

        assertThat(beerPagedList).isNotNull();
        assertThat(beerPagedList.getContent()).isEmpty();
        System.out.println(beerPagedList.toList());
    }

    @Test
    void getBeerById() {
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