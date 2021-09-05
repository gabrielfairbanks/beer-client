package com.fairbanks.beerclient.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.UUID;

import com.fairbanks.beerclient.config.WebClientConfig;
import com.fairbanks.beerclient.model.BeerDto;
import com.fairbanks.beerclient.model.BeerPagedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;


class BeerClientImplTest {

    BeerClientImpl beerClient;


    @BeforeEach
    void setUp() {
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


    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    void getBeerById(Boolean showInventoryOnHand) {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(1, 1, null, null, null);
        BeerPagedList beerPagedList = beerPagedListMono.block();
        BeerDto firstBeer = beerPagedList.toList().get(0);

        Mono<BeerDto> retrievedBeerMono = beerClient.getBeerById(firstBeer.getId(), showInventoryOnHand);
        BeerDto retrievedBeer = retrievedBeerMono.block();

        assertThat(retrievedBeer).isNotNull();
        assertThat(retrievedBeer.getId()).isEqualTo(firstBeer.getId());
    }


    @Test
    void getBeerByUPC() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(1, 1, null, null, null);
        BeerPagedList beerPagedList = beerPagedListMono.block();
        BeerDto firstBeer = beerPagedList.toList().get(0);

        Mono<BeerDto> retrievedBeerMono = beerClient.getBeerByUPC(firstBeer.getUpc());
        BeerDto retrievedBeer = retrievedBeerMono.block();

        assertThat(retrievedBeer).isNotNull();
        assertThat(retrievedBeer.getUpc()).isEqualTo(firstBeer.getUpc());
    }


    @Test
    void createBeer() {
        BeerDto beer = BeerDto.builder()
            .beerName("Birra Moretti")
            .beerStyle("IPA")
            .upc("1234")
            .price(new BigDecimal("10.99"))
            .build();

        Mono<ResponseEntity<Void>> responseEntityMono = beerClient.createBeer(beer);
        ResponseEntity responseEntity = responseEntityMono.block();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }


    @Test
    void updateBeer() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(1, 1, null, null, null);
        BeerPagedList beerPagedList = beerPagedListMono.block();
        BeerDto firstBeer = beerPagedList.toList().get(0);

        BeerDto updatedBeer = BeerDto.builder()
            .beerName("Nova Schin")
            .beerStyle(firstBeer.getBeerStyle())
            .upc(firstBeer.getUpc())
            .price(firstBeer.getPrice())
            .build();

        Mono<ResponseEntity<Void>> responseEntityMono = beerClient.updateBeer(firstBeer.getId(), updatedBeer);
        ResponseEntity responseEntity = responseEntityMono.block();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }


    @Test
    void deleteBeerById() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(1, 1, null, null, null);
        BeerPagedList beerPagedList = beerPagedListMono.block();
        BeerDto firstBeer = beerPagedList.toList().get(0);

        Mono<ResponseEntity<Void>> responseEntityMono = beerClient.deleteBeerById(firstBeer.getId());
        ResponseEntity responseEntity = responseEntityMono.block();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void deleteBeerByIdNotFound() {
        Mono<ResponseEntity<Void>> responseEntityMono = beerClient.deleteBeerById(UUID.randomUUID());

        assertThrows(WebClientResponseException.class, () -> {ResponseEntity responseEntity = responseEntityMono.block();});
    }

    @Test
    void testDeleteBeerHandleException() {
        Mono<ResponseEntity<Void>> responseEntityMono = beerClient.deleteBeerById(UUID.randomUUID());

        ResponseEntity responseEntity = responseEntityMono
            .onErrorResume(throwable -> {
                if(throwable instanceof WebClientResponseException){
                    WebClientResponseException exception = (WebClientResponseException) throwable;
                    return Mono.just(ResponseEntity.status(exception.getStatusCode()).build());
                } else {
                    throw new RuntimeException(throwable);
                }
            })
            .block();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}