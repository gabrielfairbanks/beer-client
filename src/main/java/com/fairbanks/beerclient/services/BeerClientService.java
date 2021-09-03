package com.fairbanks.beerclient.services;

import java.util.UUID;

import com.fairbanks.beerclient.domain.Beer;
import com.fairbanks.beerclient.domain.BeerStyleEnum;
import com.fairbanks.beerclient.domain.PagedBeer;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;


public interface BeerClientService {

    Mono<PagedBeer> listBeers(Integer pageNumber, Integer pageSize, String beerName, BeerStyleEnum beerStyle, boolean showInventoryOnHand);


    Mono<Beer> getBeerById(UUID id, boolean showInventoryOnHand);

    Mono<ClientResponse> createBeer(Beer beer);
    Mono<ClientResponse> updateBeer(Beer beer);

    Mono<ClientResponse> deleteBeer(UUID id);

    Mono<Beer> getBeerByUPC(String upc);












}
