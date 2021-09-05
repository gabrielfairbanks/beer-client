package com.fairbanks.beerclient.client;

import java.util.UUID;

import com.fairbanks.beerclient.model.BeerDto;
import com.fairbanks.beerclient.model.BeerStyleEnum;
import com.fairbanks.beerclient.model.BeerPagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BeerClientImpl implements BeerClient {

    @Override public Mono<BeerDto> getBeerById(UUID id, Boolean showInventoryOnHand) {
        return null;
    }


    @Override public Mono<BeerPagedList> listBeers(Integer pageNumber, Integer pageSize, String beerName, String beerStyle, Boolean showInventoryOnhand) {
        return null;
    }


    @Override public Mono<ResponseEntity> createBeer(BeerDto beerDto) {
        return null;
    }


    @Override public Mono<ResponseEntity> updateBeer(BeerDto beerDto) {
        return null;
    }


    @Override public Mono<ResponseEntity> deleteBeerById(UUID id) {
        return null;
    }


    @Override public Mono<BeerDto> getBeerByUPC(String upc) {
        return null;
    }
}
