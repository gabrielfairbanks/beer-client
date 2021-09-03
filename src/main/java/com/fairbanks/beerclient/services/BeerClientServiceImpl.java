package com.fairbanks.beerclient.services;

import java.util.UUID;

import com.fairbanks.beerclient.domain.Beer;
import com.fairbanks.beerclient.domain.BeerStyleEnum;
import com.fairbanks.beerclient.domain.PagedBeer;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;


public class BeerClientServiceImpl implements BeerClientService {

    @Override public Mono<PagedBeer> listBeers(Integer pageNumber, Integer pageSize, String beerName, BeerStyleEnum beerStyle, boolean showInventoryOnHand) {
        return null;
    }


    @Override public Mono<ClientResponse> createBeer(Beer beer) {
        return null;
    }


    @Override public Mono<Beer> getBeerById(UUID id, boolean showInventoryOnHand) {
        return null;
    }


    @Override public Mono<ClientResponse> updateBeer(Beer beer) {
        return null;
    }


    @Override public Mono<ClientResponse> deleteBeer(UUID id) {
        return null;
    }


    @Override public Mono<Beer> getBeerByUPC(String upc) {
        return null;
    }
}
