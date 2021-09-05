package com.fairbanks.beerclient.client;

import java.util.Optional;
import java.util.UUID;

import com.fairbanks.beerclient.config.WebClientProperties;
import com.fairbanks.beerclient.model.BeerDto;
import com.fairbanks.beerclient.model.BeerPagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class BeerClientImpl implements BeerClient {

    private final WebClient webClient;


    @Override public Mono<BeerDto> getBeerById(UUID id, Boolean showInventoryOnHand) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path(WebClientProperties.BEER_V1_PATH_GET_BY_ID)
                .queryParamIfPresent("showInventoryOnHand", Optional.ofNullable(showInventoryOnHand))
                .build(id)
            )
            .retrieve()
            .bodyToMono(BeerDto.class);
    }


    @Override public Mono<BeerPagedList> listBeers(Integer pageNumber, Integer pageSize, String beerName, String beerStyle, Boolean showInventoryOnhand) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path(WebClientProperties.BEER_V1_PATH)
                .queryParamIfPresent("pageNumber", Optional.ofNullable(pageNumber))
                .queryParamIfPresent("pageSize", Optional.ofNullable(pageSize))
                .queryParamIfPresent("beerName", Optional.ofNullable(beerName))
                .queryParamIfPresent("beerStyle", Optional.ofNullable(beerStyle))
                .queryParamIfPresent("showInventoryOnhand", Optional.ofNullable(showInventoryOnhand))
                .build()
            )
            .retrieve()
            .bodyToMono(BeerPagedList.class);
    }


    @Override public Mono<ResponseEntity<Void>> createBeer(BeerDto beerDto) {
        return webClient.post()
            .uri(uriBuilder -> uriBuilder.path(WebClientProperties.BEER_V1_PATH).build())
            .body(BodyInserters.fromValue(beerDto))
            .retrieve()
            .toBodilessEntity();
    }


    @Override public Mono<ResponseEntity<Void>> updateBeer(UUID id, BeerDto beerDto) {
        return webClient.put()
            .uri(uriBuilder -> uriBuilder.path(WebClientProperties.BEER_V1_PATH_GET_BY_ID).build(id))
            .body(BodyInserters.fromValue(beerDto))
            .retrieve()
            .toBodilessEntity();
    }



    @Override public Mono<ResponseEntity<Void>> deleteBeerById(UUID id) {
        return null;
    }


    @Override public Mono<BeerDto> getBeerByUPC(String upc) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path(WebClientProperties.BEER_V1_UPC_PATH)
                .build(upc)
            )
            .retrieve()
            .bodyToMono(BeerDto.class);
    }
}
