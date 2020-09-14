package prv.jws.beer.service.services;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import prv.jws.beer.service.web.model.BeerDto;
import prv.jws.beer.service.web.model.BeerPagedList;
import prv.jws.beer.service.web.model.BeerStyle;

public interface BeerService {
    BeerDto getById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeerById(UUID beerId, BeerDto beerDto);

    BeerPagedList getPageOfBeers(final String beerName, final BeerStyle beerStyle, final PageRequest pageRequest);
}
