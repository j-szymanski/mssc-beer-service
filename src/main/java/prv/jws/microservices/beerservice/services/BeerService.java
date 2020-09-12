package prv.jws.microservices.beerservice.services;

import java.util.UUID;

import prv.jws.microservices.beerservice.web.model.BeerDto;

public interface BeerService {
    BeerDto getById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeerById(UUID beerId, BeerDto beerDto);
}
