package prv.jws.beer.service.events;

import prv.jws.brewery.dto.BeerDto;
import prv.jws.brewery.events.BeerEvent;

public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(final BeerDto beerDto) {
        super(beerDto);
    }
}
