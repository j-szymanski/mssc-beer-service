package prv.jws.beer.service.events;

import prv.jws.brewery.dto.BeerDto;

public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(final BeerDto beerDto) {
        super(beerDto);
    }
}
