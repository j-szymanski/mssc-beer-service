package prv.jws.beer.service.events;

import prv.jws.brewery.dto.BeerDto;

public class NewInventoryEvent extends BeerEvent {

    public NewInventoryEvent(final BeerDto beerDto) {
        super(beerDto);
    }
}
