package prv.jws.beer.service.events;

import prv.jws.beer.service.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(final BeerDto beerDto) {
        super(beerDto);
    }
}
