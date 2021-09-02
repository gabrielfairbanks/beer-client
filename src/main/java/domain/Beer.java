package domain;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Beer {

    private UUID id;
    private String beerName;
    private BeerStyleEnum beerStyle;
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;

}
