package prv.jws.beer.service.services;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import prv.jws.brewery.model.BeerDto;
import prv.jws.beer.service.web.model.BeerPagedList;
import prv.jws.brewery.model.BeerStyle;

public interface BeerService {
    BeerDto getById(UUID beerId, final Boolean showOnHand);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeerById(UUID beerId, BeerDto beerDto);

    BeerPagedList getPageOfBeers(final String beerName, final BeerStyle beerStyle, final PageRequest pageRequest, final Boolean showOnHand);

    BeerDto getByUpc(String upc, Boolean showOnHand);
}
