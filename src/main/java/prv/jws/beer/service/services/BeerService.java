package prv.jws.beer.service.services;

import java.util.UUID;

import prv.jws.beer.service.web.model.BeerDto;

public interface BeerService {
    BeerDto getById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeerById(UUID beerId, BeerDto beerDto);
}
