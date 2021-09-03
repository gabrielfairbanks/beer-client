package com.fairbanks.beerclient.domain;

import java.util.List;

import lombok.Data;


@Data
public class PagedBeer {

    private List<Beer> content;

}
