package prv.jws.beer.service.listeners;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Component;
import prv.jws.beer.service.repositories.BeerRepository;
import prv.jws.brewery.model.BeerOrderLineDto;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;

/**
 * Created by Jerzy Szymanski on 24.09.2020 at 12:29
 */
@RequiredArgsConstructor
@Component
public class BeerOrderValidator {
    private final BeerRepository beerRepository;

    public Boolean validateOrder(final List<BeerOrderLineDto> beerOrderLines) {
        if (isNull(beerOrderLines))
            return false;
        return beerOrderLines.stream()
                             .map(BeerOrderLineDto::getUpc)
                             .map(upc -> beerRepository.findByUpc(upc)
                                                       .map(beer -> TRUE)
                                                       .orElse(FALSE))
                             .filter(value -> !value)
                             .findAny()
                             .orElse(TRUE);
    }
}
